package Business;

public class Employee {
	private int age;
	private String fName, lName, gender, empID, password;
	
	//Constructors
	public Employee() {}
	
	//Setters
	public void setEmpId(String id) {
		empID = id;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setFirstName(String name) {
		fName = name;
	}
	public void setLastName(String name) {
		lName = name;
	}
//	public void setHireDate(String date) {
//		hDate = date;
//	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	//Getters
	public String getId() {
		return empID;
	}
	public String getPassword() {
		return password;
	}
	public int getAge() {
		return age;
	}
	public String getFirstName() {
		return fName;
	}
	public String getLastName() {
		return lName;
	}
//	public String getHireDate() {
//		return hDate;
//	}
	public String getGender() {
		return gender;
	}
	
	public String toString() {
		return empID + " " + fName + " " + lName;
	}
	
	

}
