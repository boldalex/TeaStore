package Data;

import java.util.ArrayList;

import Business.Shift;

public interface ShiftReader {
	Shift getShift(String empID);
	ArrayList<Shift> getShifts();

}
