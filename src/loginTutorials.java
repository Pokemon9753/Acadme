
/*
 *
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

// TODO: Auto-generated Javadoc
/**
 * The Class loginTutorials.
 */
public class loginTutorials {

	/** The frame. */
	public static JFrame frame = new JFrame("InputDialog Example #2");
	/** The pass. */
	public static List<String> pass = new ArrayList<String>();
	/** The user. */
	public static List<String> user = new ArrayList<String>();

	/**
	 * Choose.
	 *
	 * @param cl
	 *            the cl
	 * @param u
	 *            the u
	 * @throws FileNotFoundException
	 *             the file not found exception
	 */
	public static void choose(String[] cl, String u) throws FileNotFoundException {
		String input; // SHOWS CHOICES
		JFrame jf = new javax.swing.JFrame("tmp");
		jf.setLocation(600, 400);
		jf.setVisible(true);
		jf.setAlwaysOnTop(true);
		jf.setAlwaysOnTop(false);
		input = (String) JOptionPane.showInputDialog(null, "Choose now...", "CHOOSE CLASS",
				JOptionPane.QUESTION_MESSAGE, null, cl, cl[0]); // Initial
																// choice
		jf.dispose();
		System.out.println(input);
		StudentGUI c = new StudentGUI(input, u);
		c.setVisible(true);
		// class_room c=new class_room(input,u); //CREATES class_room CLASS
		// WHICH IS A JFRAME
		// c.setVisible(true);
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws IOException {
		setup();
		boolean cont = true;
		while (cont) {

			String u = JOptionPane.showInputDialog(frame, "Insert UserName", "Login to AcademE",
					JOptionPane.QUESTION_MESSAGE);
			System.out.print(u);
			int index = -1;
			if (u != null) {
				index = user.indexOf(u);
			} else {
				u = "User Hit Cancel";
			}

			if (index > -1) { // IF USER EXISTS
				String p = "";
				JPasswordField passwordField = new JPasswordField();
				passwordField.setEchoChar('*');
				Object[] obj = { "Please enter the password:\n\n", passwordField };
				Object stringArray[] = { "OK", "Cancel" };
				if (JOptionPane.showOptionDialog(null, obj, "Need password", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, stringArray, obj) == JOptionPane.YES_OPTION) {
					p = new String(passwordField.getPassword());
				}
				try {
					int index2 = pass.indexOf(p);
					if (index2 == index) { // IF THE PASSWORD MATCHES THE
											// USERNAME PAIR

						String[] cl = openList(u); // CREATES AN ARRAY OF THE
													// CLASSES OF THE CHOSEN
													// USER

						choose(cl, u); // Creates Choice for Class Selection

						cont = false;
					} else {
						JOptionPane.showMessageDialog(frame, "That is incorrect");
						cont = true;

					}
				} catch (Exception e) {
					StudentGUI c = new StudentGUI(null, u);
					c.setVisible(true);
					cont = false;
				}

			} else if (u.equals("teacher") || u.equals("Teacher")) {

				JFrame c = new TeacherGUI(); // CREATES class_room CLASS WHICH
												// IS A JFRAME
				c.setAlwaysOnTop(true);
				c.setVisible(true); // Creates Choice for Class Selection

				cont = false;
			} else if (u.equals("User Hit Cancel")) {

				JOptionPane.showMessageDialog(frame, "Thank you for using AcadamE");
				cont = false;

			}

			else {// IF USER DOES NOT EXIST

				try {
					int reply = JOptionPane.showConfirmDialog(null, "Create User?", "AcadamE",
							JOptionPane.YES_NO_OPTION);

					if (reply == JOptionPane.YES_OPTION) {
						// CREATES IT IF USER TYPES IN TRUE
						common.write(u, "src/users");

						String p = JOptionPane.showInputDialog(frame, "Insert new Password", "Login to AcademE",
								JOptionPane.WARNING_MESSAGE);

						new StudentGUI(null, u).setVisible(true);
						common.write(p, "src/pass");
						cont = false;

					}

					else {

						JOptionPane.showMessageDialog(frame, "I'll take that as a No! Thank you for using AcadamE");
						cont = false;
					}
				}

				catch (Exception e) {
					JOptionPane.showMessageDialog(frame, "I'll take that as a No! Thank you for using AcadamE");
					cont = false;
				}

			}
		}

	}

	/**
	 * Open list.
	 *
	 * @param username
	 *            the username
	 * @return the string[]
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static String[] openList(String username) throws IOException {

		List<String> choices = new ArrayList<String>(); // Choices that the User
														// will have

		// Common Read code that will be used through out the Application
		FileInputStream fs = new FileInputStream("src/userClass/" + username + ".txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fs));

		String line;
		while ((line = br.readLine()) != null) {
			choices.add(line);

		}
		String[] stockArr = new String[choices.size()];

		stockArr = choices.toArray(stockArr);

		br.close();
		return stockArr;

	}

	/**
	 * Adds the users and the pass.
	 *
	 * @throws FileNotFoundException
	 *             the file not found exception
	 */
	public static void setup() throws FileNotFoundException {
		FileInputStream fs = new FileInputStream("src/users");
		BufferedReader br = new BufferedReader(new InputStreamReader(fs));

		String line;
		try {
			while ((line = br.readLine()) != null) {
				user.add(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileInputStream fs1 = new FileInputStream("src/pass");
		BufferedReader br1 = new BufferedReader(new InputStreamReader(fs1));

		String line1;
		try {
			while ((line1 = br1.readLine()) != null) {
				pass.add(line1);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** The frm opt. */
	JFrame frmOpt; // dummy JFrame needed to show JInput in front...

}