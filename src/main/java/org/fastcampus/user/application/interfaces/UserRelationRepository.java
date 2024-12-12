package org.fastcampus.user.application.interfaces;

import org.fastcampus.user.domain.User;

public interface UserRelationRepository {

    boolean isAlreadyFollow(User following, User follower);

    void save(User following, User follower);

    void delete(User following, User follower);

}
