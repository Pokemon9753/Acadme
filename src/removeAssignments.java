/*
 *
 */

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
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
public class removeAssignments extends JFrame {

	/** The model hw. */
	static DefaultListModel<String> modelHW = new DefaultListModel<String>();

	/** The model lesson. */
	static DefaultListModel<String> modelLESSON = new DefaultListModel<String>();

	/** The model test. */
	static DefaultListModel<String> modelTEST = new DefaultListModel<String>();
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The content pane. */
	private JPanel contentPane;

	public static void clearModels() {
		modelHW.clear();
		modelLESSON.clear();
		modelTEST.clear();

	}

	/**
	 * Create the frame.
	 *
	 * @param c
	 *            the c
	 * @throws FileNotFoundException
	 *             the file not found exception
	 */
	public removeAssignments(final String c) throws FileNotFoundException {
		// Sets up JFrame
		clearModels();
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
		ArrayList<String> choices = common.toAL("src/classes/" + c + "/HW.txt");
		String[] ar1 = new String[choices.size()];
		ar1 = choices.toArray(ar1);
		for (String hw : ar1) {
			modelHW.addElement(hw);
		}
		ArrayList<String> choices2 = common.toAL("src/classes/" + c + "/Tests.txt");
		String[] ar2 = new String[choices2.size()];
		ar2 = choices2.toArray(ar2);
		for (String test : ar2) {
			modelTEST.addElement(test);
		}
		ArrayList<String> choices3 = common.toAL("src/classes/" + c + "/Lessons.txt");
		String[] ar3 = new String[choices3.size()];
		ar3 = choices3.toArray(ar3);
		for (String lesson : ar3) {
			modelLESSON.addElement(lesson);
		}

		// Sets up Lists
		JList<String> list_3 = new JList<String>(modelHW);
		tabbedPane.addTab("Homework", null, list_3, null);
		list_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				@SuppressWarnings("unchecked")
				JList<String> list = (JList<String>) evt.getSource();
				if (SwingUtilities.isLeftMouseButton(evt)) {
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

					int reply = JOptionPane.showConfirmDialog((JFrame) SwingUtilities.getWindowAncestor(contentPane),
							"Are you sure you want to remove " + selected + " from your class?", "AcadamE",
							JOptionPane.YES_NO_OPTION);

					if (reply == JOptionPane.YES_OPTION) {

						try {
							modelHW.removeElement(selected);
							common.unwrite(selected, "src/classes/" + c + "/", "HW.txt");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		});
		JList<String> list_1 = new JList<String>(modelTEST);
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

					int reply = JOptionPane.showConfirmDialog((JFrame) SwingUtilities.getWindowAncestor(contentPane),
							"Are you sure you want to remove " + selected + " from your class?", "AcadamE",
							JOptionPane.YES_NO_OPTION);

					if (reply == JOptionPane.YES_OPTION) {

						try {
							modelTEST.removeElement(selected);
							common.unwrite(selected, "src/classes/" + c + "/", "Tests.txt");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		});
		JList<?> list_2 = new JList<String>(modelLESSON);
		tabbedPane.addTab("Lessons", null, list_2, null);

		list_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				@SuppressWarnings("unchecked")
				JList<String> list = (JList<String>) evt.getSource();
				if (SwingUtilities.isLeftMouseButton(evt)) {
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

					int reply = JOptionPane.showConfirmDialog((JFrame) SwingUtilities.getWindowAncestor(contentPane),
							"Are you sure you want to remove " + selected + " from your class?", "AcadamE",
							JOptionPane.YES_NO_OPTION);

					if (reply == JOptionPane.YES_OPTION) {

						try {
							modelLESSON.removeElement(selected);
							common.unwrite(selected, "src/classes/" + c + "/", "Lessons.txt");
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
