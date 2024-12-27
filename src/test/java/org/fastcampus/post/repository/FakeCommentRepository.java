package org.fastcampus.post.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.fastcampus.post.application.interfaces.CommentRepository;
import org.fastcampus.post.domain.comment.Comment;
import org.fastcampus.post.repository.entity.comment.CommentEntity;
import org.springframework.stereotype.Repository;

@Repository
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
    public Comment findById(Long id) {
        return repo.get(id);
    }

    public List<CommentEntity> getCommentList(Long postId) {
        List<CommentEntity> commentEntities = new ArrayList<>();
        for (Long id : repo.keySet()) {
            if (repo.get(id).getPost().getId().equals(postId)) {
                commentEntities.add(new CommentEntity(repo.get(id)));
            }
        }

        return commentEntities;
    }

}
