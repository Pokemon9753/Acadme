
/*
 *
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

// TODO: Auto-generated Javadoc
/**
 * The Class Teacher.
 */
public class Teacher extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/** The content pane. */
	private JPanel contentPane;

	/**
	 * Create the frame.
	 *
	 * @param select
	 *            the select
	 */
	public Teacher(String select) {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		final ArrayList<String> ClassListing = new ArrayList<String>();
		File file = new File("src/classes/");
		String[] names = file.list();

		for (String name : names) {
			if (new File("src/classes/" + name).isDirectory()) {
				ClassListing.add(name);
			}
		}
		String[] classes = ClassListing.toArray(new String[ClassListing.size()]);

		contentPane.setLayout(null);
		int indexSel = java.util.Arrays.asList(classes).indexOf(select);

		final JList<String> list = new JList<String>();
		final JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
		tabbedPane.setBounds(147, 11, 285, 244);
		contentPane.add(tabbedPane);

		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		tabbedPane.addTab("HW", null, textArea, null);

		JTextArea textArea_1 = new JTextArea();
		textArea_1.setLineWrap(true);
		tabbedPane.addTab("Lessons", null, textArea_1, null);

		JTextArea textArea_2 = new JTextArea();
		textArea_2.setLineWrap(true);
		tabbedPane.addTab("Tests", null, textArea_2, null);

		JButton btnSubmit = new JButton("Submit ");

		btnSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JTextArea com = (JTextArea) tabbedPane.getComponentAt(tabbedPane.getSelectedIndex());
				String add = com.getText();
				add = add.replaceAll("[\\n\\t]", "");
				String addTo = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
				String selected = list.getSelectedValue().toString();
				Writer output;
				try {
					int reply = JOptionPane.showConfirmDialog((JFrame) SwingUtilities.getWindowAncestor(contentPane),
							"Are you sure you want to add " + add + " ?", "AcadamE", JOptionPane.YES_NO_OPTION);

					if (reply == JOptionPane.YES_OPTION) {

						output = new BufferedWriter(
								new FileWriter("src/classes/" + selected + "/" + addTo + ".txt", true));

						output.append(System.getProperty("line.separator") + add);
						output.close();
						com.setText(null);
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		btnSubmit.setBounds(28, 216, 89, 23);
		contentPane.add(btnSubmit);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 127, 198);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(list);

		list.setModel(new AbstractListModel<String>() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			String[] values = ClassListing.toArray(new String[ClassListing.size()]);

			@Override
			public String getElementAt(int index) {
				return values[index];
			}

			@Override
			public int getSize() {
				return values.length;
			}
		});
		list.setSelectedIndex(indexSel);
		contentPane.validate();
	}
}
