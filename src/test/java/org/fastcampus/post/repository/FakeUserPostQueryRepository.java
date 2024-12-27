package org.fastcampus.post.repository;

import java.util.ArrayList;
import java.util.List;
import org.fastcampus.post.repository.entity.comment.CommentEntity;
import org.fastcampus.post.repository.entity.post.PostEntity;
import org.fastcampus.post.repository.post_queue.UserPostQueueQueryRepository;
import org.fastcampus.post.ui.dto.GetContentResponseDto;
import org.fastcampus.post.ui.dto.GetPostContentResponseDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("test")
public class FakeUserPostQueryRepository implements UserPostQueueQueryRepository {

    private final FakeUserQueueRedisRepository fakeUserQueueRedisRepository;
    private final FakeCommentRepository fakeCommentRepository;

    public FakeUserPostQueryRepository(FakeUserQueueRedisRepository fakeUserQueueRedisRepository,
        FakeCommentRepository fakeCommentRepository) {
        this.fakeUserQueueRedisRepository = fakeUserQueueRedisRepository;
        this.fakeCommentRepository = fakeCommentRepository;
    }

    @Override
    public List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastPostId) {
        List<PostEntity> postEntities = fakeUserQueueRedisRepository.getPostListByUserId(userId);
        List<GetPostContentResponseDto> result = new ArrayList<>();

        for (PostEntity postEntity : postEntities) {
            GetPostContentResponseDto res = GetPostContentResponseDto.builder()
                .id(postEntity.getId())
                .isLikedByMe(false).build();
            result.add(res);
        }
        return result;
    }

    @Override
    public List<GetContentResponseDto> getCommentList(Long postId) {
        List<CommentEntity> commentEntities = fakeCommentRepository.getCommentList(postId);
        List<GetContentResponseDto> result = new ArrayList<>();

        for (CommentEntity commentEntity : commentEntities) {
            GetContentResponseDto res = GetContentResponseDto.builder()
                .id(commentEntity.getId())
                .isLikedByMe(false)
                .build();

            result.add(res);
        }

        return result;
    }

}
