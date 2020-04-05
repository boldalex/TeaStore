package Business;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Shift {
	private Employee employee;
	private LocalDate date;
	private LocalTime startTime, endTime;
	private DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	public Shift(Employee emp) {
		employee = emp;
		date = LocalDate.now();
		startTime = LocalTime.now();
	}
	
	//Getters
	public String getEmpId() {
		return employee.getId();
	}
	public String getEmpFullName() {
		return employee.getFirstName() + " " + employee.getLastName();
	}
	public String getDate() {
		return date.toString();
	}
	public String getStartTime() {
		return startTime.format(format);
	}
	public String getEndTime() {
		return endTime.format(format);
	}
	
	//Setters
	public void setDate(String date) {
		LocalDate in = LocalDate.parse(date);
		this.date = in;
	}
	public void setStartTime(String time) {
		LocalTime in = LocalTime.parse(time,format);
		startTime = in;
	}
	public void setEndTime(String time) {
		LocalTime in = LocalTime.parse(time,format);
		endTime = in;
	}
	public void finishShift() {
		endTime = LocalTime.now();
	}
	
	public String toString() {
		return getEmpId() + " " + getEmpFullName() + " " + getDate() + " " + getStartTime() + " " + getEndTime(); 
	}
	

}
