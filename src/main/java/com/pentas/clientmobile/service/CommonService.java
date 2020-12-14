package com.pentas.clientmobile.service;


import com.pentas.clientmobile.common.CmmnDao;
import com.pentas.clientmobile.common.conf.properties.S3Properties;
import com.pentas.clientmobile.common.exception.UserException;
import com.pentas.clientmobile.common.module.util.CmmnUtil;
import com.pentas.clientmobile.common.module.util.DevMap;
import com.pentas.clientmobile.common.module.util.s3.S3Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class CommonService {
    private static final Logger log = LoggerFactory.getLogger(CommonService.class);

    @Autowired
    CmmnDao cmmnDao;

    @Autowired
    S3Properties s3Properties;

    public List<DevMap> sidoList() { return cmmnDao.selectList("clientmobile.common.sidoList"); }

    public List<DevMap> siggList(DevMap param) { return cmmnDao.selectList("clientmobile.common.siggList", param); }

    public DevMap getFileInfo(String param) { return cmmnDao.selectOne("clientmobile.common.selectFileInfo", param); }

    public void imageSrc(HttpServletResponse response, String fileName) throws UserException {
        // 파일 정보 셋팅
        DevMap fileInfo = getFileInfo(fileName);
        String fileStorePath = fileInfo.getString("filStorPthTxt");
        if (fileStorePath.indexOf("/") == 0) {
            fileStorePath = fileStorePath.replaceFirst("/", "");
        }

        String mimeType = fileInfo.getString("filTyp");
        if (mimeType == null || mimeType.equals("")) {
            mimeType = "application/octet-stream;";
        }
        response.setContentType(removeCRLF(mimeType));
        response.setHeader("Content-Disposition", "filename=image;");

        // 파일 다운로드
        try {
            S3Util.s3FileDownload(
                    response,
                    s3Properties.getEndPoint(),
                    s3Properties.getRegionName(),
                    s3Properties.getAccessKey(),
                    s3Properties.getSecretKey(),
                    s3Properties.getBucketName(),
                    fileStorePath,
                    fileName
            );
        } catch (Exception e) {
            log.error(CmmnUtil.getExceptionMsg(e));
            throw new UserException("이미지파일 로딩중 오류가 발생했습니다.");
        }
    }

    public static String removeCRLF(String parameter) {
        return parameter.replaceAll("\r", "").replaceAll("\n", "");
    }
}
