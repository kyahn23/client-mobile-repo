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

    /**
     * 통신유형과 제조사에 따른 
     * 스마트폰 리스트
     * @param param
     * @return
     */
    public List<DevMap> phoneList(DevMap param) {
        return cmmnDao.selectList("clientmobile.registercs.phoneList", param);
    }


    /**
     * 상담등록
     * @param param
     */
    public void registerCs(DevMap param) {
        cmmnDao.insert("clientmobile.registercs.registerCs", param);
    }
}
