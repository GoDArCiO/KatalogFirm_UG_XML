package javaxml;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Add extends JFrame {

	private JPanel contentPane;
	private JTextField txtXnip;
	private JTextField txtNazwa;
	private JTextField txtIban;
	private JTextField txtTelefon;
	private JTextField txtMail;
	private JTextField txtPesel;
	private JTextField txtDowodosobisty;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Add frame = new Add(args);
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
	public Add(String[] args) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1479, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		txtXnip = new JTextField();
		txtXnip.setText("xnip");
		panel.add(txtXnip);
		txtXnip.setColumns(10);
		
		txtNazwa = new JTextField();
		txtNazwa.setText("nazwa");
		panel.add(txtNazwa);
		txtNazwa.setColumns(10);
		
		txtIban = new JTextField();
		txtIban.setText("iban");
		panel.add(txtIban);
		txtIban.setColumns(10);
		
		txtTelefon = new JTextField();
		txtTelefon.setText("telefon");
		panel.add(txtTelefon);
		txtTelefon.setColumns(10);
		
		txtMail = new JTextField();
		txtMail.setText("mail");
		panel.add(txtMail);
		txtMail.setColumns(10);
		
		txtPesel = new JTextField();
		txtPesel.setText("pesel");
		panel.add(txtPesel);
		txtPesel.setColumns(10);
		
		txtDowodosobisty = new JTextField();
		txtDowodosobisty.setText("dowod_osobisty");
		panel.add(txtDowodosobisty);
		txtDowodosobisty.setColumns(10);
		
		JButton btnNewButton = new JButton("add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String xnip = txtXnip.getText();
				String Nazwa = txtNazwa.getText();
				String Iban= txtIban.getText();
				String Telefon = txtTelefon.getText();
				String Mail = txtMail.getText();
				String Pesel = txtPesel.getText();
				String Dowodosobisty = txtDowodosobisty.getText();
				ceny.add(args,xnip,Nazwa,Iban,Telefon,Mail,Pesel,Dowodosobisty);
				
			}
		});
		panel.add(btnNewButton);
	}

}
