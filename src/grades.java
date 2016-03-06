
/*
 *
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

// TODO: Auto-generated Javadoc
/**
 * The Class grades.
 */
public class grades {

	/**
	 * Launch the application.
	 */

	public static JFrame frame;

	/** The model. */
	static DefaultListModel<String> model = new DefaultListModel<String>();

	/** The model1. */
	static DefaultListModel<String> model1 = new DefaultListModel<String>();
	/** The table_model. */
	static DefaultTableModel table_model;

	/** The table. */
	private JTable table;

	/**
	 * Create the application.
	 */
	public grades() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		model.clear();
		model1.clear();
		final JList<String> list_1 = new JList<String>(model);
		final JList<String> list = new JList<String>(model1);
		final JLabel lblClassname = new JLabel("CLASSNAME");
		final JLabel lblStudentName = new JLabel("Student Name");
		final JLabel lblGrade = new JLabel("Grade");
		frame = new JFrame();
		frame.setTitle("GradeBook Teacher");
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBackground(Color.RED);
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(null);

		final ArrayList<String> ClassListing = new ArrayList<String>();
		File file = new File("src/classes/");
		String[] names = file.list();

		for (String name : names) {
			if (new File("src/classes/" + name).isDirectory()) {
				ClassListing.add(name);
			}
		}
		String[] v = ClassListing.toArray(new String[ClassListing.size()]);
		for (String s : v) {
			model.addElement(s);
		}

		lblStudentName.setForeground(Color.WHITE);
		lblStudentName.setFont(new Font("Algerian", Font.PLAIN, 12));
		lblStudentName.setBounds(97, 14, 123, 11);
		frame.getContentPane().add(lblStudentName);

