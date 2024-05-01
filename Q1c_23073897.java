import java.util.*;

public class Q1c_23073897 {
    
    public static ArrayList<Integer> generateItems(int size) {
        ArrayList<Integer> dayItems = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            dayItems.add(random.nextInt(500000));
        }
        return dayItems;
    }

    private static int partition(ArrayList<Integer> items, int min, int max) {
        int pivotIndex = medianOfThree(items, min, max); // Changed to Median of Three method
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
    
    private static int medianOfThree(ArrayList<Integer> items, int min, int max) {
        Random random = new Random();
        int a = random.nextInt(max - min + 1) + min;
        int b = random.nextInt(max - min + 1) + min;
        int c = random.nextInt(max - min + 1) + min;
        int aValue = items.get(a);
        int bValue = items.get(b);
        int cValue = items.get(c);

        if (aValue <= bValue && bValue <= cValue) { // Determine median value
            return b;
        } else if (bValue <= aValue && aValue <= cValue) {
            return a;
        } else {
            return c;
        }
    }

    // Option 2: Quicksort + Insertion Sort Hybrid (performs well on small/nearly sorted lists)
    public static void insertionSort(ArrayList<Integer> items, int min, int max) {
        for (int i = min + 1; i <= max; i++) {
            int key = items.get(i);
            int j = i - 1;
            while (j >= min && items.get(j) > key) {
                items.set(j + 1, items.get(j));
                j--;
            }
            items.set(j + 1, key);
        }
    }

    public static void hybridQuicksort(ArrayList<Integer> items, int min, int max) {
        int threshold = 10; // For switching between sorting methods
        if (max - min <= threshold) {
            insertionSort(items, min, max);
        } else  {
            int pivotIndex = partition(items, min, max);
            hybridQuicksort(items, min, pivotIndex - 1);
            hybridQuicksort(items, pivotIndex + 1, max);
        }
    }

    public static void main(String[] arg) {

        int[] listSizes = {1000, 5000, 10000, 50000, 75000, 100000, 500000};
        for (int size : listSizes) {
            // Random Lists
            long randomStartTime = System.currentTimeMillis();
            ArrayList<Integer> randomItems = generateItems(size);
            hybridQuicksort(randomItems, 0, randomItems.size() - 1);
            long randomEndTime = System.currentTimeMillis();
            long randomExecutionTime = randomEndTime - randomStartTime;
            System.out.println("Execution time for random items of size " + size + ": " + randomExecutionTime + " milliseconds");
            
            // Sorted Lists
            ArrayList<Integer> sortedItems = new ArrayList<>(randomItems);
            long sortedStartedTime = System.currentTimeMillis();
            hybridQuicksort(sortedItems, 0, sortedItems.size() - 1);
            long sortedEndTime = System.currentTimeMillis();
            long sortedExecutionTime = sortedEndTime - sortedStartedTime;
            System.out.println("Execution time for sorted items of size " + size + ": " + sortedExecutionTime + " milliseconds");

            // Reverse-sorted Lists
            ArrayList<Integer> reverseSortedItems = new ArrayList<>(randomItems);
            Collections.reverse(reverseSortedItems);
            long reverseSortedStartTime = System.currentTimeMillis();
            hybridQuicksort(reverseSortedItems, 0, reverseSortedItems.size() - 1);
            long reverseSortedEndTime = System.currentTimeMillis();
            long reverseSortedExecutionTime = reverseSortedEndTime - reverseSortedStartTime;
            System.out.println("Execution time for reverse-sorted items of size " + size + ": " + reverseSortedExecutionTime + " milliseconds");

            System.out.println();
        }
    }

    // public static void main(String[] arg) { // The code below hybrid quicksorts the entire list of items and prints the first 1000 items of the sorted list...
    //     int[] listSizes = {1000, 5000, 10000, 50000, 75000, 100000, 500000};
    //     String[] listDays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    //     Random random = new Random();
    //     for (int day = 0; day < 7; day++) {
    //         ArrayList<Integer> items = new ArrayList<>();
    //         for (int i = 0; i < listSizes[day]; i++) {
    //             items.add(random.nextInt(500000)); // Generate random numbers between 0 and 500000
    //         }
    //         System.err.println("Day: " + listDays[day]);
    //         System.out.println("Unsorted items (first 1000 items) of size " + listSizes[day] + ": " + items.subList(0, Math.min(1000, items.size())));
    //         System.out.println();
    //         hybridQuicksort(items, 0, items.size() - 1);
    //         System.out.println("Sorted items (first 1000 items) of size " + listSizes[day] + ": " + items.subList(0, Math.min(1000, items.size())));
    //         System.out.println();
    //     }
    // }
}

