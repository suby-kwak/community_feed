package org.fastcampus.auth;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.fastcampus.auth.domain.Password;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class PasswordTest {

    @Test
    void givenPasswordWhenMatchSamePasswordThenReturnTrue() {
        Password password = Password.createEncryptPassword("password");

        assertTrue(password.matchPassword("password"));
    }

    @Test
    void givenPasswordWhenMatchDiffPasswordThenReturnFalse() {
        Password password = Password.createEncryptPassword("password1");

        assertFalse(password.matchPassword("password"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenPasswordIsNullThenThrowError(String password) {
        assertThrows(IllegalArgumentException.class,
            () -> Password.createEncryptPassword(password));
    }
}
