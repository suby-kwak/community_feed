package org.fastcampus.post.domain.comment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.PostPublicationState;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

public class CommentTest {

    private final User author1 = new User(1L, new UserInfo("name", "url"));
    private final User user2 = new User(2L, new UserInfo("name", "url"));

    private final Post post = new Post(1L, author1, "content", PostPublicationState.PUBLIC);
    private final Comment comment = new Comment(1L, post, author1, "comment content");

    @Test
    void givenCommentWhenLikeThenLikeCountShouldBe1() {
        // when
        comment.like(user2);

        // then
        assertEquals(1, comment.getLikeCount());
    }

    @Test
    void givenCommentWhenLikeBySameUserThenLikeCountShouldThrowError() {
        // when, then
        assertThrows(IllegalArgumentException.class, () -> comment.like(author1));
    }

    @Test
    void givenCommentCreatedAndLikeWhenUnlikeThenLikeCountShouldBe0() {
        // given
        comment.like(user2);

        // when
        comment.unlike(user2);

        // then
        assertEquals(0, comment.getLikeCount());
    }

    @Test
    void givenCommentCreatedWhenUnlikeThenLikeCountShouldBe0() {
        // when
        comment.unlike(user2);

        // then
        assertEquals(0, comment.getLikeCount());
    }

    @Test
    void givenCommentWhenUpdateContentThenContentShouldBeUpdated() {
        // given
        String newContent = "new content";

        // when
        comment.updateContent(author1, newContent);

        // then
        assertEquals(newContent, comment.getContent().getContentText());
    }

    @Test
    void givenCommentWhenUpdateContentOver100ThenThrowError() {
        // given
        String newContent = "a".repeat(101);

        // when, then
        assertThrows(IllegalArgumentException.class,
            () -> comment.updateContent(author1, newContent));
    }
}
