package org.fastcampus.post.application.interfaces;

import java.util.List;
import org.fastcampus.post.ui.dto.GetContentResponseDto;

public interface CommentQueryRepository {
    List<GetContentResponseDto> getCommentList(Long postId, Long userId, Long lastCommentId);
}
