
public class MVC {

	public static void main(String[] args){
		CalculatorModel model = new CalculatorModel();
		CalculatorView view = new CalculatorView();
		CalculatorController control = new CalculatorController(view, model);	
		
		view.setVisible(true);
	}
}
