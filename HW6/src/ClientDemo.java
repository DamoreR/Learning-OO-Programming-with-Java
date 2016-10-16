import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * ClientDemo will take the user through the game, using the QueryTree.
 * This class also demonstrates the conditions and order in which 
 * QueryTree methods should be called. An existing, pre-named file
 * is used for loading and saving a tree. 
 * 
 * @author: Robert Damore
 * @version: Winter 2016
 */

public class ClientDemo {
	public static void main(String[] args) {
		final File f = new File("Tree.txt");	//fixed name file that exists in same directory as source folder
		Scanner input = new Scanner(System.in);		
		String response, newQuestion, newQAnswer;
		QueryTree t = new QueryTree();
		boolean playAgain = true;
		//First, ask the user if they want to load a previously saved tree.
		System.out.println("Do you want to use the last saved tree? (y/n)");
		response = input.nextLine().toLowerCase().trim();
		if(response.charAt(0) == 'y'){
			try{
				t.readIn(f);
			}catch(FileNotFoundException e){
				System.out.println("File was not found. Current tree will be used instead.");
			}
		}
		while(playAgain){
			//Next, tell the user to think of an object
			System.out.println("Think of an object...");
			System.out.println("Hit Enter to continue");
			response = input.nextLine();
			try{	
				//This type of loop insures there is a question to be asked when calling nextQuestion() method.
				//It also gets a user response when there is a question to be asked. 
				while(t.hasNextQuestion()){
					System.out.println(t.nextQuestion());
					response = input.nextLine();
					t.userResponse(response.charAt(0));
				}
				//When there are no more questions, make a final guess.
				System.out.println("Is your object a " + t.finalGuess() + "? (y/n)");
				response = input.nextLine().toLowerCase().trim();
				//If the guess is incorrect, ask the user about that guess in order to update the tree.
				if(response.charAt(0) == 'n'){
					System.out.println("What was your object?");
					response = input.nextLine().toLowerCase().trim();
					System.out.println("What is a yes/no question that distinguishes between the object I guessed and yours? (Please use correct grammar)");
					newQuestion = input.nextLine().trim();
					System.out.println("What would be the answer to that question for your object?  (y/n)");
					newQAnswer = input.nextLine().toLowerCase().trim();
					t.updateTree(newQuestion, response, newQAnswer.charAt(0));
				}else{
					System.out.println("Yay! I got it right!");
					t.reset();
				}
			}catch(WrongTreeStateException e){
				e.printStackTrace();
			}
			//Ask user if they want to play again.
			System.out.println("Do you want to try again?");
			response = input.nextLine().toLowerCase().trim();
			if(response.charAt(0) != 'y'){
				playAgain = false;
				input.close();
			}
		}
		//Save the tree to a file before terminating.
		try{
			t.writeOut(f);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}
}
