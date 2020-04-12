package Presentation;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import Business.Product;
import Data.DBAccessFactory;
import Data.JDBCProductDAO;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateProductFrame extends ProductAbstractFrame {
	private JDBCProductDAO productDAO = DBAccessFactory.getProductDAO();
	public CreateProductFrame() {
		
		this.setTitle("Create new Product");
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isValidData()) {
					Product product = new Product();
					product.setName(txtName.getText());
					product.setCategory((String)cbCategory.getSelectedItem());
					product.setPrice(Double.parseDouble(txtPrice.getText()));
					if (productDAO.createProduct(product) == 1) {
						JOptionPane.showMessageDialog(null,"Product has been created","Success",JOptionPane.INFORMATION_MESSAGE);
						CreateProductFrame.this.dispose();
					}
					
				}

			}
		});
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCreate.setBounds(82, 161, 110, 29);
		getContentPane().add(btnCreate);
	}
	
	public boolean isValidData() {
		if (!Validator.isPresent(txtName, "Product Name")) return false;
		if (!Validator.isText(txtName, "Product Name")) return false;
		if (!Validator.isPresent(txtPrice, "Product Price")) return false;
		if (!Validator.isDouble(txtPrice, "Product Price")) return false;
		return true;
	}

}
