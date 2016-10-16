import java.awt.*;
import javax.swing.*;

/**
 * This is a JPanel that has been customized to serve as a drawing
 * board for a KochCurve. In this class, all parts of the GUI are
 * set up and made visible. 
 * 
 * @author Robert Damore
 *
 */

@SuppressWarnings("serial")
public class MainView extends JPanel {

	private Controller mc;		//controller for this view

	/**
	 * Creates a JPanel with a yellow background
	 */
	public MainView() {    
		super();
		setBackground(Color.YELLOW);
	}

	/**
	 * Updates the graphics of this JPanel
	 */
	public void update(){
		repaint();
	}

	/**
	 * This paints the KochCurve onto the JPanel.
	 * If the user has clicked once for a starting point,
	 * the old curve is erased and text appears asking
	 * for and end point. Once the user clicks for a second 
	 * time, the text is erased and new KochCurve is drawn.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (mc.getSecondClick() != null){

			if (!mc.isCurveSet())
				mc.drawNewCurve(g);
			else
				mc.updateCurve(g);
		}else if (mc.getFirstClick() != null){
			g.drawString("Click again for endpoint of line", this.getWidth()/4, this.getHeight()/2);
		}else{
			mc.drawNewCurve(g);
		}
	}

	/**
	 * Create the two views and display them on a JFrame
	 * with a new KochCurve at level 0. Also adds
	 * mouse listener and action listeners.
	 */
	public static void play()
	{

		MainView mv = new MainView();
		LevelView lv = new LevelView();

		mv.setController(new Controller(mv, lv));
		mv.addMouseListener(mv.mc);

		lv.getUpB().addActionListener(mv.mc);
		lv.getDownB().addActionListener(mv.mc);

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 700);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));	
		mv.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight() - 40));
		lv.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight() - mv.getPreferredSize().height));
		frame.getContentPane().add(mv);
		frame.getContentPane().add(lv);
		frame.validate();
		frame.setResizable(false);
		frame.setVisible(true);
	}

	/**
	 * To be able to run from the command line or desktop
	 */
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				play();
			}
		});
	}

	/**
	 * Sets the Controller for this MainView
	 * @param mc Controller for this MainView
	 */
	private void setController(Controller mc){
		this.mc = mc;
	}
}