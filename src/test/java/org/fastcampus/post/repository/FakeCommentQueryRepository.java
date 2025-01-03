package org.fastcampus.post.repository;

import java.util.ArrayList;
import java.util.List;
import org.fastcampus.post.application.interfaces.CommentQueryRepository;
import org.fastcampus.post.repository.entity.comment.CommentEntity;
import org.fastcampus.post.ui.dto.GetContentResponseDto;

public class FakeCommentQueryRepository implements CommentQueryRepository {

    private final FakeCommentRepository fakeCommentRepository;

    public FakeCommentQueryRepository(FakeCommentRepository fakeCommentRepository) {
        this.fakeCommentRepository = fakeCommentRepository;
    }

    @Override
    public List<GetContentResponseDto> getCommentList(Long postId, Long userId,
        Long lastCommentId) {
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
