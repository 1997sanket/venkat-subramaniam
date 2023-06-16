package executearoundmethod;

import java.util.function.Consumer;

class Resource1 {


    public void operation1() {
        System.out.println("Using op1");
    }

    public void operation2() {
        System.out.println("Using op2");
    }

    public void close() {
        System.out.println("Closing the resource");
    }
}

class Resource2 {

    private Resource2() {
        System.out.println("Creating the resource");
    }
    public void operation1() {
        System.out.println("Using op1");
    }

    public void operation2() {
        System.out.println("Using op2");
    }


    public static void useResource(Consumer<Resource2> consumer) {


        Resource2 resource2 = new Resource2();  // before main code

        try {
            consumer.accept(resource2); // main code that needs to be executed
        }
        catch(Exception e) {
            System.out.println(e);
        }
        finally {
            resource2.close();  // after main code
        }
    }

    private void close() {
        System.out.println("Closing the resource");
    }
}

public class Main {
    public static void main(String[] args) {

        betterApproach();
    }

    private static void betterApproach() {
        Resource2.useResource(resource2 -> resource2.operation1());
    }


    // The problem with this approach is, user MAY forget to do the try catch, and the code looks ugly.
    // Also, we are relying on the client to close the resources.
    private static void standardApproach() {

        Resource1 resource = null;
        try {
            resource = new Resource1();
            resource.operation1();
            resource.operation2();
        } finally {
            resource.close();
        }
    }
}
