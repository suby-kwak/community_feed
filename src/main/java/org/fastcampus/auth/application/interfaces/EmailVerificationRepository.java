package org.fastcampus.auth.application.interfaces;

import org.fastcampus.auth.domain.Email;

public interface EmailVerificationRepository {

    void createEmailVerification(Email email, String token);
}
