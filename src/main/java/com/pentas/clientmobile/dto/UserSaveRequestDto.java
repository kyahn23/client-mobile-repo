package com.pentas.clientmobile.dto;

import com.pentas.clientmobile.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSaveRequestDto {
    private String memberId;
    private String passwordPin;
    private int passwordErrorCount;
    private char isLoginAllowed;
    private String memberNickname;
    private String memberLocationSido;
    private String memberLocationSigg;
    private char isMarketingAgreed;
    private char isSocialLogin;
    private String socialService;
    private String socialId;
    private char isEmailAuthenticated;

    @Builder
    public UserSaveRequestDto(
            String memberId,
            String passwordPin,
            int passwordErrorCount,
            char isLoginAllowed,
            String memberNickname,
            String memberLocationSido,
            String memberLocationSigg,
            char isMarketingAgreed,
            char isSocialLogin,
            String socialService,
            String socialId,
            char isEmailAuthenticated
    ) {
        this.memberId = memberId;
        this.passwordPin = passwordPin;
        this.passwordErrorCount = passwordErrorCount;
        this.isLoginAllowed = isLoginAllowed;
        this.memberNickname = memberNickname;
        this.memberLocationSido = memberLocationSido;
        this.memberLocationSigg = memberLocationSigg;
        this.isMarketingAgreed = isMarketingAgreed;
        this.isSocialLogin = isSocialLogin;
        this.socialService = socialService;
        this.socialId = socialId;
        this.isEmailAuthenticated = isEmailAuthenticated;
    }

    public User toEntity() {
        return User.builder()
                .memberId(memberId)
                .passwordPin(passwordPin)
                .passwordErrorCount(passwordErrorCount)
                .isLoginAllowed(isLoginAllowed)
                .memberNickname(memberNickname)
                .memberLocationSido(memberLocationSido)
                .memberLocationSigg(memberLocationSigg)
                .isMarketingAgreed(isMarketingAgreed)
                .isSocialLogin(isSocialLogin)
                .socialService(socialService)
                .socialId(socialId)
                .isEmailAuthenticated(isEmailAuthenticated)
                .build();
    }
}
