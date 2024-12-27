package org.fastcampus.user.application;

import org.fastcampus.user.application.dto.RelationUserRequestDto;
import org.fastcampus.user.application.interfaces.UserRelationRepository;
import org.fastcampus.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public class UserRelationService {

    private final UserRelationRepository userRelationRepository;
    private final UserService userService;

    public UserRelationService(UserRelationRepository userRelationRepository,
        UserService userService) {
        this.userRelationRepository = userRelationRepository;
        this.userService = userService;
    }

    public void followUser(RelationUserRequestDto dto) {
        User following = userService.getUser(dto.followingId());
        User follower = userService.getUser(dto.followerId());

        if (userRelationRepository.isAlreadyFollow(following, follower)) {
            throw new IllegalArgumentException("이미 팔로우 상태입니다.");
        }

        following.follow(follower);

        userRelationRepository.save(following, follower);
    }

    public void unfollowUser(RelationUserRequestDto dto) {
        User following = userService.getUser(dto.followingId());
        User follower = userService.getUser(dto.followerId());

        if (!userRelationRepository.isAlreadyFollow(following, follower)) {
            throw new IllegalArgumentException("팔로우 상태가 아닙니다.");
        }

        following.unfollow(follower);

        userRelationRepository.delete(following, follower);
    }
}