		String column_names[] = { "Serial Number", "Medicine Name", "Dose", "Frequency" };
		table_model = new DefaultTableModel(column_names, 4);
		table = new JTable(table_model);
		Action action = new AbstractAction() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {

				TableCellListener tcl = (TableCellListener) e.getSource();
				System.out.println("Row   : " + tcl.getRow());
				System.out.println("Column: " + tcl.getColumn());
				System.out.println("Old   : " + tcl.getOldValue());
				System.out.println("New   : " + tcl.getNewValue());
				try {
					switch (tcl.getColumn()) {
					case 1:
						double got = Double.parseDouble((String) tcl.getNewValue());

						if (got > 4) {
							JOptionPane.showMessageDialog(frame, "The grade you entered is above 4!!!!");
							table_model.setValueAt(tcl.getOldValue(), tcl.getRow(), tcl.getColumn());

						} else if (got < 0) {
							JOptionPane.showMessageDialog(frame, "The grade you entered is below 0!!!!");
							table_model.setValueAt(tcl.getOldValue(), tcl.getRow(), tcl.getColumn());

						} else {
							double outof = Double
									.parseDouble((String) table_model.getValueAt(tcl.getRow(), tcl.getColumn() + 1));
							double gotten = got / outof;
							gotten *= 100;
							table_model.setValueAt(gotten, tcl.getRow(), tcl.getColumn() + 2);
							int max = table_model.getRowCount();
							double total = 0;
							for (int i = 0; i < max; i++) {
								System.out.println(table_model.getValueAt(tcl.getRow(), tcl.getColumn() + 2));
								double val = Double.parseDouble("" + table_model.getValueAt(i, tcl.getColumn() + 2));
								total += val;
							}
							total /= max;
							lblGrade.setText(total + "");
							for (int ii = 0; ii < max; ii++) {
								Firebase ref = new Firebase(
										"https://prodtools.firebaseio.com/AP/" + list.getSelectedValue().toString()
												+ "/" + list_1.getSelectedValue().toString() + "/" + ii + "/");
								ref.setValue(table_model.getValueAt(ii, 1));
							}
						}

						break;

					}
				} catch (NumberFormatException ee) {
					// not a double
					JOptionPane.showMessageDialog(grades.frame, "NOT A NUMBER!!!");
					table_model.setValueAt(tcl.getOldValue(), tcl.getRow(), tcl.getColumn());
				}
			}
		};

		new TableCellListener(table, action);
		table.setBounds(97, 33, 248, 222);
		frame.getContentPane().add(table);

		lblGrade.setFont(new Font("Bernard MT Condensed", Font.PLAIN, 11));
		lblGrade.setForeground(Color.WHITE);
		lblGrade.setBounds(301, 12, 44, 14);
		frame.getContentPane().add(lblGrade);

		lblClassname.setForeground(Color.WHITE);
		lblClassname.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 13));
		lblClassname.setBounds(191, 12, 100, 14);
		frame.getContentPane().add(lblClassname);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 77, 244);
		frame.getContentPane().add(scrollPane);
		scrollPane.setViewportView(list_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(355, 14, 77, 241);
		frame.getContentPane().add(scrollPane_1);

		scrollPane_1.setViewportView(list);

		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				try {
					FileInputStream fsd3;
					table_model.setRowCount(0);
					table.revalidate();
					fsd3 = new FileInputStream("src/classes/" + list_1.getSelectedValue().toString() + "/HW.txt");

					BufferedReader brd3 = new BufferedReader(new InputStreamReader(fsd3));

					String lined3;
					while ((lined3 = brd3.readLine()) != null) {
						String result = lined3.replaceAll("﻿", "");
						if (!result.equals("")) // don't write out blank lines
						{
							table_model.addRow(new String[] { result, "0", "4", "0" });

						}
					}
					brd3.close();
					FileInputStream fsd33;

					fsd33 = new FileInputStream("src/classes/" + list_1.getSelectedValue().toString() + "/Tests.txt");

					BufferedReader brd33 = new BufferedReader(new InputStreamReader(fsd33));

					String lined33;
					while ((lined33 = brd33.readLine()) != null) {
						String result = lined33.replaceAll("﻿", "");
						if (!result.equals("")) // don't write out blank lines
						{
							table_model.addRow(new String[] { result, "0", "4", "0" });

						}
					}
					brd33.close();
					FileInputStream fsd333;

					fsd333 = new FileInputStream(
							"src/classes/" + list_1.getSelectedValue().toString() + "/Lessons.txt");

					BufferedReader brd333 = new BufferedReader(new InputStreamReader(fsd333));

					String lined333;
					while ((lined333 = brd333.readLine()) != null) {
						String result = lined333.replaceAll("﻿", "");
						if (!result.equals("")) // don't write out blank lines
						{
							table_model.addRow(new String[] { result, "0", "4", "0" });

						}
					}
					brd333.close();
					try {
						Firebase ref = new Firebase(
								"https://prodtools.firebaseio.com/AP/" + list.getSelectedValue().toString() + "/"
										+ list_1.getSelectedValue().toString() + "/");
						final ArrayList<String> x = new ArrayList<String>();
						ref.addListenerForSingleValueEvent(new ValueEventListener() {
							@Override
							public void onCancelled(FirebaseError arg0) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onDataChange(DataSnapshot snapshot) {
								for (DataSnapshot child : snapshot.getChildren()) {
									System.out.println(child.getValue());
									x.add((String) child.getValue());
								}
							}
						});
						try {
							Thread.sleep(1000); // 1000 milliseconds is one
												// second.
						} catch (InterruptedException ex) {
							Thread.currentThread().interrupt();
						}
						int row = 0;
						for (String grade : x) {

							table_model.setValueAt(grade, row, 1);

							double gotten = Double.parseDouble(grade) / 4;
							gotten *= 100;
							table_model.setValueAt(gotten, row, 3);
							row++;
						}
						int max = table_model.getRowCount();
						double total = 0;
						for (int i = 0; i < max; i++) {
							double val = Double.parseDouble("" + table_model.getValueAt(i, 3));
							total += val;
						}
						total /= max;
						lblGrade.setText(total + "");

					} catch (Exception e) {
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String selection = String.valueOf(list.getSelectedValue());

				if (selection != null) {
					lblStudentName.setText(selection);
				}

				// HW||Points||4
				// Tests||Points||4
				// NAME||Points||4

			}
		});

		list_1.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if (!arg0.getValueIsAdjusting()) {
					model1.clear();
					try {
						FileInputStream fsd2;

						fsd2 = new FileInputStream(
								"src/classes/" + list_1.getSelectedValue().toString() + "/classlist.txt");

						BufferedReader brd2 = new BufferedReader(new InputStreamReader(fsd2));

						String lined2;

						while ((lined2 = brd2.readLine()) != null) {
							String result = lined2.replaceAll("﻿", "");
							if (!result.equals("")) // don't write out blank
													// lines
							{
								model1.addElement(result);
							}

						}
						try {
							brd2.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					list.setSelectedIndex(0);

					lblClassname.setText(list_1.getSelectedValue().toString());

				}
			}
		});
		frame.validate();
	}
}
