/******************************************************************************
 *  Compilation:  javac Rpeasant.java
 *  Execution:    java Rpeasant input.txt
 *
 *  @author Ethan Hammond
 ******************************************************************************/
import java.util.Arrays;
import java.lang.*;
import java.io.*;

public class Rpeasant {

    
    private Rpeasant() {}

    public static double algor(double a, double b) {
    
        double result = 0;
  
        if(a % 2 != 0){
            
            result = result + b;

        }
            
        System.out.println("Multiplicand Multiplier Product");
        System.out.println("\t"+a+"\t"+b+"\t"+result);

        while(a!=1)
        {
            a= a/2;
            b= b*2;

            if(a%2!=0){
                result = result + b;
            }

            System.out.println("\t"+a+"\t"+b+"\t"+result);
        }
    
            return result;

        

    } 


    public static void main(String[] args)  { 
        
        Console console = System.console();
        System.out.print("Enter the first number:");

        double number1 = Double.parseDouble(console.readLine());

        System.out.print("Enter the second number: ");
        double number2 = Double.parseDouble(console.readLine());

        //Helper file to measure time count function takes to run
        Stopwatch timer = new Stopwatch();
        
        StdOut.println("elapsed time = " + timer.elapsedTime());
        StdOut.println("The result is: " + algor(number1, number2));
    } 
}


