package org.fastcampus.post.repository.post_queue;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.fastcampus.post.repository.entity.comment.QCommentEntity;
import org.fastcampus.post.repository.entity.like.QLikeEntity;
import org.fastcampus.post.repository.entity.post.QPostEntity;
import org.fastcampus.post.repository.entity.post.QUserPostQueueEntity;
import org.fastcampus.post.ui.dto.GetContentResponseDto;
import org.fastcampus.post.ui.dto.GetPostContentResponseDto;
import org.fastcampus.user.repository.entity.QUserEntity;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserPostQueueQueryRepositoryImpl implements UserPostQueueQueryRepository{

    private final JPAQueryFactory queryFactory;
    private static final QUserPostQueueEntity userPostQueueEntity = QUserPostQueueEntity.userPostQueueEntity;
    private static final QPostEntity postEntity = QPostEntity.postEntity;
    private static final QUserEntity userEntity = QUserEntity.userEntity;
    private static final QLikeEntity likeEntity = QLikeEntity.likeEntity;
    private static final QCommentEntity commentEntity = QCommentEntity.commentEntity;

    @Override
    public List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastContentId) {
        return queryFactory
            .select(
                Projections.fields(
                    GetPostContentResponseDto.class,
                    postEntity.id.as("id"),
                    postEntity.content.as("content"),
                    userEntity.id.as("userId"),
                    userEntity.name.as("userName"),
                    userEntity.profileImage.as("userProfileImage"),
                    postEntity.regDt.as("createdAt"),
                    postEntity.updDt.as("updatedAt"),
                    postEntity.commentCount.as("commentCount"),
                    postEntity.likeCount.as("likeCount"),
                    likeEntity.isNotNull().as("isLikedByMe")
                )
            )
            .from(userPostQueueEntity)
            .join(postEntity).on(userPostQueueEntity.postId.eq(postEntity.id))
            .join(userEntity).on(userPostQueueEntity.authorId.eq(userEntity.id))
            .leftJoin(likeEntity).on(hasLike(userId))
            .where(
                userPostQueueEntity.userId.eq(userId),
                hasLastData(lastContentId)
            )
            .orderBy(userPostQueueEntity.postId.desc())
            .limit(20)
            .fetch();
    }

    @Override
    public List<GetContentResponseDto> getCommentList(Long postId) {
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
            .join(postEntity).on(commentEntity.post.id.eq(postEntity.id))
            .join(userEntity).on(commentEntity.author.id.eq(userEntity.id))
            .leftJoin(likeEntity).on(hasCommentLike(userEntity.id))
            .where(
                commentEntity.post.id.eq(postId)
            )
            .limit(20)
            .fetch();
    }

    private BooleanExpression hasLastData(Long lastId) {
        if (lastId == null) {
            return null;
        }
        return postEntity.id.lt(lastId);
    }

    private BooleanExpression hasLike(Long userId) {
        if (userId == null) {
            return null;
        }
        return postEntity.id
            .eq(likeEntity.id.targetId)
            .and(likeEntity.id.targetType.eq("POST"))
            .and(likeEntity.id.userId.eq(userId));
    }

    private BooleanExpression hasCommentLike(NumberPath<Long> userId) {
        if (userId == null) {
            return null;
        }
        return postEntity.id
            .eq(likeEntity.id.targetId)
            .and(likeEntity.id.targetType.eq("COMMENT"))
            .and(likeEntity.id.userId.eq(userId));
    }

}
