package com.pentas.clientmobile.controller;

import com.pentas.clientmobile.common.module.mybatis.paginator.domain.PageList;
import com.pentas.clientmobile.common.module.util.DevMap;
import com.pentas.clientmobile.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/customer")
public class CustomerRestController {

    private final CustomerService customerService;

    @PostMapping("/noticeList")
    public DevMap noticeList(@RequestBody DevMap param) {
        DevMap result = new DevMap();
        PageList<DevMap> listPage = customerService.noticeList(param);

        result.put("noticeList", listPage);
        result.put("pageInfo", listPage.getPaginator());
        return result;
    }

    @PostMapping("/noticeDetail")
    public DevMap noticeDetail(@RequestBody DevMap param) {
        DevMap result = new DevMap();
        result = customerService.noticeDetail(param);
        return result;
    }

    @PostMapping("/faq")
    public DevMap faq(@RequestBody DevMap param) {
        DevMap result = new DevMap();
        PageList<DevMap> listPage = customerService.faqList(param);

        result.put("faqList", listPage);
        result.put("pageInfo", listPage.getPaginator());
        return result;
    }

}
