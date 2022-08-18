public class Executive extends Manager{

	public double totalComp = 0.0;
	
	public Executive(String name, String title, int idNum, int age, double salary) {
		super(name, title, idNum, age, salary);
	}
	
	public void setTotalComp(double profits, double bonusPercent) {
		totalComp = salary + profits * (bonusPercent/100.0);
	}
	
	public double getTotalComp() {
		return totalComp;
	}
	
	public String toString() {
		String temp = String.format("The executive name and title are %s %s\n", name, title);
		temp+= String.format("The executive ID is 3119 The executive age is %d\n", idNum, age);
		temp+= String.format("The executive salary and total compensation are $%,.2f and $%,.2f", salary, totalComp);
		return temp;
	}
	
}
