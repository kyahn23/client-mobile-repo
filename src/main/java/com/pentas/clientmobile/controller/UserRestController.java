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
        if (userService.isSocialUser(memberId)) {
            rsltStat = "SSO";
        } else if (userService.isRegisteredUser(memberId)) {
            rsltStat = "SUCC";
        }

        DevMap result = new DevMap();
        result.put("rsltStat", rsltStat);
        return result;
    }
}
