package Presentation;

import javax.swing.*;

import Business.Employee;
import Business.Shift;
import Data.ShiftDAO;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchShiftFrame extends JFrame{
	private AttendancyReportPanel parent;
	private JTextField txtId;
	private ShiftDAO shiftDAO = new ShiftDAO();

	public SearchShiftFrame(AttendancyReportPanel panel) {
		parent = panel;
		this.setVisible(true);
		this.setSize(150, 200);
		this.setLocationRelativeTo(null);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);

		JLabel lblEmployeeId = new JLabel("Employee ID:");
		lblEmployeeId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblEmployeeId = new GridBagConstraints();
		gbc_lblEmployeeId.ipadx = 10;
		gbc_lblEmployeeId.ipady = 10;
		gbc_lblEmployeeId.insets = new Insets(50, 0, 5, 5);
		gbc_lblEmployeeId.anchor = GridBagConstraints.EAST;
		gbc_lblEmployeeId.gridx = 0;
		gbc_lblEmployeeId.gridy = 0;
		getContentPane().add(lblEmployeeId, gbc_lblEmployeeId);

		txtId = new JTextField();
		GridBagConstraints gbc_txtId = new GridBagConstraints();
		gbc_txtId.insets = new Insets(50, 0, 5, 0);
		gbc_txtId.ipady = 10;
		gbc_txtId.ipadx = 10;
		gbc_txtId.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtId.gridx = 1;
		gbc_txtId.gridy = 0;
		getContentPane().add(txtId, gbc_txtId);
		txtId.setColumns(10);

		JButton btnSearch = new JButton("Search");
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.gridwidth = 2;
		gbc_btnSearch.insets = new Insets(0, 0, 0, 5);
		gbc_btnSearch.gridx = 0;
		gbc_btnSearch.gridy = 1;
		getContentPane().add(btnSearch, gbc_btnSearch);
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isValidData()) {
					ArrayList<Shift> shifts = shiftDAO.getShiftsByWorker(txtId.getText());
					if (shifts.size() > 0) {
						parent.showShifts(shifts);
						SearchShiftFrame.this.dispose();
					}
					else {
						JOptionPane.showMessageDialog(null,"No shifts for this employee","Not Found",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}

	public boolean isValidData() {
		if (!Validator.isPresent(txtId, "ID")) return false;
		return true;
	}
}

