package com.pentas.clientmobile.controller;

import com.pentas.clientmobile.common.module.util.DevMap;
import com.pentas.clientmobile.service.CallService;
import com.pentas.clientmobile.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/shop")
public class ShopRestController {
    private final ShopService shopService;
    private final CallService callService;

    @PostMapping("/name")
    public DevMap name(@RequestBody DevMap param) {
        DevMap result = new DevMap();
        List<DevMap> nameList = shopService.shopNameList(param);
        result.put("nameList", nameList);
        return result;
    }

    @PostMapping("/rating")
    public DevMap rating(@RequestBody DevMap param) {
        DevMap result = new DevMap();

        Boolean isReport = param.getBoolean("callReport");
        Boolean isTerminated = param.getBoolean("callTerminated");

        if (isReport) {
            // 신고 후 상태변경
            DevMap reportParam = new DevMap();
            reportParam.put("dealNo", param.getString("dealNo"));
            reportParam.put("bnNo", param.getString("bnNo"));
            reportParam.put("email", param.getString("email"));
            reportParam.put("reportContent", param.getString("ratingComment"));
            shopService.shopRatingBlack(reportParam);

            DevMap cancelParam = new DevMap();
            cancelParam.put("callNo", param.getString("callNo"));
            cancelParam.put("email", param.getString("email"));
            cancelParam.put("cnclCmnt", "자동: 업체 블랙리스트 신고");
            callService.callClientCancel(cancelParam);
        } else {
            // 평가 저장
            shopService.shopRatingNew(param);
            if (isTerminated) {
                // 상태 변경
                DevMap terminateParam = new DevMap();
                terminateParam.put("callNo", param.getString("callNo"));
                terminateParam.put("email", param.getString("email"));
                callService.callClientTerminate(terminateParam);
            }
        }

        result.put("success", "Y");
        return result;
    }

    @PostMapping("/update")
    public DevMap update(@RequestBody DevMap param) {
        DevMap result = new DevMap();
        shopService.shopRatingEdit(param);
        result.put("success", "Y");
        return result;
    }
}
