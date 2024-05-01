import java.util.*;

public class Q1a_23073897 {

    public static ArrayList<Integer> generateItems(int size) {
        ArrayList<Integer> dayItems = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            dayItems.add(random.nextInt(500000));
        }
        return dayItems;
    }

    private static int partition(ArrayList<Integer> items, int min, int max) {
        int pivotIndex = new Random().nextInt(max - min + 1) + min; // Random pivot
        int pivot = items.get(pivotIndex);
        Collections.swap(items, min, pivotIndex);  // Pivot to start
        int i = min + 1;
        for (int j = min + 1; j <= max; j++) {
            if (items.get(j) < pivot) {
                Collections.swap(items, i, j);
                i++;
            }
        }
        Collections.swap(items, min, i - 1);  // Pivot to final position
        return i - 1;
    }

    public static void quicksort(ArrayList<Integer> items, int min, int max) {
        if (min < max) {
            int pivotIndex = partition(items, min, max);
            quicksort(items, min, pivotIndex - 1); // Recursively sort left side
            quicksort(items, pivotIndex + 1, max); // Recursively sort right side
        }
    }

    public static void main(String[] arg) { // The code below quicksorts the entire list of items and prints the first 1000 items of the sorted list...
        int[] listSizes = {1000, 5000, 10000, 50000, 75000, 100000, 500000};
        String[] listDays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        Random random = new Random();
        for (int day = 0; day < 7; day++) {
            ArrayList<Integer> items = new ArrayList<>();
            for (int i = 0; i < listSizes[day]; i++) {
                items.add(random.nextInt(500000)); // Generate random numbers between 0 and 500000
            }
            System.err.println("Day: " + listDays[day]);
            System.out.println("Unsorted items (first 1000 items) of size " + listSizes[day] + ": " + items.subList(0, Math.min(1000, items.size())));
            System.out.println();
            quicksort(items, 0, items.size() - 1);
            System.out.println("Sorted items (first 1000 items) of size " + listSizes[day] + ": " + items.subList(0, Math.min(1000, items.size())));
            System.out.println();
        }
    }
}