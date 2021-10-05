import java.util.Random;



public class SortingAlgorithms {
    
    public static void main(String args[]) {
        
        for (int i = 10; i <= 100000; i = i * 10) {
            
            Stopwatch timer1 = new Stopwatch();
            selectionSort(generateArray(i));
            StdOut.println("elapsed time for selection sort with "
                        + i
                        + " length = "
                        + timer1.elapsedTime());
            Stopwatch timer2 = new Stopwatch();
            insertionSort(generateArray(i));
            StdOut.println("elapsed time for insertion sort with "
                        + i
                        + " length = "
                        + timer2.elapsedTime());
            Stopwatch timer3 = new Stopwatch();
            stalinSort(generateArray(i));
            StdOut.println("elapsed time for stalin sort with "
                        + i
                        + " length = "
                        + timer3.elapsedTime());
        }
        
    }
    
    
    public static int[] generateArray(int n) {
        
        Random rand = new Random(); // creating Random object
        int arr[] = new int[n];
        
        for (int i = 0; i < arr.length; i++) {
            
            arr[i] = rand.nextInt();
        }
        
        return arr;
    }
    
    
    public static void selectionSort(int arr[]) {
        
        int temp;
        int min;
        
        for (int i = 0; i < arr.length - 1; i++) {
            
            min = i;
            
            for (int j = i + 1; j < arr.length; j++) {
                
                if (arr[min] > arr[j]) {
                    
                    min = j;
                }
                
                temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
            
        }
        
    }
    
    
    public static void insertionSort(int arr[]) {
        
        int n = arr.length;
        
        for (int i = 1; i < n; ++i) {
            
            int index = arr[i];
            int j = i - 1;
            
            while (j >= 0 && arr[j] > index) {
                
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            
            arr[j + 1] = index;
        }
        
    }
    
    
    public static void stalinSort(int arr[]) {
        
        int gulagArr[] = new int[arr.length];
        int j = 1;
        gulagArr[0] = arr[0];
        
        for (int i = 1; i < arr.length; i++) {
            
            if (gulagArr[j - 1] <= arr[i]) {
                
                gulagArr[j] = arr[i];
                j++;
            }
            
        }
        
    }
    
    
    public static void print(int arr[]) {
        
        int n = arr.length;
        
        for (int i = 0; i < n; ++i) {
            
            System.out.print(arr[i] + " ");
        }
        
        System.out.println();
    }
    
}
