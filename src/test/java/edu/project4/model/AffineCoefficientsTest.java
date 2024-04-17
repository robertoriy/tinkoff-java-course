package edu.project4.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

final class AffineCoefficientsTest {
    @Test
    @DisplayName("Генерация валидных коэффициентов")
    void creatingValidCoefficients() {
        AffineCoefficients coefficients = AffineCoefficients.create();

        boolean actual = (coefficients.a() * coefficients.a() + coefficients.d() * coefficients.d() < 1)
            && (coefficients.b() * coefficients.b() + coefficients.e() * coefficients.e() < 1)
            && (coefficients.a() * coefficients.a() + coefficients.b() * coefficients.b()
            + coefficients.d() * coefficients.d() + coefficients.e() * coefficients.e()
            < 1 + (coefficients.a() * coefficients.e() - coefficients.b() * coefficients.d())
            * (coefficients.a() * coefficients.e() - coefficients.b() * coefficients.d()));

        assertThat(actual).isTrue();
    }
}
