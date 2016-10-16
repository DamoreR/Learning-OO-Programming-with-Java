
public class PenStuff {
	
	int Type=0;
	int ColorofPen=0;
	int capping=0;
	int clicks=0;
	int costofPen=0;
	
	
	void PenType(int newValue){
		Type = newValue;
	}
	
	void PenColor(int newValue){
		ColorofPen = newValue;
	}
	
	void Capped(int newValue){
		capping = newValue;
	}
	
	void Clicking(int newValue){
		clicks = newValue;
	}
	
	void PenCost(){
		costofPen = Type*ColorofPen+capping+clicks;
	}
	
	void ShowCostofPen(){
		System.out.println("The cost of the Pen is: " + costofPen);
	}
}

