package edu.hw5.task6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class SubsequencesTest {
    @ParameterizedTest
    @DisplayName("Тест для строк являющихся подпоследовательностями")
    @CsvSource({
        "abc, 12a45b684c64",
        "abc, achfdbaabgabcaabg"
    })
    void checkSubsequenceTrueCase(String subsequence, String fullLine) {
        boolean actual = Subsequences.check(subsequence, fullLine);

        assertTrue(actual);
    }

    @ParameterizedTest
    @DisplayName("Тест для строк не являющихся подпоследовательностями")
    @CsvSource({
        "abc, 12a45c684b64"
    })
    void checkSubsequenceFalseCase(String subsequence, String fullLine) {
        boolean actual = Subsequences.check(subsequence, fullLine);

        assertFalse(actual);
    }

    @Test
    @DisplayName("Тест для некорректных входных данных")
    void checkSubsequenceBadInputCase() {
        assertThatExceptionOfType(NullPointerException.class)
            .isThrownBy(() -> Subsequences.check(null, null));

        assertThatExceptionOfType(NullPointerException.class)
            .isThrownBy(() -> Subsequences.check(null, "aaa"));

        assertThatExceptionOfType(NullPointerException.class)
            .isThrownBy(() -> Subsequences.check("aaa", null));
    }
}
