package com.pentas.clientmobile.service;

import com.pentas.clientmobile.common.conf.properties.EmailProperties;
import com.pentas.clientmobile.common.module.util.CmmnUtil;
import com.pentas.clientmobile.common.module.util.email.EmailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@EnableAsync
@Service
public class EmailService {
    @Autowired
    EmailProperties emailProperties;

    @Async
    public void sendNewMemberEmail (String nickname, String email, String authToken) {
        String verifyUrl = "http://101.101.219.68:8080/client-mobile/#/verify?token=" + authToken;

        EmailUtil.sendMailAuthSSL(
                emailProperties.getSmtpHost(),
                emailProperties.getSmtpPort(),
                emailProperties.getSmtpUser(),
                emailProperties.getSmtpPassword(),
                "[Pentaworks Service] 이메일 인증 안내",
                "<html><p>" + nickname + "님의 가입을 축하합니다!<br>" +
                        "아래 링크를 누르거나 주소로 이동하여 이메일 인증을 진행해주세요.<br>" +
                        "인증 링크는 발송 후 30분 동안 유효합니다.<br></p>" +
                        "<h4>이메일 인증 링크</h4>" +
                        "<p><a href='" + verifyUrl + "' target='_blank'>" + verifyUrl + "</a></p>",
                email + "",
                emailProperties.getFromEmail(),
                emailProperties.getFromName()
        );
    }

    @Async
    public void sendFindMemberEmail (String nickname, String email, String authToken) {
        String verifyUrl = "http://101.101.219.68:8080/client-mobile/#/layer/reset?token=" + authToken;

        EmailUtil.sendMailAuthSSL(
                emailProperties.getSmtpHost(),
                emailProperties.getSmtpPort(),
                emailProperties.getSmtpUser(),
                emailProperties.getSmtpPassword(),
                "[Pentaworks Service] 비밀번호 재설정 안내",
                "<html><p>" + nickname + "님의 비밀번호를 재설정합니다.<br>" +
                        "아래 링크를 누르거나 주소로 이동하여 비밀번호 재설정을 진행해주세요.<br>" +
                        "인증 링크는 발송 후 30분 동안 유효합니다.<br></p>" +
                        "<h4>이메일 인증 링크</h4>" +
                        "<p><a href='" + verifyUrl + "' target='_blank'>" + verifyUrl + "</a></p>",
                email + "",
                emailProperties.getFromEmail(),
                emailProperties.getFromName()
        );
    }

}
