package com.pentas.clientmobile.service;

import com.pentas.clientmobile.common.CmmnDao;
import com.pentas.clientmobile.common.module.util.DevMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CallService {
    @Autowired
    CmmnDao cmmnDao;

    public List<DevMap> callList(DevMap param) { return cmmnDao.selectList("clientmobile.call.callList", param); }

    public void callCancelRead(DevMap param) { cmmnDao.update("clientmobile.call.callCancelRead", param); }

    public void callClientCancel(DevMap param) { cmmnDao.update("clientmobile.call.callClientCancel", param); }

    public void callClientTerminate(DevMap param) { cmmnDao.update("clientmobile.call.callClientTerminate", param); }

    public void callClientNew(DevMap param) {
        ArrayList<String> bnList = (ArrayList<String>) param.get("bnList");
        for (String bnNo : bnList) {
            param.put("bnNo", bnNo);
            cmmnDao.insert("clientmobile.call.callClientNew", param);
        }
    }
}
