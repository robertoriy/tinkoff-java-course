package edu.hw11.task2;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

final class ArithmeticsTest {
    @ParameterizedTest
    @DisplayName("Подмена операции сложения на умножение")
    @CsvSource({
        "1, 2, 2",
        "5, 5, 25",
        "3, 4, 12",
        "10, -2, -20"
    })
    void changeOperation(int arg1, int arg2, int expected) {
        ByteBuddyAgent.install();
        new ByteBuddy()
            .redefine(Arithmetics.class)
            .method(ElementMatchers.named("sum")).intercept(MethodDelegation.to(ArithmeticDelegator.class))
            .make()
            .load(ClassLoader.getSystemClassLoader(), ClassReloadingStrategy.fromInstalledAgent())
            .getLoaded();

        int actual = Arithmetics.sum(arg1, arg2);

        assertThat(actual).isEqualTo(expected);
    }

    private static final class ArithmeticDelegator {
        public static int multiply(int a, int b) {
            return a * b;
        }
    }
}
