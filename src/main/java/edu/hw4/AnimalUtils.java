package edu.hw4;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public final class AnimalUtils {
    private AnimalUtils() {
    }

    public static List<Animal> sortByAscendingHeight(List<Animal> animals) {
        return null;
    }

    public static List<Animal> sortByDescendingWeight(List<Animal> animals) {
        return null;
    }

    public static Map<Animal.Type, Integer> countByType(List<Animal> animals) {
        return null;
    }

    public static Animal longestName(List<Animal> animals) {
        return null;
    }

    public static Animal.Sex majority(List<Animal> animals) {
        return null;
    }

    public static Map<Animal.Type, Animal> heaviestByType(List<Animal> animals) {
        return null;
    }

    public static Animal kOldAnimal(List<Animal> animals) {
        return null;
    }

    public static Optional<Animal> heaviestAndBelow(List<Animal> animals, int height) {
        return Optional.empty();
    }

    public static Integer countPaws(List<Animal> animals) {
        return null;
    }

    public static List<Animal> ageNotEqualPaws(List<Animal> animals) {
        return null;
    }

    public static Optional<Animal> canBiteAndAbove100cm(List<Animal> animals, int height) {
        return Optional.empty();
    }

    public static Integer countWeightGreaterThanHeight(List<Animal> animals) {
        return null;
    }

    public static List<Animal> nameContainsMoreThanTwoWords(List<Animal> animals) {
        return null;
    }

    public static Boolean hasDogAbove(List<Animal> animals, int height) {
        return null;
    }

    public static Map<Animal.Type, Animal> totalWeightByTypeWhoseAgeFromTo(List<Animal> animals, int from, int to) {
        return null;
    }

    public static List<Animal> sortByTypeBySexByName(List<Animal> animals) {
        return null;
    }

    public static Boolean doSpidersBiteMoreOftenThanDogs(List<Animal> animals) {
        return null;
    }


    public static Animal heaviestFish(List<List<Animal>> listsAnimals) {
        return null;
    }

    public static Map<String, Set<ValidationError>> getErrors(List<Animal> animals) {
        return null;
    }

    public static Map<String, String> getErrorsNice(List<Animal> animals) {
        return null;
    }


    record ValidationError(String name) {}
}
