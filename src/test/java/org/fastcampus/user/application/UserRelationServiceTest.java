package org.fastcampus.user.application;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.fastcampus.common.FakeObjectFactory;
import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.application.dto.RelationUserRequestDto;
import org.fastcampus.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserRelationServiceTest {

    private final UserService userService = FakeObjectFactory.getUserService();
    private final UserRelationService userRelationService = FakeObjectFactory.getUserRelationService();
    private User user1;
    private User user2;
    private RelationUserRequestDto relationDto;


    @BeforeEach
    void setUp() {
        //given
        CreateUserRequestDto dto = new CreateUserRequestDto("Doe", "");
        this.user1 = userService.createUser(dto);
        this.user2 = userService.createUser(dto);
        this.relationDto = new RelationUserRequestDto(user1.getId(), user2.getId());
    }

    @Test
    void givenCreateTwoUserWhenFollowThenUserFollowOtherUser() {
        // when
        userRelationService.followUser(relationDto);

        // then
        assertEquals(1, user1.getFollowingCount());
        assertEquals(1, user2.getFollowerCount());
    }

    @Test
    void givenFollowUserWhenFollowAgainThenThrowException() {
        // given
        userRelationService.followUser(relationDto);

        // when, then
        assertThrows(IllegalArgumentException.class,
            () -> userRelationService.followUser(relationDto));
    }

    @Test
    void givenFollowUserWhenFollowSelfThenThrowException() {
        // given
        RelationUserRequestDto sameUser = new RelationUserRequestDto(user1.getId(), user1.getId());

        // when, then
        assertThrows(IllegalArgumentException.class,
            () -> userRelationService.followUser(sameUser));
    }

    @Test
    void givenFollowUserWhenUnfollowThenUserUnfollowOtherUser() {
        // given
        userRelationService.followUser(relationDto);

        // when
        userRelationService.unfollowUser(relationDto);

        // then
        assertEquals(0, user1.getFollowingCount());
        assertEquals(0, user2.getFollowerCount());
    }

    @Test
    void givenUnfollowUserWhenUnfollowAgainThenThrowException() {
        // when, then
        assertThrows(IllegalArgumentException.class,
            () -> userRelationService.unfollowUser(relationDto));
    }

    @Test
    void givenUnfollowUserWhenUnfollowSelfThenThrowException() {
        // given
        RelationUserRequestDto sameUser = new RelationUserRequestDto(user1.getId(), user1.getId());

        // when, then
        assertThrows(IllegalArgumentException.class,
            () -> userRelationService.unfollowUser(sameUser));
    }
}
