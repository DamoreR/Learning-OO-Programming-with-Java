import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorController{

	private CalculatorModel model;
	private CalculatorView view;
	
	public CalculatorController(CalculatorView view, CalculatorModel model){
		this.model = model;
		this.view = view;
		this.view.addCalculateListener(new CalculateListener());
	}
	
	class CalculateListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			int firstNumber, secondNumber = 0;
			try{
				firstNumber = view.getFirstNumber();
				secondNumber = view.getSecondNumber();
				model.addTwoNumbers(firstNumber, secondNumber);
				view.setSolution(model.getCalcValue());
			}catch(NumberFormatException ex){
				view.displayErrorMessage(ex.getMessage());
			}
		}
	}
}
