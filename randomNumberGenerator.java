import java.util.Arrays;
import java.util.Random;

/**
 * A random number generator creates unique random integers and then puts it into 
 * an array. The set size for this array is 25.
 * 
 * The array is then sorted using bubble and quick sort. The performance times are 
 * also calculated for both sorting algorithms and for array sizes of 250, 500, 
 * 1000, 1500, and 2000 integers. 
 * 
 * 
 * @author Saaki Vishnumolakala
 */
public class randomNumberGenerator {

    private int[] randomIntegers;

    /**
     * The constructor takes integer n as a paramater.
     * It also initializes randomIntegers and calls the generateRandomIntegers method
     * 
     * @param n
     */
    public randomNumberGenerator(int n) {

        randomIntegers = new int[n];
        generateRandomIntegers(n);
        
    }

    /**
     * This method generates random integers and checks to see if they are all unique. 
     * The while loop keeps generating new numbers until a unique one is found.
     * 
     * @param n
     */
    private void generateRandomIntegers(int n) {
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            int randomNumber = random.nextInt(2000);
            
            // Ensure uniqueness
            while (contains(randomNumber, i)) {
                randomNumber = random.nextInt(2000);
            }

            randomIntegers[i] = randomNumber;
        }
    }


    /**
     * This method checks to see if a number that was randomly generated is already in the array.
     * 
     * @param num
     * @param endOfArray
     * @return
     */
    private boolean contains(int num, int endOfArray) {
        for (int i = 0; i < endOfArray; i++) {
            if (randomIntegers[i] == num) {
                return true;
            }
        }
        return false;
    }

    

    /**
     * The bubbleSort method implements the algorithm to sort the input array (arr) in ascending order
     * 
     * @param arr
     */
    private void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // swap arr[j+1] and arr[j]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }


    
    /**
     * The quickSort method implements the algorithm to sort the input array (arr) in ascending order
     * 
     * @param arr --- Array to be sorted
     * @param low --- Starting index
     * @param high --- Ending index
     */
    private void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }



    /**
     * This method partitions the array for the quickSort method
     * 
     * The partition function takes the last element as the pivot and places it in the correct position.
     * From there it places all the ones smaller than the pivot to the left and the ones greater to the right of the pivot.
     * 
     * @param arr
     * @param low
     * @param high
     * @return
     */
    private int partition(int[] arr, int low, int high) {

        //Chooses the pivot
        int pivot = arr[high];

        //Index of smaller element
        int i = low - 1;

        //If current element is smaller than the pivot and then increments the index of smaller element and then swaps if it smaller
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        //If it is greater, swap arr[i+1] and arr[high] (pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    
    /**
     * Returns the array of random unique integers
     * 
     * @return
     */
    public int[] getRandomIntegers() {
        return randomIntegers;
    }

    public static void main(String[] args) {

        //Part 2
        int originalArraySize = 25;
        randomNumberGenerator randomIntegers = new randomNumberGenerator(originalArraySize);

        // Output the generated random integers
        System.out.println("Generated Random Integers:");
        System.out.println(Arrays.toString(randomIntegers.getRandomIntegers()));



        //Create a copy of the original array to sort with bubble sort
        int[] bubbleSortArrayOriginal = Arrays.copyOf(randomIntegers.getRandomIntegers(), originalArraySize);
        randomIntegers.bubbleSort(bubbleSortArrayOriginal);

        //Output the sorted array using bubble sort
        System.out.println("\nGenerated Random Integers (Bubble Sort):");
        System.out.println(Arrays.toString(bubbleSortArrayOriginal));



        //Create a copy of the original array to sort with quick sort
        int[] quickSortArrayOriginal = Arrays.copyOf(randomIntegers.getRandomIntegers(), originalArraySize);
        randomIntegers.quickSort(quickSortArrayOriginal, 0, originalArraySize - 1);

        //Output the sorted array using Quick Sort
        System.out.println("\nGenerated Random Integers (Quick Sort):");
        System.out.println(Arrays.toString(quickSortArrayOriginal));

        System.out.println();
        System.out.println();


        //Part 3
        int[] arraySize = {250, 500, 1000, 1500, 2000};

        for (int n : arraySize) {
            System.out.println("Array Size: " + n);

            //Generate the random array and store it
            randomNumberGenerator randomUniqueIntegers = new randomNumberGenerator(n);
            int[] originalArray = randomUniqueIntegers.getRandomIntegers();


            //bubble sort and get performance time
            long bubbleSortStartTime = System.nanoTime();
            int[] bubbleSortArray = Arrays.copyOf(originalArray, n);
            randomUniqueIntegers.bubbleSort(bubbleSortArray);
            long bubbleSortEndTime = System.nanoTime();
            System.out.println("Bubble Sort Time: " + (bubbleSortEndTime - bubbleSortStartTime) + " nanoseconds");


            //quick sort and get performance time
            long quickSortStartTime = System.nanoTime();
            int[] quickSortArray = Arrays.copyOf(originalArray, n);
            randomUniqueIntegers.quickSort(quickSortArray, 0, n - 1);
            long quickSortEndTime = System.nanoTime();
            System.out.println("Quick Sort Time: " + (quickSortEndTime - quickSortStartTime) + " nanoseconds");

            System.out.println();
        }
        

    }
}