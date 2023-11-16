package edu.hw5.task4;

import java.util.Objects;
import java.util.regex.Pattern;

public final class StrongPassword {
    private static final Pattern STRONG_PASSWORD_PATTERN = Pattern.compile("[~!@#$%^&*|]");

    private StrongPassword() {
    }

    public static boolean isValid(String password) {
        Objects.requireNonNull(password, "'password' is expected to be non-null");
        return STRONG_PASSWORD_PATTERN.matcher(password).find();
    }
}
