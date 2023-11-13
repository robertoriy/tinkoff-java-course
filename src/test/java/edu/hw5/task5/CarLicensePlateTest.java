package edu.hw5.task5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class CarLicensePlateTest {
    @ParameterizedTest
    @DisplayName("Тест для номеров соответствующих правилу")
    @CsvSource({
        "A123BE777",
        "O777OO177",
        "B123EH22"
    })
    void validationTrueCase(String input) {
        boolean actual = CarLicensePlate.isValid(input);

        assertTrue(actual);
    }

    @ParameterizedTest
    @DisplayName("Тест для номеров несоответствующих правилу")
    @CsvSource({
        "123АВЕ777",
        "А123ВГ77",
        "А123ВЕ7777",
        "fsdgsdfg",
        "1sad6f1as6f"
    })
    void validationFalseCase(String input) {
        boolean actual = CarLicensePlate.isValid(input);

        assertFalse(actual);
    }

    @Test
    @DisplayName("Тест для некорректных входных данных")
    void validationBadInputCase() {
        assertThatExceptionOfType(NullPointerException.class)
            .isThrownBy(() -> CarLicensePlate.isValid(null));
    }
}
