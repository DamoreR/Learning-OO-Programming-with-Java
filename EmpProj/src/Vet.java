
public class Vet extends Employee {

	private int vacNum;
	
	public Vet(String fN, String lN, int empID, int vacNum) {
		super(fN, lN, empID);
		this.setVacNum(vacNum);
	}

	@Override
	public double calculateWeeklyPay() {
		return 800 + (3.75 * this.getVacNum());
	}

	public int getVacNum() {
		return this.vacNum;
	}

	public void setVacNum(int vacNum) {
		this.vacNum = vacNum;
	}

}
