package edu.hw4;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

final class AnimalUtilsTest {
    @ParameterizedTest
    @DisplayName("Тест Задача1. Сортировка животных по росту в возрастающем порядке")
    @MethodSource("provideAnimalsSortedByHeight")
    void shouldSortByAscendingHeight(List<Animal> animals, List<Animal> expected) {
        List<Animal> actual = AnimalUtils.sortByAscendingHeight(animals);

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideAnimalsSortedByHeight() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Animal("cat1", Animal.Type.CAT, Animal.Sex.M, 0, 1, 0, true),
                    new Animal("cat2", Animal.Type.CAT, Animal.Sex.M, 0, 10, 0, true),
                    new Animal("dog1", Animal.Type.DOG, Animal.Sex.M, 0, 5, 0, true),
                    new Animal("fish1", Animal.Type.FISH, Animal.Sex.M, 0, 6, 0, false),
                    new Animal("bird1", Animal.Type.BIRD, Animal.Sex.M, 0, 2, 0, true)
                ),
                List.of(
                    new Animal("cat1", Animal.Type.CAT, Animal.Sex.M, 0, 1, 0, true),
                    new Animal("bird1", Animal.Type.BIRD, Animal.Sex.M, 0, 2, 0, true),
                    new Animal("dog1", Animal.Type.DOG, Animal.Sex.M, 0, 5, 0, true),
                    new Animal("fish1", Animal.Type.FISH, Animal.Sex.M, 0, 6, 0, false),
                    new Animal("cat2", Animal.Type.CAT, Animal.Sex.M, 0, 10, 0, true)

                )
            )
        );
    }

    @ParameterizedTest
    @DisplayName("Тест Задача2. Сортировка животных по весу в убывающем порядке, результат - первые K")
    @MethodSource("provideAnimalsSortedByWeightAndLimited")
    void shouldSortByDescendingWeightAndLimit(List<Animal> animals, int limiter, List<Animal> expected) {
        List<Animal> actual = AnimalUtils.sortByDescendingWeightAndLimit(animals, limiter);

        assertThat(actual).hasSize(limiter).isEqualTo(expected);
    }

    private static Stream<Arguments> provideAnimalsSortedByWeightAndLimited() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Animal("cat1", Animal.Type.CAT, Animal.Sex.M, 0, 0, 1, true),
                    new Animal("cat2", Animal.Type.CAT, Animal.Sex.M, 0, 0, 10, true),
                    new Animal("dog1", Animal.Type.DOG, Animal.Sex.M, 0, 0, 5, true),
                    new Animal("dog2", Animal.Type.DOG, Animal.Sex.M, 0, 0, 13, true),
                    new Animal("dog3", Animal.Type.DOG, Animal.Sex.M, 0, 0, 7, true)
                ),
                3,
                List.of(
                    new Animal("dog2", Animal.Type.DOG, Animal.Sex.M, 0, 0, 13, true),
                    new Animal("cat2", Animal.Type.CAT, Animal.Sex.M, 0, 0, 10, true),
                    new Animal("dog3", Animal.Type.DOG, Animal.Sex.M, 0, 0, 7, true)
                )
            )
        );
    }

    @ParameterizedTest
    @DisplayName("Тест Задача3. Подсчёт животных каждого типа")
    @MethodSource("provideMapAnimalTypeAndCounter")
    void shouldCountByType(List<Animal> animals, Map<Animal.Type, Integer> expected) {
        Map<Animal.Type, Integer> actual = AnimalUtils.countByType(animals);

        assertThat(actual).containsExactlyInAnyOrderEntriesOf(expected);
    }

    private static Stream<Arguments> provideMapAnimalTypeAndCounter() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Animal("cat1", Animal.Type.CAT, Animal.Sex.M, 0, 0, 0, true),
                    new Animal("cat2", Animal.Type.CAT, Animal.Sex.F, 0, 0, 0, true),
                    new Animal("cat3", Animal.Type.CAT, Animal.Sex.F, 0, 0, 0, true),
                    new Animal("cat4", Animal.Type.CAT, Animal.Sex.M, 0, 0, 0, true),
                    new Animal("dog1", Animal.Type.DOG, Animal.Sex.M, 0, 0, 0, true),
                    new Animal("dog2", Animal.Type.DOG, Animal.Sex.M, 0, 0, 0, true),
                    new Animal("bird1", Animal.Type.BIRD, Animal.Sex.M, 0, 0, 0, true),
                    new Animal("bird2", Animal.Type.BIRD, Animal.Sex.M, 0, 0, 0, true),
                    new Animal("bird3", Animal.Type.BIRD, Animal.Sex.M, 0, 0, 0, true),
                    new Animal("spider1", Animal.Type.SPIDER, Animal.Sex.M, 0, 0, 0, true),
                    new Animal("spider2", Animal.Type.SPIDER, Animal.Sex.M, 0, 0, 0, true)
                ),
                Map.of(
                    Animal.Type.CAT, 4,
                    Animal.Type.DOG, 2,
                    Animal.Type.BIRD, 3,
                    Animal.Type.SPIDER, 2
                )
            )
        );
    }

    @ParameterizedTest
    @DisplayName("Тест Задача4. Животное с самым длинным именем")
    @MethodSource("provideAnimalWithLongestName")
    void shouldReturnAnimalWithLongestName(List<Animal> animals, Animal expected) {
        Animal actual = AnimalUtils.longestName(animals);

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideAnimalWithLongestName() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Animal("cat", Animal.Type.CAT, Animal.Sex.M, 0, 0, 0, true),
                    new Animal("Cat Cat Cat", Animal.Type.CAT, Animal.Sex.F, 0, 0, 0, true),
                    new Animal("dog1", Animal.Type.DOG, Animal.Sex.M, 0, 5, 0, true),
                    new Animal("fish1", Animal.Type.FISH, Animal.Sex.M, 0, 0, 0, false),
                    new Animal("bird1", Animal.Type.BIRD, Animal.Sex.M, 0, 0, 0, true)
                ),
                new Animal("Cat Cat Cat", Animal.Type.CAT, Animal.Sex.F, 0, 0, 0, true)
            )
        );
    }

    @ParameterizedTest
    @DisplayName("Тест Задача5. Каких животных больше: самцов или самок")
    @MethodSource("provideAnimalWithDifferentSex")
    void shouldReturnSexThatOccursMoreOften(List<Animal> animals, Animal.Sex expected) {
        Animal.Sex actual = AnimalUtils.mostPopularSex(animals);

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideAnimalWithDifferentSex() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Animal("cat1", Animal.Type.CAT, Animal.Sex.M, 0, 0, 0, true),
                    new Animal("cat2", Animal.Type.CAT, Animal.Sex.F, 0, 0, 0, true),
                    new Animal("cat3", Animal.Type.CAT, Animal.Sex.F, 0, 0, 0, true),
                    new Animal("cat4", Animal.Type.CAT, Animal.Sex.M, 0, 0, 0, true),
                    new Animal("dog1", Animal.Type.DOG, Animal.Sex.F, 0, 0, 0, true),
                    new Animal("dog2", Animal.Type.DOG, Animal.Sex.F, 0, 0, 0, true),
                    new Animal("bird1", Animal.Type.BIRD, Animal.Sex.M, 0, 0, 0, true),
                    new Animal("bird2", Animal.Type.BIRD, Animal.Sex.F, 0, 0, 0, true),
                    new Animal("bird3", Animal.Type.BIRD, Animal.Sex.F, 0, 0, 0, true),
                    new Animal("spider1", Animal.Type.SPIDER, Animal.Sex.M, 0, 0, 0, true),
                    new Animal("spider2", Animal.Type.SPIDER, Animal.Sex.F, 0, 0, 0, true)
                ),
                Animal.Sex.F
            ),
            Arguments.of(
                List.of(
                    new Animal("cat1", Animal.Type.CAT, Animal.Sex.M, 0, 0, 0, true),
                    new Animal("cat2", Animal.Type.CAT, Animal.Sex.F, 0, 0, 0, true),
                    new Animal("cat3", Animal.Type.CAT, Animal.Sex.F, 0, 0, 0, true),
                    new Animal("cat4", Animal.Type.CAT, Animal.Sex.M, 0, 0, 0, true),
                    new Animal("dog1", Animal.Type.DOG, Animal.Sex.M, 0, 0, 0, true),
                    new Animal("dog2", Animal.Type.DOG, Animal.Sex.M, 0, 0, 0, true),
                    new Animal("bird1", Animal.Type.BIRD, Animal.Sex.M, 0, 0, 0, true),
                    new Animal("bird2", Animal.Type.BIRD, Animal.Sex.M, 0, 0, 0, true),
                    new Animal("bird3", Animal.Type.BIRD, Animal.Sex.M, 0, 0, 0, true),
                    new Animal("spider1", Animal.Type.SPIDER, Animal.Sex.M, 0, 0, 0, true),
                    new Animal("spider2", Animal.Type.SPIDER, Animal.Sex.M, 0, 0, 0, true)
                ),
                Animal.Sex.M
            )
        );
    }

    @ParameterizedTest
    @DisplayName("Тест Задача6. Поиск самого тяжелого животного каждого типа")
    @MethodSource("provideHeaviestAnimalOfEachType")
    void shouldReturnHeaviestAnimalOfEachType(List<Animal> animals, Map<Animal.Type, Animal> expected) {
        Map<Animal.Type, Animal> actual = AnimalUtils.heaviestByType(animals);

        assertThat(actual).containsExactlyInAnyOrderEntriesOf(expected);
    }

    private static Stream<Arguments> provideHeaviestAnimalOfEachType() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Animal("cat1", Animal.Type.CAT, Animal.Sex.M, 0, 0, 10, true),
                    new Animal("cat2", Animal.Type.CAT, Animal.Sex.F, 0, 0, 20, true),
                    new Animal("cat3", Animal.Type.CAT, Animal.Sex.F, 0, 0, 5, true),
                    new Animal("cat4", Animal.Type.CAT, Animal.Sex.M, 0, 0, 22, true),
                    new Animal("dog1", Animal.Type.DOG, Animal.Sex.M, 0, 0, 15, true),
                    new Animal("dog2", Animal.Type.DOG, Animal.Sex.M, 0, 0, 30, true),
                    new Animal("bird1", Animal.Type.BIRD, Animal.Sex.M, 0, 0, 5, true),
                    new Animal("bird2", Animal.Type.BIRD, Animal.Sex.M, 0, 0, 7, true),
                    new Animal("bird3", Animal.Type.BIRD, Animal.Sex.M, 0, 0, 2, true),
                    new Animal("spider1", Animal.Type.SPIDER, Animal.Sex.M, 0, 0, 1, true),
                    new Animal("spider2", Animal.Type.SPIDER, Animal.Sex.M, 0, 0, 2, true)
                ),
                Map.of(
                    Animal.Type.CAT, new Animal("cat4", Animal.Type.CAT, Animal.Sex.M, 0, 0, 22, true),
                    Animal.Type.DOG, new Animal("dog2", Animal.Type.DOG, Animal.Sex.M, 0, 0, 30, true),
                    Animal.Type.BIRD, new Animal("bird2", Animal.Type.BIRD, Animal.Sex.M, 0, 0, 7, true),
                    Animal.Type.SPIDER, new Animal("spider2", Animal.Type.SPIDER, Animal.Sex.M, 0, 0, 2, true)
                )
            )
        );
    }

    @ParameterizedTest
    @DisplayName("Тест Задача7. К-тое самое старое животное")
    @MethodSource("provideKOldestAnimal")
    void shouldReturnKOldestAnimal(List<Animal> animals, long index, Animal expected) {
        Animal actual = AnimalUtils.kOldAnimal(animals, index);

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideKOldestAnimal() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Animal("cat", Animal.Type.CAT, Animal.Sex.M, 2, 0, 0, true),
                    new Animal("cat2", Animal.Type.CAT, Animal.Sex.F, 4, 0, 0, true),
                    new Animal("dog1", Animal.Type.DOG, Animal.Sex.M, 9, 5, 0, true),
                    new Animal("fish1", Animal.Type.FISH, Animal.Sex.M, 3, 0, 0, false),
                    new Animal("bird1", Animal.Type.BIRD, Animal.Sex.M, 3, 0, 0, true),
                    new Animal("spider1", Animal.Type.SPIDER, Animal.Sex.M, 25, 0, 1, true)
                ),
                3L,
                new Animal("cat2", Animal.Type.CAT, Animal.Sex.F, 4, 0, 0, true)
            )
        );
    }

    @ParameterizedTest
    @DisplayName("Тест Задача8. Самое тяжелое животное, которое ниже K см")
    @MethodSource("provideHeaviestAnimalAndBelowK")
    void shouldReturnHeaviestAnimalAndBelowK(List<Animal> animals, int height, Optional<Animal> expected) {
        Optional<Animal> actual = AnimalUtils.heaviestAndBelow(animals, height);

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideHeaviestAnimalAndBelowK() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Animal("cat1", Animal.Type.CAT, Animal.Sex.M, 0, 20, 10, true),
                    new Animal("cat2", Animal.Type.CAT, Animal.Sex.F, 0, 30, 20, true),
                    new Animal("cat3", Animal.Type.CAT, Animal.Sex.F, 0, 15, 5, true),
                    new Animal("cat4", Animal.Type.CAT, Animal.Sex.M, 0, 30, 22, true),
                    new Animal("dog1", Animal.Type.DOG, Animal.Sex.M, 0, 20, 15, true),
                    new Animal("dog2", Animal.Type.DOG, Animal.Sex.M, 0, 40, 30, true),
                    new Animal("bird1", Animal.Type.BIRD, Animal.Sex.M, 0, 15, 5, true),
                    new Animal("bird2", Animal.Type.BIRD, Animal.Sex.M, 0, 20, 7, true),
                    new Animal("bird3", Animal.Type.BIRD, Animal.Sex.M, 0, 8, 2, true),
                    new Animal("spider1", Animal.Type.SPIDER, Animal.Sex.M, 0, 5, 1, true),
                    new Animal("spider2", Animal.Type.SPIDER, Animal.Sex.M, 0, 8, 2, true)
                ),
                25,
                Optional.of(new Animal("dog1", Animal.Type.DOG, Animal.Sex.M, 0, 20, 15, true))
            ),
            Arguments.of(
                List.of(
                    new Animal("cat1", Animal.Type.CAT, Animal.Sex.M, 0, 20, 10, true),
                    new Animal("cat2", Animal.Type.CAT, Animal.Sex.F, 0, 30, 20, true),
                    new Animal("cat3", Animal.Type.CAT, Animal.Sex.F, 0, 15, 5, true),
                    new Animal("cat4", Animal.Type.CAT, Animal.Sex.M, 0, 30, 22, true),
                    new Animal("dog1", Animal.Type.DOG, Animal.Sex.M, 0, 20, 15, true),
                    new Animal("dog2", Animal.Type.DOG, Animal.Sex.M, 0, 40, 30, true),
                    new Animal("bird1", Animal.Type.BIRD, Animal.Sex.M, 0, 15, 5, true),
                    new Animal("bird2", Animal.Type.BIRD, Animal.Sex.M, 0, 20, 7, true),
                    new Animal("bird3", Animal.Type.BIRD, Animal.Sex.M, 0, 8, 2, true),
                    new Animal("spider1", Animal.Type.SPIDER, Animal.Sex.M, 0, 5, 1, true),
                    new Animal("spider2", Animal.Type.SPIDER, Animal.Sex.M, 0, 8, 2, true)
                ),
                4,
                Optional.empty()
            )
        );
    }

    @ParameterizedTest
    @DisplayName("Тест Задача9. Подсчёт всех лап животных")
    @MethodSource("provideTotalNumberOfPaws")
    void shouldReturnTotalNumberOfPaws(List<Animal> animals, int expected) {
        int actual = AnimalUtils.countPaws(animals);

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideTotalNumberOfPaws() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Animal("cat1", Animal.Type.CAT, Animal.Sex.M, 0, 0, 0, true),
                    new Animal("cat2", Animal.Type.CAT, Animal.Sex.F, 0, 0, 0, true),
                    new Animal("cat3", Animal.Type.CAT, Animal.Sex.F, 0, 0, 0, true),
                    new Animal("cat4", Animal.Type.CAT, Animal.Sex.M, 0, 0, 0, true),
                    new Animal("dog1", Animal.Type.DOG, Animal.Sex.M, 0, 0, 0, true),
                    new Animal("dog2", Animal.Type.DOG, Animal.Sex.M, 0, 0, 0, true),
                    new Animal("bird1", Animal.Type.BIRD, Animal.Sex.M, 0, 0, 0, true),
                    new Animal("bird2", Animal.Type.BIRD, Animal.Sex.M, 0, 0, 0, true),
                    new Animal("bird3", Animal.Type.BIRD, Animal.Sex.M, 0, 0, 0, true),
                    new Animal("spider1", Animal.Type.SPIDER, Animal.Sex.M, 0, 0, 0, true),
                    new Animal("spider2", Animal.Type.SPIDER, Animal.Sex.M, 0, 0, 0, true)
                ),
                (4 * 4) + (4 * 2) + (2 * 3) + (8 * 2) // cats + dogs + birds + spiders
            )
        );
    }

    @ParameterizedTest
    @DisplayName("Тест Задача10. Фильтрация: возраст животного не равен количеству его лап")
    @MethodSource("provideAnimalsWhereAgeNotEqualPaws")
    void shouldContainAnimalsWhereAgeNotEqualPaws(List<Animal> animals, List<Animal> expected) {
        List<Animal> actual = AnimalUtils.ageNotEqualPaws(animals);

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideAnimalsWhereAgeNotEqualPaws() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Animal("cat1", Animal.Type.CAT, Animal.Sex.M, 4, 0, 0, true),
                    new Animal("cat2", Animal.Type.CAT, Animal.Sex.M, 3, 0, 0, true),
                    new Animal("dog1", Animal.Type.DOG, Animal.Sex.M, 4, 0, 0, true),
                    new Animal("dog2", Animal.Type.DOG, Animal.Sex.M, 3, 0, 0, true),
                    new Animal("fish1", Animal.Type.FISH, Animal.Sex.M, 1, 0, 0, false),
                    new Animal("bird1", Animal.Type.BIRD, Animal.Sex.M, 2, 0, 0, true)
                ),
                List.of(
                    new Animal("cat2", Animal.Type.CAT, Animal.Sex.M, 3, 0, 0, true),
                    new Animal("dog2", Animal.Type.DOG, Animal.Sex.M, 3, 0, 0, true),
                    new Animal("fish1", Animal.Type.FISH, Animal.Sex.M, 1, 0, 0, false)
                )
            ),
            Arguments.of(
                List.of(
                    new Animal("cat1", Animal.Type.CAT, Animal.Sex.M, 4, 0, 0, true),
                    new Animal("dog1", Animal.Type.DOG, Animal.Sex.M, 4, 0, 0, true),
                    new Animal("fish1", Animal.Type.FISH, Animal.Sex.M, 0, 0, 0, false),
                    new Animal("bird1", Animal.Type.BIRD, Animal.Sex.M, 2, 0, 0, true),
                    new Animal("spider1", Animal.Type.SPIDER, Animal.Sex.M, 8, 0, 0, true)
                ),
                Collections.emptyList()
            )
        );
    }

    @ParameterizedTest
    @DisplayName("Тест Задача11. Фильтрация: может укусить и выше K")
    @MethodSource("provideAnimalsThatCanBiteAndAboveK")
    void shouldContainAnimalsThatCanBiteAndAboveK(List<Animal> animals, int height, List<Animal> expected) {
        List<Animal> actual = AnimalUtils.canBiteAndAbove(animals, height);

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideAnimalsThatCanBiteAndAboveK() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Animal("cat1", Animal.Type.CAT, Animal.Sex.M, 0, 22, 0, true),
                    new Animal("cat2", Animal.Type.CAT, Animal.Sex.F, 0, 30, 0, false),
                    new Animal("cat3", Animal.Type.CAT, Animal.Sex.F, 0, 20, 0, true),
                    new Animal("cat4", Animal.Type.CAT, Animal.Sex.M, 0, 15, 0, false),
                    new Animal("dog1", Animal.Type.DOG, Animal.Sex.M, 0, 40, 0, true),
                    new Animal("dog2", Animal.Type.DOG, Animal.Sex.M, 0, 35, 0, false),
                    new Animal("bird1", Animal.Type.BIRD, Animal.Sex.M, 0, 15, 0, true),
                    new Animal("bird2", Animal.Type.BIRD, Animal.Sex.M, 0, 21, 0, false),
                    new Animal("bird3", Animal.Type.BIRD, Animal.Sex.M, 0, 25, 0, true),
                    new Animal("spider1", Animal.Type.SPIDER, Animal.Sex.M, 0, 10, 0, false),
                    new Animal("spider2", Animal.Type.SPIDER, Animal.Sex.M, 0, 33, 0, true)
                ),
                21,
                List.of(
                    new Animal("cat1", Animal.Type.CAT, Animal.Sex.M, 0, 22, 0, true),
                    new Animal("dog1", Animal.Type.DOG, Animal.Sex.M, 0, 40, 0, true),
                    new Animal("bird3", Animal.Type.BIRD, Animal.Sex.M, 0, 25, 0, true),
                    new Animal("spider2", Animal.Type.SPIDER, Animal.Sex.M, 0, 33, 0, true)
                )
            ),
            Arguments.of(
                List.of(
                    new Animal("cat1", Animal.Type.CAT, Animal.Sex.M, 0, 22, 0, true),
                    new Animal("cat2", Animal.Type.CAT, Animal.Sex.F, 0, 30, 0, false),
                    new Animal("cat3", Animal.Type.CAT, Animal.Sex.F, 0, 20, 0, true),
                    new Animal("cat4", Animal.Type.CAT, Animal.Sex.M, 0, 15, 0, false),
                    new Animal("dog1", Animal.Type.DOG, Animal.Sex.M, 0, 40, 0, true)
                ),
                100,
                Collections.emptyList()
            )
        );
    }

    @ParameterizedTest
    @DisplayName("Тест Задача12. Количество животных у которых вес больше роста")
    @MethodSource("provideAnimalsWithWeightGreaterThanHeight")
    void shouldCountAnimalsWithWeightGreaterThanHeight(List<Animal> animals, long expected) {
        long actual = AnimalUtils.countWeightGreaterThanHeight(animals);

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideAnimalsWithWeightGreaterThanHeight() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Animal("cat1", Animal.Type.CAT, Animal.Sex.M, 0, 20, 30, true),
                    new Animal("cat2", Animal.Type.CAT, Animal.Sex.F, 0, 25, 25, true),
                    new Animal("cat3", Animal.Type.CAT, Animal.Sex.F, 0, 8, 10, true),
                    new Animal("cat4", Animal.Type.CAT, Animal.Sex.M, 0, 15, 14, true),
                    new Animal("dog1", Animal.Type.DOG, Animal.Sex.M, 0, 40, 20, true),
                    new Animal("dog2", Animal.Type.DOG, Animal.Sex.M, 0, 50, 25, true),
                    new Animal("bird1", Animal.Type.BIRD, Animal.Sex.M, 0, 15, 20, true),
                    new Animal("bird2", Animal.Type.BIRD, Animal.Sex.M, 0, 10, 10, true),
                    new Animal("bird3", Animal.Type.BIRD, Animal.Sex.M, 0, 12, 14, true),
                    new Animal("spider1", Animal.Type.SPIDER, Animal.Sex.M, 0, 5, 10, true),
                    new Animal("spider2", Animal.Type.SPIDER, Animal.Sex.M, 0, 10, 2, true)
                ),
                5L
            )
        );
    }

    @ParameterizedTest
    @DisplayName("Тест Задача13. Фильтрация: имя состоит из более чем двух слов")
    @MethodSource("provideAnimalsWithAtLeastThreeWordName")
    void shouldContainAnimalsWithAtLeastThreeWordName(List<Animal> animals, List<Animal> expected) {
        List<Animal> actual = AnimalUtils.nameContainsMoreThanTwoWords(animals);

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideAnimalsWithAtLeastThreeWordName() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Animal("cat1 cat1 cat1 cat1", Animal.Type.CAT, Animal.Sex.M, 0, 0, 0, true),
                    new Animal("cat2 cat2", Animal.Type.CAT, Animal.Sex.M, 0, 0, 0, true),
                    new Animal("dog1 dog1 dog1", Animal.Type.DOG, Animal.Sex.M, 0, 0, 0, true),
                    new Animal("   fish1 fish1fish1  ", Animal.Type.FISH, Animal.Sex.M, 0, 0, 0, false),
                    new Animal("  bird1 cf  ", Animal.Type.BIRD, Animal.Sex.M, 0, 0, 0, true)
                ),
                List.of(
                    new Animal("cat1 cat1 cat1 cat1", Animal.Type.CAT, Animal.Sex.M, 0, 0, 0, true),
                    new Animal("dog1 dog1 dog1", Animal.Type.DOG, Animal.Sex.M, 0, 0, 0, true)
                )
            )
        );
    }

    @ParameterizedTest
    @DisplayName("Тест Задача14. Есть ли собака ростом более, чем K")
    @MethodSource("provideAnswerHasDogAndAboveK")
    void shouldBeTrueIfHasDogAndAboveK(List<Animal> animals, int height, boolean expected) {
        boolean actual = AnimalUtils.hasDogAbove(animals, height);

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideAnswerHasDogAndAboveK() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Animal("cat1", Animal.Type.CAT, Animal.Sex.M, 0, 20, 0, true),
                    new Animal("cat2", Animal.Type.CAT, Animal.Sex.F, 0, 25, 0, true),
                    new Animal("dog1", Animal.Type.DOG, Animal.Sex.M, 0, 40, 0, true),
                    new Animal("dog2", Animal.Type.DOG, Animal.Sex.M, 0, 51, 0, true),
                    new Animal("bird1", Animal.Type.BIRD, Animal.Sex.M, 0, 15, 0, true),
                    new Animal("spider1", Animal.Type.SPIDER, Animal.Sex.M, 0, 5, 0, true),
                    new Animal("spider2", Animal.Type.SPIDER, Animal.Sex.M, 0, 10, 0, true)
                ),
                50,
                true
            ), Arguments.of(
                List.of(
                    new Animal("cat1", Animal.Type.CAT, Animal.Sex.M, 0, 20, 0, true),
                    new Animal("cat2", Animal.Type.CAT, Animal.Sex.F, 0, 25, 0, true),
                    new Animal("dog1", Animal.Type.DOG, Animal.Sex.M, 0, 40, 0, true),
                    new Animal("dog2", Animal.Type.DOG, Animal.Sex.M, 0, 51, 0, true),
                    new Animal("bird1", Animal.Type.BIRD, Animal.Sex.M, 0, 15, 0, true),
                    new Animal("spider1", Animal.Type.SPIDER, Animal.Sex.M, 0, 5, 0, true),
                    new Animal("spider2", Animal.Type.SPIDER, Animal.Sex.M, 0, 10, 0, true)
                ),
                100,
                false
            ),
            Arguments.of(
                List.of(
                    new Animal("cat1", Animal.Type.CAT, Animal.Sex.M, 0, 20, 30, true),
                    new Animal("bird1", Animal.Type.BIRD, Animal.Sex.M, 0, 15, 20, true),
                    new Animal("spider1", Animal.Type.SPIDER, Animal.Sex.M, 0, 5, 10, true)
                ),
                50,
                false
            )
        );
    }

    @ParameterizedTest
    @DisplayName("Тест Задача15. Подсчёт общего веса животных по типу, которым от X о Y лет")
    @MethodSource("provideTotalWeightByTypeWhoseAgeFromTo")
    void shouldReturnTotalWeightByTypeWhoseAgeFromTo(
        List<Animal> animals, int from, int to, Map<Animal.Type, Integer> expected
    ) {
        Map<Animal.Type, Integer> actual = AnimalUtils.totalWeightByTypeWhoseAgeFromTo(animals, from, to);

        assertThat(actual).containsExactlyInAnyOrderEntriesOf(expected);
    }

    private static Stream<Arguments> provideTotalWeightByTypeWhoseAgeFromTo() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Animal("cat1", Animal.Type.CAT, Animal.Sex.M, 1, 0, 10, true),
                    new Animal("cat2", Animal.Type.CAT, Animal.Sex.F, 6, 0, 15, true),
                    new Animal("cat3", Animal.Type.CAT, Animal.Sex.F, 9, 0, 20, true),
                    new Animal("cat4", Animal.Type.CAT, Animal.Sex.M, 15, 0, 20, true),
                    new Animal("dog1", Animal.Type.DOG, Animal.Sex.M, 7, 0, 15, true),
                    new Animal("dog2", Animal.Type.DOG, Animal.Sex.M, 10, 0, 20, true),
                    new Animal("bird1", Animal.Type.BIRD, Animal.Sex.M, 3, 0, 5, true),
                    new Animal("bird2", Animal.Type.BIRD, Animal.Sex.M, 5, 0, 7, true),
                    new Animal("bird3", Animal.Type.BIRD, Animal.Sex.M, 13, 0, 9, true),
                    new Animal("spider1", Animal.Type.SPIDER, Animal.Sex.M, 1, 0, 1, true),
                    new Animal("spider2", Animal.Type.SPIDER, Animal.Sex.M, 15, 0, 2, true)
                ),
                6,
                13,
                Map.of(
                    Animal.Type.CAT, 15 + 20,
                    Animal.Type.DOG, 15 + 20,
                    Animal.Type.BIRD, 9
                )
            )
        );
    }

    @ParameterizedTest
    @DisplayName("Тест Задача16. Сортировка: сначала по типу, потом по полу, потом по имени")
    @MethodSource("provideSortedByTypeBySexByName")
    void shouldSortByTypeBySexByName(List<Animal> animals, List<Animal> expected) {
        List<Animal> actual = AnimalUtils.sortByTypeBySexByName(animals);

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideSortedByTypeBySexByName() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Animal("spider2", Animal.Type.SPIDER, Animal.Sex.M, 0, 0, 0, true),
                    new Animal("a bird2", Animal.Type.BIRD, Animal.Sex.F, 0, 0, 0, true),
                    new Animal("bird3", Animal.Type.BIRD, Animal.Sex.M, 0, 0, 0, true),
                    new Animal("aa cat1", Animal.Type.CAT, Animal.Sex.M, 0, 0, 0, true),
                    new Animal("b bird1", Animal.Type.BIRD, Animal.Sex.F, 0, 0, 0, true),
                    new Animal("bb cat4", Animal.Type.CAT, Animal.Sex.M, 0, 0, 0, true),
                    new Animal("dog1", Animal.Type.DOG, Animal.Sex.F, 0, 0, 0, true),
                    new Animal("bb cat2", Animal.Type.CAT, Animal.Sex.F, 0, 0, 0, true),
                    new Animal("aa cat3", Animal.Type.CAT, Animal.Sex.F, 0, 0, 0, true),
                    new Animal("dog2", Animal.Type.DOG, Animal.Sex.M, 0, 0, 0, true),
                    new Animal("spider1", Animal.Type.SPIDER, Animal.Sex.F, 0, 0, 0, true)
                ),
                List.of(
                    new Animal("aa cat1", Animal.Type.CAT, Animal.Sex.M, 0, 0, 0, true),
                    new Animal("bb cat4", Animal.Type.CAT, Animal.Sex.M, 0, 0, 0, true),
                    new Animal("aa cat3", Animal.Type.CAT, Animal.Sex.F, 0, 0, 0, true),
                    new Animal("bb cat2", Animal.Type.CAT, Animal.Sex.F, 0, 0, 0, true),
                    new Animal("dog2", Animal.Type.DOG, Animal.Sex.M, 0, 0, 0, true),
                    new Animal("dog1", Animal.Type.DOG, Animal.Sex.F, 0, 0, 0, true),
                    new Animal("bird3", Animal.Type.BIRD, Animal.Sex.M, 0, 0, 0, true),
                    new Animal("a bird2", Animal.Type.BIRD, Animal.Sex.F, 0, 0, 0, true),
                    new Animal("b bird1", Animal.Type.BIRD, Animal.Sex.F, 0, 0, 0, true),
                    new Animal("spider2", Animal.Type.SPIDER, Animal.Sex.M, 0, 0, 0, true),
                    new Animal("spider1", Animal.Type.SPIDER, Animal.Sex.F, 0, 0, 0, true)
                )
            )
        );
    }

    @ParameterizedTest
    @DisplayName("Тест Задача17. Пауки кусаются чаще, чем собаки")
    @MethodSource("provideSpidersAndDogs")
    void shouldBeTrueIfSpiderBitesMoreOftenThanDogs(List<Animal> animals, boolean expected) {
        boolean actual = AnimalUtils.doSpidersBiteMoreOftenThanDogs(animals);

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideSpidersAndDogs() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Animal("cat1", Animal.Type.CAT, Animal.Sex.M, 0, 0, 10, true),
                    new Animal("dog1", Animal.Type.DOG, Animal.Sex.M, 0, 0, 15, false),
                    new Animal("dog2", Animal.Type.DOG, Animal.Sex.M, 0, 0, 15, false),
                    new Animal("dog3", Animal.Type.DOG, Animal.Sex.M, 0, 0, 15, true),
                    new Animal("dog4", Animal.Type.DOG, Animal.Sex.M, 0, 0, 15, false),
                    new Animal("dog5", Animal.Type.DOG, Animal.Sex.M, 0, 0, 15, true),
                    new Animal("bird1", Animal.Type.BIRD, Animal.Sex.M, 0, 0, 5, true),
                    new Animal("spider1", Animal.Type.SPIDER, Animal.Sex.M, 0, 0, 1, true),
                    new Animal("spider2", Animal.Type.SPIDER, Animal.Sex.M, 0, 0, 1, true),
                    new Animal("spider3", Animal.Type.SPIDER, Animal.Sex.M, 0, 0, 1, false),
                    new Animal("spider4", Animal.Type.SPIDER, Animal.Sex.M, 0, 0, 1, true),
                    new Animal("fish1", Animal.Type.FISH, Animal.Sex.M, 0, 0, 15, true)
                ),
                true
            ),
            Arguments.of(
                List.of(
                    new Animal("cat1", Animal.Type.CAT, Animal.Sex.M, 0, 0, 10, true),
                    new Animal("dog1", Animal.Type.DOG, Animal.Sex.M, 0, 0, 15, true),
                    new Animal("dog2", Animal.Type.DOG, Animal.Sex.M, 0, 0, 15, true),
                    new Animal("dog3", Animal.Type.DOG, Animal.Sex.M, 0, 0, 15, true),
                    new Animal("dog4", Animal.Type.DOG, Animal.Sex.M, 0, 0, 15, true),
                    new Animal("dog5", Animal.Type.DOG, Animal.Sex.M, 0, 0, 15, false),
                    new Animal("bird1", Animal.Type.BIRD, Animal.Sex.M, 0, 0, 5, true),
                    new Animal("spider1", Animal.Type.SPIDER, Animal.Sex.M, 0, 0, 1, true),
                    new Animal("spider2", Animal.Type.SPIDER, Animal.Sex.M, 0, 0, 1, false),
                    new Animal("spider3", Animal.Type.SPIDER, Animal.Sex.M, 0, 0, 1, true),
                    new Animal("spider4", Animal.Type.SPIDER, Animal.Sex.M, 0, 0, 1, true),
                    new Animal("fish1", Animal.Type.FISH, Animal.Sex.M, 0, 0, 15, true)
                ),
                false
            )
        );
    }

    @ParameterizedTest
    @DisplayName("Тест Задача18. Найти самую тяжелую рыбу в нескольких списках")
    @MethodSource("provideListsWithHeaviestFish")
    void shouldReturnHeaviestFish(List<List<Animal>> listsAnimals, Animal expected) {
        Animal actual = AnimalUtils.heaviestFish(listsAnimals);

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideListsWithHeaviestFish() {
        return Stream.of(
            Arguments.of(
                List.of(
                    List.of(
                        new Animal("cat1", Animal.Type.CAT, Animal.Sex.M, 0, 0, 10, true),
                        new Animal("dog1", Animal.Type.DOG, Animal.Sex.M, 0, 0, 15, true),
                        new Animal("bird1", Animal.Type.BIRD, Animal.Sex.M, 0, 0, 5, true),
                        new Animal("spider1", Animal.Type.SPIDER, Animal.Sex.M, 0, 0, 1, true),
                        new Animal("fish1", Animal.Type.FISH, Animal.Sex.M, 0, 0, 15, true)
                    ),
                    List.of(
                        new Animal("cat2", Animal.Type.CAT, Animal.Sex.M, 0, 0, 130, true),
                        new Animal("dog2", Animal.Type.DOG, Animal.Sex.M, 0, 0, 5, true),
                        new Animal("bird2", Animal.Type.BIRD, Animal.Sex.M, 0, 0, 55, true),
                        new Animal("spider2", Animal.Type.SPIDER, Animal.Sex.M, 0, 0, 61, true),
                        new Animal("fish2", Animal.Type.FISH, Animal.Sex.F, 0, 0, 27, true)
                    ), List.of(
                        new Animal("cat3", Animal.Type.CAT, Animal.Sex.M, 0, 0, 30, true),
                        new Animal("dog3", Animal.Type.DOG, Animal.Sex.M, 0, 0, 4, true),
                        new Animal("bird3", Animal.Type.BIRD, Animal.Sex.M, 0, 0, 9, true),
                        new Animal("spider3", Animal.Type.SPIDER, Animal.Sex.M, 0, 0, 6, true),
                        new Animal("fish3", Animal.Type.FISH, Animal.Sex.M, 0, 0, 21, true)
                    )
                ),
                new Animal("fish2", Animal.Type.FISH, Animal.Sex.F, 0, 0, 27, true)
            )
        );
    }

    @ParameterizedTest
    @DisplayName("Тест Задача19. Животные, в записях о которых есть ошибки: Имя - ошибки")
    @MethodSource("provideAnimalsWithErrors")
    void shouldReturnAnimalsErrors(List<Animal> animals, Map<String, Set<ValidationError>> expected) {
        Map<String, Set<ValidationError>> actual = AnimalUtils.getErrors(animals);

        assertThat(actual).containsExactlyInAnyOrderEntriesOf(expected);
    }

    private static Stream<Arguments> provideAnimalsWithErrors() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Animal("cat", null, null, -1, 2, 10, true),
                    new Animal("dog", Animal.Type.DOG, null, 0, -3, 15, true),
                    new Animal("bird", Animal.Type.BIRD, Animal.Sex.M, 0, 0, -4, true),
                    new Animal("spider", Animal.Type.SPIDER, Animal.Sex.M, 0, 2, 1, true),
                    new Animal("fish", null, Animal.Sex.M, 0, 0, 15, true)
                ),
                Map.of(
                    "cat", Set.of(
                        new ValidationError("type", ValidationError.AnimalError.NULL_VALUE),
                        new ValidationError("sex", ValidationError.AnimalError.NULL_VALUE),
                        new ValidationError("age", ValidationError.AnimalError.NEGATIVE_VALUE)
                    ),
                    "dog", Set.of(
                        new ValidationError("sex", ValidationError.AnimalError.NULL_VALUE),
                        new ValidationError("height", ValidationError.AnimalError.NEGATIVE_VALUE)
                    ),
                    "bird", Set.of(
                        new ValidationError("weight", ValidationError.AnimalError.NEGATIVE_VALUE)
                    ),
                    "fish", Set.of(
                        new ValidationError("type", ValidationError.AnimalError.NULL_VALUE)
                    ),
                    "spider", Collections.emptySet()
                )
            )
        );
    }

    @ParameterizedTest
    @DisplayName("Тест Задача20. Животные, в записях о которых есть ошибки: Имя - поля с ошибками")
    @MethodSource("provideAnimalsWithErrorsNice")
    void shouldReturnAnimalsErrorsNice(List<Animal> animals, Map<String, String> expected) {
        Map<String, String> actual = AnimalUtils.getErrorsNice(animals);

        assertThat(actual).containsExactlyInAnyOrderEntriesOf(expected);
    }

    private static Stream<Arguments> provideAnimalsWithErrorsNice() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Animal("cat", null, null, -1, 2, 10, true),
                    new Animal("dog", Animal.Type.DOG, null, 0, -3, 15, true),
                    new Animal("bird", Animal.Type.BIRD, Animal.Sex.M, 0, 0, -4, true),
                    new Animal("spider", Animal.Type.SPIDER, Animal.Sex.M, 0, 2, 1, true),
                    new Animal("fish", null, Animal.Sex.M, 0, 0, 15, true)
                ),
                Map.of(
                    "cat", "type - Must be not null, sex - Must be not null, age - Must be positive",
                    "dog", "sex - Must be not null, height - Must be positive",
                    "bird", "weight - Must be positive",
                    "spider", "",
                    "fish", "type - Must be not null"
                )
            )
        );
    }
}
