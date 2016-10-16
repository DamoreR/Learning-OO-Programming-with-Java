import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

/**
 * GrammarSolver organizes the lines of non-terminal rules
 * and generates random possible lines using them
 * 
 * @author: Robert Damore
 * @version: Winter 2016
 */

public class GrammarSolver {
	//map of non-terminals and their rules
	private TreeMap<String,ArrayList<String[]>> grammar = new TreeMap<String,ArrayList<String[]>>();	//map of non-terminals and their rules
	private Random ran = new Random();

	/**
	 * Creates GrammarSolver by organizing non-terminals into a TreeMap
	 * @param grammar lines of non-terminal rules
	 * @throws IllegalArgumentException When non-terminals are repeated
	 */
	public GrammarSolver(List<String> grammar) throws IllegalArgumentException{
		//loops through each line, breaking them into the parts of the non-terminal rule
		for(String s: grammar){
			String[] keyAndVals = new String[2];
			keyAndVals = s.split(":");
			String key = keyAndVals[0].trim();

			if(grammarContains(key))
				throw new IllegalArgumentException("Repeated non-terminals not allowed. Try again.");

			String[] vals = keyAndVals[1].split("[|]");
			for(int i=0; i<vals.length; i++){
				vals[i] = vals[i].trim();
			}

			ArrayList<String[]> values = new ArrayList<String[]>();

			for(int i = 0; i < vals.length; i++){
				String[] possRule = vals[i].split("[ \t]+");
				values.add(possRule);
			}
			this.grammar.put(key, values);
		}
	}

	/**
	 * @return String of all the non-terminal symbols
	 */
	public String getSymbols() {
		String symbols = "[";

		for(String key: this.grammar.keySet()){
			symbols += (key + ", ");
		}
		return symbols.substring(0, symbols.length()-2) + "]";
	}

	/**
	 * @param symbol non-terminal symbol
	 * @return true if symbol is a non-terminal in grammar
	 */
	public boolean grammarContains(String symbol) {
		return this.grammar.containsKey(symbol);
	}

	/**
	 * 
	 * @param symbol non-terminal symbol
	 * @param times number of possible lines to generate
	 * @return String[] of generated lines
	 * @throws IllegalArgumentException when symbol is not a non-terminal, or when times is 0
	 */
	public String[] generate(String symbol, int times) throws IllegalArgumentException{

		if(!grammarContains(symbol))
			throw new IllegalArgumentException("Non-terminal not found.");
		if(times==0)
			throw new IllegalArgumentException("Can't show anything 0 times.");

		String[] lines = new String[times];

		do{
			//get random rule for given non-terminal
			ArrayList<String[]> rules = new ArrayList<String[]>();
			rules = this.grammar.get(symbol);
			String[] rule = rules.get(ran.nextInt(rules.size()));
			String line = "";
			//loops through each part of rule
			//adds it to the line if it's terminal
			//uses getTerminals if it's non-terminal
			for(int i=0; i<rule.length; i++){
				if(!grammarContains(rule[i])){
					if(!rule[i].equals(" ")){
						line+=rule[i];
						line+=" ";
					}
				}else{
					line+=(getTerminals(rule[i]));
				}
			}
			lines[--times]=line;
		}while(times>=1);
		return lines;
	}

	/*
	 * Recursively gets the terminals from the given non-terminal
	 * and returns them in one string
	 */
	private String getTerminals(String key){
		String terminals = "";
		ArrayList<String[]> rules = new ArrayList<String[]>();
		rules = this.grammar.get(key);
		String[] rule = rules.get(ran.nextInt(rules.size()));
		for(int i=0; i<rule.length; i++){
			if(!grammarContains(rule[i])){
				if(!rule[i].equals(" ")){
					terminals+=rule[i];
					terminals+=" ";
				}	
			}else{
				terminals+=(getTerminals(rule[i]));
			}
		}
		return terminals;
	}
}
