
/*
 *
 */
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

// TODO: Auto-generated Javadoc
/**
 * The Class sreading.
 */
public class sreading {

	/** The model. */
	static DefaultListModel<String> model = new DefaultListModel<String>();

	/**
	 * Launch the application.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					sreading window = new sreading();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		Firebase ref = new Firebase("https://prodtools.firebaseio.com/AP/suggestedReading/");

		ref.addValueEventListener(new ValueEventListener() {
			@Override
			public void onCancelled(FirebaseError arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onDataChange(DataSnapshot snapshot) {
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				model.clear();
				for (DataSnapshot child : snapshot.getChildren()) {

					model.addElement(child.getKey());
				}
			}
		});

		// rightPanel.add(label);
		// rightPanel.revalidate();

	}

	/** The frame. */
	private JFrame frame;

	/** The text field. */
	private JTextField textField;

	/**
	 * Create the application.
	 */
	public sreading() {
		initialize();
	}

	/**
	 * Addnew.
	 */

	/**
	 * Close.
	 */
	public void close() {
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		final JList<String> list = new JList<String>();
		Firebase ref = new Firebase("https://prodtools.firebaseio.com/AP/suggestedReading/");

		ref.addValueEventListener(new ValueEventListener() {
			@Override
			public void onCancelled(FirebaseError arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onDataChange(DataSnapshot snapshot) {
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				DefaultListModel<String> newmodel = new DefaultListModel<String>();
				for (DataSnapshot child : snapshot.getChildren()) {

					newmodel.addElement(child.getKey());
				}
				list.setModel(newmodel);
			}
		});

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Suggested Reading");
		textField = new JTextField();
		textField.setBounds(10, 6, 323, 20);
		textField.setToolTipText("Add a new book to the list");
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Add New ");
		btnNewButton.setBounds(343, 5, 89, 23);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String usr = textField.getText();
				textField.setText("");
				System.out.println(usr);
				Firebase fb = new Firebase("https://prodtools.firebaseio.com/AP/suggestedReading/" + usr);
				fb.setValue(1, 1);

			}
		});
		frame.getContentPane().add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 37, 412, 218);
		frame.getContentPane().add(scrollPane);

		list.addMouseListener(new MouseAdapter() {
			// http://www.google.com/search?q=test
			@Override
			public void mouseClicked(MouseEvent evt) {
				if (SwingUtilities.isRightMouseButton(evt)) {
					list.setSelectedIndex(list.locationToIndex(evt.getPoint()));
					String selected12 = list.getSelectedValue();
					selected12 = selected12.replace(' ', '+');

					selected12 = "http://www.googl.com/#q=" + selected12;

					try {
						URI uri = new URI(selected12);

						if (Desktop.isDesktopSupported()) {
							try {

								Desktop.getDesktop().browse(uri);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								System.out.println(selected12);
							}
						}

					} catch (URISyntaxException e) {
						System.out.println(selected12);
					}
				} else {

					String selected = String.valueOf(list.getSelectedValue());
					System.out.print(selected);
					if (model.indexOf(selected) != -1) {
						final Firebase fb = new Firebase(
								"https://prodtools.firebaseio.com/AP/suggestedReading/" + selected);

						fb.addListenerForSingleValueEvent(new ValueEventListener() {
							@Override
							public void onCancelled(FirebaseError arg0) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onDataChange(final DataSnapshot snapshot) {

								Object val = snapshot.getValue();
								if (val != null) {
									long readers = (long) val;
									readers++;
									fb.setValue(readers, -readers);
									System.out.print(readers);
								} else {
								}

							}
						});
					}

				}
			}
		});
		scrollPane.setViewportView(list);
		frame.validate();
		// Firebase fb = new
		// Firebase("https://prodtools.firebaseio.com/AP/suggestedReading/"+usr);
	}
}
