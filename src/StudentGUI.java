
/*
 *
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

// TODO: Auto-generated Javadoc
/**
 * * Title: StudentGUI.java
 * Programmer: Kush Pakki
 * Purpose: Creates the Student Graphical Interface...
 * Version: 1.0
 */
public class StudentGUI extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The content pane. */
	private JPanel contentPane;

	/**
	 * Create the frame.
	 *
	 * @param c
	 *            the c
	 * @param user
	 *            the user
	 */
	public StudentGUI(final String c, final String user) {

		setTitle("Student Options");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnCheckGrades = new JButton("Check Grades");
		btnCheckGrades.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StudentGrades cFrame;
				try {
					cFrame = new StudentGrades(user);

					cFrame.setAlwaysOnTop(true);
					cFrame.setVisible(true);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "You Have no classes...");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "You Have no classes...");
				}
			}
		});
		btnCheckGrades.setBounds(10, 11, 422, 23);
		contentPane.add(btnCheckGrades);

		JButton btnClassRoom = new JButton("Class Room");
		btnClassRoom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				class_room cFrame;
				try {
					cFrame = new class_room(c, user);

					cFrame.setAlwaysOnTop(true);
					cFrame.setVisible(true);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "You Have no classes...");
				}
			}
		});
		btnClassRoom.setBounds(10, 45, 422, 23);
		contentPane.add(btnClassRoom);

		JButton btnSuggestedReading = new JButton("Suggested Reading");
		btnSuggestedReading.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				sreading.main(null);
			}
		});
		btnSuggestedReading.setBounds(10, 79, 422, 23);
		contentPane.add(btnSuggestedReading);
	}
}
