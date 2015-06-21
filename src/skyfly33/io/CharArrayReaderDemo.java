package skyfly33.io;

import java.io.CharArrayReader;
import java.io.IOException;
public class CharArrayReaderDemo {
	public static void main(String args[]) throws IOException { 
		char c[] = {'H','e','l','l','o',' ','W','o','r','l','d','!'}; 
		CharArrayReader chARedOne = new CharArrayReader(c); 
		CharArrayReader chARedTwo = new CharArrayReader(c, 0, 5); 
		int i; 
		while((i = chARedOne.read()) != -1) { 
			System.out.print((char)i); 
		} 
		System.out.println(); 
		while((i = chARedTwo.read()) != -1) { 
		    System.out.print((char)i); 
		} 
	} 
} 
