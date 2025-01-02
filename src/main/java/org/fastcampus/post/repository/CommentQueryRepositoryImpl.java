package org.fastcampus.post.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.fastcampus.post.application.interfaces.CommentQueryRepository;
import org.fastcampus.post.repository.entity.comment.QCommentEntity;
import org.fastcampus.post.repository.entity.like.QLikeEntity;
import org.fastcampus.post.ui.dto.GetContentResponseDto;
import org.fastcampus.user.repository.entity.QUserEntity;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentQueryRepositoryImpl implements CommentQueryRepository {

    private final JPAQueryFactory queryFactory;
    private static final QCommentEntity commentEntity = QCommentEntity.commentEntity;
    private static final QUserEntity userEntity = QUserEntity.userEntity;
    private static final QLikeEntity likeEntity = QLikeEntity.likeEntity;

    @Override
    public List<GetContentResponseDto> getCommentList(Long postId, Long userId, Long lastCommentId) {
        return queryFactory
            .select(
                Projections.fields(
                    GetContentResponseDto.class,
                    commentEntity.id.as("id"),
                    commentEntity.content.as("content"),
                    userEntity.id.as("userId"),
                    userEntity.name.as("userName"),
                    userEntity.profileImage.as("userProfileImage"),
                    commentEntity.regDt.as("createdAt"),
                    commentEntity.updDt.as("updatedAt"),
                    commentEntity.likeCount.as("likeCount"),
                    likeEntity.isNotNull().as("isLikedByMe")
                )
            )
            .from(commentEntity)
            .join(userEntity).on(commentEntity.author.id.eq(userEntity.id))
            .leftJoin(likeEntity).on(hasLike(userId))
            .where(
                commentEntity.post.id.eq(postId),
                hasLastData(lastCommentId)
            )
            .limit(20)
            .fetch();
    }

    private BooleanExpression hasLastData(Long lastId) {
        if (lastId == null) {
            return null;
        }
        return commentEntity.id.lt(lastId);
    }

    private BooleanExpression hasLike(Long userId) {
        if (userId == null) {
            return null;
        }
        return commentEntity.id
            .eq(likeEntity.id.targetId)
            .and(likeEntity.id.targetType.eq("COMMENT"))
            .and(likeEntity.id.userId.eq(userId));
    }
}
