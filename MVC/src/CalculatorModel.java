
public class CalculatorModel {

	private int calcValue;
	
	public void addTwoNumbers(int firstNumber, int secondNumber){
		calcValue = firstNumber + secondNumber;
	}
	
	public int getCalcValue(){
		return calcValue;
	}
	
	
}
