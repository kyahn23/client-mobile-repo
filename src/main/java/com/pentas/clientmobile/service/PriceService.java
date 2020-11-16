package com.pentas.clientmobile.service;

import com.pentas.clientmobile.common.CmmnDao;
import com.pentas.clientmobile.common.module.mybatis.paginator.domain.PageBounds;
import com.pentas.clientmobile.common.module.mybatis.paginator.domain.PageList;
import com.pentas.clientmobile.common.module.util.DevMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceService {

    @Autowired
    CmmnDao cmmnDao;

    /**
     * 제조사별 스마트폰 리스트
     * @param param
     * @return
     */
    public List<DevMap> phoneList(DevMap param) {
        return cmmnDao.selectList("clientmobile.price.phoneList", param);
    }

    /**
     * 선택한 모델에 따른 선택 가능 요금제
     * @param param
     * @return
     */
    public List<DevMap> mntRtList(DevMap param) {
        return cmmnDao.selectList("clientmobile.price.mntRtList", param);
    }


    public PageList<DevMap> priceList(DevMap param) {
        int page = Integer.parseInt(param.getString("page"));
        int pageSize = 10;

        PageBounds pageBounds = new PageBounds(page, pageSize);

        return cmmnDao.selectListPage("clientmobile.price.priceList", param, pageBounds);
    }
}
