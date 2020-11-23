package com.pentas.clientmobile.controller;

import com.pentas.clientmobile.common.module.util.DevMap;
import com.pentas.clientmobile.service.CallService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/call")
public class CallRestController {
    private final CallService callService;

    @PostMapping("/list")
    public DevMap list(@RequestBody DevMap param) {
        DevMap result = new DevMap();
        List<DevMap> list = callService.callList(param);
        result.put("callList", list);
        return result;
    }

    @PostMapping("/delete")
    public DevMap delete(@RequestBody DevMap param) {
        DevMap result = new DevMap();
        callService.callClientCancel(param);
        result.put("success", "Y");
        return result;
    }

    @PostMapping("/terminate")
    public DevMap terminate(@RequestBody DevMap param) {
        DevMap result = new DevMap();
        callService.callClientTerminate(param);
        result.put("success", "Y");
        return result;
    }

    @PostMapping("/register")
    public DevMap register(@RequestBody DevMap param) {
        DevMap result = new DevMap();
        callService.callClientNew(param);
        result.put("success", "Y");
        return result;
    }
}
