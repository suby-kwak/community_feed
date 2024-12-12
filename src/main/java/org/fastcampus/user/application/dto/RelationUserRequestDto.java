package org.fastcampus.user.application.dto;

import org.fastcampus.user.domain.User;

public record RelationUserRequestDto(User following, User follower) {

}
