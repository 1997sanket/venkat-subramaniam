package parallel;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        var numbers = List.of(2, 4, 5, 7, 8);
        // var numbers = Set.of(2, 4, 5, 7, 8);

        /*
         Even though this is parallel, it will give the correct answer because findFirst() is ordered by default
         because the source is a List, and it is ordered by default, for Set we can't guarantee.
         */

        System.out.println("Total Processors: " + Runtime.getRuntime().availableProcessors());

        // This shows 1 less than total cores, because main thread also comes in the Common pool
        // Thus the CommonForkJoinPool has same no of threads as the cores
        System.out.println("Threads in Common pool: " + ForkJoinPool.commonPool().getParallelism());

        Integer integer = numbers.parallelStream()
                .filter(num -> {
                    System.out.println(Thread.currentThread().getName());
                    return num % 2 == 1;
                })
                .findFirst()    // ordered Operation
                .orElse(-1);

        System.out.println(integer);
    }
}
