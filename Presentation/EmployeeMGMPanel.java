package Presentation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import Business.Employee;
import Business.Shift;
import Data.EmployeeDAO;


public class EmployeeMGMPanel extends JPanel {
	private JButton btnAddNew;
	private JButton btnShowAll;
	private JTextArea txtEmployees;
	private EmployeeDAO empDAO = new EmployeeDAO();
	private JButton btnSearch;
	
	public EmployeeMGMPanel() {
		
		GridBagLayout gbl_jpEmpReport = new GridBagLayout();
		gbl_jpEmpReport.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_jpEmpReport.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_jpEmpReport.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_jpEmpReport.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		this.setLayout(gbl_jpEmpReport);
		
		btnAddNew = new JButton("Add New");
		GridBagConstraints gbc_btnAddNew = new GridBagConstraints();
		gbc_btnAddNew.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddNew.gridx = 3;
		gbc_btnAddNew.gridy = 0;
		this.add(btnAddNew, gbc_btnAddNew);
		btnAddNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddEmployeeFrame(EmployeeMGMPanel.this);
			}	
		});
		
		btnShowAll = new JButton("Show all");
		GridBagConstraints gbc_btnShowAll = new GridBagConstraints();
		gbc_btnShowAll.insets = new Insets(0, 0, 5, 0);
		gbc_btnShowAll.gridx = 3;
		gbc_btnShowAll.gridy = 1;
		this.add(btnShowAll, gbc_btnShowAll);
		btnShowAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showAllEmployees();
			}	
		});
		
		txtEmployees = new JTextArea();
		txtEmployees.setEditable(false);
		GridBagConstraints gbc_txtEmployees = new GridBagConstraints();
		gbc_txtEmployees.gridheight = 9;
		gbc_txtEmployees.gridwidth = 3;
		gbc_txtEmployees.insets = new Insets(0, 0, 0, 5);
		gbc_txtEmployees.fill = GridBagConstraints.BOTH;
		gbc_txtEmployees.gridx = 0;
		gbc_txtEmployees.gridy = 0;
		this.add(txtEmployees, gbc_txtEmployees);
		
		btnSearch = new JButton("Search");
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.insets = new Insets(0, 0, 5, 0);
		gbc_btnSearch.gridx = 3;
		gbc_btnSearch.gridy = 2;
		add(btnSearch, gbc_btnSearch);
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SearchEmployeeFrame(EmployeeMGMPanel.this);
			}	
		});
		showAllEmployees();
	}
	
	public void showAllEmployees() {
		ArrayList<Employee> employees = new ArrayList<Employee>();
		employees = empDAO.getEmployees();
		String output = "ID\tPASSWORD\tFIRSTNAME\tLASTNAME\tAGE\tGENDER\n";
		for (Employee emp: employees) {
			output += emp.getId() + "\t";
			output += emp.getPassword() + "\t";
			output += emp.getFirstName() + "\t";
			output += emp.getLastName() + "\t";
			output += emp.getAge() + "\t";
			output += emp.getGender() + "\n";
		}
		txtEmployees.setText(output);
	}
	public void showEmployee(Employee emp) {
		String output = "ID\tPASSWORD\tFIRSTNAME\tLASTNAME\tAGE\tGENDER\n";
		output += emp.getId() + "\t";
		output += emp.getPassword() + "\t";
		output += emp.getFirstName() + "\t";
		output += emp.getLastName() + "\t";
		output += emp.getAge() + "\t";
		output += emp.getGender();
		txtEmployees.setText(output);
	}

}
