package exceptionhandling.completablefuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/*
 How CompletableFuture handles exception ?

 It has a data channel and an error channel

 If everything goes right, the flow will never go in exceptionally method
 But, in case of exception, the flow will go to the nearest exceptionally method and then if the exception is handled there
 and some data is returned, then it will go to the next proper function.

 So in here, where to put exceptionally and what to do with it is very important.
 Although it is better than handling exception in Streams.


 Reactive Programming probably handles exception the best
 Firstly, we can directly call methods that throw CheckedException inside Reactive method pipelines, it allows it.
 And it has 3 possible events:  data, error, and complete.

 if there is an error event, the next element in the stream won't be processed and the processing will stop, hence this is the
 only drawback of this approach.

 But other than that, it is arguably better than handling exceptions in streams or CompletableFutures.

 */


public class Main {
    public static void main(String[] args) {

        create(2)
                .thenApply(Main::compute)
                .thenAccept(System.out::println)
                .thenRun(() -> System.out.println("Done"))
                .exceptionally(Main::handleExceptonTwo);
    }

    private static Void handleExceptonTwo(Throwable throwable) {
        System.out.println("In handle exception 2");
        return null;
    }

    private static Void handleExcepton(Throwable throwable) {
        System.out.println("IN Handle Exception 1");
        return null;
    }

    private static int compute(int num) {

        if(Math.random() > 0.5) throw new RuntimeException("Oops !");

        return num + 2;
    }

    private static CompletableFuture<Integer> create(int num) {
        return CompletableFuture.supplyAsync(() -> num * 5);
    }
}
