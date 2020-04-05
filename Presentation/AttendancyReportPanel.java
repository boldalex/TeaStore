package Presentation;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import Business.Shift;
import Data.ShiftDAO;

public class AttendancyReportPanel extends JPanel {
	private JButton btnAllShifts, btnShiftsByWorker;
	private JTextArea txtResult;
	private ShiftDAO shiftDAO = new ShiftDAO();

	public AttendancyReportPanel() {
		GridBagLayout gbl_this = new GridBagLayout();
		gbl_this.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_this.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_this.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_this.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		this.setLayout(gbl_this);

		btnAllShifts = new JButton("All shifts");
		GridBagConstraints gbc_btnAllShifts = new GridBagConstraints();
		gbc_btnAllShifts.insets = new Insets(0, 0, 5, 0);
		gbc_btnAllShifts.gridx = 3;
		gbc_btnAllShifts.gridy = 0;
		this.add(btnAllShifts, gbc_btnAllShifts);
		btnAllShifts.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showAllShifts();
			}
		});

		txtResult = new JTextArea();
		txtResult.setEditable(false);
		GridBagConstraints gbc_txtResult = new GridBagConstraints();
		gbc_txtResult.gridheight = 9;
		gbc_txtResult.gridwidth = 3;
		gbc_txtResult.insets = new Insets(0, 0, 0, 5);
		gbc_txtResult.fill = GridBagConstraints.BOTH;
		gbc_txtResult.gridx = 0;
		gbc_txtResult.gridy = 0;
		this.add(txtResult, gbc_txtResult);

		btnShiftsByWorker = new JButton("Shifts by worker");
		GridBagConstraints gbc_btnShiftsByWorker = new GridBagConstraints();
		gbc_btnShiftsByWorker.insets = new Insets(0, 0, 5, 0);
		gbc_btnShiftsByWorker.gridx = 3;
		gbc_btnShiftsByWorker.gridy = 1;
		this.add(btnShiftsByWorker, gbc_btnShiftsByWorker);
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
			output += shift.getEmpFullName() + "\t\t";
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
			output += shift.getEmpFullName() + "\t\t";
			output += shift.getDate() + "\t";
			output += shift.getStartTime() + "\t";
			output += shift.getEndTime() + "\n";
		}
		txtResult.setText(output);
	}

}
