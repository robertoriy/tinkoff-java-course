package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class AnimalUtils {
    private AnimalUtils() {
    }

    // Задача 1 - Отсортировать животных по росту от самого маленького к самому большому -> List<Animal>
    public static List<Animal> sortByAscendingHeight(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::height))
            .toList();
    }

    // Задача 2 - Отсортировать животных по весу от самого тяжелого к самому легкому, выбрать k первых -> List<Animal>
    public static List<Animal> sortByDescendingWeightAndLimit(List<Animal> animals, int limiter) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::weight, Comparator.reverseOrder()))
            .limit(limiter)
            .toList();
    }

    // Задача 3 - Сколько животных каждого вида -> Map<Animal.Type, Integer>
    public static Map<Animal.Type, Integer> countByType(List<Animal> animals) {
        return animals.stream()
            .collect(
                Collectors.groupingBy(
                    Animal::type,
                    Collectors.summingInt(animal -> 1)
                )
            );
    }

    // Задача 4 - У какого животного самое длинное имя -> Animal
    public static Animal longestName(List<Animal> animals) {
        return animals.stream()
            .max(Comparator.comparingInt(animal -> animal.name().length()))
            .orElse(null);
    }

    // Задача 5 - Каких животных больше: самцов или самок -> Sex
    public static Animal.Sex mostPopularSex(List<Animal> animals) {
        return animals.stream()
            .collect(
                Collectors.groupingBy(
                    Animal::sex, Collectors.counting()
                )
            )
            .entrySet()
            .stream()
            .max(Comparator.comparingLong(Map.Entry::getValue))
            .map(Map.Entry::getKey)
            .orElse(null);
    }

    // Задача 6 - Самое тяжелое животное каждого вида -> Map<Animal.Type, Animal>
    public static Map<Animal.Type, Animal> heaviestByType(List<Animal> animals) {
        return animals.stream()
            .collect(
                Collectors.toMap(
                    Animal::type, Function.identity(),
                    BinaryOperator.maxBy(Comparator.comparingInt(Animal::weight))
                )
            );
    }

    // Задача 7 - K-е самое старое животное -> Animal
    public static Animal kOldAnimal(List<Animal> animals, long index) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::age, Comparator.reverseOrder()))
            .skip(index - 1)
            .findFirst()
            .orElse(null);
    }

    // Задача 8 - Самое тяжелое животное среди животных ниже k см -> Optional<Animal>
    public static Optional<Animal> heaviestAndBelow(List<Animal> animals, int height) {
        return animals.stream()
            .filter(animal -> animal.height() < height)
            .max(Comparator.comparing(Animal::weight));
    }

    // Задача 9 - Сколько в сумме лап у животных в списке -> Integer
    public static Integer countPaws(List<Animal> animals) {
        return animals.stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    // Задача 10 - Список животных, возраст у которых не совпадает с количеством лап -> List<Animal>
    public static List<Animal> ageNotEqualPaws(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.age() != animal.paws())
            .toList();
    }

    // Задача 11 - Список животных, которые могут укусить (bites == true) и рост которых превышает K -> List<Animal>
    public static List<Animal> canBiteAndAbove(List<Animal> animals, int lowerHeightLimit) {
        return animals.stream()
            .filter(animal -> animal.bites() && animal.height() > lowerHeightLimit)
            .toList();
    }

    // Задача 12 - Сколько в списке животных, вес которых превышает рост -> Integer
    public static Long countWeightGreaterThanHeight(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.weight() > animal.height())
            .count();
    }

    // Задача 13 - Список животных, имена которых состоят из более чем двух слов -> List<Animal>
    public static List<Animal> nameContainsMoreThanTwoWords(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.name().strip().split(" ").length > 2)
            .toList();
    }

    // Задача 14 - Есть ли в списке собака ростом более k см -> Boolean
    public static Boolean hasDogAbove(List<Animal> animals, int height) {
        return animals.stream()
            .anyMatch(animal -> animal.type() == Animal.Type.DOG && animal.height() > height);
    }

    // Задача 15 - Найти суммарный вес животных каждого вида, которым от k до l лет -> Map<Animal.Type, Integer>
    public static Map<Animal.Type, Integer> totalWeightByTypeWhoseAgeFromTo(List<Animal> animals, int from, int to) {
        return animals.stream()
            .filter(animal -> animal.age() >= from && animal.age() <= to)
            .collect(
                Collectors.groupingBy(
                    Animal::type,
                    Collectors.summingInt(Animal::weight)
                )
            );
    }

    // Задача 16 - Список животных, отсортированный по виду, затем по полу, затем по имени -> List<Animal>
    public static List<Animal> sortByTypeBySexByName(List<Animal> animals) {
        return animals.stream()
            .sorted(
                Comparator.comparing(Animal::type)
                    .thenComparing(Animal::sex)
                    .thenComparing(Animal::name)
            )
            .toList();
    }

    // Задача 17 - Правда ли, что пауки кусаются чаще, чем собаки -> Boolean
    public static Boolean doSpidersBiteMoreOftenThanDogs(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> (
                animal.bites() && (animal.type() == Animal.Type.SPIDER || animal.type() == Animal.Type.DOG)
            ))
            .collect(
                Collectors.collectingAndThen(
                    Collectors.groupingBy(
                        Animal::type, Collectors.counting()
                    ),
                    biters -> biters.get(Animal.Type.SPIDER) > biters.get(Animal.Type.DOG)
                )
            );
    }

    // Задача 18 - Найти самую тяжелую рыбку в 2-х или более списках -> Animal
    public static Animal heaviestFish(List<List<Animal>> listsAnimals) {
        return listsAnimals.stream()
            .flatMap(List::stream)
            .filter(animal -> animal.type() == Animal.Type.FISH)
            .max(Comparator.comparing(Animal::weight))
            .orElse(null);
    }

    // Задача 19 - Животные, в записях о которых есть ошибки:
    // вернуть имя животного и список ошибок -> Map<String, Set<ValidationError>>.
    //Класс ValidationError и набор потенциальных проверок нужно придумать самостоятельно.
    public static Map<String, Set<ValidationError>> getErrors(List<Animal> animals) {
        return animals.stream()
            .collect(
                Collectors.groupingBy(
                    Animal::name,
                    Collectors.flatMapping(
                        ValidationError::errors,
                        Collectors.toSet()
                    )
                )
            );
    }

    // Задача 20 - Сделать результат предыдущего задания более читабельным:
    // вернуть имя и названия полей с ошибками, объединенные в строку -> Map<String, String>
    public static Map<String, String> getErrorsNice(List<Animal> animals) {
        return animals.stream()
            .collect(
                Collectors.toMap(
                    Animal::name,
                    animal -> ValidationError.errors(animal)
                        .map(animalError -> animalError.field() + " - " + animalError.error().getDescription())
                        .collect(Collectors.joining(", "))
                )
            );
    }
}
