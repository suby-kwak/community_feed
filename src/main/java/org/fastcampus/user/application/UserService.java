package org.fastcampus.user.application;

import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.application.dto.GetUserResponseDto;
import org.fastcampus.user.application.interfaces.UserRepository;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserRequestDto dto) {
        UserInfo userInfo = new UserInfo(dto.userName(), dto.userProfileUrl());
        User user = new User(null, userInfo);
        return userRepository.save(user);
    }

    public User getUser(Long id) {
        return userRepository.findById(id);
    }

    public GetUserResponseDto getUserProfile(Long id) {
        User user = getUser(id);
        return new GetUserResponseDto(user);
    }
}
