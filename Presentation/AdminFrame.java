package Presentation;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class AdminFrame extends JFrame{
	private JMenuBar mBar;
	private JMenu mSales, mProducts, mAttendancy, mMenu;
	private JMenuItem mDaily, mWeekly, mMonthly;
	private JMenuItem mList, mReport;
	private JMenuItem mAttReport, mEmpReport, mLogOut;
	private JButton jbPrint, jbSend;
	private JPanel jpMain, jpAttReport, jpEmpReport;
	private JButton btnAllShifts;
	private JButton btnShiftsByTime;
	private JButton btnShiftsByWorker;
	private JTextArea txtResult;
	private JButton btnAddNew;
	private JButton btnRemove;
	private JTextArea txtEmployees;


	public AdminFrame() {
		mBar = new JMenuBar();

		mSales = new JMenu("Sales Report");
		mSales.setMnemonic(KeyEvent.VK_S);

		mProducts = new JMenu("Products");
		mProducts.setMnemonic(KeyEvent.VK_P);

		mAttendancy = new JMenu("Attendancy");
		mAttendancy.setMnemonic(KeyEvent.VK_A);

		mMenu = new JMenu("Menu");
		mMenu.setMnemonic(KeyEvent.VK_E);

		mDaily = new JMenuItem("Daily Report");
		mDaily.setMnemonic(KeyEvent.VK_D);
		mDaily.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,ActionEvent.CTRL_MASK));

		mWeekly = new JMenuItem("Weekly Report");
		mWeekly.setMnemonic(KeyEvent.VK_W);
		mWeekly.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,ActionEvent.CTRL_MASK));

		mMonthly = new JMenuItem("Monthly Report");
		mMonthly.setMnemonic(KeyEvent.VK_M);
		mMonthly.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,ActionEvent.CTRL_MASK));

		mList = new JMenuItem("Product List");
		mList.setMnemonic(KeyEvent.VK_L);
		mList.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,ActionEvent.CTRL_MASK));

		mReport = new JMenuItem("Product Report");
		mReport.setMnemonic(KeyEvent.VK_R);
		mReport.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,ActionEvent.CTRL_MASK));

		mAttReport = new JMenuItem("Attendancy Report");
		mAttReport.setMnemonic(KeyEvent.VK_A);
		mAttReport.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
		mAttReport.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminFrame.this.setContentPane(new AttendancyReportPanel());
				AdminFrame.this.setSize(650,500);
			}
		});
		
		mEmpReport = new JMenuItem("Employee Management");
		mEmpReport.setMnemonic(KeyEvent.VK_P);
		mEmpReport.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
		mEmpReport.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminFrame.this.setContentPane(new EmployeeMGMPanel());	
				AdminFrame.this.setSize(601,500);
			}
		});
		

		mLogOut = new JMenuItem("Log Out");
		mLogOut.setMnemonic(KeyEvent.VK_L);
		mLogOut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,ActionEvent.CTRL_MASK));
		mLogOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new LogInFrame();
				AdminFrame.this.dispose();	
			}
		});


		mSales.add(mDaily);
		mSales.add(mWeekly);
		mSales.add(mMonthly);

		mProducts.add(mList);
		mProducts.add(mReport);

		mAttendancy.add(mAttReport);
		mAttendancy.add(mEmpReport);
		mMenu.add(mLogOut);

		mBar.add(mSales);
		mBar.add(mProducts);
		mBar.add(mAttendancy);
		mBar.add(mMenu);


		this.setJMenuBar(mBar);
		
		
		//Content for main page
		jpMain = new JPanel();
		jpMain.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		jpMain.add(jbPrint = new JButton("Print"));
		jpMain.add(jbSend = new JButton("Send via Email"));
		jbPrint.setSize(70,50);
		jbSend.setSize(70,50);
		
		
//		this.setContentPane(new EmployeeMGMPanel());
		this.setVisible(true);
		this.setTitle("Administrator Panel");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600,500);
		this.setLocationRelativeTo(null);

	}
}
