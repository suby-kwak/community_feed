package org.fastcampus.post.repository.post_queue;

import java.util.List;
import org.fastcampus.post.repository.entity.post.PostEntity;

public interface UserQueueRedisRepository {

    void publishPostToFollowingUserList(PostEntity post, List<Long> userIdList);

    void publishPostListToFollowerUser(List<PostEntity> postEntities, Long userId);

    void deleteFeed(Long userId, Long targetUserId);

}
