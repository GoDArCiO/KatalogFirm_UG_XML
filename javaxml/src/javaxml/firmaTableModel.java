package javaxml;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class firmaTableModel extends AbstractTableModel{
	
	private static final int LAST_NAME_COL = 0;
	private static final int FIRST_NAME_COL = 1;
	private static final int EMAIL_COL = 2;
	private static final int EMAIL_COL2 = 3;
	private static final int EMAIL_COL3 = 4;
	private static final int EMAIL_COL4 = 5;
	private static final int EMAIL_COL5 = 6;


	private String[] columnNames = { "nip", "nazwa", "iban","telefon","mail","pesel","dowod_osobisty"};
	
	private List<Firma> employees;

	public firmaTableModel(List<Firma> theEmployees) {
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

		Firma tempEmployee = employees.get(row);

		switch (col) {
		case LAST_NAME_COL:
			return tempEmployee.getNip();
		case FIRST_NAME_COL:
			return tempEmployee.getNazwa();
		case EMAIL_COL:
			return tempEmployee.getIban();
		case EMAIL_COL2:
			return tempEmployee.getTelefon();
		case EMAIL_COL3:
			return tempEmployee.getMail();
		case EMAIL_COL4:
			return tempEmployee.getPesel();
		case EMAIL_COL5:
			return tempEmployee.getDowod_osobisty();
		default:
			return tempEmployee.getNip();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
