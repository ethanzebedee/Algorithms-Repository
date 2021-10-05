import java.util.Arrays;
import java.lang.*;
import java.io.*;



public class Hanoi {
    
    private Hanoi () {}
    
    
    static void tower(int numOfDisc, char source, char destination, char auxiliary) {
        
        if (numOfDisc == 1) {
            
            System.out.println("Move disk 1 from rod " + source + " to rod " + destination);
            return;
        }
        
        tower(numOfDisc - 1, source, auxiliary, destination);
        System.out.println(
                    "Move disk " + numOfDisc + " from rod " + source + " to rod " + destination);
        tower(numOfDisc - 1, auxiliary, destination, source);
    }
    
    
    public static void main(String args[]) {
        
        int numOfDisc = 12;
        Stopwatch timer = new Stopwatch();
        tower(numOfDisc, 'A', 'B', 'C');
        StdOut.println("elapsed time = " + timer.elapsedTime());
    }
    
}
