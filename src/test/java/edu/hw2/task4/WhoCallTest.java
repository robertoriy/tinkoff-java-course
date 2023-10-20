package edu.hw2.task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

final class WhoCallTest {
    @Test
    @DisplayName("Тест для получения информации о том кто вызвал метод")
    void testCallingInfo() {
        CallingInfo caller = WhoCall.callingInfo();

        assertThat(caller.className()).isEqualTo("edu.hw2.task4.WhoCallTest");
        assertThat(caller.methodName()).isEqualTo("testCallingInfo");
    }
}
