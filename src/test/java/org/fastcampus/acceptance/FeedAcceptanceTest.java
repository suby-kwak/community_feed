package org.fastcampus.acceptance;

import static org.fastcampus.acceptance.steps.FeedAcceptanceSteps.requestCreatePost;
import static org.fastcampus.acceptance.steps.FeedAcceptanceSteps.requestFeed;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.fastcampus.acceptance.utils.AcceptanceTestTemplate;
import org.fastcampus.post.application.dto.CreatePostRequestDto;
import org.fastcampus.post.domain.PostPublicationState;
import org.fastcampus.post.ui.dto.GetPostContentResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FeedAcceptanceTest extends AcceptanceTestTemplate {

    /**
     * User 1 --- follow ---> User2
     * User 1 --- follow ---> User3
     */
    @BeforeEach
    void setup() {
        super.init();
    }

    /**
     * User 2 create Post 1 User 1 Get Post 1 From Feed
     */
    @Test
    void givenUserHasFollowerAndCreatePostWhenFollowerUserRequestFeedThenFollowerCanGetPostFromFeed() {
        // given,
        CreatePostRequestDto dto = new CreatePostRequestDto(2L, "user 1 can get this post",
            PostPublicationState.PUBLIC);
        Long createdPostId = requestCreatePost(dto);

        // when, 팔로워 피드 요청
        List<GetPostContentResponseDto> result = requestFeed(1L);

        // then
        assertEquals(1, result.size());
        assertEquals(createdPostId, result.get(0).getId());
    }

}
