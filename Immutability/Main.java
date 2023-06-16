package immutability;

import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        // Bad code
        List<Integer> list = List.of(1, 2, 3, 4, 5);

        int factor [] = new int[] {2};

        Stream<Integer> stream = list.stream()
                .map(e -> factor[0] * 2);   // impure function because depending on value that can change

        factor[0] = 0;

        stream.forEach(System.out::println);    // the above map operation is done here, that is why Streams are Lazy.
    }
}
