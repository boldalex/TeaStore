package Presentation;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Business.Order;
import Business.OrderItem;
public class PropertiesPanel extends JPanel {
	private static JRadioButton jrbLarge, jrbRegular, 
	jrbNormalIce, jrbLessIce, jrbNoIce, jrbWarm, jrbHot,
	jrbNormalSugar, jrbLessSugar, jrbHalfSugar, jrbSlightSugar, jrbNoSugar;

	private static JCheckBox jcbTapioca, jcbPudding, jcbCheese, jcbGrassJelly;
	
	private static JLabel jlSize, jlSugar, jlIce, jlToppings, jlPriceLabel, jlPriceValue, jlName;
	
	private static JButton jbDelete;
	
	private static ButtonGroup groupSugar, groupIce, groupSize;

	private static OrderItem orderItem;

	public PropertiesPanel() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		jlSize = new JLabel("Size: ");
		jlSize.setFont(new Font("Tahoma", Font.BOLD,14));
		
		jlIce = new JLabel("Ice: ");
		jlIce.setFont(new Font("Tahoma", Font.BOLD,14));
		
		jlSugar = new JLabel("Sugar: ");
		jlSugar.setFont(new Font("Tahoma", Font.BOLD,14));
		
		jlToppings = new JLabel("Toppings: ");
		jlToppings.setFont(new Font("Tahoma", Font.BOLD,14));
		
		jlPriceLabel = new JLabel("Unit Price: ");
		jlPriceLabel.setFont(new Font("Tahoma", Font.BOLD,14));
		
		jlPriceValue = new JLabel("0$");
		jlPriceValue.setFont(new Font("Tahoma", Font.BOLD,14));
		
		jlName = new JLabel("Product Name");
		jlName.setFont(new Font("Tahoma", Font.BOLD,16));
		
