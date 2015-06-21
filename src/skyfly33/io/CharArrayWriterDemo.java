package skyfly33.io;

import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class CharArrayWriterDemo {
	public static void main(String args[]) throws IOException { 
		char c[] = {'H','e','l','l','o',' ','W','o','r','l','d','!'}; 
		CharArrayWriter out = new CharArrayWriter(); 
		out.write(c);
		FileWriter f1 = new FileWriter(new File("./a.txt"));
		out.writeTo(f1); //File written successfully.
		
		FileWriter f2 = new FileWriter(new File("./b.txt"));
		out.writeTo(f2); //File written successfully.
		
		f1.close();
		f2.close();
		
		//CharArrayWriter is closed.
		out.close();

		FileWriter f3 = new FileWriter(new File("./c.txt"));
		//Write again to a file. No Exception from CharArrayWriter but no data will be written.
		out.writeTo(f3);
		
		System.out.println("Done");
	} 
} 
