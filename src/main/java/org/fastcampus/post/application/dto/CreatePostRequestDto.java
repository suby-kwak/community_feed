package org.fastcampus.post.application.dto;

import org.fastcampus.post.domain.PostPublicationState;

public record CreatePostRequestDto(Long id, String content, PostPublicationState state) {

}