		jbDelete = new JButton("Delete Item");
		jbDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OrderItem temp = orderItem;
				Order.deleteItem(temp);
				OrderPanel.updateCost();
			}	
		});
		
		
		
		jrbLarge=new JRadioButton("Large");
		jrbRegular=new JRadioButton("Regular");
		jrbLarge.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				orderItem.setSize("Large");
				jlPriceValue.setText(String.valueOf(orderItem.getPrice()) + "$");
				OrderPanel.updateCost();
			}	
		});
		jrbRegular.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				orderItem.setSize("Regular");
				jlPriceValue.setText(String.valueOf(orderItem.getPrice()) + "$");
				OrderPanel.updateCost();
			}	
		});
		groupSize = new ButtonGroup();
		groupSize.add(jrbLarge);
		groupSize.add(jrbRegular);
		
		

		JPanel jpIce = new JPanel();
		jpIce.setLayout(new GridLayout(3,2));
		jpIce.add(jrbNormalIce=new JRadioButton("Normal Ice"));
		jpIce.add(jrbLessIce=new JRadioButton("Less Ice"));
		jpIce.add(jrbNoIce=new JRadioButton("No Ice"));
		jpIce.add(jrbWarm=new JRadioButton("Warm"));
		jpIce.add(jrbHot=new JRadioButton("Hot"));
		
		jrbNormalIce.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				orderItem.setIce("Normal Ice");
			}	
		});
		jrbLessIce.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				orderItem.setIce("Less Ice");
			}	
		});
		jrbNoIce.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				orderItem.setIce("No Ice");
			}	
		});
		jrbWarm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				orderItem.setIce("Warm");
			}	
		});
		jrbHot.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				orderItem.setIce("Hot");
			}	
		});


		groupIce = new ButtonGroup();
		groupIce.add(jrbNormalIce);
		groupIce.add(jrbLessIce);
		groupIce.add(jrbNoIce);
		groupIce.add(jrbWarm);
		groupIce.add(jrbHot);


		jrbNormalSugar= new JRadioButton("Normal Sugar");
		jrbLessSugar= new JRadioButton("Less Sugar");
		jrbHalfSugar= new JRadioButton("Half Sugar");
		jrbSlightSugar= new JRadioButton("Slight Sugar");
		jrbNoSugar= new JRadioButton("No Sugar");	
		
		jrbNormalSugar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				orderItem.setSugar("Normal Sugar");
			}	
		});
		jrbLessSugar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				orderItem.setSugar("Less Sugar");
			}	
		});
		jrbHalfSugar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				orderItem.setSugar("Half Sugar");
			}	
		});
		jrbSlightSugar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				orderItem.setSugar("Slight Sugar");
			}	
		});
		jrbNoSugar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				orderItem.setSugar("No Sugar");
			}	
		});

		groupSugar = new ButtonGroup();
		groupSugar.add(jrbNormalSugar);
		groupSugar.add(jrbLessSugar);
		groupSugar.add(jrbHalfSugar);
		groupSugar.add(jrbSlightSugar);
		groupSugar.add(jrbNoSugar);

		

		JPanel jpToppings = new JPanel();

		jpToppings.setLayout(new GridLayout(1,5));
		jpToppings.add(jcbTapioca= new JCheckBox("Tapioca <0.5>"));
		jpToppings.add(jcbPudding= new JCheckBox("Pudding <1>"));
		jpToppings.add(jcbCheese= new JCheckBox("Cheese <1>"));
		jpToppings.add(jcbGrassJelly= new JCheckBox("Grass Jelly <0.5>"));

		c.ipadx = 3;
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		//Adding labels
		//Adding elements for size property
		c.gridx = 0;
		c.gridy = 1;
		this.add(jlSize, c);
		c.gridy = 2;
		c.gridheight = 3;
		this.add(jlIce, c);
		c.gridy = 5;
		this.add(jlSugar, c);
		c.gridy = 8;
		c.gridheight = 2;
		this.add(jlToppings,c);
		c.gridy = 10;
		c.gridheight = 1;
		c.anchor = GridBagConstraints.LINE_END;
		this.add(jlPriceLabel, c);
		c.gridx = 1;
		c.anchor = GridBagConstraints.LINE_START;
		this.add(jlPriceValue, c);
		c.gridx = 2;
		c.ipadx = 10;
		c.ipady = 10;
		this.add(jbDelete,c);
		
		
		c.ipadx = 0;
		c.ipady = 0;		
		c.gridheight = 1;
		c.insets = new Insets(0,0,10,0);
		//Adding radiobuttons for size
		c.gridy = 1;
		c.gridx = 1;
		this.add(jrbLarge, c);
		c.gridx = 2;
		this.add(jrbRegular, c);
		
		c.insets = new Insets(0,0,0,0);
		//Adding radiobuttons for ice property
		c.gridy = 2;
		c.gridx = 1;
		this.add(jrbNormalIce,c);
		c.gridx = 2;
		this.add(jrbLessIce,c);
		c.gridy = 3;
		c.gridx = 1;
		this.add(jrbNoIce,c);
		c.gridx = 2;
		this.add(jrbWarm,c);
		c.gridx = 1;
		c.gridwidth = 2;
		c.gridy = 4;
		c.insets = new Insets(0,0,10,0);
		this.add(jrbHot,c);
		
		c.gridwidth = 1;
		c.insets = new Insets(0,0,0,0);
		//Adding radiobuttons for sugar property jrbNormalSugar, jrbLessSugar, jrbHalfSugar, jrbSlightSugar, jrbNoSugar
		c.gridy = 5;
		c.gridx = 1;
		this.add(jrbNormalSugar,c);
		c.gridx = 2;
		this.add(jrbLessSugar,c);
		c.gridy = 6;
		c.gridx = 1;
		this.add(jrbHalfSugar,c);
		c.gridx = 2;
		this.add(jrbSlightSugar,c);
		c.gridy = 7;
		c.gridx = 1;
		c.gridwidth = 2;
		c.insets = new Insets(0,0,10,0);
		this.add(jrbNoSugar,c);
		
		c.gridwidth = 1;
		c.insets = new Insets(0,0,0,0);
		//Adding checkboxes for toppings jcbTapioca, jcbPudding, jcbCheese, jcbGrassJelly
		c.gridy = 8;
		c.gridx = 1;
		this.add(jcbTapioca,c);
		c.gridx = 2;
		this.add(jcbPudding,c);
		c.gridy = 9;
		c.gridx = 1;
		c.insets = new Insets(0,0,30,0);
		this.add(jcbCheese,c);
		c.gridx = 2;
		this.add(jcbGrassJelly,c);
		
		
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		this.add(jlName,c);

		//this.add(jpSugar);
		//this.add(jpIce);
		//this.add(jpToppings);


	}

	public static void showItemProperties(OrderItem oi) {
		if (OrderPanel.isSelection()) {
			orderItem = oi;
			jlName.setText(orderItem.getProductName());
			jlPriceValue.setText(String.valueOf(orderItem.getPrice()) + "$");
			

			String size = oi.getSize();
			if(size.equals("Regular"))
				jrbRegular.setSelected(true);
			else
				jrbLarge.setSelected(true);

			String ice = oi.getIce();
			if(ice.equals("Normal Ice"))
				jrbNormalIce.setSelected(true);
			else if (ice.equals("Less Ice"))
				jrbLessIce.setSelected(true);
			else if (ice.equals("No Ice"))
				jrbNoIce.setSelected(true);
			else if (ice.equals("Warm"))
				jrbWarm.setSelected(true);
			else
				jrbHot.setSelected(true);

			String sugar = oi.getSugar();
			if(sugar.equals("Normal Sugar"))
				jrbNormalSugar.setSelected(true);
			else if (sugar.equals("Less Sugar"))
				jrbLessSugar.setSelected(true);
			else if (sugar.equals("Half Sugar"))
				jrbHalfSugar.setSelected(true);
			else if (sugar.equals("Slight Sugar"))
				jrbSlightSugar.setSelected(true);
			else
				jrbNoSugar.setSelected(true);
		}
		else {
			showEmpty();
		}
	}
	
	public static void showEmpty() {
		orderItem = null;
		jlName.setText("<Product Name>");
		groupIce.clearSelection();
		groupSize.clearSelection();
		groupSugar.clearSelection();
	}

}
