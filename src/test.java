
/*
 *
 */
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

// TODO: Auto-generated Javadoc
/**
 * The Class test.
 */
public class test {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		String password = "";
		JPasswordField passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		Object[] obj = { "Please enter the password:\n\n", passwordField };
		Object stringArray[] = { "OK", "Cancel" };
		if (JOptionPane.showOptionDialog(null, obj, "Need password", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, stringArray, obj) == JOptionPane.YES_OPTION) {
			password = new String(passwordField.getPassword());
		}
		System.out.print(password);
	}

}
