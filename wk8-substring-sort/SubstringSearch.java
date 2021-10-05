import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class SubstringSearch {
	public static void main(String args[]) {
		
		String pattern="mismatch";
		File file=new File("Substring_Search.txt");
		
		try {
		
			Scanner sc= new Scanner(file);
			Stopwatch timer = new Stopwatch();
			int lCount=0;
			
			while(sc.hasNextLine()) {
			
				String line = sc.nextLine();
				lCount++;
				
				//Test the brute force pattern
				int index = bruteForce(line, pattern);
				
				if(index!=line.length()) {
					System.out.println("brute force found " + pattern + " at index "+ index + " on the line " + lCount + "\nAfter " + timer.elapsedTime());
				}

			}
		
		
			sc.close();
			sc = new Scanner(file);
			
			timer = new Stopwatch();
			lCount = 0;
			
			while(sc.hasNextLine()) {
				
				String line=sc.nextLine();
				lCount++;
				
				int index = KMP(line, pattern);
				
				if(index!=line.length()) {
					System.out.println("KMP algorithm found " + pattern + " at index "+ index+ " on the line " + lCount + "\nAfter " + timer.elapsedTime());
				}
			}
			
			sc.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static int bruteForce(String text, String pattern) {
		
		int n = text.length();
		int m = pattern.length();
		int j;

		for (int i = 0; i < n-m; i++ )             
		{
			for(j = 0; j < m; j++) {
				if(text.charAt(i+j) != pattern.charAt(j)) {
					break;
				}
			}

			if(j == m) {
				return i;
			}
		}
		
		return n; //pattern not found
	}
	
	public static int KMP(String text, String pattern) {
		
		int m = pattern.length();
		int n = text.length();
  
		int lpsArray[] = new int [m]; 
		int i = 0;
		int j = 0; 
				  
		computeLPSArray(pattern, m, lpsArray);	  

		while (i < n) { 
			
			if (pattern.charAt(j) == text.charAt(i)) {
				i++;
				j++;
		 	} 
			
			if (j == m) 
			{ 

				return i-j;   

			} else if ((i < n) &&( pattern.charAt(j) != text.charAt(i))) { 
				
				if(j!=0) {
					j = lpsArray[j-1];
				}
				else {
					i++;
				}

			} 
		} 
		
		return n; //pattern not found
	}

	private static void computeLPSArray(String pattern,int m, int lpsArray []) {
		
		int len = 0;
		int i = 1;
		lpsArray[0] = 0;
		
		
		while(i<m) {
			
			if(pattern.charAt(i)==pattern.charAt(len)) {

				len++;
				lpsArray[i] = len;
				i++;
			
			} else {
				
				if(len!=0) {
					len = lpsArray[len-1];
				}
				else {
					lpsArray[i] = len;
					i++;
				}
			}
		}
	}
}
