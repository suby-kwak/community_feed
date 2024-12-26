package org.fastcampus.post.ui;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.fastcampus.common.ui.Response;
import org.fastcampus.post.repository.post_queue.UserPostQueueQueryRepository;
import org.fastcampus.post.ui.dto.GetContentResponseDto;
import org.fastcampus.post.ui.dto.GetPostContentResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {

    private final UserPostQueueQueryRepository userPostQueueQueryRepository;

    @GetMapping("/post/{userId}")
    public Response<List<GetPostContentResponseDto>> getPostFeed(
        @PathVariable(name = "userId") Long userId, Long lastPostId) {
        List<GetPostContentResponseDto> result = userPostQueueQueryRepository.getContentResponse(
            userId, lastPostId);

        return Response.ok(result);
    }

    @GetMapping("/comment/{postId}")
    public Response<List<GetContentResponseDto>> getCommentFeed(
        @PathVariable(name="postId") Long postId) {
        List<GetContentResponseDto> result = userPostQueueQueryRepository.getCommentList(postId);

        return Response.ok(result);
    }
}
