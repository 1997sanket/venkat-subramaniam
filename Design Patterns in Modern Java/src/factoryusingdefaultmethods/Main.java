package factoryusingdefaultmethods;

interface Person {

    /* Now person should have a Animal to play with, but has-a relation cannot be applied in Interface
           Hence, we create a abstract method and let the implementations implement it.
        */
    Animal getAnimal();

    default void play() {
        System.out.println("Playing with " + getAnimal());
    }
}

interface Animal {}
class Dog implements Animal {}

class Cat implements Animal {}

class DogPerson implements Person {

    @Override
    public Animal getAnimal() {
        return new Dog();
    }
}

class CatLover implements Person {

    @Override
    public Animal getAnimal() {
        return new Cat();
    }
}

public class Main {
    public static void main(String[] args) {
        Person dogPerson = new DogPerson();
        dogPerson.play();
    }
}
