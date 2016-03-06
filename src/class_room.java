
/*
 *
 */

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

// TODO: Auto-generated Javadoc
/**
 * The Class class_room.
 */
public class class_room extends JFrame {

	DefaultListModel<String> model1 = new DefaultListModel<String>();;
	DefaultListModel<String> model2 = new DefaultListModel<String>();;
	DefaultListModel<String> model3 = new DefaultListModel<String>();;

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
	 * @throws FileNotFoundException
	 *             the file not found exception
	 */
	public class_room(String c, final String user) throws FileNotFoundException {
		// Sets up JFrame
		setTitle("Classroom");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Documents and Settings\\Kush_2\\Desktop\\Images\\a3d355afa32c27c6da412df8d9961bca.jpg"));
		setBackground(Color.BLUE);
		setAutoRequestFocus(false);
		setAlwaysOnTop(true);
		setBounds(100, 100, 615, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
		tabbedPane.setBounds(0, 0, 607, 391);
		contentPane.add(tabbedPane);

		// Sets up Sources for Lists
		ArrayList<String> HWNOchoices = common.toAL("src/" + user + "/finHW.txt");
		ArrayList<String> choices = common.toAL("src/classes/" + c + "/HW.txt");
		String[] ar = new String[choices.size()];
		ar = choices.toArray(ar);
		for (String a1 : ar) {
			if (!HWNOchoices.contains(a1))
				model1.addElement(a1);
		}
		ArrayList<String> TNOchoices = common.toAL("src/" + user + "/finT.txt");
		ArrayList<String> choices2 = common.toAL("src/classes/" + c + "/Tests.txt");
		String[] ar2 = new String[choices.size()];
		ar2 = choices2.toArray(ar2);

		for (String a1 : ar2) {
			if (!TNOchoices.contains(a1))
				model2.addElement(a1);
		}
		ArrayList<String> LesNOchoices = common.toAL("src/" + user + "/finLes.txt");
		ArrayList<String> choices3 = common.toAL("src/classes/" + c + "/Lessons.txt");
		String[] ar3 = new String[choices3.size()];
		ar3 = choices3.toArray(ar3);

		for (String a1 : ar3) {
			if (!LesNOchoices.contains(a1))
				model3.addElement(a1);
		}

		// Sets up Lists
		JList<String> list_3 = new JList<String>(model1);
		tabbedPane.addTab("Homework", null, list_3, null);
		list_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				@SuppressWarnings("unchecked")
				JList<String> list = (JList<String>) evt.getSource();
				if (SwingUtilities.isLeftMouseButton(evt)) {

					list.setSelectedIndex(list.locationToIndex(evt.getPoint()));
					String selected = list.getSelectedValue().toString();

					try {
						URI uri = new URI(selected);

						if (Desktop.isDesktopSupported()) {
							try {

								Desktop.getDesktop().browse(uri);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

					} catch (URISyntaxException e) {
					}
				} else if (SwingUtilities.isRightMouseButton(evt)) {

					list.setSelectedIndex(list.locationToIndex(evt.getPoint()));
					String selected = list.getSelectedValue().toString();
					int reply = JOptionPane.showConfirmDialog(contentPane,
							"Are you sure you want to delete " + selected + " from your class?", "AcadamE",
							JOptionPane.YES_NO_OPTION);

					if (reply == JOptionPane.YES_OPTION) {
						Writer output2;
						try {
							model1.removeElement(selected);
							output2 = new BufferedWriter(new FileWriter("src/" + user + "/finHW.txt", true));
							output2.append(System.getProperty("line.separator") + selected);

							output2.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}
			}
		});
		JList<String> list_1 = new JList<String>(model2);
		tabbedPane.addTab("Tests", null, list_1, null);
		list_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				@SuppressWarnings("unchecked")
				JList<String> list = (JList<String>) evt.getSource();
				if (SwingUtilities.isLeftMouseButton(evt)) {
					String selected = list.getSelectedValue().toString();
					System.out.println(selected);
					try {
						URI uri = new URI(selected);

						if (Desktop.isDesktopSupported()) {
							try {

								Desktop.getDesktop().browse(uri);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

					} catch (URISyntaxException e) {
					}
				} else if (SwingUtilities.isRightMouseButton(evt)) {

					list.setSelectedIndex(list.locationToIndex(evt.getPoint()));
					String selected = list.getSelectedValue().toString();
					int reply = JOptionPane.showConfirmDialog(contentPane,
							"Are you sure you want to delete " + selected + " from your class?", "AcadamE",
							JOptionPane.YES_NO_OPTION);

					if (reply == JOptionPane.YES_OPTION) {
						Writer output2;
						try {
							model2.removeElement(selected);
							output2 = new BufferedWriter(new FileWriter("src/" + user + "/finT.txt", true));
							output2.append(System.getProperty("line.separator") + selected);

							output2.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		});
		JList<?> list_2 = new JList<String>(model3);
		tabbedPane.addTab("Lessons", null, list_2, null);

		list_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				@SuppressWarnings("unchecked")
				JList<String> list = (JList<String>) evt.getSource();
				if (SwingUtilities.isLeftMouseButton(evt)) {
					String selected = list.getSelectedValue().toString();
					System.out.println(selected);
					try {
						URI uri = new URI(selected);

						if (Desktop.isDesktopSupported()) {
							try {

								Desktop.getDesktop().browse(uri);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

					} catch (URISyntaxException e) {
					}
				} else if (SwingUtilities.isRightMouseButton(evt)) {

					list.setSelectedIndex(list.locationToIndex(evt.getPoint()));
					String selected = list.getSelectedValue().toString();
					int reply = JOptionPane.showConfirmDialog(contentPane,
							"Are you sure you want to delete " + selected + " from your class?", "AcadamE",
							JOptionPane.YES_NO_OPTION);

					if (reply == JOptionPane.YES_OPTION) {
						Writer output2;
						try {
							model3.removeElement(selected);
							output2 = new BufferedWriter(new FileWriter("src/" + user + "/finLes.txt", true));
							output2.append(System.getProperty("line.separator") + selected);

							output2.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		});

	}
}
