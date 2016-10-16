import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class HangmanView extends JFrame{
	
	HangmanController hc;	
	DrawPanel drawPanel;
	
	public HangmanView(){
		
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setSize(new Dimension(450, 450));
		
		hc = new HangmanController(this);
		
		JPanel topButtons = new JPanel();
		
		JButton infoB = new JButton("Info");
		JButton startB = new JButton("Start");
		JButton stopB = new JButton("Stop");
		JButton exitB = new JButton("Exit");
		
		infoB.addActionListener(hc);
		startB.addActionListener(hc);
		stopB.addActionListener(hc);
		exitB.addActionListener(hc);
		
		topButtons.add(infoB);
		topButtons.add(startB);
		topButtons.add(stopB);
		topButtons.add(exitB);
		
		JPanel gamePanel = new JPanel(new BorderLayout());
		
		drawPanel = new DrawPanel();
		JButton newB = new JButton("New");
		newB.setActionCommand("New");
		
		drawPanel.setBackground(Color.WHITE);
		newB.addActionListener(hc);
		
		gamePanel.add(drawPanel, BorderLayout.CENTER);
		gamePanel.add(newB, BorderLayout.SOUTH);
		gamePanel.setPreferredSize(new Dimension(this.getWidth(), this.getHeight() - 100));
		gamePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		add(topButtons, BorderLayout.NORTH);
		add(gamePanel, BorderLayout.SOUTH);
	}
	
	public static void play()
	{
		HangmanView frame = new HangmanView();
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
	
	public void updateHangman(){
		drawPanel.update();
	}
	
	@SuppressWarnings("serial")
	public class DrawPanel extends JPanel{
	
		public DrawPanel(){
			super();
			addKeyListener(hc);
		}
		
		public void update(){
			repaint();
		}
		
		public void paintComponent(Graphics g) {
			
			requestFocus();
			super.paintComponent(g);
			g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
			
			String imageName = "images/hangman" + hc.getPicNum() + ".gif";
			BufferedImage img = null;
			
			try {
			    img = ImageIO.read(new File(imageName));
				g.drawImage(img, (this.getWidth()/2) - (img.getWidth()/2), 10, null);
			} catch (IOException e) {
			}
			
			String guessWord = hc.getGuessWord();
			int guessLeft = hc.getGuessesLeft();
			String state = (String) hc.getState();
			char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
			
			g.drawString(guessWord, (this.getWidth()/2) - (g.getFontMetrics().stringWidth(guessWord) / 2), this.getHeight() - 60);
			
			if (state.equals(HangmanModel.IN_PROGRESS)){
				String guesses = guessLeft + " guesses left!";
				g.drawString(guesses, (this.getWidth()/2) - (g.getFontMetrics().stringWidth(guesses) / 2), this.getHeight() - 40);
			}else if (state.equals(HangmanModel.GAME_LOST)){
				String lost = "Game Over (" + hc.getWord() + ")";
				g.drawString(lost, (this.getWidth()/2) - (g.getFontMetrics().stringWidth(lost) / 2), this.getHeight() - 40);
			}else if (state.equals(HangmanModel.GAME_WON)){
				String won = "You won with " + hc.getGuessesLeft() + " guesses left!";
				g.drawString(won, (this.getWidth()/2) - (g.getFontMetrics().stringWidth(won) / 2), this.getHeight() - 40);
			}
			
			for (int i = 0; i < alphabet.length; i++){
				g.setColor(Color.BLACK);
				if (hc.hasGuessed(alphabet[i]))
					g.setColor(Color.BLUE);
				g.drawChars(alphabet, i, 1, (this.getWidth()/2) - (364 / 2) + (i*14), this.getHeight() - 20);
			}
			
		}
		
		
	}
	
	
}
