import java.text.DecimalFormat;
import java.util.ArrayList;

public class Employees implements EmployeesInterface{

	ArrayList<Employee> employees;
	int errorsFound;
	DecimalFormat df = new DecimalFormat("0.00");	
	
	public Employees(){
		this.employees = new ArrayList<Employee>();
		this.errorsFound = 0;
	}
	
	@Override
	public void addEmployee(int paycode, String fname, String lname, double first, int second, int empNum) {
		switch(paycode){
		case 1:
			Manager m = new Manager(fname, lname, empNum, first);
			this.employees.add(m);
			break;
		case 2:
			Clerk c = new Clerk(fname, lname, empNum, first, second);
			this.employees.add(c);
			break;
		case 3:
			Vet v = new Vet(fname, lname, empNum, second);
			this.employees.add(v);
			break;
		case 4:
			Stylist s = new Stylist(fname, lname, empNum, first, second);
			this.employees.add(s);
			break;
		default:
			errorsFound ++;
			break;
		}
	}

	@Override
	public double calculateWeeklyPay() {
		
		double sum = 0;
		
		for(Employee e: this.employees){
			sum += e.calculateWeeklyPay();
		}
		return sum;
	}

	@Override
	public int getNumManagers() {
		int counter = 0;
		
		for(Employee e: this.employees){
			if(e.getClass().isInstance(new Manager("", "", 1, 1)))
				counter++;
		}
		
		return counter;
	}

	@Override
	public int getNumClerks() {
		int counter = 0;
		
		for(Employee e: this.employees){
			if(e.getClass().isInstance(new Clerk("", "", 1, 1, 1)))
				counter++;
		}
		
		return counter;
	}

	@Override
	public int getNumVets() {
		int counter = 0;
		
		for(Employee e: this.employees){
			if(e.getClass().isInstance(new Vet("", "", 1, 1)))
				counter++;
		}
		
		return counter;
	}

	@Override
	public int getNumStylists() {
		int counter = 0;
		
		for(Employee e: this.employees){
			if(e.getClass().isInstance(new Stylist("", "", 1, 1, 1)))
				counter++;
		}
		
		return counter;
	}

	@Override
	public void sort() {
		
		if(this.employees.size()>1){
			for (int index = 1; index < this.employees.size(); index++)
			{
				Employee key = this.employees.get(index);
				int position = index;

				//  Shift larger values to the right
				while (position > 0 && key.compareTo(this.employees.get(position-1)) < 0)
				{
					this.employees.remove(position);
					this.employees.add(position-1, key);
					position--;
				}
			}
		}
	}

	@Override
	public String generateWeeklyReport() {
		
		
		String title = new String("WEEKLY PAY REPORT FOR PETE'S PETS" + "\n\n");
		String subTitles = new String("Employee                     Weekly Pay" + "\n");
		String data = this.toString();
		String total = new String("\nTotal Payroll: " + df.format(this.calculateWeeklyPay()) + "\n\n");
		String m = new String("Total # of Managers paid: " + this.getNumManagers() + "\n");
		String c = new String("Total # of Clerks paid: " + this.getNumClerks() + "\n");
		String v = new String("Total # of Vets paid: " + this.getNumVets() + "\n");
		String s = new String("Total # of Stylists paid: " + this.getNumStylists() + "\n");
		
		String report = new String(title + subTitles + data + total + m + c + v + s);
		
		return report;
	}
	
	//returns string of a list of all the employees with ID, Name, and weekly pay
	public String toString(){
		
		String s = new String();
		
		
		if(this.employees.size()>0){
			for(Employee e: this.employees){
				s += e.toString() + "     " + df.format(e.calculateWeeklyPay()) + "\n";
			}
		}else
			s = "Nothing there, try loading a new file, or adding employees manually. \n";
		
		return s;
	}
	
	public int getErrorsFound(){
		return this.errorsFound;
	}

}
