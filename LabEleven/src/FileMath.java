import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/** This class defines a method, {@link FileMath#main}, that will read numbers from a file and perform
 * arithmetic operations on them.
 * @author Sean Holden (holdens@my.erau.edu), Oct 2014
 */
public class FileMath {

	/** Read numbers from a file and perform arithmetic operations.
	 * @param args Command-line arguments (unused)
	 */
	public static void main(String[] args)  {

		String inFilename;
		File inputFile;
		List<Integer> ints;

		// Ask the user for the name of a file to read
		System.out.println("Enter the name of the file to read: ");
		Scanner in = new Scanner(System.in);
		inFilename = in.nextLine();
		in.close();

		// Create the input file object
		inputFile = new File(inFilename);

		// Get the list of integers from the file
		
		try{
			ints = parseInts(inputFile);
			double avg = average(ints);
			System.out.println(avg);
		}catch(FileNotFoundException e){
			System.out.println("File not Found!");
			System.exit(0);
		}catch(EmptyListException e2){
			System.out.println(e2.getMessage());
		}



	}

	/** Parses a list of integers from a given file.
	 * @param file The file to read
	 * @return The list of integers in the file
	 */
	public static List<Integer> parseInts(File file) throws FileNotFoundException{
		List<Integer> ints = new ArrayList<Integer>();

		// Create the pipeline that allows us to read the file
		Scanner fileScanner = new Scanner(file);

		// Get the integers from the file and add them to the list
		while (fileScanner.hasNextInt()){
			ints.add(fileScanner.nextInt());
		}

		// Close the stream
		fileScanner.close();

		// Return the numbers list
		return ints;
	}
	
	
	public static double average(List<Integer> ints) throws EmptyListException{
		if(ints.size()==0)
			throw new EmptyListException();
		else
			return sum(ints) / ints.size();
	}
	
	/** Sums the integer elements of a List
	 * @param ints The list of integers to add
	 * @return The sum, as a double
	 */
	private static double sum(List<Integer> ints){
		int sum = 0;

		// For-each loop to add the value of each element to the total
		// For more on for-each loops and their usage, check out:
		// http://docs.oracle.com/javase/1.5.0/docs/guide/language/foreach.html
		for(Integer i : ints){
			sum += i;
		}
		
		return sum;
	}

}
