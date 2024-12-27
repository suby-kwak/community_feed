package org.fastcampus.user.application.dto;

import org.fastcampus.user.domain.User;

public record GetUserResponseDto(Long id, String name, String profileImageUrl, Integer followerCount, Integer followingCount) {

    public GetUserResponseDto(User user) {
        this(user.getId(), user.getName(), user.getProfileImage(), user.getFollowerCount(),
            user.getFollowingCount());
    }
}
