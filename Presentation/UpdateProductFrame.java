package Presentation;
import javax.swing.JButton;
import java.awt.Font;

public class UpdateProductFrame extends ProductAbstractFrame {
	public UpdateProductFrame() {
		
		this.setTitle("Update Product Info");
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpdate.setBounds(80, 152, 106, 31);
		getContentPane().add(btnUpdate);
		
	}

}
