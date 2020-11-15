package com.pentas.clientmobile.controller;

import com.pentas.clientmobile.common.module.mybatis.paginator.domain.PageList;
import com.pentas.clientmobile.common.module.util.DevMap;
import com.pentas.clientmobile.service.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
