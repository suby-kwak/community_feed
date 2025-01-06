package org.fastcampus.post.repository;

import java.util.List;
import org.fastcampus.post.repository.entity.post.PostEntity;
import org.fastcampus.post.repository.post_queue.UserQueueRedisRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile({"!test"})
public class UserQueueRedisRepositoryImpl implements UserQueueRedisRepository {

    @Override
    public void publishPostToFollowingUserList(PostEntity post, List<Long> userIdList) {

    }

    @Override
    public void publishPostListToFollowerUser(List<PostEntity> postEntities, Long userId) {

    }

    @Override
    public void deleteFeed(Long userId, Long targetUserId) {

    }
}
