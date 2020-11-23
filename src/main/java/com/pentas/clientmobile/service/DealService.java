package com.pentas.clientmobile.service;

import com.pentas.clientmobile.common.CmmnDao;
import com.pentas.clientmobile.common.module.mybatis.paginator.domain.PageBounds;
import com.pentas.clientmobile.common.module.mybatis.paginator.domain.PageList;
import com.pentas.clientmobile.common.module.util.DevMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DealService {
    @Autowired
    CmmnDao cmmnDao;

    public PageList<DevMap> dealList(DevMap param) {
        int page = Integer.parseInt(param.getString("page"));
        int pageSize = 10;

        PageBounds pageBounds = new PageBounds(page, pageSize);

        return cmmnDao.selectListPage("clientmobile.deal.dealList", param, pageBounds);
    }

    public List<DevMap> dealDateList(DevMap param) { return cmmnDao.selectList("clientmobile.deal.dealDateList", param); }

    public void dealDateExtend(DevMap param) { cmmnDao.update("clientmobile.deal.dealDateExtend", param); }

    public void dealClientCancel(DevMap param) { cmmnDao.update("clientmobile.deal.dealClientCancel", param); }

    public DevMap dealOne(DevMap param) {
        return cmmnDao.selectOne("clientmobile.deal.dealOne", param);
    }

    public List<DevMap> sellingList(DevMap param) { return cmmnDao.selectList("clientmobile.deal.sellingList", param); }
}
