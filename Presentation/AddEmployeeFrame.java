package Presentation;

import javax.swing.*;

import Business.Employee;
import Data.EmployeeDAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEmployeeFrame extends JFrame {
	private JTextField txtId;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtAge;
	private EmployeeDAO empDAO = new EmployeeDAO();
	private JTextField txtPassword;
	private EmployeeMGMPanel parent;
	
	public AddEmployeeFrame(EmployeeMGMPanel panel) {
		parent = panel;
		setResizable(false);
		this.setSize(250, 280);
		this.setLocationRelativeTo(null);
		this.setTitle("Add employee");
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblId = new GridBagConstraints();
		gbc_lblId.insets = new Insets(10, 0, 5, 5);
		gbc_lblId.anchor = GridBagConstraints.EAST;
		gbc_lblId.gridx = 0;
		gbc_lblId.gridy = 0;
		getContentPane().add(lblId, gbc_lblId);
		
		txtId = new JTextField();
		GridBagConstraints gbc_txtId = new GridBagConstraints();
		gbc_txtId.gridwidth = 2;
		gbc_txtId.insets = new Insets(10, 0, 5, 0);
		gbc_txtId.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtId.gridx = 1;
		gbc_txtId.gridy = 0;
		getContentPane().add(txtId, gbc_txtId);
		txtId.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 1;
		getContentPane().add(lblPassword, gbc_lblPassword);
		
		txtPassword = new JTextField();
		GridBagConstraints gbc_txtPassword = new GridBagConstraints();
		gbc_txtPassword.gridwidth = 2;
		gbc_txtPassword.insets = new Insets(0, 0, 5, 0);
		gbc_txtPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPassword.gridx = 1;
		gbc_txtPassword.gridy = 1;
		getContentPane().add(txtPassword, gbc_txtPassword);
		txtPassword.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
		gbc_lblFirstName.anchor = GridBagConstraints.EAST;
		gbc_lblFirstName.insets = new Insets(0, 10, 5, 5);
		gbc_lblFirstName.gridx = 0;
		gbc_lblFirstName.gridy = 2;
		getContentPane().add(lblFirstName, gbc_lblFirstName);
		
		txtFirstName = new JTextField();
		GridBagConstraints gbc_txtFirstName = new GridBagConstraints();
		gbc_txtFirstName.gridwidth = 2;
		gbc_txtFirstName.insets = new Insets(0, 0, 5, 0);
		gbc_txtFirstName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFirstName.gridx = 1;
		gbc_txtFirstName.gridy = 2;
		getContentPane().add(txtFirstName, gbc_txtFirstName);
		txtFirstName.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblLastName = new GridBagConstraints();
		gbc_lblLastName.anchor = GridBagConstraints.EAST;
		gbc_lblLastName.insets = new Insets(0, 0, 5, 5);
		gbc_lblLastName.gridx = 0;
		gbc_lblLastName.gridy = 3;
		getContentPane().add(lblLastName, gbc_lblLastName);
		
		txtLastName = new JTextField();
		GridBagConstraints gbc_txtLastName = new GridBagConstraints();
		gbc_txtLastName.gridwidth = 2;
		gbc_txtLastName.insets = new Insets(0, 0, 5, 0);
		gbc_txtLastName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtLastName.gridx = 1;
		gbc_txtLastName.gridy = 3;
		getContentPane().add(txtLastName, gbc_txtLastName);
		txtLastName.setColumns(10);
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblAge = new GridBagConstraints();
		gbc_lblAge.anchor = GridBagConstraints.EAST;
		gbc_lblAge.insets = new Insets(0, 0, 5, 5);
		gbc_lblAge.gridx = 0;
		gbc_lblAge.gridy = 4;
		getContentPane().add(lblAge, gbc_lblAge);
		
		txtAge = new JTextField();
		GridBagConstraints gbc_txtAge = new GridBagConstraints();
		gbc_txtAge.gridwidth = 2;
		gbc_txtAge.insets = new Insets(0, 0, 5, 0);
		gbc_txtAge.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAge.gridx = 1;
		gbc_txtAge.gridy = 4;
		getContentPane().add(txtAge, gbc_txtAge);
		txtAge.setColumns(10);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblGender = new GridBagConstraints();
		gbc_lblGender.anchor = GridBagConstraints.EAST;
		gbc_lblGender.insets = new Insets(0, 0, 5, 5);
		gbc_lblGender.gridx = 0;
		gbc_lblGender.gridy = 5;
		getContentPane().add(lblGender, gbc_lblGender);
		
		JRadioButton rdbtnMale = new JRadioButton("Male");
		GridBagConstraints gbc_rdbtnMale = new GridBagConstraints();
		gbc_rdbtnMale.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnMale.gridx = 1;
		gbc_rdbtnMale.gridy = 5;
		getContentPane().add(rdbtnMale, gbc_rdbtnMale);
		
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		GridBagConstraints gbc_rdbtnFemale = new GridBagConstraints();
		gbc_rdbtnFemale.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnFemale.gridx = 2;
		gbc_rdbtnFemale.gridy = 5;
		getContentPane().add(rdbtnFemale, gbc_rdbtnFemale);
		
		JButton btnAdd = new JButton("Add");
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(0, 0, 5, 0);
		gbc_btnAdd.gridx = 2;
		gbc_btnAdd.gridy = 6;
		getContentPane().add(btnAdd, gbc_btnAdd);
		
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isValidData()) {
					//Getting data about person
					Employee emp = new Employee();
					emp.setEmpId(txtId.getText());
					emp.setPassword(txtPassword.getText());
					emp.setFirstName(txtFirstName.getText());
					emp.setLastName(txtLastName.getText());
					emp.setAge(Integer.parseInt(txtAge.getText()));
					if (rdbtnMale.isSelected())
						emp.setGender("Male");
					else 
						emp.setGender("Female");
					if (empDAO.addEmployee(emp)) {
						String result = "ID: " + txtId.getText() + "\nFull Name: " + txtFirstName.getText() + txtLastName.getText();
						JOptionPane.showMessageDialog(null,"Employee data is saved \n" + result,"Success",JOptionPane.INFORMATION_MESSAGE);
					}
					parent.showAllEmployees();
					AddEmployeeFrame.this.dispose();
				}
			}
		});
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	public boolean isValidData() {
		if (!Validator.isPresent(txtId, "ID")) return false;
		if (!Validator.isPresent(txtPassword, "Password")) return false;
		if (!Validator.isPresent(txtFirstName, "First Name")) return false;
		if (!Validator.isPresent(txtLastName, "Last Name")) return false;
		if (!Validator.isInteger(txtAge, "Age")) return false;
		return true;
	}
	
}
