package Presentation;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.text.JTextComponent;

public class Validator {
	public static boolean isPresent(JTextComponent c, String title) {
		if (c.getText().length() == 0) {
			showMessage(c, title + " is a required field.\nPlease re-enter");
			c.requestFocusInWindow();
			return false;
		}
		return true;
	}
	public static boolean isPassword(JPasswordField c, String title) {
		if (c.getPassword().length == 0) {
			showMessage(c, title + " is a required field.\nPlease re-enter");
			c.requestFocusInWindow();
			return false;
		}
		return true;
	}
	public static boolean isInteger(JTextComponent c, String title) {
		try {
			int i = Integer.parseInt(c.getText());
			return true;
		}
		catch (NumberFormatException e) {
			showMessage(c, title + "must be integer.\nPlease re-enter");
			c.requestFocusInWindow();
			return false;
		}
	}
	public static boolean isDouble(JTextComponent c, String title) {
		try {
			double d = Double.parseDouble(c.getText());
			return true;
		}
		catch (NumberFormatException e) {
			showMessage(c, title + "must be integer.\nPlease re-enter");
			c.requestFocusInWindow();
			return false;
		}
	}
	private static void showMessage(JTextComponent c, String message) {
		JOptionPane.showMessageDialog(c, message, "Invalid Entry", JOptionPane.ERROR_MESSAGE);
	}

	public static boolean isText(JTextComponent c, String title) {
		String s = c.getText();
		int len = s.length();
		for (int i = 0; i < len; i++) {
			// checks whether the character is not a letter
			// if it is not a letter ,it will return false
			if ((!Character.isLetter(s.charAt(i)))&&(!(s.charAt(i) == ' '))) {
				showMessage(c, title + " can't contain digits.\nPlease re-enter");
				c.requestFocusInWindow();
				return false;
			}
		}
		return true;
	}

}
