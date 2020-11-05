package com.pentas.clientmobile.domain.user;

import com.pentas.clientmobile.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Getter
@Entity
@Table(name="TBL001")
@NoArgsConstructor
public class User extends BaseTimeEntity {

    @Id
    @Column(name="CL_MBR_ID")
    private String memberId;

    @Column(name="PWD_NO")
    private String passwordPin;

    @Column(name="PWERR_CNT")
    private int passwordErrorCount;

    @Column(name="LGIN_AVL_YN", insertable=false)
    private char isLoginAllowed;

    @Column(name="MBR_NM")
    private String memberNickname;

    @Column(name="MBR_LOC_SIDO", columnDefinition="char")
    private String memberLocationSido;

    @Column(name="MBR_LOC_SIGG", columnDefinition="char")
    private String memberLocationSigg;

    @Column(name="MKT_YN")
    private char isMarketingAgreed;

    @Column(name="SNS_LOGIN_YN")
    private char isSocialLogin;

    @Column(name="SNS_SERVICE", columnDefinition="char")
    private String socialService;

    @Column(name="SNS_ID")
    private String socialId;

    @Column(name="AUTH_YN")
    private char isEmailAuthenticated;

    @Builder
    public User(
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

}
