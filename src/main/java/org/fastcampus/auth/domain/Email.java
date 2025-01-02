package org.fastcampus.auth.domain;

import java.util.regex.Pattern;

public class Email {

    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    private final String emailText;

    private Email(String emailText) {
        this.emailText = emailText;
    }

    public static Email createEmail(String emailText) {
        if (emailText == null || emailText.isBlank()) {
            throw new IllegalArgumentException("email is not valid");
        }

        if (isNotValidEmailString(emailText)) {
            throw new IllegalArgumentException("email is not valid");
        }

        return new Email(emailText);
    }

    public String getEmailText() {
        return this.emailText;
    }

    private static boolean isNotValidEmailString(String emailText) {
        return !pattern.matcher(emailText).matches();
    }
}
