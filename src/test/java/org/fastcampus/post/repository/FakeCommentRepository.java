package org.fastcampus.post.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.fastcampus.post.application.interfaces.CommentRepository;
import org.fastcampus.post.domain.comment.Comment;

public class FakeCommentRepository implements CommentRepository {

    private final Map<Long, Comment> repo = new HashMap<>();

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() != null) {
            repo.put(comment.getId(), comment);
            return comment;
        }

        long id = repo.size() + 1;
        Comment newComment = new Comment(id, comment.getPost(), comment.getAuthor(),
            comment.getContent());
        repo.put(id, newComment);
        return newComment;
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return Optional.ofNullable(repo.get(id));
    }
}
