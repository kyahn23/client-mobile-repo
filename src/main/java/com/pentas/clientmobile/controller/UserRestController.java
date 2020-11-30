package com.pentas.clientmobile.controller;

import com.pentas.clientmobile.common.module.util.CmmnUtil;
import com.pentas.clientmobile.common.module.util.DevMap;
import com.pentas.clientmobile.dto.UserSaveRequestDto;
import com.pentas.clientmobile.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class UserRestController {
    private final UserService userService;

    @PostMapping("/registered")
    public Boolean registered(@RequestBody DevMap param) {
        return userService.isRegisteredUser(param.getString("email"));
    }

    @PostMapping("/signup")
    public DevMap signup(@RequestBody UserSaveRequestDto requestDto) {
        String rsltStat = "FAIL";
        char isSocialLogin = requestDto.getIsSocialLogin();
        String rsltId = userService.save(requestDto);

        if (rsltId != null && !rsltId.isEmpty()) {
            rsltStat = "SUCC";
            if (isSocialLogin != 'Y') {
                DevMap param = new DevMap();
                String authKey = generateAuthKey();
                param.put("authKey", authKey);
                param.put("email", requestDto.getMemberId());
                param.put("nickname", requestDto.getMemberNickname());
                int rowCount = 0;
                rowCount = userService.setAuthKey(param, "NEW");
                if (rowCount <= 0) {
                    rsltStat = "FAIL";
                }
            }
        }

        DevMap result = new DevMap();
        result.put("rsltStat", rsltStat);
        return result;
    }

    @PostMapping("/signin")
    public DevMap signin(@RequestBody DevMap param) {
        String rsltStat = "FAIL";
        String memberId = param.getString("email");
        String passwordPin = param.getString("bibun");
        if (userService.isSocialUser(memberId)) {
            rsltStat = "SSO";
        } else if (userService.isRegisteredUser(memberId)) {
            if (!userService.isLoginAllowed(memberId)) {
                rsltStat = "DND";
            } else {
                int errorCount = userService.getPasswordErrorCount(memberId);
                if (userService.isLoginMatch(memberId, passwordPin)) {
                    if (userService.isEmailAuthenticated(memberId)) {
                        if (errorCount > 0) {
                            userService.resetPasswordErrorCount(memberId);
                        }
                        rsltStat = "SUCC";
                    } else {
                        rsltStat = "MAIL";
                    }
                } else {
                    if (errorCount < 5) {
                        userService.setPasswordErrorCount(memberId);
                        rsltStat = "CHK";
                    } else {
                        userService.setLoginDenied(memberId);
                        rsltStat = "DND";
                    }
                }
            }
        }

        DevMap result = new DevMap();
        result.put("rsltStat", rsltStat);
        return result;
    }

    @PostMapping("/verify")
    public DevMap verify(@RequestBody DevMap param) {
        DevMap result = new DevMap();
        String email = "";
        String hashEmail = param.getString("mbr");
        String authKey = param.getString("cue");
        String target = param.getString("tgt");

        email = userService.getEmailByAuthKey(authKey);
        if (hashEmail.equals(CmmnUtil.encryptSHA256(email))) {
            if (target.equals("MAIL")) {
                userService.verifyEmail(email);
            } else if (target.equals("USER")) {
                String nickname = "";

                DevMap infoParam = new DevMap();
                infoParam.put("email", email);

                DevMap userInfo = userService.getUserInfo(infoParam);
                nickname = userInfo.getString("mbrNm");

                result.put("userNm", nickname);
                result.put("userId", email);
            }
        }

        result.put("success", "Y");
        return result;
    }

    @PostMapping("/findid")
    public DevMap findid(@RequestBody DevMap param) {
        DevMap result = new DevMap();
        return result;
    }

    @PostMapping("/findpw")
    public DevMap findpw(@RequestBody DevMap param) {
        String successCd = "Y";
        String email = param.getString("email");
        String nickname = param.getString("nickname");

        if (userService.isIdAndNameMatch(email, nickname)) {
            int rowCount = 0;
            String authKey = generateAuthKey();
            param.put("authKey", authKey);
            rowCount = userService.setAuthKey(param, "EXT");
            if (rowCount <= 0) {
                successCd = "N";
            }
        }

        DevMap result = new DevMap();
        result.put("success", successCd);
        return result;
    }

    @PostMapping("/reset")
    public DevMap reset(@RequestBody DevMap param) {
        int rowCount = 0;
        String successCd = "Y";
        DevMap result = new DevMap();

        rowCount = userService.resetLogin(param);
        if (rowCount <= 0) {
            successCd = "N";
        }

        result.put("success", successCd);
        return result;
    }

    @PostMapping("/getinfo")
    public DevMap getinfo(@RequestBody DevMap param) {
        DevMap result = new DevMap();
        result = userService.getUserInfo(param);
        return result;
    }

    @PostMapping("/update")
    public DevMap update(@RequestBody DevMap param) {
        String email = param.getString("email");
        Boolean sociallogin = param.getBoolean("sociallogin");
        String password = param.getString("oldPassword");
        int rowCount = 0;

        if (sociallogin || userService.isLoginMatch(email, password)) {
            rowCount = userService.updateUserInfo(param);
        }

        DevMap result = new DevMap();
        if (rowCount > 0) {
            result.put("success", "Y");
        } else {
            result.put("success", "N");
        }
        return result;
    }

    private String generateAuthKey() {
        String authKey = RandomStringUtils.randomAlphanumeric(8, 64);
        authKey = CmmnUtil.encryptSHA256(authKey);
        return authKey;
    }
}
