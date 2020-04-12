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
		setLayout(null);
		
		btnAddNew = new JButton("Add New");
		btnAddNew.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAddNew.setBounds(462, 10, 98, 30);
		this.add(btnAddNew);
		btnAddNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddEmployeeFrame(EmployeeMGMPanel.this);
			}	
		});
		
		btnShowAll = new JButton("Show all");
		btnShowAll.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnShowAll.setBounds(462, 49, 98, 30);
		this.add(btnShowAll);
		btnShowAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showAllEmployees();
			}	
		});
		
		txtEmployees = new JTextArea();
		txtEmployees.setBounds(10, 10, 420, 319);
		txtEmployees.setEditable(false);
		this.add(txtEmployees);
		
		btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSearch.setBounds(462, 89, 98, 30);
		add(btnSearch);
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
