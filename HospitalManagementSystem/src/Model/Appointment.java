package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Appointment {
	private int id,doctorID,patientID;
	private String doctorName, patientName, appDate;
	
	DBConnection conn = new DBConnection();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;
	
	public Appointment(int id, int doctorID, int patientID, String doctorName, String patientName, String appDate) {
		super();
		this.id = id;
		this.doctorID = doctorID;
		this.patientID = patientID;
		this.doctorName = doctorName;
		this.patientName = patientName;
		this.appDate = appDate;
	}
	
	public Appointment() {
	}
	
	public ArrayList<Appointment> getPatientList(int patient_id) throws SQLException{
		ArrayList<Appointment> list = new ArrayList<>();
		Appointment obj;
		Connection con = conn.connDb();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM appointment WHERE patient_id = " + patient_id);
			while(rs.next()) {
				obj = new Appointment();
				obj.setId(rs.getInt("id"));
				obj.setDoctorID(rs.getInt("doctor_id"));
				obj.setDoctorName(rs.getString("doctor_name"));
				obj.setPatientID(rs.getInt("patient_id"));
				obj.setPatientName(rs.getString("patient_name"));
				obj.setAppDate(rs.getString("app_date"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			st.close();
			rs.close();
			con.close();
		}
		
		return list;
	}

	
	public ArrayList<Appointment> getDoctorList(int doctor_id) throws SQLException{
		ArrayList<Appointment> list = new ArrayList<>();
		Appointment obj;
		Connection con = conn.connDb();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM appointment WHERE docotor_id = " + doctor_id);
			while(rs.next()) {
				obj = new Appointment();
				obj.setId(rs.getInt("id"));
				obj.setDoctorID(rs.getInt("doctor_id"));
				obj.setDoctorName(rs.getString("doctor_name"));
				obj.setPatientID(rs.getInt("patient_id"));
				obj.setPatientName(rs.getString("patient_name"));
				obj.setAppDate(rs.getString("app_date"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			st.close();
			rs.close();
			con.close();
		}
		
		return list;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDoctorID() {
		return doctorID;
	}

	public void setDoctorID(int doctorID) {
		this.doctorID = doctorID;
	}

	public int getPatientID() {
		return patientID;
	}

	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getAppDate() {
		return appDate;
	}

	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}
	
	
	
}
