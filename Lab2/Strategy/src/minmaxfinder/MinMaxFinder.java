package minmaxfinder;

public class MinMaxFinder {
    private Comparator comparator = null;

    public MinMaxFinder(Comparator comparator) {
        this.comparator = comparator;
    }

    public void setComparator(Comparator comparator) {
        this.comparator = comparator;
    }

    public double findMax(double[] numbers) {
        double max = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (comparator.isLesser(max, numbers[i])) {
                max = numbers[i];
            }
        }
        return max;
    }

    public double findMin(double[] numbers) {
        double min = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (!comparator.isLesser(min, numbers[i])) {
                min = numbers[i];
            }
        }
        return min;
    }
}
