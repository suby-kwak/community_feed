package org.fastcampus.post.application.interfaces;

import java.util.Optional;
import org.fastcampus.post.domain.Post;

public interface PostRepository {

    Post save(Post post);

    Post findById(Long id);

}
