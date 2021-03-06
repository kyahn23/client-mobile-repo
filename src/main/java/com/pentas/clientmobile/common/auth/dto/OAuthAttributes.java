package com.pentas.clientmobile.common.auth.dto;

import com.pentas.clientmobile.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String socialService;
    private String socialId;
    private String memberId;
    private String memberNickname;

    @Builder
    public OAuthAttributes(
            Map<String, Object> attributes,
            String nameAttributeKey,
            String socialService,
            String socialId,
            String memberId,
            String memberNickname
    ) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.socialService = socialService;
        this.socialId = socialId;
        this.memberId = memberId;
        this.memberNickname = memberNickname;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        if("naver".equals(registrationId)) {
            return ofNaver(registrationId, "id", attributes);
        } else if("kakao".equals(registrationId)) {
            return ofKakao(registrationId, "id", attributes);
        }

        return ofGoogle(registrationId, userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .socialService(registrationId)
                .socialId((String) attributes.get(userNameAttributeName))
                .memberId((String) attributes.get("email"))
                .memberNickname((String) attributes.get("name"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofNaver(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .socialService(registrationId)
                .socialId((String) response.get(userNameAttributeName))
                .memberId((String) response.get("email"))
                .memberNickname((String) response.get("name"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofKakao(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("kakao_account");
        // kakao_account??? properties?????? ????????? ??? ????????? ????????? ?????? ????????? key ???
        Integer kakao_id = (Integer) attributes.get(userNameAttributeName);
        response.put("id", kakao_id);
        // id ??? (????????? ?????? ??????) ?????? ???????????? attributes ?????? ?????? kakao_account??? ??????
        Map<String, Object> kakao_profile = (Map<String, Object>) response.get("profile");
        // ????????? ????????? ????????? ?????? ?????? kakao_account ?????? profile JSON ????????? ?????? ??????

        return OAuthAttributes.builder()
                .socialService(registrationId)
                .socialId(kakao_id.toString())
                .memberId((String) response.get("email"))
                .memberNickname((String) kakao_profile.get("nickname"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity() {
        return User.builder()
                .socialService(socialService)
                .socialId(socialId)
                .memberId(memberId)
                .memberNickname(memberNickname)
                .build();
    }

}
