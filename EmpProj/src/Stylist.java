
public class Stylist extends Employee {

	private double pRate;
	private int apNum;
	
	public Stylist(String fN, String lN, int empID, double pRate, int apNum) {
		super(fN, lN, empID);
		this.setpRate(pRate);
		this.setApNum(apNum);
	}

	@Override
	public double calculateWeeklyPay() {
		return this.getpRate() * this.getApNum();
	}

	public double getpRate() {
		return this.pRate;
	}

	public void setpRate(double pRate) {
		this.pRate = pRate;
	}

	public int getApNum() {
		return this.apNum;
	}

	public void setApNum(int apNum) {
		this.apNum = apNum;
	}

}
