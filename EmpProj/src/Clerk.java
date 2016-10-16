
public class Clerk extends Employee {

	private double hPay;
	private int hoursWorked;
	
	public Clerk(String fN, String lN, int empID, double hPay, int hoursWorked) {
		super(fN, lN, empID);
		this.sethPay(hPay);
		this.setHoursWorked(hoursWorked);
	}

	@Override
	public double calculateWeeklyPay() {
		
		if(this.getHoursWorked() > 40)
			return (this.gethPay() * this.getHoursWorked()) + (1.5 * this.gethPay() * (this.getHoursWorked()-40));
		else
			return this.gethPay() * this.getHoursWorked();
	}

	public double gethPay() {
		return this.hPay;
	}

	public void sethPay(double hPay) {
		this.hPay = hPay;
	}

	public int getHoursWorked() {
		return this.hoursWorked;
	}

	public void setHoursWorked(int hoursWorked) {
		this.hoursWorked = hoursWorked;
	}



}
