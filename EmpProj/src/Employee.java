public abstract class Employee implements Comparable<Employee>{

	private String fName;
	private String lName;
	private int empNum;

	public abstract double calculateWeeklyPay();

	public Employee(String fN, String lN, int empID)
	{
		this.setfName(fN);
		this.setlName(lN);
		this.setEmpNum(empID);
	}

	public String toString()
	{
		String S = this.empNum + "   " + this.fName + " " + this.lName;
		return S;
	}

	@Override
	public int compareTo( final Employee e) {
		return Integer.compare(this.empNum, e.empNum);
	}

	public String getfName() {
		return this.fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return this.lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public int getEmpNum() {
		return this.empNum;
	}

	public void setEmpNum(int empNum) {
		this.empNum = empNum;
	}

}