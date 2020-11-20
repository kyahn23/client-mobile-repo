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


    /**
     * 요금제 목록 가져오기
     * @param param
     * @return
     */
    @PostMapping("/mntRtList")
    public DevMap mntRtList(@RequestBody DevMap param) {
        DevMap rslt = new DevMap();
        List<DevMap> resultList = null;

        resultList = registerCsService.mntRtList(param);
        rslt.put("mntRtList", resultList);
        return rslt;
    }

    /**
     * 스마트폰 목록 가져오기
     * @param param
     * @return
     */
    @PostMapping("/phoneList")
    public DevMap phoneList(@RequestBody DevMap param) {
        DevMap rslt = new DevMap();
        List<DevMap> resultList = null;

        resultList = registerCsService.phoneList(param);
        rslt.put("phoneList", resultList);
        return rslt;
    }

    /**
     * 상담등록
     * @param param
     */
    @PostMapping("/registerCs")
    public void registerCs(@RequestBody DevMap param) {
        switch (param.getString("pnRegDis")){
            case "moveCarr":
                param.put("PN_REG_DIS", "A");
                break;
            case "chgDev":
                param.put("PN_REG_DIS", "B");
                break;
            case "newSign":
                param.put("PN_REG_DIS", "C");
                break;
        }


        registerCsService.registerCs(param);
    }
}
