import java.util.ArrayList;

public class Manager extends Employee{

	public ArrayList<Employee> managedEmployees = new ArrayList<Employee>();
	
	public Manager(String name, String title, int idNum, int age, double salary) {
		super(name, title, idNum, age, salary);
	}
	
	public void addManagedEmployee(Employee newEmployee) {
		managedEmployees.add(newEmployee);
	}
	
	public int getManagedEmployeesListSize() {
		return managedEmployees.size();
	}
	
	public Employee getManagedEmployee(int i) {
		return managedEmployees.get(i);
	}
	
	public String toString() {
		String temp = String.format("The manager name and title are %s %s\n", name, title);
		temp+= String.format("The manager ID is %d The manger age is %d\n", idNum, age);
		temp+= String.format("The manager salary is $%,.2f\n", salary);
		return temp;
	}
	
	
}
