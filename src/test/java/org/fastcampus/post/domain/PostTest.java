package org.fastcampus.post.domain;

import org.fastcampus.post.domain.content.Content;
import org.fastcampus.post.domain.content.PostPublicationState;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class PostTest {

    private final User user1 = new User(1L, new UserInfo("test", "url"));
    private final User user2 = new User(2L, new UserInfo("test", "url"));

    private final Post post1 = new Post(1L, user1, "content", PostPublicationState.PUBLIC);

    @Test
    void givenPostCreatedWhenLikeThenLikeCountShouldBe1() {
        // when
        post1.like(user2);

        // then
        assertEquals(1, post1.getLikeCount());
    }

    @Test
    void givenPostCreatedWhenLikeByOtherUserThenThrowException() {
        // when, then
        assertThrows(IllegalArgumentException.class, () -> post1.like(user1));
    }

    @Test
    void givenPostCreatedAndLikeWhenUnlikeThenLikeCountShouldBe0() {
        // given
        post1.like(user2);

        // when
        post1.unlike(user2);

        // then
        assertEquals(0, post1.getLikeCount());
    }

    @Test
    void givenPostCreatedWhenUpdateContentThenContentShouldBeUpdated() {
        // given
        String newPostContent = "new Content";

        // when
        post1.updatePost(user1, newPostContent);

        // then
        Content content = post1.getContent();
        assertEquals(newPostContent, content.getContentText());
    }

    @Test
    void givenPostCreatedWhenUpdateContentByOtherUserThenThrowException() {
        // given
        String newPostContent = "new Content";

        // when, then
        assertThrows(IllegalArgumentException.class,
            () -> post1.updatePost(user2, newPostContent));
    }

    @Test
    void givenPostCreatedWhenUpdateStateThenStateShouldBeUpdated() {
        // given
        PostPublicationState newPostState = PostPublicationState.PRIVATE;

        // when
        post1.updateState(newPostState);

        // then
        assertEquals(newPostState,post1.getState());
    }
}
