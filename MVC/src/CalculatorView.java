import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class CalculatorView extends JFrame{

	private JTextField firstNumT = new JTextField(10);
	private JTextField secondNumT = new JTextField(10);
	private JTextField solutionT = new JTextField(10);
	private JLabel add = new JLabel("+");
	private JButton calcB = new JButton("Calculate");
	
	public CalculatorView(){
		JPanel panel = new JPanel();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(600,200);
		panel.add(firstNumT);
		panel.add(add);
		panel.add(secondNumT);
		panel.add(calcB);
		panel.add(solutionT);
		this.add(panel);
	}
	
	public int getFirstNumber(){
		return Integer.parseInt(firstNumT.getText());
	}
	
	public int getSecondNumber(){
		return Integer.parseInt(secondNumT.getText());
	}
	
	public void setSolution(int solution){
		solutionT.setText(Integer.toString(solution));
	}
	
	public void addCalculateListener(ActionListener listenForCalcB){
		calcB.addActionListener(listenForCalcB);
	}
	
	public void displayErrorMessage(String error){
		JOptionPane.showMessageDialog(this, error);
	}
}
