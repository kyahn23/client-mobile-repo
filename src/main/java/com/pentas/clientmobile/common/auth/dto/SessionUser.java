package com.pentas.clientmobile.common.auth.dto;

import com.pentas.clientmobile.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String socialService;
    private String socialId;
    private String memberId;
    private String memberNickname;

    public SessionUser(User user) {
        this.socialService = user.getSocialService();
        this.socialId = user.getSocialId();
        this.memberId = user.getMemberId();
        this.memberNickname = user.getMemberNickname();
    }
}
