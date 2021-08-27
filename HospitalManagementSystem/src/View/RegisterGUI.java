package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Patient;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class RegisterGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_name;
	private JTextField fld_idno;
	private JPasswordField fld_pass;
	private Patient patient = new Patient();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		setResizable(false);
		setTitle("Hospital Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 255, 300);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel label = new JLabel("Name Surname");
		label.setFont(new Font("Serif", Font.PLAIN, 16));
		label.setBounds(10, 11, 135, 27);
		w_pane.add(label);
		
		fld_name = new JTextField();
		fld_name.setColumns(10);
		fld_name.setBounds(10, 37, 221, 20);
		w_pane.add(fld_name);
		
		JLabel label_1 = new JLabel("Identity Number");
		label_1.setFont(new Font("Serif", Font.PLAIN, 16));
		label_1.setBounds(10, 68, 135, 27);
		w_pane.add(label_1);
		
		fld_idno = new JTextField();
		fld_idno.setColumns(10);
		fld_idno.setBounds(10, 95, 221, 20);
		w_pane.add(fld_idno);
		
		JLabel label_2 = new JLabel("Password");
		label_2.setFont(new Font("Serif", Font.PLAIN, 16));
		label_2.setBounds(10, 126, 134, 27);
		w_pane.add(label_2);
		
		fld_pass = new JPasswordField();
		fld_pass.setBounds(10, 153, 221, 20);
		w_pane.add(fld_pass);
		
		JButton btn_register = new JButton("SIGN UP");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_idno.getText().length() == 0 || fld_pass.getText().length() == 0 || fld_name.getText().length() == 0) {
					Helper.showMsg("fill");
				}else {
					try {
						boolean control = patient.register(fld_idno.getText(), fld_pass.getText() , fld_name.getText());
						if(control) {
							Helper.showMsg("success");
							LoginGUI login = new LoginGUI();
							login.setVisible(true);
							dispose();
						}else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_register.setFont(new Font("Serif", Font.PLAIN, 13));
		btn_register.setBounds(10, 184, 221, 23);
		w_pane.add(btn_register);
		
		JButton btn_backto = new JButton("CANCEL");
		btn_backto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btn_backto.setFont(new Font("Serif", Font.PLAIN, 13));
		btn_backto.setBounds(10, 218, 221, 23);
		w_pane.add(btn_backto);
	}
}
