package com.pentas.clientmobile.controller;

import com.pentas.clientmobile.common.module.util.DevMap;
import com.pentas.clientmobile.dto.UserSaveRequestDto;
import com.pentas.clientmobile.service.UserService;
import lombok.RequiredArgsConstructor;
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
        if (isSocialLogin != 'Y') {
            userService.sendNewMemberEmail(requestDto.getMemberId(), requestDto.getMemberNickname());
        }
        String rsltId = userService.save(requestDto);
        if (rsltId != null && !rsltId.isEmpty()) {
            rsltStat = "SUCC";
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
            if (userService.isLoginAllowed(memberId, passwordPin)) {
                if (userService.isEmailAuthenticated(memberId)) {
                    rsltStat = "SUCC";
                } else {
                    rsltStat = "MAIL";
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
        String email = param.getString("email");
        if (userService.isRegisteredUser(email)) {
            userService.verifyEmail(email);
        }
        result.put("success", "Y");
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

        if (sociallogin || userService.isLoginAllowed(email, password)) {
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
}
