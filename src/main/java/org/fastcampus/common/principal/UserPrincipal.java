package org.fastcampus.common.principal;

import lombok.Getter;
import org.fastcampus.auth.domain.UserRole;

@Getter
public class UserPrincipal {

    private Long userId;
    private UserRole userRole;

    public UserPrincipal(Long userId, String userRole) {
        this.userId = userId;
        this.userRole = UserRole.valueOf(userRole);
    }
}
