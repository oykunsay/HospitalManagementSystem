package View;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Helper.Helper;
import Model.Clinic;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UpdateClinicGUI extends JFrame {

	private JPanel contentPane;
	private JTextField fld_cName;
	private static Clinic clinic;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateClinicGUI frame = new UpdateClinicGUI(clinic);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public UpdateClinicGUI(final Clinic clinic) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 220, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_cName = new JLabel("Clinic Name");
		lbl_cName.setFont(new Font("Serif", Font.PLAIN, 16));
		lbl_cName.setBounds(10, 11, 185, 27);
		contentPane.add(lbl_cName);
		
		fld_cName = new JTextField();
		fld_cName.setColumns(10);
		fld_cName.setBounds(10, 37, 185, 20);
		fld_cName.setText(clinic.getName());
		contentPane.add(fld_cName);
		
		JButton btn_cUPDATE = new JButton("UPDATE");
		btn_cUPDATE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Helper.confirm("sure")) {
					try {
						clinic.updateClinic(clinic.getId(), fld_cName.getText());
						Helper.showMsg("success");
						dispose();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_cUPDATE.setFont(new Font("Serif", Font.PLAIN, 13));
		btn_cUPDATE.setBounds(10, 68, 185, 23);
		contentPane.add(btn_cUPDATE);
	}
}
