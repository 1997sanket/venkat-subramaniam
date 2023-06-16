package collectors;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

public class Main {

    private static List<Person> create() {
        List<Person> people = List.of(
                new Person("John", 25),
                new Person("Alice", 30),
                new Person("Michael", 35),
                new Person("John", 40),
                new Person("Emma", 30),
                new Person("Alice", 22)
        );

        return people;
    }

    public static void main(String[] args) {

        var people = create();

        //Collect names of all people in a list
        System.out.println("All names: " +
                people.stream()
                        .map(person -> person.age())
                        .collect(toList())
        );


        //Get names of people with age > 30, name in upper case and comma separated
        System.out.println("name of age > 30: " +
                    people.stream()
                            .filter(person -> person.age() > 30)
                            .map(Person::name)
                            .map(String::toUpperCase)
                            .collect(joining(", "))
                );



        // Get even and odd aged people
        // One approach is to do filter and iterate twice, but not very optimal, here is the best approach
        System.out.println("Partition by even and odd age" +
                people.stream()
                        .collect(partitioningBy(person -> person.age() % 2 == 0))
        );


        //Create group of people by name
        Map<String, List<Person>> nameVsPerson = people.stream()
                .collect(groupingBy(Person::name));
        System.out.println("Group by name vs Person: " + nameVsPerson);


        //Create group of people's age by name
        Map<String, List<Integer>> nameVsAge = people.stream()
                .collect(groupingBy(Person::name, mapping(Person::age, toList())));
        System.out.println("Group by name vs age: " + nameVsAge);


        //Count by name
        Map<String, Long> nameVsCount = people.stream()
                .collect(groupingBy(Person::name, counting()));

        //Count by name but counting should be Integer
        Map<String, Integer> nameVsCountInt = people.stream()
                .collect(groupingBy(Person::name, collectingAndThen(counting(), Long::intValue)));
        System.out.println("Count by name: " + nameVsCountInt);


        //Get Person with max age
        System.out.println("Person with max age: " +
                people.stream()
                        .collect(maxBy((p1, p2) -> p1.age().compareTo(p2.age())))
        );


        //Get Person name with min age
        System.out.println("Person name with min age: " +
                people.stream()
                        .collect(collectingAndThen(minBy((p1, p2) -> p1.age().compareTo(p2.age())),
                                personOpt -> personOpt.map(Person::name).orElse("No such person")))
        );

        //Group by age vs name, and name.length should be > 4
        System.out.println("Group by Age vs name where name.length > 4: " +
                people.stream()
                        .collect(groupingBy(Person::age, mapping(Person::name, filtering(name -> name.length() > 4, toList()))))
        );


        //Print all characters of all the person's names
        System.out.println(
                people.stream()
                        .map(Person::name)
                        .flatMap(name -> Stream.of(name.split("")))
                        .collect(toList())
        );

        // Sort persons on age and then on name
                people.stream()
                        .sorted(comparing(Person::age).thenComparing(Person::name))
                        .forEach(System.out::println);
    }
}
