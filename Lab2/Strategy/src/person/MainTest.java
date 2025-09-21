package person;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainTest {
    public MainTest() {
        Person[] people = {
            new Person(3000, "Dooly"),
            new Person(30, "Ddochi"),
            new Person(25, "Michol"),
            new Person(20000, "Douner"),
            new Person(3, "HeeDong")
        };

        List<Person> pList = new ArrayList<>();
        for (var p : people) {
            System.out.println(p);
            pList.add(p);
        }

        System.out.println("\n\ntry to sort by Name (Person must have implemention of Comparable)");
        Arrays.sort(people, null);
        for (var p : people) {
            System.out.println(p);
        }

        System.out.println("\n\nsort by Name");
        Arrays.sort(people, new NameComparator());
        for (var p : people) {
            System.out.println(p);
        }

        System.out.println("\n\npList sort by Age Person Comparable");
        pList.sort(null); // Comparable
        pList.forEach(System.out::println);

        System.out.println("\n\npList sort by NameComparator");
        pList.sort(new NameComparator()); // Comparator
        pList.forEach(System.out::println);
    }
}
