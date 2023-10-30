package edu.hw3.task7;

import java.util.TreeMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class NullEnabledComparatorTest {
    @Test
    @DisplayName("Тест хранения null в TreeMap")
    void testComparator() {
        TreeMap<String, String> tree = new TreeMap<>(new NullEnabledComparator<>());

        tree.put(null, "test");

        assertTrue(tree.containsKey(null));
    }
}
