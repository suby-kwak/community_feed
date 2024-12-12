package org.fastcampus.user.application;

import org.fastcampus.user.application.dto.RelationUserRequestDto;
import org.fastcampus.user.application.interfaces.UserRelationRepository;
import org.fastcampus.user.domain.User;

public class UserRelationService {

    private final UserRelationRepository userRelationRepository;

    public UserRelationService(UserRelationRepository userRelationRepository) {
        this.userRelationRepository = userRelationRepository;
    }

    public void follow(RelationUserRequestDto dto) {
        if (userRelationRepository.isAlreadyFollow(dto.following(), dto.follower())) {
            throw new IllegalArgumentException("이미 팔로우 상태입니다.");
        }

        dto.following().follow(dto.follower());
    }

    public void unfollow(RelationUserRequestDto dto) {
        if (!userRelationRepository.isAlreadyFollow(dto.following(), dto.follower())) {
            throw new IllegalArgumentException("팔로우 상태가 아닙니다.");
        }

        dto.following().unfollow(dto.follower());
    }
}
