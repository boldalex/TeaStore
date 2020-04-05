package Data;

import java.io.Closeable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import Business.Employee;
import Business.Shift;

public class ShiftDAO implements ShiftWriter, ShiftReader {
	private File shiftFile = null;
	private int count;
	private RandomAccessFile dataFile = null;
	private EmployeeDAO empDAO = new EmployeeDAO();

	public ShiftDAO() {
		shiftFile = new File(ShiftConstants.FILENAME);
	}

	private void checkFile() throws IOException {
		if (!shiftFile.exists()) {
			shiftFile.createNewFile();
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
	public Shift getShift(String empID) {
		ArrayList<Shift> shifts = getShifts();
		for (Shift shift: shifts) {
			if (shift.getEmpId().equalsIgnoreCase(empID))
				return shift;
		}
		return null;
	}
	
	public ArrayList<Shift> getShiftsByWorker(String empID){
		ArrayList<Shift> result = new ArrayList<Shift>();
		ArrayList<Shift> shifts = getShifts();
		for (Shift shift: shifts) {
			if (shift.getEmpId().equalsIgnoreCase(empID))
				result.add(shift);
		}
		return result;
	}

	@Override
	public ArrayList<Shift> getShifts() {
		ArrayList<Shift> shifts = new ArrayList<Shift>();
		try {
			dataFile = new RandomAccessFile(shiftFile, "rw");
			count = (int)dataFile.length()/ShiftConstants.RECORD_SIZE;
			for (int i =0; i < count; i++) {
				dataFile.seek(i * ShiftConstants.RECORD_SIZE);
				//Getting employee object for the shift
				Employee emp = new Employee();
				String empID = readString(dataFile, ShiftConstants.ID_SIZE);
				emp = empDAO.getEmployee(empID);
				
				//Creating new shift
				Shift shift = new Shift(emp);
				shift.setDate(readString(dataFile, ShiftConstants.DATE_SIZE));
				shift.setStartTime(readString(dataFile, ShiftConstants.START_TIME_SIZE));
				shift.setEndTime(readString(dataFile, ShiftConstants.END_TIME_SIZE));
				shifts.add(shift);
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
		return shifts;
	}

	@Override
	public boolean addShift(Shift shift) {
		boolean success = false;
		try {
			dataFile = new RandomAccessFile(shiftFile, "rw");
			count = (int)dataFile.length()/ShiftConstants.RECORD_SIZE;
			dataFile.seek(count * ShiftConstants.RECORD_SIZE);
			writeString(dataFile, ShiftConstants.ID_SIZE,shift.getEmpId());
			writeString(dataFile, ShiftConstants.DATE_SIZE,shift.getDate());
			writeString(dataFile, ShiftConstants.START_TIME_SIZE,shift.getStartTime());
			writeString(dataFile, ShiftConstants.END_TIME_SIZE,shift.getEndTime());
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
