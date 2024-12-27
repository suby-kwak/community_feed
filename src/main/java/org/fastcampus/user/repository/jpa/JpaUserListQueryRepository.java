package org.fastcampus.user.repository.jpa;

import java.util.List;
import org.fastcampus.user.application.dto.GetUserListResponseDto;
import org.fastcampus.user.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaUserListQueryRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT new org.fastcampus.user.application.dto.GetUserListResponseDto(u2.name, u2.profileImage) "
        + "FROM UserRelationEntity u "
        + "INNER JOIN UserEntity u2 ON u.followerUserId = u2.id "
        + "WHERE u.followingUserId = :userId")
    List<GetUserListResponseDto> getFollowingUserList(Long userId);

    @Query("SELECT new org.fastcampus.user.application.dto.GetUserListResponseDto(u2.name, u2.profileImage) "
        + "FROM UserRelationEntity u "
        + "INNER JOIN UserEntity u2 ON u.followingUserId = u2.id "
        + "WHERE u.followerUserId = :userId")
    List<GetUserListResponseDto> getFollowerUserList(Long userId);

}
