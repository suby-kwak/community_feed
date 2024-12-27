package org.fastcampus.acceptance.utils;

import static org.fastcampus.acceptance.steps.UserAcceptanceSteps.createUser;
import static org.fastcampus.acceptance.steps.UserAcceptanceSteps.followUser;


import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.application.dto.RelationUserRequestDto;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    public void loadData() {
        CreateUserRequestDto dto = new CreateUserRequestDto("test user", "");
        createUser(dto);
        createUser(dto);
        createUser(dto);

        followUser(new RelationUserRequestDto(1L, 2L));
        followUser(new RelationUserRequestDto(1L, 3L));
    }


}
