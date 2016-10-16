import java.awt.*;
import javax.swing.*;

/**
 * This is a JPanel customized for displaying and changing
 * the level of a KochCurve. 
 * 
 * @author Robert Damore
 *
 */

@SuppressWarnings("serial")
public class LevelView extends JPanel {

	private JButton upB, downB;		//up and down buttons
	private JLabel levelNum;		//label for current level

	/**
	 * Creates a JPanel with an orange background
	 * that contains JButton, JLabel, JButton, 
	 * in that order
	 */
	public LevelView(){
		super();
		setBackground(Color.ORANGE);

		upB = new JButton("Up");
		downB = new JButton("Down");
		levelNum = new JLabel("Level: " + 0);

		add(upB);
		add(Box.createRigidArea(new Dimension(100, 0)));
		add(levelNum);
		add(Box.createRigidArea(new Dimension(100, 0)));
		add(downB);

		validate();
	}

	/**
	 * @return up button
	 */
	public JButton getUpB(){
		return upB;
	}

	/**
	 * @return down button
	 */
	public JButton getDownB(){
		return downB;
	}

	/**
	 * @return level label
	 */
	public JLabel getLabel(){
		return levelNum;
	}

}
