package Presentation;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.*;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import Business.Shift;
import Data.ShiftDAO;
import java.awt.Font;

public class AttendancyReportPanel extends JPanel {
	private JButton btnAllShifts, btnShiftsByWorker;
	private JTextArea txtResult;
	private ShiftDAO shiftDAO = new ShiftDAO();
	private JButton btnShiftsByDay;

	public AttendancyReportPanel() {
		SqlDateModel dateModel = new SqlDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(dateModel, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setSize(132, 30);
		datePicker.setLocation(536, 90);
		
		add(datePicker);
		setLayout(null);

		btnAllShifts = new JButton("All shifts");
		btnAllShifts.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAllShifts.setBounds(536, 10, 132, 30);
		this.add(btnAllShifts);
		btnAllShifts.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showAllShifts();
			}
		});

		txtResult = new JTextArea();
		txtResult.setBounds(10, 10, 501, 334);
		txtResult.setEditable(false);
		this.add(txtResult);

		btnShiftsByWorker = new JButton("Shifts by worker");
		btnShiftsByWorker.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnShiftsByWorker.setBounds(536, 50, 132, 30);
		this.add(btnShiftsByWorker);
		
		btnShiftsByDay = new JButton("Shifts by day");
		btnShiftsByDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String date = ((Date)datePicker.getModel().getValue()).toString();
				shiftsByDay(date);
			}
		});
		btnShiftsByDay.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnShiftsByDay.setBounds(536, 130, 132, 30);
		add(btnShiftsByDay);
		btnShiftsByWorker.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SearchShiftFrame(AttendancyReportPanel.this);
			}		
		});

		showAllShifts();
	}

	public void showAllShifts() {
		String output = "EMP_ID\tEMP_FULL_NAME\tDATE\tSTART_TIME\tEND_TIME\n";
		ArrayList<Shift> shifts = new ArrayList<Shift>();
		shifts = shiftDAO.getShifts();
		for (Shift shift: shifts) {
			output += shift.getEmpId() + "\t";
			output += shift.getEmpFullName() + "\t";
			if (shift.getEmpFullName().length() < 17) output += "\t";
			output += shift.getDate() + "\t";
			output += shift.getStartTime() + "\t";
			output += shift.getEndTime() + "\n";
		}
		txtResult.setText(output);
	}

	public void showShifts(ArrayList<Shift> shifts) {
		String output = "EMP_ID\tEMP_FULL_NAME\tDATE\tSTART_TIME\tEND_TIME\n";
		for (Shift shift: shifts) {
			output += shift.getEmpId() + "\t";
			output += shift.getEmpFullName() + "\t";
			output += shift.getDate() + "\t";
			output += shift.getStartTime() + "\t";
			output += shift.getEndTime() + "\n";
		}
		txtResult.setText(output);
	}
	public void shiftsByDay(String date) {
		String output = "EMP_ID\tEMP_FULL_NAME\tDATE\tSTART_TIME\tEND_TIME\n";
		ArrayList<Shift> shifts = new ArrayList<Shift>();
		shifts = shiftDAO.getShifts();
		for (Shift shift: shifts) {
			if (shift.getDate().equals(date)) {
				output += shift.getEmpId() + "\t";
				output += shift.getEmpFullName() + "\t";
				output += shift.getDate() + "\t";
				output += shift.getStartTime() + "\t";
				output += shift.getEndTime() + "\n";
			}
		}
		txtResult.setText(output);
	}

}
