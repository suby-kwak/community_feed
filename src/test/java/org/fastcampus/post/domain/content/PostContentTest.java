package org.fastcampus.post.domain.content;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class PostContentTest {

    @Test
    void givenContentLengthIsOkWhenCreatePostContentThenReturnTextContext() {
        // given
        String contentText = "this is a test content";

        // when
        PostContent content = new PostContent(contentText);

        // then
        assertEquals(contentText, content.getContentText());
    }

    @Test
    void givenContentLengthIsOverLimitCreatepostContentThenThrowError() {
        // given
        String content = "a".repeat(501);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁", "닭", "굵"})
    void givenContentLengthIsOverLimitAndKoreanCreatePostContentThenThrowError(
        String koreanContent) {
        // given
        String content = koreanContent.repeat(501);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @Test
    void givenContentLengthIsUnderLimitCreatePostContentThenThrowError() {
        // given
        String content = "abcd";

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenContentLengthIsEmptyAndNullLimitCreatePostContentThenThrowError(String content) {
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @Test
    void givenContentLengthIsOkWhenUpdateContentThenNotThrowError() {
        // given
        String content = "this is a test content";
        PostContent postContent = new PostContent(content);

        // when
        String newContent = "this is an other test content";
        postContent.updateContent(newContent);

        // then
        assertEquals(newContent, postContent.getContentText());
        assertTrue(postContent.isEdited());
    }

    @Test
    void givenContentLengthIsOverLimitWhenUpdateContentThenThrowError() {
        // given
        String content = "this is a test content";
        PostContent postContent = new PostContent(content);

        // when, then
        String overLimitContent = "a".repeat(501);
        assertThrows(IllegalArgumentException.class,
            () -> postContent.updateContent(overLimitContent));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenContentLengthIsOverLimitAndKoreanWhenUpdateContentThenThrowError(
        String nullOrEmptyContent) {
        // given
        String content = "this is a test content";
        PostContent postContent = new PostContent(content);

        // when, then
        assertThrows(IllegalArgumentException.class,
            () -> postContent.updateContent(nullOrEmptyContent));
    }
}
