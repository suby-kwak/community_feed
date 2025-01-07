package org.fastcampus.admin.ui.query;

import java.util.List;
import org.fastcampus.admin.ui.dto.users.GetDailyRegisterUserResponseDto;

public interface UserStatsQueryRepository {
    List<GetDailyRegisterUserResponseDto> getDailyRegisterUserStats(int beforeDays);
}
