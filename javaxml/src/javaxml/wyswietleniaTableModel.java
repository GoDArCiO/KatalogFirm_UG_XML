package javaxml;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class wyswietleniaTableModel extends AbstractTableModel{
	private static final int LAST_NAME_COL = 0;
	private static final int FIRST_NAME_COL = 1;
	private static final int EMAIL_COL = 2;
	private static final int EMAIL_COL2 = 3;


	private String[] columnNames = { "nip", "dataStart", "dataStop","koszt"};
	
	private List<Wyswietlenie> employees;

	public wyswietleniaTableModel(List<Wyswietlenie> theEmployees) {
		employees = theEmployees;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return employees.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Wyswietlenie tempEmployee = employees.get(row);

		switch (col) {
		case LAST_NAME_COL:
			return tempEmployee.getNip();
		case FIRST_NAME_COL:
			return tempEmployee.getdStart();
		case EMAIL_COL:
			return tempEmployee.getdStop();
		case EMAIL_COL2:
			return tempEmployee.getKoszt();
		default:
			return tempEmployee.getNip();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
