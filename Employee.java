public class Employee {

	public String name, title;
	public int idNum, age;
	public double salary;
	
	public Employee(String name, String title, int idNum, int age, double salary) {
		setName(name);
		setTitle(title);
		setId(idNum);
		setAge(age);
		setSalary(salary);
	}
	
	public void setName(String newName) {
		name = newName;
	}
	
	public String getName() {
		return name;
	}
	
	public void setTitle(String newTitle) {
		title = newTitle;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setId(int newId) {
		idNum = newId;
	}
	
	public int getId() {
		return idNum;
	}
	
	public void setAge(int newAge) {
		age = newAge;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setSalary(double newSalary) {
		salary = newSalary;
	}
	
	public double getSalary() {
		return salary;
	}
	
	

	public void changeSalary(double percent) {
		salary = salary * (1.0 + (percent/100.0));
	}
	
	public String toString() {
		return String.format("The employee name and title are %s %s\nThe employee ID is %d The employee age is %d\nThe employee salary is $%,.2f\n", name, title, idNum, age, salary);
	}
	
	
}
