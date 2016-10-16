import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class HangmanController extends KeyAdapter implements ActionListener {

	private HangmanModel hm;
	private HangmanView hv;
	
	public HangmanController(HangmanView view){
		hm = new HangmanModel();
		hv = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("New")){
			hm.reset();
			hv.updateHangman();
		}
	}
	
	public void keyPressed(KeyEvent e) {
		hm.guess(e.getKeyChar());
		hv.updateHangman();
	}
	
	public int getPicNum(){
		return hm.getNumGuessesLeft();
	}
	
	public String getGuessWord(){
		return hm.getGuessWord();
	}
	
	public int getGuessesLeft(){
		return hm.getNumGuessesLeft();
	}
	
	public Object getState(){
		return (String) hm.getState();
	}
	
	public String getWord(){
		return hm.getSecretWord();
	}
	
	public boolean hasGuessed(char c){
		return hm.hasGuessed(c);
	}

}
