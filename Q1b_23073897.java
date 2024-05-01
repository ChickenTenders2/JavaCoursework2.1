import java.util.*;

public class Q1b_23073897 {
    
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

    public static void main(String[] arg) {
        
        int[] listSizes = {1000, 5000, 10000, 50000, 75000, 100000, 500000};
        for (int size : listSizes) {
            // Random Lists
            long randomStartTime = System.currentTimeMillis();
            ArrayList<Integer> randomItems = generateItems(size);
            quicksort(randomItems, 0, randomItems.size() - 1);
            long randomEndTime = System.currentTimeMillis();
            long randomExecutionTime = randomEndTime - randomStartTime;
            System.out.println("Execution time for random items of size " + size + ": " + randomExecutionTime + " milliseconds");
            
            // Sorted Lists
            ArrayList<Integer> sortedItems = new ArrayList<>(randomItems);
            long sortedStartedTime = System.currentTimeMillis();
            quicksort(sortedItems, 0, sortedItems.size() - 1);
            long sortedEndTime = System.currentTimeMillis();
            long sortedExecutionTime = sortedEndTime - sortedStartedTime;
            System.out.println("Execution time for sorted items of size " + size + ": " + sortedExecutionTime + " milliseconds");

            // Reverse-sorted Lists
            ArrayList<Integer> reverseSortedItems = new ArrayList<>(randomItems);
            Collections.reverse(reverseSortedItems);
            long reverseSortedStartTime = System.currentTimeMillis();
            quicksort(reverseSortedItems, 0, reverseSortedItems.size() - 1);
            long reverseSortedEndTime = System.currentTimeMillis();
            long reverseSortedExecutionTime = reverseSortedEndTime - reverseSortedStartTime;
            System.out.println("Execution time for reverse-sorted items of size " + size + ": " + reverseSortedExecutionTime + " milliseconds");

            System.out.println();
        }
    }
}
