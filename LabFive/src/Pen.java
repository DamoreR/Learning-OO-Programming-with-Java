
public class Pen {
	
	public static void main(String ars[]){
		PenStuff Pen1 = new PenStuff();
		PenStuff Pen2 = new PenStuff();
		
		Pen1.PenType(3);
		Pen1.PenColor(20);
		Pen1.Capped(0);
		Pen1.Clicking(1);
		Pen1.PenCost();
		Pen1.ShowCostofPen();
		
		Pen2.PenType(3);
		Pen2.PenColor(10);
		Pen2.Capped(1);
		Pen2.Clicking(1);
		Pen2.PenCost();
		Pen2.ShowCostofPen();
		
	}
}
