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
@RequestMapping("/api/user")
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

        DevMap rslt = new DevMap();
        rslt.put("rsltStat", rsltStat);
        return rslt;
    }

}
