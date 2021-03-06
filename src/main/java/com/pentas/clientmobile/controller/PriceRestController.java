package com.pentas.clientmobile.controller;


import com.pentas.clientmobile.common.module.mybatis.paginator.domain.PageList;
import com.pentas.clientmobile.common.module.util.DevMap;
import com.pentas.clientmobile.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/price")
public class PriceRestController {

    private final PriceService priceService;

    @PostMapping("/phoneList")
    public DevMap phoneList(@RequestBody DevMap param) {
        DevMap rslt = new DevMap();
        List<DevMap> resultList = null;

        resultList = priceService.phoneList(param);
        rslt.put("phoneList", resultList);
        return rslt;
    }

    @PostMapping("/mntRtList")
    public DevMap mntRtList(@RequestBody DevMap param) {
        DevMap rslt = new DevMap();
        List<DevMap> resultList = null;

        resultList = priceService.mntRtList(param);
        rslt.put("mntRtList", resultList);
        return rslt;
    }

    @PostMapping("/priceList")
    public DevMap priceList(@RequestBody DevMap param) {
        DevMap result = new DevMap();
        switch (param.getString("signType")){
            case "moveCarr":
                param.put("SIGN_TYPE", "A");
                break;
            case "chgDev":
                param.put("SIGN_TYPE", "B");
                break;
            case "newSign":
                param.put("SIGN_TYPE", "C");
                break;
        }


        List<DevMap> resultList = priceService.priceList(param);
        result.put("priceList", resultList);
        return result;
    }
}
