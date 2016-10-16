import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import javax.swing.JOptionPane;

/**
 * This class acts as the controller connecting the GUI to the 
 * KochCurve model. It extends MouseAdapter so that any clicks 
 * on the drawing board will be used to create a new curve. 
 * To draw and create a KochCurve, it implements UseKochCurve.
 * It also implements ActionListener so that it can update 
 * the curve when the level is changed with up and down buttons.
 * 
 * @author Robert Damore
 *
 */

public class Controller extends MouseAdapter implements ActionListener, UseKochCurve{

	private KochCurve kc;								//current KochCurve
	private MainView mv;								//drawing board view
	private LevelView lv;								//view for showing level
	private Point2D.Double firstClick, secondClick;		//start and end points
	private int clickCounter, level;					//counter for clicking start and end point
														//local int for level of current KochCurve

	/**
	 * Creates a Controller that sets up an initial pair of start
	 * and end points for the first KochCurve. 
	 * 
	 * @param mv view for drawing the KochCurve on
	 * @param lv view for updating the current level
	 */
	public Controller(MainView mv, LevelView lv) {
		super();
		firstClick = new Point2D.Double(100, 300);
		secondClick = new Point2D.Double(900, 300);
		this.mv = mv;
		this.lv = lv;
		clickCounter = 1;
		level = 0;
	}

	/**
	 * Updates the start and end points every time the mouse is 
	 * clicked in the drawing view
	 */
	public void mouseClicked(MouseEvent e) {
		switch (clickCounter){
		case 1:
			if (firstClick != null)
				resetClicks();
			firstClick = new Point2D.Double(e.getX(), e.getY());
			mv.update();
			clickCounter++;
			break;
		case 2:
			secondClick = new Point2D.Double(e.getX(), e.getY());
			mv.update();
			clickCounter--;
			break;
		}
	}

	/*
	 * Resets everything for a new KochCurve to be drawn
	 */
	private void resetClicks(){
		firstClick = null;
		secondClick = null;
		kc = null;
		lv.getLabel().setText("Level: " + 0);
	}

	/**
	 * Draws a new KochCurve on the drawing view.
	 */
	public void drawNewCurve(Graphics g){
		kc = new KochCurve(firstClick, secondClick, g);

	}

	/**
	 * Updates the KochCurve with a new level after
	 * the up or down buttons are pressed
	 */
	public void updateCurve(Graphics g){
		kc.updateCurve(level, g);
	}

	/**
	 * When either the up or down buttons are pressed this changes the level.
	 * The level cannot be changed to a negative number, or a number 
	 * larger than 6. An error message is displayed when the user tries to. 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (kc != null){
			if (e.getSource().equals(lv.getUpB())){
				if(kc.getLevel() < 6){	
					level = 1 + kc.getLevel();
					lv.getLabel().setText("Level: " + level);
					mv.update();
				}else{
					JOptionPane.showMessageDialog(mv, "Upper level limit is 6");
				}
			}else if (e.getSource().equals(lv.getDownB())){
				if (kc.getLevel() > 0){	
					level = kc.getLevel() - 1;
					lv.getLabel().setText("Level: " + level);
					mv.update();
				}else{
					JOptionPane.showMessageDialog(mv, "Lower level limit is 0");
				}
			}
		}
	}

	/**
	 * @return start point of KochCurve
	 */
	public Point2D.Double getFirstClick(){
		return firstClick;
	}

	/**
	 * @return end point of KochCurve
	 */
	public Point2D.Double getSecondClick(){
		return secondClick;
	}

	/**
	 * @return true if this KochCurve is not null
	 */
	public boolean isCurveSet(){
		if (kc == null){
			return false;
		}else{
			return true;
		}
	}
}
