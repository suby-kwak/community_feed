package org.fastcampus.post.domain.content;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

public class DatetimeInfoTest {

    @Test
    void givenCreatedWhenUpdateThenEditedTrueAndTimeIsUpdated() {
        // given
        DateTimeInfo info = new DateTimeInfo();
        LocalDateTime dateTime = info.getDateTime();

        // when
        info.updateEditDateTime();

        // then
        assertNotEquals(dateTime, info.getDateTime());
        assertTrue(info.isEdited());
    }
}
