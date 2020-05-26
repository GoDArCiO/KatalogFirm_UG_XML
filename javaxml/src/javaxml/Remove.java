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

public class Remove extends JFrame {

	private JPanel contentPane;
	private JTextField txtXnip;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Remove frame = new Remove(args);
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
	public Remove(String[] args) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 502, 143);
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
		
		JButton btnNewButton = new JButton("firma+wysw");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String xnip = txtXnip.getText();
				ceny.rm(args,xnip,false);
				ceny.rm(args,xnip,true);
			}
		});
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("wyswietlenia");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String xnip = txtXnip.getText();
				ceny.rm(args,xnip,false);
			}
		});
		panel.add(btnNewButton_1);
	}

}
