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
        // kakao_account는 properties에서 설정해 준 사용자 정보가 담긴 객체의 key 값
        Integer kakao_id = (Integer) attributes.get(userNameAttributeName);
        response.put("id", kakao_id);
        // id 값 (사용자 식별 숫자) 경우 카카오는 attributes 안에 있고 kakao_account에 없음
        Map<String, Object> kakao_profile = (Map<String, Object>) response.get("profile");
        // 사용자 이름과 프로필 사진 값은 kakao_account 안에 profile JSON 객체에 담겨 있음

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
