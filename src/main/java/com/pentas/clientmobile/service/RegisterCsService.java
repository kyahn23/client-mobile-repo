package com.pentas.clientmobile.service;

import com.pentas.clientmobile.common.CmmnDao;
import com.pentas.clientmobile.common.module.mybatis.paginator.domain.PageBounds;
import com.pentas.clientmobile.common.module.mybatis.paginator.domain.PageList;
import com.pentas.clientmobile.common.module.util.DevMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterCsService {

    @Autowired
    CmmnDao cmmnDao;

    /**
     * 통신사별 요금제
     * @param param
     * @return
     */
    public List<DevMap> mntRtList(DevMap param) {
        return cmmnDao.selectList("clientmobile.registercs.mntRtList", param);
    }


    public PageList<DevMap> priceList(DevMap param) {
        int page = Integer.parseInt(param.getString("page"));
        int pageSize = 10;

        PageBounds pageBounds = new PageBounds(page, pageSize);

        return cmmnDao.selectListPage("clientmobile.price.priceList", param, pageBounds);
    }
}
