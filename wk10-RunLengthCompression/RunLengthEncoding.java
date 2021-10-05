import util.StdOut;



public class RunLengthEncoding {
    
    public static void main(String[] args) {
        
        if (args.length != 0) {
            
            StdOut.println("Input:" + args[0] + "\n");
            StdOut.println("output:" + RLE(args[0]));
        }
        
    }
    
    
    public static String RLE(String input) {
        
        input += " ";
        String output = " ";
        int counter = 1;
        
        if (input.length() > 1) {
            
            for (int i = 1; i < input.length(); i++) {
                
                if (input.charAt(i) == input.charAt(i - 1)) {
                    
                    counter++;
                } else {
                    
                    output += input.charAt(i - 1);
                    
                    if (counter > 1) {
                        
                        output += counter;
                    } else {
                        
                        output += 1;
                    }
                    
                    counter = 1;
                }
                
            }
            
            return output;
        } else {
            
            return input;
        }
        
    }
    
}