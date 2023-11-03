package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public final class AnimalUtils {
    private AnimalUtils() {
    }

    public static List<Animal> sortByAscendingHeight(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::height))
            .toList();
    }

    public static List<Animal> sortByDescendingWeightAndLimit(List<Animal> animals, int limiter) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::weight, Comparator.reverseOrder()))
            .limit(limiter)
            .toList();
    }

    public static Map<Animal.Type, Integer> countByType(List<Animal> animals) {
        return animals.stream()
            .collect(
                Collectors.groupingBy(
                    Animal::type,
                    Collectors.summingInt(animal -> 1)
                )
            );
    }

    public static Animal longestName(List<Animal> animals) {
        return animals.stream()
            .max(Comparator.comparingInt(animal -> animal.name().length()))
            .orElseThrow();
    }

    public static Animal.Sex majority(List<Animal> animals) {
        return animals.stream()
            .count();
    }

    public static Map<Animal.Type, Animal> heaviestByType(List<Animal> animals) {
        return animals.stream()
            .collect(
                Collectors.toMap(
                    Animal::type, animal -> animal,
                    BinaryOperator.maxBy(Comparator.comparingInt(Animal::weight))
                )
            );
    }

    public static Animal kOldAnimal(List<Animal> animals, long index) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::age, Comparator.reverseOrder()))
            .skip(index - 1)
            .findFirst()
            .orElseThrow();
    }

    public static Optional<Animal> heaviestAndBelow(List<Animal> animals, int height) {
        return animals.stream()
            .filter(animal -> animal.height() < height)
            .max(Comparator.comparing(Animal::weight));
    }

    public static Integer countPaws(List<Animal> animals) {
        return animals.stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    public static List<Animal> ageNotEqualPaws(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.age() != animal.paws())
            .toList();
    }

    public static List<Animal> canBiteAndAbove(List<Animal> animals, int height) {
        return animals.stream()
            .filter(animal -> animal.bites() && animal.height() > height)
            .toList();
    }

    public static Long countWeightGreaterThanHeight(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.weight() > animal.height())
            .count();
    }

    public static List<Animal> nameContainsMoreThanTwoWords(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.name().split(":").length > 2)
            .toList();
    }

    public static Boolean hasDogAbove(List<Animal> animals, int height) {
        return animals.stream()
            .anyMatch(animal -> animal.type() == Animal.Type.DOG && animal.height() > height);
    }

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

    public static List<Animal> sortByTypeBySexByName(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::type))
            .sorted(Comparator.comparing(Animal::sex))
            .sorted(Comparator.comparing(Animal::name))
            .toList();
    }

    public static Boolean doSpidersBiteMoreOftenThanDogs(List<Animal> animals) {
        return animals.stream()
            .anyMatch();
    }

    public static Animal heaviestFish(List<List<Animal>> listsAnimals) {
        return null;
    }

    public static Map<String, Set<ValidationError>> getErrors(List<Animal> animals) {
        return animals.stream()
            .collect(
                Collectors.groupingBy(
                    Animal::name,
                    Collectors.flatMapping(
                        ValidationError::findErrors,
                        Collectors.toSet()
                    )
                )
            );
    }

    public static Map<String, String> getErrorsNice(List<Animal> animals) {
        return animals.stream()
            .collect(
                Collectors.groupingBy(
                    Animal::name,
                    Collectors.flatMapping(
                        ValidationError::findErrors,
                        Collectors.toSet()
                    )
                )
            );;
    }
}
