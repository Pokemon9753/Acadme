
/*
 *
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

// TODO: Auto-generated Javadoc
/**
 * addDel.java Purpose: Prints a message to the screen.
 *
 * @author Kush Pakki
 * @version 1.0 8/20/03
 */

public class addDel {

	/** The allusers. */
	static ArrayList<String> allusers;

	/** The Class. */
	static String Class;

	/** The currusers. */
	static ArrayList<String> currusers;

	/**
	 * Adds the whatever is needed to be added.
	 *
	 * @param toAdd
	 *            the to add
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private static void add(String toAdd) throws IOException {
		common.write(toAdd, "src/classes/" + Class + "/classlist.txt");
		common.write(Class, "src/userClass/" + toAdd + ".txt");
	}

	/**
	 * Removes the.
	 *
	 * @param hi
	 *            the hi
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void remove(String hi) throws IOException {
		common.unwrite(hi, "src/classes/" + Class + "/", "classlist.txt");

	}

	/**
	 * Start.
	 *
	 * @param args
	 *            the args
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void start(String[] args) throws IOException {
		try {
			allusers = common.toAL("src/users");
			currusers = common.toAL("src/classes/" + Class + "/classlist.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] options1 = { "add", "remove" };
		String input; // SHOWS CHOICES
		JFrame jf = new javax.swing.JFrame("tmp");
		jf.setLocation(600, 400);
		jf.setVisible(true);
		jf.setAlwaysOnTop(true);
		jf.setAlwaysOnTop(false);
		input = (String) JOptionPane.showInputDialog(null, "Choose now...", "CHOOSE CLASS",
				JOptionPane.QUESTION_MESSAGE, null, options1, options1[0]); // Initial
																			// choice
		jf.dispose();

		if (input.equals("add")) {
			ArrayList<String> availusers = new ArrayList<String>();
			for (String a : allusers) {
				String text = a.replace("\n", "").replace("\r", "");
				if (!text.equals("")) {
					if (!currusers.contains(a)) {
						availusers.add(a);
					}
				}
			}
			String[] options2 = availusers.toArray(new String[availusers.size()]);
			JFrame jff = new JFrame("tmp");
			jff.setLocation(600, 400);
			jff.setVisible(true);
			jff.setAlwaysOnTop(true);
			jff.setAlwaysOnTop(false);
			input = (String) JOptionPane.showInputDialog(null, "Choose now...", "Add Student",
					JOptionPane.QUESTION_MESSAGE, null, options2, options2[0]); // Initial
																				// choice
			jff.dispose();

			int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to add " + input + " to your class?",
					"AcadamE", JOptionPane.YES_NO_OPTION);
			System.out.println(input);
			if (reply == JOptionPane.YES_OPTION && input != "null") {
				add(input);
			}
		} else {
			ArrayList<String> currusersNew = new ArrayList<String>();
			for (String check : currusers) {

				String text = check.replace("\n", "").replace("\r", "");
				if (!text.equals("")) {
					currusersNew.add(text);
				}

			}
			String[] options3 = currusersNew.toArray(new String[currusersNew.size()]);
			System.out.println(options3[0]);
			JFrame jfff = new javax.swing.JFrame("tmp");
			jfff.setLocation(600, 400);
			jfff.setVisible(true);
			jfff.setAlwaysOnTop(true);
			jfff.setAlwaysOnTop(false);
			input = (String) JOptionPane.showInputDialog(null, "Choose now...", "Remove Student",
					JOptionPane.QUESTION_MESSAGE, null, options3, options3[0]); // Initial
																				// choice
			jfff.dispose();
			int reply = JOptionPane.showConfirmDialog(null,
					"Are you sure you want to remove " + input + " from your class?", "AcadamE",
					JOptionPane.YES_NO_OPTION);

			if (reply == JOptionPane.YES_OPTION && input != "null") {
				remove(input);
			}

		}

	}

	/**
	 * Start options.
	 *
	 * @param title
	 *            the title
	 */
	public static void startOptions(String title) {
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("============================");
		System.out.println("|   " + title + "??????   |");
		System.out.println("============================");
		System.out.println("| Options:                 |");
	}

	/**
	 * Instantiates a new adds the del.
	 *
	 * @param classUser
	 *            the class user
	 */
	addDel(String classUser) {
		Class = classUser;

		try {
			start(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
