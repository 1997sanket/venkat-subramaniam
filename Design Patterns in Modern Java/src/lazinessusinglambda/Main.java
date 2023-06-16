package lazinessusinglambda;

public class Main {

    public static int heavyComputation() {

        // Suppose this is a heavy processing method
        System.out.println("Heavy Processing");

        return 5;
    }

    public static void main(String[] args) {

        standardApproach();
        lazyLambdaApproach();
    }

    private static void standardApproach() {
        boolean flag = false;

        // Unnecessary heavyComputation is called here, which may not even be used in the 'if' condition if flag == false
        int num = heavyComputation();

        if(flag && num > 4) {
            System.out.println("Heavy processing output: " + num);  // using that value
        }
    }

    private static void lazyLambdaApproach() {

        boolean flag = false;

        // We are getting a Lazy value instead of the actual value
        Lazy<Integer> lazyNum = new Lazy(() -> heavyComputation());

        //if flag == true then only the lazy calculation will apply
        if(flag && lazyNum.get() > 4) {
            System.out.println("Heavy processing output: " + lazyNum.get());  // here the value is not calculated again
        }
    }


}
