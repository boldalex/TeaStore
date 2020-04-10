package Presentation;
import javax.swing.JButton;
import java.awt.Font;

public class CreateProductFrame extends ProductAbstractFrame {
	public CreateProductFrame() {
		
		this.setTitle("Create new Product");
		
		JButton btnCreate = new JButton("Create");
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCreate.setBounds(82, 161, 110, 29);
		getContentPane().add(btnCreate);
	}

}
