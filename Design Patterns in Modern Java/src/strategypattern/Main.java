package strategypattern;

import java.util.List;
import java.util.function.Predicate;

public class Main {

    static void printEvenNumbers(List<Integer> numbers) {
        numbers.stream()
                .filter(n -> n % 2 == 0)
                .forEach(System.out::println);
    }

    static void printOddNumbers(List<Integer> numbers) {
        numbers.stream()
                .filter(n -> n % 2 == 1)
                .forEach(System.out::println);
    }

    static void printAllNumbers(List<Integer> numbers) {
        numbers.stream()
                .forEach(System.out::println);
    }

    // If we don't use this method, we will have to create separate methods to print odd, even, all numbers
    static void printNumbers(List<Integer> numbers, Predicate<Integer> predicate) {
        numbers.stream()
                .filter(predicate)
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        var numbers = List.of(1, 2, 3, 4, 5, 6);

        //print even numbers
        printNumbers(numbers, n -> n % 2 == 0);

        //print odd numbers
        printNumbers(numbers, n -> n % 2 == 1);

        //print all numbers
        printNumbers(numbers, n -> true);
    }
}
