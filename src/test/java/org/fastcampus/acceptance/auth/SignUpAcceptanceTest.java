package org.fastcampus.acceptance.auth;

import static org.fastcampus.acceptance.steps.SignUpAcceptanceSteps.registerUser;
import static org.fastcampus.acceptance.steps.SignUpAcceptanceSteps.requestSendEmail;
import static org.fastcampus.acceptance.steps.SignUpAcceptanceSteps.requestVerifyEmail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.fastcampus.acceptance.utils.AcceptanceTestTemplate;
import org.fastcampus.auth.application.dto.CreateUserAuthRequestDto;
import org.fastcampus.auth.application.dto.SendEmailRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SignUpAcceptanceTest extends AcceptanceTestTemplate {

    private final String email = "email@email.com";

    @BeforeEach
    void setup() {
        this.cleanUp();
    }

    @Test
    void givenEmailWhenSendEmailThenVerificationTokenSaved() {
        // given
        SendEmailRequestDto dto = new SendEmailRequestDto(email);

        // when
        Integer code = requestSendEmail(dto);

        // then
        String token = this.getEmailToken(email);
        assertNotNull(token);
        assertEquals(0, code);
    }

    @Test
    void givenInValidEmailWhenEmailSendThenVerificationTokenNotSaved() {
        // given
        SendEmailRequestDto dto = new SendEmailRequestDto("abcd");

        // when
        Integer code = requestSendEmail(dto);

        // then
        assertEquals(400, code);
    }

    @Test
    void givenSendEmail_whenVerifyEmail_thenEmailVerified() {
        // given
        requestSendEmail(new SendEmailRequestDto(email));

        // when
        String token = getEmailToken(email);
        Integer code = requestVerifyEmail(email, token);

        // then
        boolean isEmailVerified = isEmailVerified(email);
        assertEquals(0, code);
        assertTrue(isEmailVerified);
    }

    @Test
    void givenSendEmail_whenVerifyEmailWithWrongToken_thenEmailNotVerified() {
        // given
        requestSendEmail(new SendEmailRequestDto(email));

        // when
        Integer code = requestVerifyEmail(email, "wrong token");

        // then
        boolean isEmailVerified = isEmailVerified(email);
        assertEquals(400, code);
        assertFalse(isEmailVerified);
    }

    @Test
    void givenSendEmailVerified_whenVerifyAgain_thenThrowError() {
        // given
        requestSendEmail(new SendEmailRequestDto(email));
        String token = getEmailToken(email);
        requestVerifyEmail(email, token);

        // when
        Integer code = requestVerifyEmail(email, token);

        // then
        assertEquals(400, code);
    }

    @Test
    void givenSendEmail_whenVerifyEmailWithWrongEmail_thenThrowError() {
        // given
        requestSendEmail(new SendEmailRequestDto(email));

        // when
        Integer code = requestVerifyEmail("wrong email", "token");

        // then
        assertEquals(400, code);
    }

    @Test
    void givenVerifiedEmail_whenRegister_thenUserRegistered() {
        // given
        requestSendEmail(new SendEmailRequestDto(email));
        String token = getEmailToken(email);
        requestVerifyEmail(email, token);

        // when
        CreateUserAuthRequestDto dto = new CreateUserAuthRequestDto(email, "password", "USER", "name", "profileImageUrl");
        Integer code = registerUser(dto);

        // then
        assertEquals(0, code);
        Long userId = getUserId(email);
        assertEquals(1L, userId);
    }

    @Test
    void givenUnverifiedEmail_whenRegister_thenThrowError() {
        // given
        requestSendEmail(new SendEmailRequestDto(email));

        // when
        CreateUserAuthRequestDto dto = new CreateUserAuthRequestDto(email, "password", "USER", "name", "profileImageUrl");
        Integer code = registerUser(dto);

        // then
        assertEquals(400, code);
    }
}
