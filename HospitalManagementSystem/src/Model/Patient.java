package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Helper.Helper;

public class Patient extends User{
	
	Statement st = null;
	ResultSet rs = null;
	Connection con = conn.connDb();
	PreparedStatement preparedStatement = null;
	

	public Patient() {
	}

	public Patient(int id, String idno, String password, String name, String type) {
		super(id, idno, password, name, type);
	}
	
	public boolean register(String idno, String password, String name) throws SQLException {
		int key = 0;
		boolean duplicate = false;
		String query = "INSERT INTO user" + "(idno,password,name,type) VALUES" + "(?,?,?,?)";
		
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM user WHERE idno = '" + idno +"'");
			
			while(rs.next()) {
				duplicate = true;
				Helper.showMsg("There is no register for this ID number.");
				break;
			}
			
			if(!duplicate) {
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, idno);
				preparedStatement.setString(2, password);
				preparedStatement.setString(3, name);
				preparedStatement.setString(4, "patient");
				preparedStatement.executeUpdate();
			}
			key=1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(key== 1)
			return true;
		else 
			return false;
	
	}
	
	public boolean addAppointment(int doctor_id, int patient_id, String doctor_name, String patient_name, String appDate) throws SQLException {
		int key = 0;
		String query = "INSERT INTO appointment" + "(doctor_id,doctor_name, patient_id,patient_name,app_date) VALUES" + "(?,?,?,?,?)";
		
		try {			
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setInt(1, doctor_id);
				preparedStatement.setString(2, doctor_name);
				preparedStatement.setInt(3, patient_id);
				preparedStatement.setString(4, patient_name);
				preparedStatement.setString(5, appDate);
				preparedStatement.executeUpdate();
				key=1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(key== 1)
			return true;
		else 
			return false;
	
	}

	public boolean updateWhoursStatus(int doctor_id, String wdate) throws SQLException {
		int key = 0;
		String query = "UPDATE whour SET status = ? WHERE doctor_id = ? AND wdate = ?";
		
		
		try {			
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, "p");
				preparedStatement.setInt(2, doctor_id);
				preparedStatement.setString(3, wdate);
				preparedStatement.executeUpdate();
				key=1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		boolean res = (key==1) ? (true) : (false);
	
	}
	
	
}
