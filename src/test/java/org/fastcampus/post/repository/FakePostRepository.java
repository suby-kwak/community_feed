package org.fastcampus.post.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.fastcampus.post.application.interfaces.PostRepository;
import org.fastcampus.post.domain.Post;

public class FakePostRepository implements PostRepository {

    private final Map<Long, Post> repo = new HashMap<>();

    @Override
    public Post save(Post post) {
        if (post.getId() != null) {
            repo.put(post.getId(), post);
            return post;
        }

        long id = repo.size() + 1;
        Post newPost = new Post(id, post.getAuthor(), post.getContent(), post.getState());
        repo.put(id, newPost);
        return newPost;
    }

    @Override
    public Post findById(Long id) {
        return repo.get(id);
    }
}
