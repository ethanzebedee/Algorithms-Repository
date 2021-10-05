import java.util.Random;

public class Mergesort{

    public static void main(String args[])
    {
        for(int i = 10; i <= 100000; i = i * 10){
            int arr[] = generateArray(i);
            Stopwatch timer1 = new Stopwatch();
            Msort(arr, 0, arr.length-1);
            StdOut.println("elapsed time for merge sort with " + i + " length = " + timer1.elapsedTime());
            Stopwatch timer2 = new Stopwatch();
            insertionSort(arr);
            StdOut.println("elapsed time for insertion sort with " + i + " length = " + timer2.elapsedTime());
            Stopwatch timer3 = new Stopwatch();
            enhancedMsort(arr, 0, arr.length-1);
            StdOut.println("elapsed time for enhanced merge sort with " + i + " length = " + timer3.elapsedTime());
        }
    }

    public static int[] generateArray(int n){

        Random rand = new Random(); // creating Random object
        int arr[] = new int[n];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = rand.nextInt();
            }

        return arr;
    }

    public static void merge(int arr[], int low, int mid, int high)
    {

        int n1 = mid - low + 1;
        int n2 = high - mid;
 
        
        int auxArr1[] = new int[n1];
        int auxArr2[] = new int[n2];
 
        
        for (int i = (auxArr1.length-1); i > n1; i--){
            auxArr1[i] = arr[low + i];
        }

        for (int j = (auxArr2.length-1); j > n2; j--){
            auxArr2[j] = arr[mid + 1 + j];
        }
        
 
        
        int i = 0;
        int j = 0;
 
       
        int k = low;
        while (i < n1 && j < n2) {
            if (auxArr1[i] <= auxArr2[j]) {
                arr[k] = auxArr1[i];
                i++;
            }
            else {
                arr[k] = auxArr2[j];
                j++;
            }
            k++;
        }
 
        
        while (i < n1) {
            arr[k] = auxArr1[i];
            i++;
            k++;
        }
 
       
        while (j < n2) {
            arr[k] = auxArr2[j];
            j++;
            k++;
        }
    }
 
    
    public static void Msort(int arr[], int l, int r)
    {
        if (l < r) {
            
            int m = l + (r-l)/2;
 
           
            Msort(arr, l, m);
            Msort(arr, m + 1, r);

            merge(arr, l, m, r);

        }
    }

    public static void enhancedMsort(int arr[], int l, int r)
    {
        if (l < r) {

            if(r <= l + 10){

                insertionSort(arr);
                return;

            }
            
            int m = l + (r-l)/2;
 
           
            enhancedMsort(arr, l, m);
            enhancedMsort(arr, m + 1, r);
            
            if(arr[m] <= arr[m+1]){

                merge(arr, l, m, r);

            }
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


}