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
        int pivot = items.get(min);
        int i = min + 1;
        for (int j = min + 1; j <= max; j++) {
            if (items.get(j) < pivot) {
                Collections.swap(items, i, j);
                i++;
            }
        }
        Collections.swap(items, min, i - 1);
        return i - 1;
    }

    // Option 2: Insertion Sort + Quicksort Hybrid (performs well on small/nearly sorted lists)
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
        int threshold = 10;
        if (max - min <= threshold) {
            insertionSort(items, min, max);
        } else  {
            int pivotIndex = partition(items, min, max);
            hybridQuicksort(items, min, pivotIndex - 1);
            hybridQuicksort(items, pivotIndex + 1, max);
        }
    }

    // public static void main(String[] arg) { //Generates hybrid quicksort for each list item...
    //     int[] listSizes = {1000, 5000, 10000, 50000, 75000, 100000, 500000};
    //     String[] listDays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    //     Random random = new Random();
    //     for (int day = 0; day < 7; day++) {
    //         ArrayList<Integer> items = new ArrayList<>();
    //         for (int i = 0; i < listSizes[day]; i++) {
    //             items.add(random.nextInt(500000)); //To generate random numbers between 1000 and 500000
    //         }
    //         System.err.println("Day: " + listDays[day]);
    //         System.out.println("Unsorted items (first 1000 items) of size " + listSizes[day] + ": " + items.subList(0, Math.min(1000, items.size())));
    //         System.out.println();
    //         hybridQuicksort(items, 0, items.size() - 1);
    //         System.out.println("Sorted items (first 1000 items) of size " + listSizes[day] + ": " + items.subList(0, Math.min(1000, items.size())));
    //         System.out.println();
    //     }
    // }
    
    public static void main(String[] arg) { //Generates execution time for each list item...
        // int[] listSizes = {1000, 5000, 10000, 50000, 75000, 100000, 500000};
        int[] listSizes = {1, 2, 4, 8, 16, 32, 64};
        long totalStartTime = System.currentTimeMillis();
        for (int size : listSizes) {
            ArrayList<Integer> items = generateItems(size);
            long startTime = System.currentTimeMillis();
            hybridQuicksort(items, 0, items.size() - 1);
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            System.out.println("Execution time for list of size " + size + ": " + executionTime + " milliseconds");
        }
        long totalEndTime = System.currentTimeMillis();
        long totalExecutionTime = totalEndTime - totalStartTime;
        System.out.println("Total execution time for all lists: " + totalExecutionTime + " milliseconds");
    }
}

