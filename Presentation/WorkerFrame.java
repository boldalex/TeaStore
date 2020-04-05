package Presentation;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.*;

import Business.Employee;
import Business.Order;
import Business.Shift;
import Data.ShiftDAO;

public class WorkerFrame extends JFrame {
	private JMenuBar mBar;
	private JMenu mMenu;
	private JMenu mLogOut;
	private Shift shift;
	private Employee emp;
	private ShiftDAO shiftDAO = new ShiftDAO();
	
	public WorkerFrame(Employee employee){
		//CREATING NEW SHIFT AT CURRENT DATE AND TIME
		emp = employee;
		shift = new Shift(emp);	
		
		JMenuBar mBar = new JMenuBar();
		JMenu mMenu = new JMenu("Menu");
		mMenu.setMnemonic(KeyEvent.VK_M);
		JMenuItem mLogOut = new JMenuItem("Log out");
		mLogOut.setMnemonic(KeyEvent.VK_L);
		mLogOut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,ActionEvent.CTRL_MASK));
		mLogOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
		    	System.out.println("finished");
		        shift.finishShift();
		        shiftDAO.addShift(shift);
				new LogInFrame();
				WorkerFrame.this.dispose();	
			}
		});
		mMenu.add(mLogOut);
		mBar.add(mMenu);
		this.setJMenuBar(mBar);
		
		ProductPanel product = new ProductPanel();
		OrderPanel order = new OrderPanel();
		PropertiesPanel properties = new PropertiesPanel();
		
		JButton jbEncash = new JButton("Encash");
		JButton jbClear = new JButton("Clear All");
		jbEncash.setFont(new Font("Tahoma", Font.BOLD,14));
		jbClear.setFont(new Font("Tahoma", Font.BOLD,14));
		jbClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					Order.clear();
			}
		});
		
		
		product.setBounds(10, 0, 600, 150);
		//product.setBorder(new LineBorder(Color.BLACK, 1, true));
		properties.setBounds(650, 10, 330, 320);
		properties.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		order.setBounds(30, 200, 570, 200);
		order.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		jbClear.setBounds(650, 340, 150, 60);
		jbEncash.setBounds(830, 340, 150, 60);
		
		
		
		
		this.add(product);
		this.add(properties);
		this.add(order);
		this.add(jbClear);
		this.add(jbEncash);
		this.add(new JPanel());
		
		this.setVisible(true);
		this.setSize(1000, 470);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//SETTING END TIME FOR THE CURRENT SHIFT AND SAVING IT
		this.addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent e)
		    {
		    	System.out.println("finished");
		        shift.finishShift();
		        shiftDAO.addShift(shift);
		    }
		});

	}


}
