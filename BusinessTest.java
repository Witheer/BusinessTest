import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class BusinessTest {
	
	public static void main(String[] args) throws IOException{
		
		Scanner scnr = new Scanner(System.in);
		
		ArrayList<Employee> employeeAL = new ArrayList<Employee>();
		ArrayList<Manager> managerAL = new ArrayList<Manager>();
		ArrayList<Executive> executiveAL = new ArrayList<Executive>();
		
		System.out.println("Please enter the name of the input file with employee name and data:");
		String fileName = scnr.nextLine();
		
		File input = new File(fileName);
		
		if(!input.exists()) {
			System.out.println("Error file not found");
			System.exit(0);
		}
		
		Scanner inFile = new Scanner(input);
		
		while(inFile.hasNext()) {
			String name = inFile.next();
			String title = inFile.next();
			int idNum = inFile.nextInt();
			int age = inFile.nextInt();
			int salary = inFile.nextInt();
			if(idNum <= 2000) {
				Employee newEmployee = new Employee(name, title, idNum, age, salary);
				employeeAL.add(newEmployee);
			} else if(idNum <= 3000) {
				Manager newManager = new Manager(name, title, idNum, age, salary);
				managerAL.add(newManager);
			} else if (idNum <= 3500){
				Executive newExecutive = new Executive(name, title, idNum, age, salary);
				executiveAL.add(newExecutive);
			}		
		}
		
		
		for(int i = 0, j = 0; i < employeeAL.size(); i++, j++) {
			if(j >= managerAL.size()) {
				j = 0;
			}
			managerAL.get(j).addManagedEmployee(employeeAL.get(i));			
		}
		
		for(int i = 0, j = 0; i < managerAL.size(); i++, j++) {			
			if(j >= executiveAL.size()) {
				j = 0;
			}
			executiveAL.get(j).addManagedEmployee(managerAL.get(i));			
		}
		
		
		System.out.print("Please enter the total company profit for the year: $");
		double profits = scnr.nextDouble();
		System.out.print("Please enter the executive bonus percentage for the year correct to 1 decimal place: ");
		double bonusPercent = scnr.nextDouble();
		scnr.nextLine();
		
		for(int i = 0; i < executiveAL.size(); i++) {
			executiveAL.get(i).setTotalComp(profits, bonusPercent);
		}
		
		
		System.out.print("Please enter the name of the output file: ");
		fileName = scnr.nextLine();
		
		File output = new File(fileName);
		PrintWriter outFile = new PrintWriter(output);
		
		
		double totalPay = 0;
		
		for(int i = 0; i < employeeAL.size(); i++) {
			totalPay += employeeAL.get(i).getSalary();
		}
		
		for(int i = 0; i < managerAL.size(); i++) {
			totalPay+= managerAL.get(i).getSalary();
		}
		
		for(int i = 0; i < executiveAL.size(); i++) {
			totalPay+= executiveAL.get(i).getTotalComp();
		}
		
		
		
		outFile.printf("The total payroll for the business is $%,.2f\n\n", totalPay);
		outFile.printf("Compensation Table\nExecutives\n");
		outFile.printf("Name           Title             ID       Age    Salary          Total Comp\n");
		
		for(int i = 0; i < executiveAL.size(); i++) {
			Executive temp = executiveAL.get(i);
			outFile.printf("%-15s%-18s%-9d%-7d$%,-15.2f$%,.2f\n",temp.getName(),temp.getTitle(),temp.getId(),temp.getAge(),temp.getSalary(),temp.getTotalComp());
		}
		
		outFile.printf("\nManagers\n");
		outFile.printf("Name           Title             ID       Age    Salary\n");
		
		for(int i = 0; i < managerAL.size(); i++) {
			Manager temp = managerAL.get(i);
			outFile.printf("%-15s%-18s%-9d%-7d$%,-15.2f\n",temp.getName(),temp.getTitle(),temp.getId(),temp.getAge(),temp.getSalary());
		}
		
		outFile.printf("\nEmployees\n");
		outFile.printf("Name           Title             ID       Age    Salary\n");
		
		for(int i = 0; i < employeeAL.size(); i++) {
			Employee temp = employeeAL.get(i);
			outFile.printf("%-15s%-18s%-9d%-7d$%,-15.2f\n",temp.getName(),temp.getTitle(),temp.getId(),temp.getAge(),temp.getSalary());
		}
		
		
		outFile.printf("\n\nReporting Structure\n\nDirect Reports To Executive\n");
		for(int i = 0; i < executiveAL.size(); i++) {
			Executive temp = executiveAL.get(i);
			outFile.printf("Executive %s has the following direct reports\n", temp.getName());
			
			for(int j = 0; j < temp.getManagedEmployeesListSize(); j++) {
				Employee tempManager = temp.getManagedEmployee(j);
				outFile.printf("%-15s%d\n",tempManager.getName(),tempManager.getId());
			}
			outFile.println();
		}
		
		
		outFile.printf("\nDirect Reports To Managers\n");
		for(int i = 0; i < managerAL.size(); i++) {
			Manager temp = managerAL.get(i);
			outFile.printf("Manager %s has the following direct reports\n", temp.getName());
			
			for(int j = 0; j < temp.getManagedEmployeesListSize(); j++) {
				Employee tempEmployee = temp.getManagedEmployee(j);
				outFile.printf("%-15s%d\n",tempEmployee.getName(),tempEmployee.getId());
			}
			outFile.println();
		}
		
		
		System.out.print("Please enter the salary change for a Manager as a percentage: ");
		double newPercentage = scnr.nextDouble();
		scnr.nextLine();
		
		
		outFile.printf("\nManger List with new Salary\n");
		
		for(int i = 0; i < managerAL.size(); i++) {
			Manager temp = managerAL.get(i);
			temp.changeSalary(newPercentage);
			outFile.printf("%s\n", temp.toString());
		}
		
		
		outFile.close();
		inFile.close();
		scnr.close();
		
	}
	
}
