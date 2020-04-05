package Presentation;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import Business.Employee;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;

import Data.EmployeeDAO;
public class LogInFrame extends JFrame{
	private JLabel jlId, jlPw, jlProvide;	
	private JTextField jtId;
	private JPasswordField jpfPw, jpfPw2;
	private JButton jbLogIn, jbLogIn2;
	private JMenuBar mBar;
	private JMenu mMenu;
	private JMenuItem mWorker, mAdmin, mExit;
	private JPanel jpWorker, jpAdmin, jpId, jpPw, jpLogIn, jpLabel;
	private EmployeeDAO empDAO = new EmployeeDAO();
	private HashMap<String,String> empDict = new HashMap<String,String>();
	

	public LogInFrame () {
		getEmployeeDict();
		//Creating Menu Elements
		JMenuBar mBar = new JMenuBar();
		JMenu mMenu = new JMenu("Menu");
		mMenu.setMnemonic(KeyEvent.VK_M);
		JMenuItem mWorker = new JMenuItem("Log In as Worker");
		JMenuItem mAdmin = new JMenuItem("Log In as Admin");
		JMenuItem mExit = new JMenuItem("Exit");
		mWorker.setMnemonic(KeyEvent.VK_W);
		mWorker.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,ActionEvent.CTRL_MASK));
		mWorker.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LogInFrame.this.setContentPane(jpWorker);
				LogInFrame.this.getContentPane().setBackground(Color.WHITE);
				LogInFrame.this.setSize(320, 560);
			}
		});
		mAdmin.setMnemonic(KeyEvent.VK_A);
		mAdmin.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
		mAdmin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LogInFrame.this.setContentPane(jpAdmin);
				LogInFrame.this.getContentPane().setBackground(Color.WHITE);
				LogInFrame.this.setSize(320, 550);
			}
		});
		mExit.setMnemonic(KeyEvent.VK_X);
		mExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
		mExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mMenu.add(mWorker);
		mMenu.add(mAdmin);
		mMenu.add(mExit);
		mBar.add(mMenu);
		this.setJMenuBar(mBar);
		
		//Creating admin panel
		jpfPw = new JPasswordField();
		jpfPw.setPreferredSize(new Dimension(120,30));
		JLabel jl=new JLabel(new ImageIcon("logo.jpg"));
		jbLogIn = new JButton("Log In");
		jbLogIn.setPreferredSize(new Dimension(100,30));
		jbLogIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (new String(jpfPw.getPassword()).equals("admin")) {
					new AdminFrame();
					LogInFrame.this.dispose();
				}	
			}
		});
		jpAdmin = new JPanel();
		jpAdmin.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.ipadx = 5;
		c.ipady = 10;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		jpAdmin.add(jl,c);
		c.gridy = 1;
		jpAdmin.add(jlProvide = new JLabel("Please provide admin password"),c);
		jlProvide.setFont(new Font("Tahoma", Font.BOLD,14));
		c.gridy = 2;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.2;
		jpAdmin.add(new JLabel("Password: "),c);
		c.gridx = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.3;
		c.insets = new Insets(0,0,0,40); 
		jpAdmin.add(jpfPw,c);
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(10,0,10,0); 
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		jpAdmin.add(jbLogIn,c);
		jpAdmin.setFont(new Font("Tahoma", Font.BOLD,14));
		
		//Creating worker panel
		jpfPw2 = new JPasswordField();
		jpfPw2.setPreferredSize(new Dimension(120,30));
		JLabel jl2=new JLabel(new ImageIcon("logo.jpg"));
		jbLogIn2 = new JButton("Log In");
		jbLogIn2.setPreferredSize(new Dimension(100,30));
		jpWorker = new JPanel();
		jpWorker.setLayout(new GridBagLayout());
		c.insets = new Insets(0,0,0,0);
		c.ipadx = 5;
		c.ipady = 10;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		jpWorker.add(jl2,c);
		c.gridy = 1;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.2;
		jpWorker.add(new JLabel("Employee ID: "),c);
		c.gridy = 2;
		jpWorker.add(new JLabel("Password: "),c);
		c.gridy = 1;
		c.gridx = 1;
		c.weightx = 0.3;
		c.insets = new Insets(0,0,0,40); 
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		jpWorker.add(jtId=new JTextField(),c);
		jtId.setPreferredSize(new Dimension(120,30));
		c.gridy = 2;
		jpWorker.add(jpfPw2,c);
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(10,0,10,0); 
		jpWorker.add(jbLogIn2,c);
		jbLogIn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(isValidData()) {
					String targetId = jtId.getText();
					String targetPassword = new String(jpfPw2.getPassword());
					if ((empDict.containsKey(targetId)) && (empDict.get(targetId).equals(targetPassword))) {
						Employee emp = empDAO.getEmployee(targetId);
						new WorkerFrame(emp);
						LogInFrame.this.dispose();
					}
					else
						JOptionPane.showMessageDialog(null,"Invalid Id and Password","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		this.setContentPane(jpWorker);
		this.getContentPane().setBackground(Color.WHITE);
		this.setTitle("Log in");
		this.setVisible(true);
		this.setSize(320, 560);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//Creating a dictionary with employees ID and Passwords
	public void getEmployeeDict() {
		ArrayList<Employee> employees = new ArrayList<Employee>();
		employees = empDAO.getEmployees();
		for (Employee emp: employees) {
			empDict.put(emp.getId(), emp.getPassword());
		}
	}
	
	public boolean isValidData() {
		if (!Validator.isPresent(jtId, "ID")) return false;
		if (!Validator.isPassword(jpfPw2, "Password")) return false;
		return true;
	}

	public static void main(String[] args) {
        UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels(); 
        for (UIManager.LookAndFeelInfo look : looks) { 
            System.out.println(look.getClassName()); 
        }
        try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new LogInFrame();

	}

}
