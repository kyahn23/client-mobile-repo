package com.pentas.clientmobile.service;


import com.pentas.clientmobile.common.CmmnDao;
import com.pentas.clientmobile.common.module.util.DevMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonService {
    @Autowired
    CmmnDao cmmnDao;

    public List<DevMap> sidoList() { return cmmnDao.selectList("clientmobile.common.sidoList"); }

    public List<DevMap> siggList(DevMap param) { return cmmnDao.selectList("clientmobile.common.siggList", param); }
}
