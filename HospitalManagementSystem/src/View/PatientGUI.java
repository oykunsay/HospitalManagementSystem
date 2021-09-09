package View;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import Helper.Helper;
import Helper.Item;
import Model.Appointment;
import Model.Clinic;
import Model.Patient;
import Model.Whour;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class PatientGUI extends JFrame {

	private JPanel w_pane;
	private static Patient patient = new Patient();
	private Clinic clinic = new Clinic();
	private JTable table_doctor;
	private DefaultTableModel doctorModel;
	private Object[] doctorData = null;
	private JTable table_whour;
	private Whour whour = new Whour();
	private DefaultTableModel whourModel;
	private Object[] whourData = null;
	private int selectDoctorID = 0;
	private String selectDoctorName = null;
	private JTable table_appoint;
	private DefaultTableModel appointModel;
	private Object[] appointData = null;
	private Appointment appoint = new Appointment();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientGUI frame = new PatientGUI(patient);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PatientGUI(final Patient patient) throws SQLException {
		
		doctorModel = new DefaultTableModel();
		Object[] colDoctor = new Object[2];
		colDoctor[0] = "ID";
		colDoctor[1] = "Name Surname";
		doctorModel.setColumnIdentifiers(colDoctor);
		doctorData = new Object[2];
		
		
		whourModel = new DefaultTableModel();
		Object[] colWhour = new Object[2];
		colWhour[0] = "ID";
		colWhour[1] = "Appointment Hours";
		whourModel.setColumnIdentifiers(colWhour);
		whourData = new Object[2];
		
		appointModel = new DefaultTableModel();
		Object[] colAppoint = new Object[3];
		colAppoint[0] = "ID";
		colAppoint[1] = "Doctor";
		colAppoint[2] = "Date";
		appointModel.setColumnIdentifiers(colAppoint);
		appointData = new Object[3];
		for(int i=0; i < appoint.getPatientList(patient.getId()).size(); i++){
			appointData[0] = appoint.getPatientList(patient.getId()).get(i).getId();
			appointData[1] = appoint.getPatientList(patient.getId()).get(i).getDoctorName();
			appointData[2] = appoint.getPatientList(patient.getId()).get(i).getAppDate();
			appointModel.addRow(appointData);
		}
		
		setResizable(false);
		setTitle("Hospital Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 420);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel label = new JLabel("Welcome, " + patient.getName());
		label.setFont(new Font("Serif", Font.PLAIN, 23));
		label.setBounds(10, 22, 416, 27);
		w_pane.add(label);
		
		JButton button = new JButton("EXIT");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		button.setFont(new Font("Serif", Font.PLAIN, 13));
		button.setBounds(495, 28, 89, 23);
		w_pane.add(button);
		
		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(10, 60, 574, 304);
		w_pane.add(w_tab);
		
		JPanel w_appointment = new JPanel();
		w_appointment.setBackground(Color.WHITE);
		w_tab.addTab("Appointment System", null, w_appointment, null);
		w_appointment.setLayout(null);
		
		JScrollPane w_scrollDoctor = new JScrollPane();
		w_scrollDoctor.setBounds(10, 22, 235, 243);
		w_appointment.add(w_scrollDoctor);
		
		table_doctor = new JTable(doctorModel);
		w_scrollDoctor.setViewportView(table_doctor);
		
		JLabel lblNewLabel = new JLabel("Doctor List");
		lblNewLabel.setFont(new Font("Serif", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, -3, 87, 31);
		w_appointment.add(lblNewLabel);
		
		JLabel lbl_clinic = new JLabel("Clinic Name");
		lbl_clinic.setFont(new Font("Serif", Font.PLAIN, 16));
		lbl_clinic.setBounds(255, -1, 112, 27);
		w_appointment.add(lbl_clinic);
		
		final JComboBox select_clinic = new JComboBox();
		select_clinic.setBounds(255, 28, 112, 22);
		select_clinic.addItem("-Choose Policinic-");
		
		for(int i = 0; i < clinic.getList().size(); i++) {
			select_clinic.addItem(new Item(clinic.getList().get(i).getId(), clinic.getList().get(i).getName()));
		}
		select_clinic.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(select_clinic.getSelectedIndex() != 0) {
					JComboBox c = (JComboBox) e.getSource();
					Item item = (Item) c.getSelectedItem();
					DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
					clearModel.setRowCount(0);
					try {
						for(int i=0; i< clinic.getClinicDoctorList(item.getKey()).size(); i++) {
							doctorData[0] = clinic.getClinicDoctorList(item.getKey()).get(i).getId();
							doctorData[1] = clinic.getClinicDoctorList(item.getKey()).get(i).getName();
							doctorModel.addRow(doctorData);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}else {
					DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
					clearModel.setRowCount(0);
					
				}
			}
		});
		w_appointment.add(select_clinic);
		
		JLabel lblChooseDoctor = new JLabel("Choose Doctor");
		lblChooseDoctor.setFont(new Font("Serif", Font.PLAIN, 16));
		lblChooseDoctor.setBounds(255, 72, 112, 27);
		w_appointment.add(lblChooseDoctor);
		
		JButton btn_selDoctor = new JButton("CHOOSE");
		btn_selDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_doctor.getSelectedRow();
				if(row >= 0) {
					String value = table_doctor.getModel().getValueAt(row, 0).toString();
					int id = Integer.parseInt(value);
					DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
					clearModel.setRowCount(0);
					
					try {
						for(int i = 0; i < whour.getWhourList(id).size(); i++) {
							whourData[0] = whour.getWhourList(id).get(i).getId();
							whourData[1] = whour.getWhourList(id).get(i).getWdate();
							whourModel.addRow(whourData);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					table_whour.setModel(whourModel);
					selectDoctorID = id;
					selectDoctorName = table_doctor.getModel().getValueAt(row, 1).toString();
					
				}else{
					Helper.showMsg("Please choose a valid doctor!");
				}
			}
		});
		btn_selDoctor.setFont(new Font("Serif", Font.PLAIN, 13));
		btn_selDoctor.setBounds(255, 103, 112, 23);
		w_appointment.add(btn_selDoctor);
		
		JScrollPane w_scrollWhour = new JScrollPane();
		w_scrollWhour.setBounds(374, 22, 185, 243);
		w_appointment.add(w_scrollWhour);
		
		table_whour = new JTable(whourModel);
		w_scrollWhour.setViewportView(table_whour);
		table_whour.getColumnModel().getColumn(0).setPreferredWidth(5);
		
		JLabel lblAppointmentHours = new JLabel("Appointment Hours");
		lblAppointmentHours.setFont(new Font("Serif", Font.PLAIN, 16));
		lblAppointmentHours.setBounds(374, -3, 185, 31);
		w_appointment.add(lblAppointmentHours);
		
		JLabel lblMakeAppointment = new JLabel("Appointment");
		lblMakeAppointment.setFont(new Font("Serif", Font.PLAIN, 16));
		lblMakeAppointment.setBounds(255, 146, 112, 27);
		w_appointment.add(lblMakeAppointment);
		
		JButton btn_addAppointment = new JButton("MAKE APPOINTMENT");
		btn_addAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_whour.getSelectedRow();
				if(selRow >= 0) {
					String date = table_whour.getModel().getValueAt(selRow, 1).toString();
					try {
						boolean control = patient.addAppointment(selectDoctorID, patient.getId(), selectDoctorName, patient.getName(), date);
						if(control) {
							Helper.showMsg("success");
							patient.updateWhoursStatus(selectDoctorID, date);
							updateWhourModel(selectDoctorID);
							updateAppointModel(patient.getId());
						}else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}else {
					Helper.showMsg("Please choose a valid date!");
				}
			}
		});
		btn_addAppointment.setFont(new Font("Serif", Font.PLAIN, 10));
		btn_addAppointment.setBounds(255, 177, 112, 23);
		w_appointment.add(btn_addAppointment);
		
		JPanel w_appoint = new JPanel();
		w_tab.addTab("My Appointments", null, w_appoint, null);
		w_appoint.setLayout(null);
		
		JScrollPane w_scrollAppoint = new JScrollPane();
		w_scrollAppoint.setBounds(10, 11, 549, 254);
		w_appoint.add(w_scrollAppoint);
		
		table_appoint = new JTable(appointModel);
		w_scrollAppoint.setViewportView(table_appoint);
	}
	
	public void updateWhourModel(int doctor_id) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
		clearModel.setRowCount(0);
		for(int i = 0; i < whour.getWhourList(doctor_id).size(); i++) {
			whourData[0] = whour.getWhourList(doctor_id).get(i).getId();
			whourData[1] = whour.getWhourList(doctor_id).get(i).getWdate();
			whourModel.addRow(whourData);
			
		}
	}
	
	
	public void updateAppointModel(int patient_id) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_appoint.getModel();
		clearModel.setRowCount(0);
		for(int i=0; i < appoint.getPatientList(patient_id).size(); i++){
			appointData[0] = appoint.getPatientList(patient_id).get(i).getId();
			appointData[1] = appoint.getPatientList(patient_id).get(i).getDoctorName();
			appointData[2] = appoint.getPatientList(patient_id).get(i).getAppDate();
			appointModel.addRow(appointData);
		}
	}
}
