package minmaxfinder;

import java.util.Arrays;

public class MainTest {
    public MainTest() {
        double[] numbers = {-20.5, 87.3, 12.3, 2.5, -5.7, 65.4};
        System.out.println(Arrays.toString(numbers) + "\n");

        System.out.println("\nNaturalNumComparator");
        MinMaxFinder finder = new MinMaxFinder(new NaturalNumComparator());
        double max = finder.findMax(numbers);
        System.out.println("max=" + max);
        double min = finder.findMin(numbers);
        System.out.println("min=" + min);

        System.out.println("\nAbsNumComparator");
        finder.setComparator(new AbsNumComparator());
        max = finder.findMax(numbers);
        System.out.println("max=" + max);
        min = finder.findMin(numbers);
        System.out.println("min=" + min);
    }
}
