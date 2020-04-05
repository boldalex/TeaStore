package Data;

import java.util.ArrayList;

import Business.Employee;

public interface EmployeeReader {
	Employee getEmployee(String empID);
	ArrayList<Employee> getEmployees();
}
