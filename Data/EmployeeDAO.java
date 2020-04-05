package Data;

import java.io.*;
import java.util.ArrayList;

import Business.Employee;

public class EmployeeDAO implements EmployeeConstants, EmployeeReader, EmployeeWriter {
	private File personFile = null;
	private int count;
	private RandomAccessFile dataFile = null;
	
	public EmployeeDAO() {
		personFile = new File(EmployeeConstants.FILENAME);
	}
	
	private void checkFile() throws IOException {
		if (!personFile.exists()) {
			personFile.createNewFile();
		}
	}
	
	private void close(Closeable stream) {
		try {
			if (stream != null) {
				stream.close();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	@Override
	public boolean addEmployee(Employee emp) {
		boolean success = false;
		try {
			dataFile = new RandomAccessFile(personFile, "rw");
			count = (int)dataFile.length()/EmployeeConstants.RECORD_SIZE;
			dataFile.seek(count * EmployeeConstants.RECORD_SIZE);
			writeString(dataFile, EmployeeConstants.ID_SIZE,emp.getId());
			writeString(dataFile, EmployeeConstants.PASSWORD_SIZE,emp.getPassword());
			writeString(dataFile, EmployeeConstants.FIRST_NAME_SIZE,emp.getFirstName());
			writeString(dataFile, EmployeeConstants.LAST_NAME_SIZE,emp.getLastName());
			dataFile.writeInt(emp.getAge());
			writeString(dataFile, EmployeeConstants.GENDER_SIZE,emp.getGender());
//			writeString(dataFile, EmployeeConstants.HIRE_DATE_SIZE,emp.getHireDate());
			success = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				dataFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return success;
	}

	@Override
	public Employee getEmployee(String empID) {
		ArrayList<Employee> employees = getEmployees();
		for (Employee emp: employees) {
			if (emp.getId().equalsIgnoreCase(empID))
				return emp;
		}
		return null;
	}

	@Override
	public ArrayList<Employee> getEmployees() {
		ArrayList<Employee> employees = new ArrayList<Employee>();
		try {
			dataFile = new RandomAccessFile(personFile, "rw");
			count = (int)dataFile.length()/EmployeeConstants.RECORD_SIZE;
			for (int i =0; i < count; i++) {
				dataFile.seek(i * EmployeeConstants.RECORD_SIZE);
				Employee emp = new Employee();
				emp.setEmpId(readString(dataFile, EmployeeConstants.ID_SIZE));
				emp.setPassword(readString(dataFile, EmployeeConstants.PASSWORD_SIZE));
				emp.setFirstName(readString(dataFile, EmployeeConstants.FIRST_NAME_SIZE));
				emp.setLastName(readString(dataFile, EmployeeConstants.LAST_NAME_SIZE));
				emp.setAge(dataFile.readInt());
				emp.setGender(readString(dataFile, EmployeeConstants.GENDER_SIZE));
//				emp.setHireDate(readString(dataFile, EmployeeConstants.HIRE_DATE_SIZE));
				employees.add(emp);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				dataFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return employees;
	}
	
	private static void writeString(DataOutput out, int length, String s) throws IOException {
		for (int i = 0; i < length; i++) {
			if ( i < s.length() ) {
				out.writeChar(s.charAt(i));
			}
			else {
				out.writeChar(0);
			}
		}
	}
	private static String readString(DataInput in, int length) throws IOException {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			char symbol = in.readChar();
			if (symbol != 0)
				sb.append(symbol);
		}
		return sb.toString();
	}

}
