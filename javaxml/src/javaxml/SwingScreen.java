package javaxml;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JTextField;

public class SwingScreen extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingScreen frame = new SwingScreen(args);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SwingScreen(String[] args) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1511, 666);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton btnNewButton_1 = new JButton("edytuj");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Edit.main(args);
			}
		});
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_3 = new JButton("usun");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Remove.main(args);
			}
		});
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("dodaj");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Add.main(args);
			}
		});
		panel.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("sortuj");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			      Collections.sort(ceny.lista2);
					List<Wyswietlenie> firmal = null;
					firmal = ceny.lista2;
					wyswietleniaTableModel model = new wyswietleniaTableModel(firmal);
					table.setModel(model);
			}
		});
		panel.add(btnNewButton_5);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("firmy");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ceny.pop(args);
				List<Firma> firmal = null;
				firmal = ceny.lista;
				firmaTableModel model = new firmaTableModel(firmal);
				table.setModel(model);
			}
		});
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("wyswietlenia");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ceny.pop(args);
				List<Wyswietlenie> firmal = null;
				firmal = ceny.lista2;
				wyswietleniaTableModel model = new wyswietleniaTableModel(firmal);
				table.setModel(model);
			}
		});
		panel_1.add(btnNewButton_2);
		
		table = new JTable();
		contentPane.add(table, BorderLayout.CENTER);
	}

}
