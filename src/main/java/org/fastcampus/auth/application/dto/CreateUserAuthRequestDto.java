package org.fastcampus.auth.application.dto;

public record CreateUserAuthRequestDto(String email, String password, String role, String name,
                                       String profileImageUrl) {

}