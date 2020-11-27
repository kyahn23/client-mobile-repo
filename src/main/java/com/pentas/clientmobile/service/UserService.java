package com.pentas.clientmobile.service;

import com.pentas.clientmobile.common.CmmnDao;
import com.pentas.clientmobile.common.conf.properties.EmailProperties;
import com.pentas.clientmobile.common.module.util.CmmnUtil;
import com.pentas.clientmobile.common.module.util.DevMap;
import com.pentas.clientmobile.common.module.util.email.EmailUtil;
import com.pentas.clientmobile.domain.user.User;
import com.pentas.clientmobile.domain.user.UserRepository;
import com.pentas.clientmobile.dto.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    CmmnDao cmmnDao;

    @Autowired
    EmailProperties emailProperties;

    /**
     * JPA
     */
    public Boolean isRegisteredUser (String memberId) { return userRepository.findById(memberId).isPresent(); }

    public Boolean isLoginAllowed (String memberId, String passwordPin) { return userRepository.findByMemberIdAndPasswordPin(memberId, passwordPin).isPresent(); }

    public Boolean isEmailAuthenticated (String memberId) {
        char isEmailAuthenticated = 'N';
        Optional<User> user = userRepository.findById(memberId);
        if (user.isPresent()) {
            isEmailAuthenticated = user.get().getIsEmailAuthenticated();
        }

        return isEmailAuthenticated == 'Y';
    }

    public Boolean isSocialUser (String memberId) {
        char isSocialUserYn = 'N';
        Optional<User> user = userRepository.findById(memberId);
        if (user.isPresent()) {
            isSocialUserYn = user.get().getIsSocialLogin();
        }

        return isSocialUserYn == 'Y';
    }

    public String save (UserSaveRequestDto requestDto) { return userRepository.save(requestDto.toEntity()).getMemberId(); }

    /**
     * MyBatis
     */
    public DevMap getUserInfo (DevMap param) { return cmmnDao.selectOne("clientmobile.user.getUserInfo", param); }

    public int updateUserInfo (DevMap param) { return cmmnDao.update("clientmobile.user.updateUserInfo", param); }

    public int setAuthKey (DevMap param) {
        int rowCount = 0;
        rowCount = cmmnDao.update("clientmobile.user.setAuthKey", param);
        if (rowCount > 0) {
            sendNewMemberEmail(
                    param.getString("nickname"),
                    param.getString("email"),
                    param.getString("authKey")
            );
        }
        return rowCount;
    }

    public String getEmailByAuthKey (String authKey) { return cmmnDao.selectOne("clientmobile.user.getEmailByAuthKey", authKey); }

    public void verifyEmail (String email) { cmmnDao.update("clientmobile.user.verifyEmail", email); }

    /**
     * Email Util
     */
    private void sendNewMemberEmail (String nickname, String email, String authKey) {
        String hashEmail = CmmnUtil.encryptSHA256(email);
        String verifyUrl = "http://localhost:8081/#/verify?mbr=" + hashEmail + "&cue=" + authKey;

        EmailUtil.sendMailAuthSSL(
                emailProperties.getSmtpHost(),
                emailProperties.getSmtpPort(),
                emailProperties.getSmtpUser(),
                emailProperties.getSmtpPassword(),
                "[Pentaworks Service] 이메일 인증 안내",
                "<html><p>" + nickname + "님의 가입을 축하합니다!<br>" +
                        "아래 링크를 누르거나 주소로 이동하여 이메일 인증을 진행해주세요.<br></p>" +
                        "<h4>이메일 인증 링크</h4>" +
                        "<p><a href='" + verifyUrl + "' target='_blank'>" + verifyUrl + "</a></p>",
                email + "",
                emailProperties.getFromEmail(),
                emailProperties.getFromName()
        );
    }
}
