package com.pentas.clientmobile.service;

import com.pentas.clientmobile.common.CmmnDao;
import com.pentas.clientmobile.common.module.util.DevMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {
    @Autowired
    CmmnDao cmmnDao;

    public List<DevMap> shopNameList(DevMap param) { return cmmnDao.selectList("clientmobile.shop.shopNameList", param); }

    public void shopRatingNew(DevMap param) { cmmnDao.insert("clientmobile.shop.shopRatingNew", param); }

    public void shopRatingBlack(DevMap param) { cmmnDao.insert("clientmobile.shop.shopRatingBlack", param); }

    public void shopRatingEdit(DevMap param) { cmmnDao.update("clientmobile.shop.shopRatingEdit", param); }
}
