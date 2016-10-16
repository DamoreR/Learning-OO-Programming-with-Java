// Class Group
// Author: N. Brixius, Oct 1999
// Modified by Sean Holden, May 2014
// -- parameterized generic type Vector
// -- used for-each loop for printing info about people
// Use: Demo program for CS225 Labs

import java.util.Random;
import java.util.Vector;

public class Group {

	public static void main(String args[]) {

		Vector<Person> group = new Vector<Person>();
		int groupSize, age, nation, numCountries = 4;
		Random rGen = new Random(System.currentTimeMillis() % 1000);

		groupSize = rGen.nextInt(9) + 2;

		for (int i = 1; i <= groupSize; i++) {
			age = rGen.nextInt(64) + 1;
			nation = rGen.nextInt(numCountries);
			switch (nation) {
			case 0:  group.add(new Person("Person" + i, age));
				break;
			case 1:  group.add(new American("Person" + i, age, "Florida"));
				break;
			case 2:  group.add(new German("Person" + i, age));
				break;
			case 3:  group.add(new Spaniard("Person" + i, age));
				break;
			default:  group.add(new Person("Person" + i, age));
			}
		}

		System.out.println(group.size() + " people were put in group.");

		for (Person person : group){
			System.out.println(" ");
			person.sayHello();
			System.out.println("I am " + person.howOld() + " years old and"
					+ " I am from " + person.whatCountry() + ".");
			System.out.println("The class of this object" + " is "
					+ person.getClass().getName());

			if (person instanceof American) {
				System.out.println("This person resides in the state of "
						+ ((American) person).getState());
				System.out.println(person);
			}
		}
	}
}
// -------------------------------------------------------------------------------
class Person {
	protected int age;
	protected String name;

	Person(String theName, int theAge) {
		name = theName;
		age = theAge;
	}

	public String whatCountry() {
		return "nowhere :(";
	}

	public int howOld() {
		return age;
	}

	public void sayHello() {
		System.out.println("Greetings, my name is " + name);
	}
}
// -------------------------------------------------------------------------------
class American extends Person {

	private String state = "No state";

	American(String theName, int theAge) {
		super(theName, theAge);
	}

	American(String theName, int theAge, String aState) {
		super(theName, theAge);
		state = aState;
	}

	public void setState(String aState) {
		state = aState;
	}

	public String getState() {
		return state;
	}

	public String whatCountry() {
		return "United States";
	}

	public void sayHello() {
		System.out.println("Howdy, my name is " + name);
	}

	public String toString() {
		return ("This description is defined in the American class");
	}
}
// -------------------------------------------------------------------------------
class German extends Person {

	German(String theName, int theAge) {
		super(theName, theAge);
	}

	public String whatCountry() {
		return "Germany";
	}

	public void sayHello() {
		System.out.println("Guten Tag, my name is " + name);
	}
}
// -------------------------------------------------------------------------------
class Spaniard extends Person {

	Spaniard(String theName, int theAge) {
		super(theName, theAge);
	}

	public String whatCountry() {
		return "Spain";
	}

	public void sayHello() {
		System.out.println("Buenos Dias, my name is " + name);
	}
}
