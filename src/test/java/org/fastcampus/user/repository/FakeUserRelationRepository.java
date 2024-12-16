package org.fastcampus.user.repository;

import java.util.HashSet;
import java.util.Set;
import org.fastcampus.user.application.interfaces.UserRelationRepository;
import org.fastcampus.user.domain.User;

public class FakeUserRelationRepository implements UserRelationRepository {

    private final Set<Relation> repo = new HashSet<>();

    @Override

    public boolean isAlreadyFollow(User following, User follower) {
        return repo.contains(new Relation(following.getId(), follower.getId()));
    }

    @Override
    public void save(User following, User follower) {
        repo.add(new Relation(following.getId(), follower.getId()));
    }

    @Override
    public void delete(User following, User follower) {
        repo.remove(new Relation(following.getId(), follower.getId()));
    }

}

record Relation(Long followingId, Long followerId) {
}