package org.fastcampus.user.domain;

import java.util.Objects;
import org.fastcampus.common.domain.PositiveIntegerCounter;

public class User {

    private final Long id;
    private final UserInfo info;
    private final PositiveIntegerCounter followingCount;
    private final PositiveIntegerCounter followerCount;

    public User(Long id, UserInfo info) {
        this.id = id;
        this.info = info;
        this.followingCount = new PositiveIntegerCounter();
        this.followerCount = new PositiveIntegerCounter();
    }

    public void follow(User targetUser) {
        if (this.equals(targetUser)) {
            throw new IllegalArgumentException();
        }

        followingCount.increase();
        targetUser.increaseFollowerCount();
    }

    public void unfollow(User targetUser) {
        if (this.equals(targetUser)) {
            throw new IllegalArgumentException();
        }

        followingCount.decrease();
        targetUser.decreaseFollowerCount();
    }

    private void increaseFollowerCount() {
        followerCount.increase();
    }

    private void decreaseFollowerCount() {
        followerCount.decrease();
    }

    public int getFollowingCount() {
        return followingCount.getCount();
    }

    public int getFollowerCount() {
        return followerCount.getCount();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
