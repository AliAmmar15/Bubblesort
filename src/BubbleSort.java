import java.io.*;
import java.util.*;

public class BubbleSort {

    
    public static int[] createRandomArray(int arrayLength) {
        Random rand = new Random();
        int[] array = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            array[i] = rand.nextInt(101); 
        }
        return array;
    }

   
    public static void writeArrayToFile(int[] array, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (int num : array) {
                writer.println(num);
            }
            System.out.println("Array successfully written to " + filename);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

   
    public static int[] readFileToArray(String filename) {
        List<Integer> list = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextInt()) {
                list.add(scanner.nextInt());
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
            return new int[0]; 
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

    public static void bubbleSort(int[] array) {
        int n = array.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break; 
        }
    }

    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Generate a random array and save it to a file");
            System.out.println("2. Read an existing file, sort it, and save the sorted array to another file");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter the number of elements: ");
                    int length = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter the filename to save the array: ");
                    String fileName = scanner.nextLine();

                    int[] randomArray = createRandomArray(length);
                    writeArrayToFile(randomArray, fileName);
                    break;

                case 2:
                    System.out.print("Enter the filename to read the array: ");
                    String inputFile = scanner.nextLine();
                    System.out.print("Enter the filename to save the sorted array: ");
                    String outputFile = scanner.nextLine();

                    int[] array = readFileToArray(inputFile);
                    if (array.length > 0) {
                        bubbleSort(array);
                        writeArrayToFile(array, outputFile);
                        System.out.println("Sorted array saved to " + outputFile);
                    }
                    break;

                case 3:
                    System.out.println("Exiting program.");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice! Please enter 1, 2, or 3.");
            }
        }
    }
}
