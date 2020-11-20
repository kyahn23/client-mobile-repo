package com.pentas.clientmobile.controller;

import com.pentas.clientmobile.common.module.util.DevMap;
import com.pentas.clientmobile.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/common")
public class CommonRestController {
    private final CommonService commonService;

    @PostMapping("/sido")
    public DevMap sido() {
        DevMap result = new DevMap();
        List<DevMap> sidoList = commonService.sidoList();
        result.put("sidoList", sidoList);
        return result;
    }

    @PostMapping("/sigg")
    public DevMap sigg(@RequestBody DevMap param) {
        DevMap result = new DevMap();
        List<DevMap> siggList = commonService.siggList(param);
        result.put("siggList", siggList);
        return result;
    }
}
