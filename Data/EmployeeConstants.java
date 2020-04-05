package Data;

public interface EmployeeConstants {
	String FILENAME = "employees.dat";
	String FIELD_SEP = "\t";
	int ID_SIZE = 4;
	int PASSWORD_SIZE = 6;
	int FIRST_NAME_SIZE = 25;
	int LAST_NAME_SIZE = 25;
	int AGE_SIZE = 4;
	int GENDER_SIZE = 6;
	int HIRE_DATE_SIZE = 11;
	int RECORD_SIZE = ID_SIZE*2 + PASSWORD_SIZE*2 + FIRST_NAME_SIZE*2 + LAST_NAME_SIZE*2 + AGE_SIZE + GENDER_SIZE*2;
}
