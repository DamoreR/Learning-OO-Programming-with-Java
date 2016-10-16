
public class Manager extends Employee {

	
	private double salary;
	
	public Manager(String fN, String lN, int empID, double s) {
		super(fN, lN, empID);
		this.setSalary(s);
	}

	@Override
	public double calculateWeeklyPay() {
		return this.getSalary() / 52;
	}

	public double getSalary() {
		return this.salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}


}
