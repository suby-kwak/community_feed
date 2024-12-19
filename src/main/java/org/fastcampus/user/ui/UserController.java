package org.fastcampus.user.ui;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.fastcampus.common.ui.Response;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.application.dto.GetUserListResponseDto;
import org.fastcampus.user.application.dto.GetUserResponseDto;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.repository.jpa.JpaUserListQueryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final JpaUserListQueryRepository jpaUserListQueryRepository;

    @PostMapping
    public Response<Long> createUser(@RequestBody CreateUserRequestDto dto) {
        User user = userService.createUser(dto);
        return Response.ok(user.getId());
    }

    @GetMapping("/{userId}")
    public Response<GetUserResponseDto> getUserProfile(@PathVariable(name = "userId") Long userId) {
        GetUserResponseDto dto = userService.getUserProfile(userId);
        return Response.ok(dto);
    }

    @GetMapping("/{userId}/following")
    public Response<List<GetUserListResponseDto>> getFollowingList(
        @PathVariable(name = "userId") Long userId) {
        List<GetUserListResponseDto> list = jpaUserListQueryRepository.getFollowingUserList(userId);
        return Response.ok(list);
    }

    @GetMapping("/{userId}/follower")
    public Response<List<GetUserListResponseDto>> getFollowerList(
        @PathVariable(name = "userId") Long userId) {
        List<GetUserListResponseDto> list = jpaUserListQueryRepository.getFollowerUserList(userId);
        return Response.ok(list);
    }
}
