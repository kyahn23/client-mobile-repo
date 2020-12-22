package com.pentas.clientmobile.service;

import com.pentas.clientmobile.common.CmmnDao;
import com.pentas.clientmobile.common.module.util.DevMap;
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
    EmailService emailService;

    /**
     * JPA
     */
    public Boolean isRegisteredUser (String memberId) { return userRepository.findById(memberId).isPresent(); }

    public Boolean isLoginMatch (String memberId, String passwordPin) { return userRepository.findByMemberIdAndPasswordPin(memberId, passwordPin).isPresent(); }

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

    public Boolean isIdAndNameMatch (String memberId, String memberNickname) { return userRepository.findByMemberIdAndMemberNickname(memberId, memberNickname).isPresent(); }

    public String save (UserSaveRequestDto requestDto) { return userRepository.save(requestDto.toEntity()).getMemberId(); }

    /**
     * MyBatis
     */
    public DevMap getUserInfo (DevMap param) { return cmmnDao.selectOne("clientmobile.user.getUserInfo", param); }

    public String getPwdSalt (String email) { return cmmnDao.selectOne("clientmobile.user.getPwdSalt", email); }

    public int updateUserInfo (DevMap param) { return cmmnDao.update("clientmobile.user.updateUserInfo", param); }

    public int setAuthToken (DevMap param, String type) {
        int rowCount = 0;
        rowCount = cmmnDao.update("clientmobile.user.setAuthToken", param);
        if (rowCount > 0) {
            if (type.equals("NEW")) {
                emailService.sendNewMemberEmail(
                        param.getString("nickname"),
                        param.getString("email"),
                        param.getString("authToken")
                );
            } else if (type.equals("EXT")) {
                emailService.sendFindMemberEmail(
                        param.getString("nickname"),
                        param.getString("email"),
                        param.getString("authToken")
                );
            }
        }
        return rowCount;
    }

    public DevMap getEmailByAuthToken (String authToken) { return cmmnDao.selectOne("clientmobile.user.getEmailByAuthToken", authToken); }

    public void verifyEmail (String email) { cmmnDao.update("clientmobile.user.verifyEmail", email); }

    public Boolean isLoginAllowed (String email) { return cmmnDao.selectOne("clientmobile.user.getLoginAllowed", email).equals("Y"); }

    public void setLoginDenied (String email) { cmmnDao.update("clientmobile.user.setLoginDenied", email); }

    public int getPasswordErrorCount (String email) { return cmmnDao.selectOne("clientmobile.user.getPasswordErrorCount", email); }

    public void setPasswordErrorCount (String email) { cmmnDao.update("clientmobile.user.setPasswordErrorCount", email); }

    public void resetPasswordErrorCount (String email) { cmmnDao.update("clientmobile.user.resetPasswordErrorCount", email); }

    public int resetLogin (DevMap param) { return cmmnDao.update("clientmobile.user.resetLogin", param); }
}
