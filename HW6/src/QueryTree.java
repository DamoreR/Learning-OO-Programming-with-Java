import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

/**
 * QueryTree is tree of nodes that hold questions/items for a guessing game.
 * In this tree, the answer "no" leads to the node to the left while 
 * the answer "yes" leads to the node to the right.
 * 
 * @author: Robert Damore
 * @version: Winter 2016
 */

public class QueryTree {
	TreeNode overallRoot, current;	//These nodes keep up with starting node and current node
	/**
	 * Creates QueryTree with one node.
	 * This node contains the item, "computer".
	 */
	public QueryTree(){
		overallRoot = new TreeNode("computer", null, null);
		current = overallRoot;
	}
	/**
	 * This method should always be called before calling nextQuestion().
	 * @return true if there is another question to ask.
	 */
	public boolean hasNextQuestion(){
		if(current.left!=null&&current.right!=null)
			return true;
		return false;
	}
	/**
	 * This method should only be called when hasNextQuestion has 
	 * been called first and returns true.
	 * @return the next question to ask the user.
	 * @throws WrongTreeStateException if QueryTree is not currently on a question node.
	 */
	public String nextQuestion() throws WrongTreeStateException{
		if(!hasNextQuestion())
			throw new WrongTreeStateException("There is not another question");
		return current.data;
	}
	/**
	 * Moves to the next node in this QueryTree based on the user response.
	 * Response should be 'y' or 'n', but is not case sensitive. This method 
	 * should only be called after successfully calling nextQuestion().
	 * @param input user response to a question.
	 * @throws WrongTreeStateException if QueryTree is not currently on a question node.
	 */
	public void userResponse(char input) throws WrongTreeStateException{
		if(current.left==null||current.right==null)
			throw new WrongTreeStateException("Already in final guess state");
		if(input == 'y' || input == 'Y')
			current = current.right;
		if(input == 'n' || input == 'N')
			current = current.left;
	}
	/**
	 * This method should only be called when hasNextQuestion() has been
	 * called first and returns false.
	 * @return the guess of this QueryTree.
	 * @throws WrongTreeStateException if QueryTree is not currently on an answer node. 
	 */
	public String finalGuess() throws WrongTreeStateException{
		if(current.left!=null||current.right!=null)
			throw new WrongTreeStateException("Not in final guess state");
		else
			return current.data;
	}
	/**
	 * Moves the current node pointer back to the starting node. 
	 * The Query Tree is not changed in the process. Any "learned"
	 * nodes will still be there. 
	 */
	public void reset(){
		current = overallRoot;
	}
	/**
	 * Adds new question node to QueryTree that distinguishes between the 
	 * final guess and the user's item. The final guess and new item
	 * are attached to that new question. This method should only be called
	 * immediately after and incorrect final guess. 
	 * @param q new question.
	 * @param item new item.
	 * @param input yes/no answer to new question for new item. Not case sensitive.
	 */
	public void updateTree(String q, String item, char input) throws WrongTreeStateException{
		if(current.left!=null||current.right!=null)
			throw new WrongTreeStateException("Not in final guess state");
		if(input == 'y' || input == 'Y'){
			current.left = new TreeNode(current.data, null, null);
			current.right = new TreeNode(item, null, null);
			current.data = q;
		}
		if(input == 'n' || input == 'N'){
			current.left = new TreeNode(item, null, null);
			current.right = new TreeNode(current.data, null, null);
			current.data = q;
		}
		reset();
	}
	/**
	 * Reads in a new set of questions/items into this QueryTree from the given file, 
	 * replacing whatever was currently in the QueryTree. This should be a txt file, 
	 * and each question should be preceded by "Q:" on the line above it while each
	 * item is preceded by "A:" on the line above it. The questions and items
	 * should also be in preorder. 
	 * @param f txt file to be used
	 * @throws FileNotFoundException
	 */
	public void readIn(File f) throws FileNotFoundException{
		if(f.exists()){
			Scanner input = new Scanner(f);
			overallRoot = treeFromFile(input);
			reset();
			input.close();
		}else
			throw new FileNotFoundException();
	}
	/*
	 * Recursive helper method for iterating through lines of file while adding nodes 
	 * to the QueryTree in preorder.
	 */
	private TreeNode treeFromFile(Scanner input){
		if(input.hasNext()){
			String type = input.next().trim();
			input.nextLine();
			String data = input.nextLine().trim();
			//if on an answer line, add an item node, else add a question node and get 
			//the next two connecting nodes. 
			if(type.equals("A:"))
				return new TreeNode(data, null, null);
			else
				return new TreeNode(data, treeFromFile(input), treeFromFile(input));
		}else
			return null;
	}
	/**
	 * Write the current set of questions/items in this QueryTree out to the given file in 
	 * preorder. Does not change the current state.
	 * @param f file to be written to.
	 * @throws FileNotFoundException
	 */
	public void writeOut(File f) throws FileNotFoundException{
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(f));
			fileFromTree(overallRoot, writer);
			writer.close();
		}catch(IOException io){
			io.printStackTrace();
		}
	}
	/*
	 * Recursive helper method for iterating through nodes in preorder and 
	 * writing each one to the file.
	 */
	private void fileFromTree(TreeNode node, Writer writer){
		try{
			if(node.left==null||node.right==null){
				writer.write("A:\n");
				writer.write(node.data + "\n");
			}else{
				writer.write("Q:\n");
				writer.write(node.data + "\n");
				fileFromTree(node.left, writer);
				fileFromTree(node.right, writer);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	/*
	 * Private class for nodes used in the QueryTree.
	 */
	private class TreeNode {
		String data;
		TreeNode left, right;
		TreeNode(String data, TreeNode left, TreeNode right){
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}
}
