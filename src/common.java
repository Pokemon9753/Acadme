
/*
 *
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

// TODO: Auto-generated Javadoc
/**
 * The Class common.
 */
public class common {

	/**
	 * Title: common.java
	 * Programmer: Kush Pakki
	 * Purpose: Common Functions shared throughout the project
 	* Version: 1.0
	 *
	 * @return the string
	 * @throws FileNotFoundException
	 *             the file not found exception
	 */
	public static String choose2() throws FileNotFoundException { //Creates a Dropdown of Class choices
		ArrayList<String> ClassListing = new ArrayList<String>();
		File file = new File("src/classes/");
		String[] names = file.list();

		for (String name : names) {
			if (new File("src/classes/" + name).isDirectory()) {
				ClassListing.add(name);
			}
		}
		String[] cl = ClassListing.toArray(new String[ClassListing.size()]);

		String input; // SHOWS CHOICES
		JFrame jf = new javax.swing.JFrame("tmp");
		jf.setLocation(600, 400);
		jf.setVisible(true);
		jf.setAlwaysOnTop(true);
		jf.setAlwaysOnTop(false);
		try {
			input = (String) JOptionPane.showInputDialog(null, "Choose now...", "CHOOSE CLASS",
					JOptionPane.QUESTION_MESSAGE, null, cl, cl[0]); // Initial
																	// choice
			jf.dispose();
			System.out.println(input);
			if (input != "null") {
				return input;
			} else {
				return "HIGAP A";
			}
		} catch (Exception e) {
			return "HIGAP A";
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
			if (line.matches(".*[a-zA-Z]+.*")) {
				choices.add(line);
			}
		}
		String[] stockArr = new String[choices.size()];

		stockArr = choices.toArray(stockArr);

		br.close();
		return stockArr;

	}

	/**
	 * Read file.
	 *
	 * @param path
	 *            the path
	 * @return the string builder
	 */
	public static StringBuilder readFile(String path) {
		// Common ReadFile that will be used throughout the Application
		File file = new File(path);
		StringBuilder builder = new StringBuilder();
		if (!file.exists()) {
			throw new RuntimeException("File not found");
		}
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return builder;
	}

	/**
	 * To Array List.
	 *
	 * @param path
	 *            the path
	 * @return the array list
	 * @throws FileNotFoundException
	 *             the file not found exception
	 */
	public static ArrayList<String> toAL(String path) throws FileNotFoundException {
		ArrayList<String> x = new ArrayList<String>();
		FileInputStream fsd3 = new FileInputStream(path);
		BufferedReader brd3 = new BufferedReader(new InputStreamReader(fsd3));

		String lined3;
		try {
			while ((lined3 = brd3.readLine()) != null) {
				x.add(lined3);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			brd3.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return x;

	}

	/**
	 * Unwrites from text file.
	 *
	 * @param what
	 *            the what
	 * @param where
	 *            the where
	 * @param who
	 *            the who
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void unwrite(String what, String where, String who) throws IOException {
		File inputFile = new File(where + who);
		File tempFile = new File(where + "myTempFile.txt");

		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

		String lineToRemove = what;
		String currentLine;

		while ((currentLine = reader.readLine()) != null) {
			currentLine.trim();
			if (currentLine.contains(lineToRemove)) {
				continue;
			}
			writer.write(currentLine + System.getProperty("line.separator"));
		}
		writer.close();
		reader.close();
		inputFile.delete();
		tempFile.renameTo(new File(where + who));

	}

	/**
	 * Writes to file.
	 *
	 * @param what
	 *            the what
	 * @param where
	 *            the where
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void write(String what, String where) throws IOException {

		Writer output2;
		output2 = new BufferedWriter(new FileWriter(where, true));
		output2.append(System.getProperty("line.separator") + what);
		output2.close();

	}

}
