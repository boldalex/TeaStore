package Data;

public interface ShiftConstants {
	String FILENAME = "shifts.dat";
	String FIELD_SEP = "\t";
	int ID_SIZE = 4;
	int DATE_SIZE = 10;
	int START_TIME_SIZE = 8;
	int END_TIME_SIZE = 8;
	int RECORD_SIZE = ID_SIZE*2 + DATE_SIZE*2 + START_TIME_SIZE*2 + END_TIME_SIZE*2;
}
