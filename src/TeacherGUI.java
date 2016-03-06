
/*
 *
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

// TODO: Auto-generated Javadoc
/**
 * Title: TeacherGUI.java
 * Programmer: Kush Pakki
 * Purpose: Creates the Teacher Graphical Interface...
 * Version: 1.0
 */
public class TeacherGUI extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/** The content pane. */
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public TeacherGUI() {
		setTitle("Teacher Options");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnRemoveAssignments = new JButton("Remove Assignments");
		btnRemoveAssignments.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String clas = common.choose2();
					if (clas != null) {
						JFrame remove = new removeAssignments(clas);

						remove.setAlwaysOnTop(true);
						remove.setLocationRelativeTo(null);
						remove.setVisible(true);
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		// removeAssignments
		btnRemoveAssignments.setBounds(5, 5, 432, 23);
		contentPane.add(btnRemoveAssignments);

		JButton btnAddAssignments = new JButton("Add Assignments");
		btnAddAssignments.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String clas = common.choose2();
					if (clas != null) {
						JFrame add = new Teacher(clas);
						add.setAlwaysOnTop(true);
						add.setLocationRelativeTo(null);
						add.setVisible(true);
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnAddAssignments.setBounds(5, 35, 432, 23);
		contentPane.add(btnAddAssignments);

		JButton btnAddremoveStudents = new JButton("Add/Remove Students");
		btnAddremoveStudents.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					String clas = common.choose2();
					if (clas != null) {
						new addDel(clas);
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		btnAddremoveStudents.setBounds(5, 69, 427, 23);
		contentPane.add(btnAddremoveStudents);

		JButton btnGradebook = new JButton("Gradebook");
		btnGradebook.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				grades window = new grades();
				JFrame grade = grades.frame;
				grade.setAlwaysOnTop(true);
				grade.setLocationRelativeTo(null);
				grade.setVisible(true);

			}
		});

		btnGradebook.setBounds(5, 103, 427, 23);
		contentPane.add(btnGradebook);

		textField = new JTextField();
		textField.setBounds(15, 131, 303, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnCreateNewClass = new JButton("Create New Class");
		btnCreateNewClass.setToolTipText("Create New Class");
		btnCreateNewClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int reply = JOptionPane.showConfirmDialog((JFrame) SwingUtilities.getWindowAncestor(contentPane),
						"Are you sure you want to create " + textField.getText() + "?", "AcadamE",
						JOptionPane.YES_NO_OPTION);

				if (reply == JOptionPane.YES_OPTION) {

					File big = new File("src/classes/" + textField.getText());
					big.mkdirs();
					System.out.println(big);
					File h = new File("src/classes/" + textField.getText() + "/HW.txt");
					try {
						common.write("admin", "src/classes/" + textField.getText() + "/classlist.txt");

						common.write(textField.getText(), "src/userClass/" + "admin" + ".txt");

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						common.write("Welcome to" + textField.getText(),
								"src/classes/" + textField.getText() + "/HW.txt");
						common.write("Welcome to" + textField.getText(),
								"src/classes/" + textField.getText() + "/Lessons.txt");
						common.write("Welcome to" + textField.getText(),
								"src/classes/" + textField.getText() + "/Tests.txt");

						common.write(textField.getText(), "src/userClass/" + "admin" + ".txt");
						textField.setText("");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		btnCreateNewClass.setBounds(320, 130, 117, 23);
		contentPane.add(btnCreateNewClass);
	}
}
