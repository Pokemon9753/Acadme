
/*
 * 
 */
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

// TODO: Auto-generated Javadoc
/*
 *  This class listens for changes made to the data in the table via the
 *  TableCellEditor. When editing is started, the value of the cell is saved
 *  When editing is stopped the new value is saved. When the oold and new
 *  values are different, then the provided Action is invoked.
 *
 *  The source of the Action is a TableCellListener instance.
 */
/**
 * The listener interface for receiving tableCell events. The class that is
 * interested in processing a tableCell event implements this interface, and the
 * object created with that class is registered with a component using the
 * component's <code>addTableCellListener<code> method. When the tableCell event
 * occurs, that object's appropriate method is invoked.
 *
 * @see TableCellEvent
 */
public class TableCellListener implements PropertyChangeListener, Runnable {

	/** The action. */
	private Action action;

	/** The column. */
	private int column;

	/** The new value. */
	private Object newValue;

	/** The old value. */
	private Object oldValue;

	/** The row. */
	private int row;

	/** The table. */
	private JTable table;

	/**
	 * Create a TableCellListener.
	 *
	 * @param table
	 *            the table to be monitored for data changes
	 * @param action
	 *            the Action to invoke when cell data is changed
	 */
	public TableCellListener(JTable table, Action action) {
		this.table = table;
		this.action = action;
		this.table.addPropertyChangeListener(this);
	}

	/**
	 * Create a TableCellListener with a copy of all the data relevant to the
	 * change of data for a given cell.
	 *
	 * @param table
	 *            the table
	 * @param row
	 *            the row of the changed cell
	 * @param column
	 *            the column of the changed cell
	 * @param oldValue
	 *            the old data of the changed cell
	 * @param newValue
	 *            the new data of the changed cell
	 */
	private TableCellListener(JTable table, int row, int column, Object oldValue, Object newValue) {
		this.table = table;
		this.row = row;
		this.column = column;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	/**
	 * Get the column that was last edited.
	 *
	 * @return the column that was edited
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * Get the new value in the cell.
	 *
	 * @return the new value in the cell
	 */
	public Object getNewValue() {
		return newValue;
	}

	/**
	 * Get the old value of the cell.
	 *
	 * @return the old value of the cell
	 */
	public Object getOldValue() {
		return oldValue;
	}

	/**
	 * Get the row that was last edited.
	 *
	 * @return the row that was edited
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Get the table of the cell that was changed.
	 *
	 * @return the table of the cell that was changed
	 */
	public JTable getTable() {
		return table;
	}

	/*
	 * Save information of the cell about to be edited
	 */
	/**
	 * Process editing started.
	 */
	private void processEditingStarted() {
		// The invokeLater is necessary because the editing row and editing
		// column of the table have not been set when the "tableCellEditor"
		// PropertyChangeEvent is fired.
		// This results in the "run" method being invoked

		SwingUtilities.invokeLater(this);
	}

	/*
	 * Update the Cell history when necessary
	 */
	/**
	 * Process editing stopped.
	 */
	private void processEditingStopped() {
		newValue = table.getModel().getValueAt(row, column);

		// The data has changed, invoke the supplied Action

		if (!newValue.equals(oldValue)) {
			// Make a copy of the data in case another cell starts editing
			// while processing this change

			TableCellListener tcl = new TableCellListener(getTable(), getRow(), getColumn(), getOldValue(),
					getNewValue());

			ActionEvent event = new ActionEvent(tcl, ActionEvent.ACTION_PERFORMED, "");
			action.actionPerformed(event);
		}
	}

	//
	// Implement the PropertyChangeListener interface
	//
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.
	 * PropertyChangeEvent)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent e) {
		// A cell has started/stopped editing

		if ("tableCellEditor".equals(e.getPropertyName())) {
			if (table.isEditing()) {
				processEditingStarted();
			} else {
				processEditingStopped();
			}
		}
	}

	/*
	 * See above.
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		row = table.convertRowIndexToModel(table.getEditingRow());
		column = table.convertColumnIndexToModel(table.getEditingColumn());
		oldValue = table.getModel().getValueAt(row, column);
		newValue = null;
	}
}