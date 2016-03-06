
/*
 * 
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import javax.swing.JScrollPane;

// TODO: Auto-generated Javadoc
/**
 * The Class StudentGrades.
 */
public class StudentGrades extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The table_model. */

	static DefaultTableModel table_model = new DefaultTableModel() {

		/**
		 *
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public boolean isCellEditable(int row, int column) {
			// all cells false
			return false;
		}
	};

	/** The content pane. */
	private JPanel contentPane;

	/** The model list. */
	public DefaultListModel<String> modelList = new DefaultListModel<String>();

	/** The table. */
	private JTable table;

	/**
	 * Launch the application.
	 *
	 * @param user
	 *            the user
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public StudentGrades(final String user) throws IOException {
		setTitle(user);
		final JLabel lblGrade = new JLabel("Grade");
		final JLabel lblClass = new JLabel("Class");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		String[] cl = common.openList(user);
		for (String clas : cl) {
			modelList.addElement(clas);
		}
		final JList<String> list = new JList<String>(modelList);
		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				try {
					FileInputStream fsd3;
					table_model.setRowCount(0);
					table.revalidate();
					fsd3 = new FileInputStream("src/classes/" + list.getSelectedValue() + "/HW.txt");

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

					fsd33 = new FileInputStream("src/classes/" + list.getSelectedValue().toString() + "/Tests.txt");

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

					fsd333 = new FileInputStream("src/classes/" + list.getSelectedValue().toString() + "/Lessons.txt");

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
						Firebase ref = new Firebase("https://prodtools.firebaseio.com/AP/" + user + "/"
								+ list.getSelectedValue().toString() + "/");
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
					lblClass.setText(selection);
				}

				// HW||Points||4
				// Tests||Points||4
				// NAME||Points||4

			}
		});

		list.setBounds(10, 11, 110, 244);
		contentPane.add(list);

		String column_names[] = { "Assignment Names", "Points Gotten", "Points Possible", "Grade" };
		table_model = new DefaultTableModel(column_names, 4);

		lblGrade.setBounds(146, 24, 46, 14);
		contentPane.add(lblGrade);

		lblClass.setBounds(236, 24, 137, 14);
		contentPane.add(lblClass);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(130, 41, 302, 214);
		contentPane.add(scrollPane);
		table = new JTable(table_model);
		scrollPane.setViewportView(table);
	}
}
