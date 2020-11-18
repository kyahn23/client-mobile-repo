package com.pentas.clientmobile.controller;

import com.pentas.clientmobile.common.module.mybatis.paginator.domain.PageList;
import com.pentas.clientmobile.common.module.util.DevMap;
import com.pentas.clientmobile.service.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/deal")
public class DealRestController {
    private final DealService dealService;

    @PostMapping("/list")
    public DevMap list(@RequestBody DevMap param) {
        DevMap result = new DevMap();
        PageList<DevMap> listPage = dealService.dealList(param);
        result.put("dealList", listPage);
        result.put("pageInfo", listPage.getPaginator());
        return result;
    }

    @PostMapping("/one")
    public DevMap one(@RequestBody DevMap param) {
        DevMap result = new DevMap();
        result = dealService.dealOne(param);
        return result;
    }

    @PostMapping("/consult")
    public DevMap consult(@RequestBody DevMap param) {
        DevMap result = new DevMap();
        List<DevMap> list = dealService.consultList(param);
        result.put("consultList", list);
        return result;
    }

    @PostMapping("/selling")
    public DevMap selling(@RequestBody DevMap param) {
        DevMap result = new DevMap();
        List<DevMap> list = dealService.sellingList(param);
        result.put("sellingList", list);
        return result;
    }
}
