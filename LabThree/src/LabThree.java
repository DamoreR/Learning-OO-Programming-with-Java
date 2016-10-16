import javax.swing.JOptionPane;

class LabThree{
	public static void main(String[] args){
		
		double input1, input2;
		double small, large, power, sqrrt, summed;
		long rounded;
		
		input1 = Double.parseDouble(JOptionPane.showInputDialog(null, 
				"Enter a number between 0 and 10."));
		
		input2 = Double.parseDouble(JOptionPane.showInputDialog(null, 
				"Enter one more number between 0 and 10."));
		
		small = Math.min(input1, input2);
		
		large = Math.max(input1, input2);
		
		power = Math.pow(input1, input2);
		
		summed = input1 + input2;
		
		sqrrt = Math.sqrt(summed);
		
		rounded = Math.round(summed);
		
		JOptionPane.showMessageDialog(null, "The largest number is: "
				+ large + "\nThe smallest number is: " + small +
				"\n" + input1 + "^" + input2 + " = " + power +
				"\nThe square root of the sum is: " + sqrrt +
				"\n" + summed + " rounded to the nearest integer is: "
				+ rounded);
		
		
	}
}