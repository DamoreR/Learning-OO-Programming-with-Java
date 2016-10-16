import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;


/** This class defines a method, {@link Copy#main}, that will copy the contents of one file into another file.
 * @author Unknown<br>
 * Modified by Sean Holden (holdens@my.erau.edu), Oct 2014
 */
public class Copy {

	/** Copy the contents of one file into another file.
	 * @param args Command-line arguments (unused)
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException  {
		
		int charCount=0;
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		String input = bf.readLine();
		
		// Create the File Objects
		File inputFile = new File(input);
		File outputFile = new File("outagain.txt");

		// Create the pipeline that allows us to read the file
		Scanner fileScanner = new Scanner(inputFile);
		PrintWriter printWriter = new PrintWriter(outputFile);

		BufferedWriter bfW = new BufferedWriter(printWriter);
		
		// Read the file character-by-character...
		while (fileScanner.hasNextLine()){
			// ...and write what it reads to the PrintWriter
			bfW.write(fileScanner.nextLine());
			bfW.newLine();
			charCount++;
		}

		/* Close our streams (this saves resources and makes 
		 * sure that we don't lose any buffered data). */
		bfW.close();
		printWriter.close();
		fileScanner.close();
		
		
		if(outputFile.exists()){
			System.out.println(inputFile.getAbsolutePath() + "     " + charCount + " Characters");
			System.out.println(outputFile.getAbsolutePath() + "     " + charCount + " Characters");
		}
		else{
			System.out.println("no copy made");
		}
	}
}
