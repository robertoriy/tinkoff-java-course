package edu.hw5.task5;

import java.util.Objects;

public final class CarLicensePlate {
    private static final String LICENSE_PLATE_PATTERN = "[ABEKMHOPCTYX]\\d{3}[ABEKMHOPCTYX]{2}\\d{2,3}";

    private CarLicensePlate() {
    }

    public static boolean isValid(String licensePlate) {
        Objects.requireNonNull(licensePlate, "'licensePlate' is expected to be non-null");
        return licensePlate.matches(LICENSE_PLATE_PATTERN);
    }
}
