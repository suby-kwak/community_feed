package org.fastcampus.user.repository;

import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.fastcampus.post.repository.post_queue.UserPostQueueCommandRepository;
import org.fastcampus.user.application.interfaces.UserRelationRepository;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.repository.entity.UserEntity;
import org.fastcampus.user.repository.entity.UserRelationEntity;
import org.fastcampus.user.repository.entity.UserRelationIdEntity;
import org.fastcampus.user.repository.jpa.JpaUserRelationRepository;
import org.fastcampus.user.repository.jpa.JpaUserRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRelationRepositoryImpl implements UserRelationRepository {

    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final JpaUserRepository jpaUserRepository;
    private final UserPostQueueCommandRepository commandRepository;

    @Override
    public boolean isAlreadyFollow(User following, User follower) {
        UserRelationIdEntity id = new UserRelationIdEntity(following.getId(), follower.getId());
        return jpaUserRelationRepository.existsById(id);
    }

    @Override
    @Transactional
    public void save(User following, User follower) {
        UserRelationEntity relation = new UserRelationEntity(following.getId(), follower.getId());
        jpaUserRelationRepository.save(relation);
        jpaUserRepository.saveAll(List.of(new UserEntity(following), new UserEntity(follower)));
        commandRepository.saveFollowPost(following.getId(), follower.getId());
    }

    @Override
    @Transactional
    public void delete(User following, User follower) {
        UserRelationIdEntity id = new UserRelationIdEntity(following.getId(), follower.getId());
        jpaUserRelationRepository.deleteById(id);
        jpaUserRepository.saveAll(List.of(new UserEntity(following), new UserEntity(follower)));
        commandRepository.deleteUnfollowPost(following.getId(), follower.getId());
    }
}
