package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import Helper.*;
import Model.ChiefPhysician;
import Model.Doctor;
import Model.Patient;

public class LoginGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_patientID;
	private JTextField fld_doctorID;
	private JPasswordField fld_doctorPassword;
	private JPasswordField fld_patientPassword;
	private DBConnection conn = new DBConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginGUI() {
		setResizable(false);
		setTitle("Hospital");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 410);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblNewLabel = new JLabel("WELCOME TO OUR HOSPITAL");
		lblNewLabel.setFont(new Font("Sitka Banner", Font.PLAIN, 23));
		lblNewLabel.setBounds(99, 32, 284, 56);
		w_pane.add(lblNewLabel);

		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(10, 133, 474, 227);
		w_pane.add(w_tabpane);

		JPanel w_patientlogin = new JPanel();
		w_patientlogin.setBackground(Color.WHITE);
		w_tabpane.addTab("Patient Login", null, w_patientlogin, null);
		w_patientlogin.setLayout(null);

		JLabel lblIdNumber = new JLabel("ID NUMBER:");
		lblIdNumber.setFont(new Font("Rockwell", Font.PLAIN, 16));
		lblIdNumber.setBounds(55, 42, 102, 39);
		w_patientlogin.add(lblIdNumber);

		JLabel lblPassword = new JLabel("PASSWORD:");
		lblPassword.setFont(new Font("Rockwell", Font.PLAIN, 16));
		lblPassword.setBounds(55, 92, 102, 39);
		w_patientlogin.add(lblPassword);

		fld_patientID = new JTextField();
		fld_patientID.setBounds(167, 53, 250, 20);
		w_patientlogin.add(fld_patientID);
		fld_patientID.setColumns(10);

		JButton btn_register = new JButton("SIGN UP");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI rGUI = new RegisterGUI();
				rGUI.setVisible(true);
				dispose();
			}
		});
		btn_register.setFont(new Font("Rockwell", Font.PLAIN, 16));
		btn_register.setBounds(55, 153, 146, 35);
		w_patientlogin.add(btn_register);

		JButton btn_patientLogin = new JButton("SIGN IN");
		btn_patientLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_patientID.getText().length() == 0 || fld_patientPassword.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					boolean key = true;
					try {
						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");
						while (rs.next()) {
							if (fld_patientID.getText().equals(rs.getString("idno"))
									&& fld_patientPassword.getText().equals(rs.getString("password"))) {
								if (rs.getString("type").equals("patient")) {
									Patient patient = new Patient();
									patient.setId(rs.getInt("id"));
									patient.setPassword("password");
									patient.setIdno(rs.getString("idno"));
									patient.setName(rs.getString("name"));
									patient.setType(rs.getString("type"));
									PatientGUI pGUI = new PatientGUI(patient);
									pGUI.setVisible(true);
									dispose();
									key = false;
								}
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if (key) {
						Helper.showMsg("We cannot find a patient, please sign up.");
					}
				}
			}
		});
		btn_patientLogin.setFont(new Font("Rockwell", Font.PLAIN, 16));
		btn_patientLogin.setBounds(271, 153, 146, 35);
		w_patientlogin.add(btn_patientLogin);

		fld_patientPassword = new JPasswordField();
		fld_patientPassword.setBounds(167, 103, 250, 20);
		w_patientlogin.add(fld_patientPassword);

		JPanel w_doctorlogin = new JPanel();
		w_doctorlogin.setBackground(Color.WHITE);
		w_tabpane.addTab("Doctor Login", null, w_doctorlogin, null);
		w_doctorlogin.setLayout(null);

		JLabel label = new JLabel("ID NUMBER:");
		label.setFont(new Font("Rockwell", Font.PLAIN, 16));
		label.setBounds(55, 42, 102, 39);
		w_doctorlogin.add(label);

		fld_doctorID = new JTextField();
		fld_doctorID.setColumns(10);
		fld_doctorID.setBounds(167, 53, 250, 20);
		w_doctorlogin.add(fld_doctorID);

		JLabel label_1 = new JLabel("PASSWORD:");
		label_1.setFont(new Font("Rockwell", Font.PLAIN, 16));
		label_1.setBounds(55, 92, 102, 39);
		w_doctorlogin.add(label_1);

		JButton btn_doctorLogin = new JButton("SIGN IN");
		btn_doctorLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_doctorID.getText().length() == 0 || fld_doctorPassword.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");
						while (rs.next()) {
							if (fld_doctorID.getText().equals(rs.getString("idno"))
									&& fld_doctorPassword.getText().equals(rs.getString("password"))) {
								if (rs.getString("type").equals("chief physician")) {
									ChiefPhysician cp = new ChiefPhysician();
									cp.setId(rs.getInt("id"));
									cp.setPassword("password");
									cp.setIdno(rs.getString("idno"));
									cp.setName(rs.getString("name"));
									cp.setType(rs.getString("type"));
									ChiefPhysicianGUI cGUI = new ChiefPhysicianGUI(cp);
									cGUI.setVisible(true);
									dispose();
								}
								if (rs.getString("type").equals("doctor")) {
									Doctor doctor = new Doctor();
									doctor.setId(rs.getInt("id"));
									doctor.setPassword("password");
									doctor.setIdno(rs.getString("idno"));
									doctor.setName(rs.getString("name"));
									doctor.setType(rs.getString("type"));
									DoctorGUI dGUI = new DoctorGUI(doctor);
									dGUI.setVisible(true);
									dispose();
								}
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

			}
		});
		btn_doctorLogin.setFont(new Font("Rockwell", Font.PLAIN, 16));
		btn_doctorLogin.setBounds(115, 153, 239, 35);
		w_doctorlogin.add(btn_doctorLogin);

		fld_doctorPassword = new JPasswordField();
		fld_doctorPassword.setBounds(167, 103, 250, 20);
		w_doctorlogin.add(fld_doctorPassword);
	}
}
