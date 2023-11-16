package edu.hw5.task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class StrongPasswordTest {
    @ParameterizedTest
    @DisplayName("Тест для паролей соответствующих правилу")
    @CsvSource({
        "~aaaaaaaa",
        "a!aaaaaa",
        "aa@aaaaa",
        "aaa#aaaa",
        "aaaa$aaa",
        "aaaaa%aa",
        "aaaaaa^a",
        "aaaaaaa&a",
        "aaaaaaaa*a",
        "aaaaaaaaa|",
        "P@ssword~123",
        "Secr#tP@ssw*rd",
        "1A!bC^dEf|",
        "MyP@ss~word",
        "H@shTag&123",
        "5*6$7^8!9|",
        "Pa$$w0rd~XYZ",
        "Welc*me!2023"
    })
    void validationTrueCase(String input) {
        boolean actual = StrongPassword.isValid(input);

        assertTrue(actual);
    }

    @ParameterizedTest
    @DisplayName("Тест для паролей несоответствующих правилу")
    @CsvSource({
        "aaa4564aada6sf",
        "dfsg4564hdfsh",
        "А123ВЕ7777"
    })
    void validationFalseCase(String input) {
        boolean actual = StrongPassword.isValid(input);

        assertFalse(actual);
    }

    @Test
    @DisplayName("Тест для некорректных входных данных")
    void validationBadInputCase() {
        assertThatExceptionOfType(NullPointerException.class)
            .isThrownBy(() -> StrongPassword.isValid(null));
    }
}
