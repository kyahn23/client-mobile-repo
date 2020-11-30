package com.pentas.clientmobile.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findBySocialId(String socialId);

    Optional<User> findByMemberIdAndPasswordPin(String memberId, String passwordPin);

    Optional<User> findByMemberIdAndMemberNickname(String memberId, String memberNickname);

}
