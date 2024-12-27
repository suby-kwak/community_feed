package org.fastcampus.user.repository;

import java.util.HashMap;
import java.util.Map;
import org.fastcampus.user.application.interfaces.UserRepository;
import org.fastcampus.user.domain.User;

public class FakeUserRepository implements UserRepository {

    private final Map<Long, User> repo = new HashMap<>();

    @Override
    public User save(User user) {
        if (user.getId() != null) {
            repo.put(user.getId(), user);
            return user;
        }
        long id = repo.size() + 1;
        User newUser = new User(id, user.getInfo());
        repo.put(id, newUser);
        return newUser;
    }

    @Override
    public User findById(Long id) {
        return repo.get(id);
    }
}
