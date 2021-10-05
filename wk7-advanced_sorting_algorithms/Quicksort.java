import java.util.Random;
import java.util.Collections;

public class Quicksort{

    public static void main(String args[])
    {
        for(int i = 10; i <= 100000; i = i * 10){
            int arr[] = generateArray(i);
            Stopwatch timer1 = new Stopwatch();
            Stopwatch timer2 = new Stopwatch();
            Stopwatch timer3 = new Stopwatch();
            quickSort(arr, 0, arr.length-1);
            StdOut.println("elapsed time for quick sort with " + i + " length = " + timer1.elapsedTime());
            insertionSort(arr);
            StdOut.println("elapsed time for insertion sort with " + i + " length = " + timer2.elapsedTime());
            enhancedQuickSort(arr, 0, arr.length-1);
            StdOut.println("elapsed time for enhanced quick sort with " + i + " length = " + timer3.elapsedTime());
        }
    }

    static void swap(int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static void shuffleFunc(int[] arr)
    {
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) 
        {
			int randIndex = rand.nextInt(arr.length);
			int temp = arr[randIndex];
			arr[randIndex] = arr[i];
			arr[i] = temp;
		}
    }

    static int medianCalc(int[] arr, int start, int end){

        int median = 0;
        int len = arr.length;
        if(len%2==1)
	    {
		    median = arr[(len+1)/2-1];

	    }else{

		    median = (arr[len/2-1] + arr[len/2])/2;
	    }

        return median;

    }

    static int partition(int[] arr, int start, int end)
    {
        int pivot = arr[end];
        int i = (start - 1);
    
        for(int j = start; j <= end-1; j++)
        {
            if (arr[j] < pivot)
            {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, end);
        return (i + 1);
    }

    static int enhancedPartition(int[] arr, int start, int end)
    {
        int pivot = medianCalc(arr, start, end);
        int i = (start - 1);
    
        for(int j = start; j <= end-1; j++)
        {
            if (arr[j] < pivot)
            {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, end);
        return (i + 1);
    }

    static void quickSort(int[] arr, int start, int end)
    {
        if (start < end)
        {
            int piv = partition(arr, start, end);
    
            quickSort(arr, start, piv - 1);
            quickSort(arr, piv + 1, end);
        }
    }

    static void enhancedQuickSort(int[] arr, int start, int end)
    {
        if (start < end)
        {
            if(start <= end + 10){
                insertionSort(arr);
                return;
            }

            shuffleFunc(arr);

            int piv = enhancedPartition(arr, start, end);
        
            quickSort(arr, start, piv - 1);
            quickSort(arr, piv + 1, end);
        }
    }

    public static void insertionSort(int arr[]){
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

        //print(arr);

    }

    public static int[] generateArray(int n){

        Random rand = new Random(); // creating Random object
        int arr[] = new int[n];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = rand.nextInt();
            }

        return arr;
    }
}