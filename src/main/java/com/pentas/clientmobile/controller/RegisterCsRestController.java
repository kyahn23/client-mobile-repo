package com.pentas.clientmobile.controller;

import com.pentas.clientmobile.common.module.util.DevMap;
import com.pentas.clientmobile.service.RegisterCsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/regCounsel")
public class RegisterCsRestController {
    private final RegisterCsService registerCsService;


    @PostMapping("/mntRtList")
    public DevMap mntRtList(@RequestBody DevMap param) {
        DevMap rslt = new DevMap();
        List<DevMap> resultList = null;

        resultList = registerCsService.mntRtList(param);
        rslt.put("mntRtList", resultList);
        return rslt;
    }
}
