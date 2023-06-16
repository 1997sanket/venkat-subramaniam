package exceptionhandling;

import java.util.List;
import java.util.function.Function;

/*
    Exception are suitable for imperative style of programming.
    It does not go well with functional style.
 */

public class Main {
    public static void main(String[] args) {
        var list = List.of(1, 2, 3, 4, 5);

        approachThree(list);
    }

    private static int process (int num) throws Exception {

        if(Math.random() > 0.5)
            throw new Exception("Oops !!");

        return num * 2;
    }

    private static void approachOne(List<Integer> list) {

        // This approach has ugly looking code, and we can also extract the try-catch into another method.
        // But this approach is not recommended, it defeats the whole purpose of Functional Programming.
        // The compiler was actually trying to help us, we just shut it up by throwing a RuntimeException

        list.stream()
                .map(x -> {
                    try {
                        return process(x);
                    } catch (Exception e) {
                        throw new RuntimeException();
                    }
                })
                .forEach(System.out::println);


    }


    // This is worse than approach one, we are generalizing it.
    private static void approachTwo(List<Integer> list) {
        list.stream()
                .map(convertToRuntimeException(x -> process(x)))
                .forEach(System.out::println);
    }

    private static <T, R> Function<T,R> convertToRuntimeException(FunctionEx<T, R> function) {

        return input -> {
            try {
                return function.apply(input);
            } catch(Exception e) {
                throw new RuntimeException();
                // return -1;
            }
        };
    }


    // This can work, but it is still complicated, we have to work with Try type all the time
    // We can use a library called "vavr" to achieve it, but it is tedious.
    private static void approachThree(List<Integer> list) {

        // Here we are only printing the success types.

        list.stream()
                .map(num -> Try.tryPlease(num, x -> process(x)))
                .map(tryObj -> tryObj.getData())
                .forEach(System.out::println);
    }
}
