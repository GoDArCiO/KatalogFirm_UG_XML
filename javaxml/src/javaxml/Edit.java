package javaxml;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.awt.event.ActionEvent;

public class Edit extends JFrame {

	private JPanel contentPane;
	private JTextField txtXnip;
	private JTextField txtNazwa;
	private JTextField txtIban;
	private JTextField txtTelefon;
	private JTextField txtMail;
	private JTextField txtPesel;
	private JTextField txtDowodosobisty;
	private JPanel panel_1;
	private JTextField txtXnip_1;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Edit frame = new Edit(args);
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
	public Edit(String[] args) {
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
		
		JButton btnNewButton = new JButton("save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String xnip = txtXnip.getText();
				String Nazwa = txtNazwa.getText();
				String Iban= txtIban.getText();
				String Telefon = txtTelefon.getText();
				String Mail = txtMail.getText();
				String Pesel = txtPesel.getText();
				String Dowodosobisty = txtDowodosobisty.getText();
				ceny.rm(args,xnip,true);
				ceny.add(args,xnip,Nazwa,Iban,Telefon,Mail,Pesel,Dowodosobisty);
				
			}
		});
		panel.add(btnNewButton);
		
		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		
		txtXnip_1 = new JTextField();
		txtXnip_1.setText("xnip");
		panel_1.add(txtXnip_1);
		txtXnip_1.setColumns(10);
		
		btnNewButton_1 = new JButton("get");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
	              Iterator itr = ceny.lista.iterator();

	              while(itr.hasNext()) {   
	                   Firma firma = (Firma) itr.next();
	                   if(txtXnip_1.getText().equals(firma.getNip())) {
	       				txtXnip.setText(firma.getNip());
	    				txtNazwa.setText(firma.getNazwa());
	    				txtIban.setText(firma.getIban());
	    				txtTelefon.setText(firma.getTelefon());
	    				txtMail.setText(firma.getMail());
	    				txtPesel.setText(firma.getPesel());
	    				txtDowodosobisty.setText(firma.getDowod_osobisty());
	                   }
	              }
	              
	              
			}
		});
		panel_1.add(btnNewButton_1);
	}

}
