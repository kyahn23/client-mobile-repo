package com.pentas.clientmobile.service;

import com.pentas.clientmobile.domain.user.User;
import com.pentas.clientmobile.domain.user.UserRepository;
import com.pentas.clientmobile.dto.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public Boolean isRegisteredUser (String memberId) { return userRepository.findById(memberId).isPresent(); }

    public Boolean isSocialUserById (String memberId) {
        char isSocialUserYn = 'N';
        Optional<User> user = userRepository.findById(memberId);
        if (user.isPresent()) {
            isSocialUserYn = user.get().getIsSocialLogin();
        }

        return isSocialUserYn == 'Y';
    }

    public Boolean isSocialUser (String socialId) {
        return userRepository.findBySocialId(socialId).isPresent();
    }

    public String save(UserSaveRequestDto requestDto) { return userRepository.save(requestDto.toEntity()).getMemberId(); }

}
