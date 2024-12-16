package org.fastcampus.user.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class UserTest {

    private final UserInfo userInfo = new UserInfo("test", "");
    private final User user1 = new User(1L, userInfo);
    private final User user2 = new User(2L, userInfo);
    private final User user3 = new User(3L, userInfo);

    // 동일성 검사? equal / hashCode
    @Test
    void givenCreateSameIdUserWhenEqualSameIdThenReturnTrue() {
        // given
        UserInfo testInfo = new UserInfo("test1", "1");
        User oneUser = new User(1L, testInfo);

        // when, then
        assertEquals(oneUser, user1);
    }

    // follow 기능 확인
    @Test
    void givenUser1FollowUser2WhenUser1UnfollowUser2ThenReturnZero() {
        // given
        user1.follow(user2);

        // when
        user1.unfollow(user2);

        // then
        assertEquals(0, user1.getFollowerCount());
        assertEquals(0, user1.getFollowingCount());
        assertEquals(0, user2.getFollowerCount());
        assertEquals(0, user2.getFollowingCount());
    }

    // Follow 카운트가 0이하로 떨어지지 않는지 확인
    @Test
    void whenUser1UnfollowUser2ThenAllCountZero() {
        // when
        user1.unfollow(user2);

        // then
        assertEquals(0, user1.getFollowerCount());
        assertEquals(0, user1.getFollowingCount());
        assertEquals(0, user2.getFollowerCount());
        assertEquals(0, user2.getFollowingCount());
    }

    // follow 상태 확인은 서비스에서 진행하기 때문에 여기서는 틀린 값이 반환된다. 즉 이 테스트 케이스는 여기서 진행하기 적합하지않다.
    @Test
    void givenUser1FollowUser3WhenUser1UnfollowUser2ThenReturnOne() {
        // given
        user1.follow(user3);

        // when
        user1.unfollow(user2);

        // then
        assertEquals(1, user1.getFollowingCount());
        assertEquals(0, user1.getFollowerCount());
        assertEquals(0, user2.getFollowingCount());
        assertEquals(0, user2.getFollowerCount());
        assertEquals(0, user3.getFollowingCount());
        assertEquals(1, user3.getFollowerCount());
    }

    // 자기 자신을 팔로우할 경우 에러 반환
    @Test
    void givenOneUserWhenFollowSameUserThenThrowError() {
        assertThrows(IllegalArgumentException.class, () -> user1.follow(user1));
    }

    // 자기 자신을 언팔했을 경우 에러 반환
    @Test
    void givenOneUserWhenUnfollowSameUserThenThrowError() {
        assertThrows(IllegalArgumentException.class, () -> user1.unfollow(user1));
    }
}
