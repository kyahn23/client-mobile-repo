package com.pentas.clientmobile.controller;

import com.pentas.clientmobile.common.exception.UserException;
import com.pentas.clientmobile.common.module.util.DevMap;
import com.pentas.clientmobile.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @RequestMapping("/image")
    public void getImage(HttpServletRequest request, HttpServletResponse response) throws UserException {
        String filename = request.getParameter("filename");
        commonService.imageSrc(response, filename);
    }
}
