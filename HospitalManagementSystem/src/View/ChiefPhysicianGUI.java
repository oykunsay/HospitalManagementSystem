package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Model.*;

import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import Helper.*;
import javax.swing.JComboBox;

public class ChiefPhysicianGUI extends JFrame {

	static ChiefPhysician chiefphysician = new ChiefPhysician();
	Clinic clinic = new Clinic();
	private JPanel w_pane;
	private JTextField fld_dName;
	private JTextField fld_dID;
	private JTextField fld_dPass;
	private JTextField fld_UserID;
	private JTable table_doctor;
	private DefaultTableModel doctorModel = null;
	private Object[] doctorData = null;
	private JTable table_clinic;
	private JTextField fld_clinicName;
	private DefaultTableModel clinicModel = null;
	private Object[] clinicData = null;
	private JPopupMenu clinicMenu;
	private JTable table_worker;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChiefPhysicianGUI frame = new ChiefPhysicianGUI(chiefphysician);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ChiefPhysicianGUI(final ChiefPhysician chiefphysician) throws SQLException {
		setTitle("Hospital Management System");

		doctorModel = new DefaultTableModel();
		Object[] colDoctorName = new Object[4];
		colDoctorName[0] = "ID";
		colDoctorName[1] = "Name Surname";
		colDoctorName[2] = "Identity Number";
		colDoctorName[3] = "Password";
		doctorModel.setColumnIdentifiers(colDoctorName);
		doctorData = new Object[4];
		for (int i = 0; i < chiefphysician.getDoctorList().size(); i++) {
			doctorData[0] = chiefphysician.getDoctorList().get(i).getId();
			doctorData[1] = chiefphysician.getDoctorList().get(i).getName();
			doctorData[2] = chiefphysician.getDoctorList().get(i).getIdno();
			doctorData[3] = chiefphysician.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
		}

		clinicModel = new DefaultTableModel();
		Object[] colClinic = new Object[2];
		colClinic[0] = "ID";
		colClinic[1] = "Clinic Name";
		clinicModel.setColumnIdentifiers(colClinic);
		clinicData = new Object[2];
		for (int i = 0; i < clinic.getList().size(); i++) {
			clinicData[0] = clinic.getList().get(i).getId();
			clinicData[1] = clinic.getList().get(i).getName();
			clinicModel.addRow(clinicData);
		}

		final DefaultTableModel workerModel = new DefaultTableModel();
		Object[] colWorker = new Object[2];
		colWorker[0] = "ID";
		colWorker[1] = "Name Surname";
		workerModel.setColumnIdentifiers(colWorker);
		final Object[] workerData = new Object[2];

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 410);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Welcome, " + chiefphysician.getName());
		lblNewLabel.setFont(new Font("Serif", Font.PLAIN, 23));
		lblNewLabel.setBounds(10, 18, 431, 27);
		w_pane.add(lblNewLabel);

		JButton btnNewButton = new JButton("EXIT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Serif", Font.PLAIN, 13));
		btnNewButton.setBounds(478, 24, 89, 23);
		w_pane.add(btnNewButton);

		JTabbedPane w_clini = new JTabbedPane(JTabbedPane.TOP);
		w_clini.setBounds(10, 56, 574, 304);
		w_pane.add(w_clini);

		JPanel w_doctor = new JPanel();
		w_doctor.setBackground(Color.WHITE);
		w_clini.addTab("Doctor Management", null, w_doctor, null);
		w_doctor.setLayout(null);

		JLabel label = new JLabel("Name Surname");
		label.setFont(new Font("Serif", Font.PLAIN, 16));
		label.setBounds(424, 0, 135, 27);
		w_doctor.add(label);

		fld_dName = new JTextField();
		fld_dName.setColumns(10);
		fld_dName.setBounds(424, 26, 135, 20);
		w_doctor.add(fld_dName);

		JLabel label_1 = new JLabel("Identity Number");
		label_1.setFont(new Font("Serif", Font.PLAIN, 16));
		label_1.setBounds(424, 52, 135, 27);
		w_doctor.add(label_1);

		fld_dID = new JTextField();
		fld_dID.setColumns(10);
		fld_dID.setBounds(424, 82, 135, 20);
		w_doctor.add(fld_dID);

		JLabel label_2 = new JLabel("Password");
		label_2.setFont(new Font("Serif", Font.PLAIN, 16));
		label_2.setBounds(425, 107, 134, 27);
		w_doctor.add(label_2);

		fld_dPass = new JTextField();
		fld_dPass.setColumns(10);
		fld_dPass.setBounds(424, 137, 135, 20);
		w_doctor.add(fld_dPass);

		JButton btn_add = new JButton("ADD");
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_dName.getText().length() == 0 || fld_dPass.getText().length() == 0
						|| fld_dID.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						boolean control = chiefphysician.addDoctor(fld_dID.getText(), fld_dPass.getText(),
								fld_dName.getText());
						if (control) {
							Helper.showMsg("success");
							fld_dName.setText(null);
							fld_dID.setText(null);
							fld_dPass.setText(null);
							updateDoctorModel();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_add.setFont(new Font("Serif", Font.PLAIN, 13));
		btn_add.setBounds(424, 160, 135, 23);
		w_doctor.add(btn_add);

		JLabel label_3 = new JLabel("User ID");
		label_3.setFont(new Font("Serif", Font.PLAIN, 16));
		label_3.setBounds(425, 189, 134, 27);
		w_doctor.add(label_3);

		fld_UserID = new JTextField();
		fld_UserID.setColumns(10);
		fld_UserID.setBounds(425, 217, 134, 20);
		w_doctor.add(fld_UserID);

		JButton btn_delete = new JButton("DELETE");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_UserID.getText().length() == 0) {
					Helper.showMsg("Please choose a valid doctor!");
				} else {
					if (Helper.confirm("sure")) {
						int selectID = Integer.parseInt(fld_UserID.getText());
						try {
							boolean control = chiefphysician.deleteDoctor(selectID);
							if (control) {
								Helper.showMsg("success");
								fld_UserID.setText(null);
								updateDoctorModel();
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btn_delete.setFont(new Font("Serif", Font.PLAIN, 13));
		btn_delete.setBounds(424, 247, 135, 23);
		w_doctor.add(btn_delete);

		JScrollPane w_scrollDoctor = new JScrollPane();
		w_scrollDoctor.setBounds(0, 0, 418, 270);
		w_doctor.add(w_scrollDoctor);

		table_doctor = new JTable(doctorModel);
		w_scrollDoctor.setViewportView(table_doctor);

		table_doctor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					fld_UserID.setText(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());
				} catch (Exception ex) {
				}
			}
		});

		table_doctor.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int selectID = Integer
							.parseInt(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());
					String selectName = table_doctor.getValueAt(table_doctor.getSelectedRow(), 1).toString();
					String selectIDno = table_doctor.getValueAt(table_doctor.getSelectedRow(), 2).toString();
					String selectPassword = table_doctor.getValueAt(table_doctor.getSelectedRow(), 3).toString();

					try {
						boolean control = chiefphysician.updateDoctor(selectID, selectIDno, selectPassword, selectName);
						if (control) {
							Helper.showMsg("success");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

			}
		});

		JPanel w_clinic = new JPanel();
		w_clinic.setBackground(Color.WHITE);
		w_clini.addTab("Clinic", null, w_clinic, null);
		w_clinic.setLayout(null);

		JScrollPane w_scrollClinic = new JScrollPane();
		w_scrollClinic.setBounds(10, 11, 240, 255);
		w_clinic.add(w_scrollClinic);

		clinicMenu = new JPopupMenu();
		JMenuItem updateMenu = new JMenuItem("Update");
		JMenuItem deleteMenu = new JMenuItem("Delete");
		clinicMenu.add(updateMenu);
		clinicMenu.add(deleteMenu);

		updateMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selID = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());
				Clinic selectClinic = clinic.getFetch(selID);
				UpdateClinicGUI updateGUI = new UpdateClinicGUI(selectClinic);
				updateGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				updateGUI.setVisible(true);
				updateGUI.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						try {
							updateClinicModel();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				});
			}
		});

		deleteMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Helper.confirm("sure")) {
					int selID = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());
					try {
						if (clinic.deleteClinic(selID)) {
							Helper.showMsg("success");
							updateClinicModel();
						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

			}
		});

		table_clinic = new JTable(clinicModel);
		table_clinic.setComponentPopupMenu(clinicMenu);
		table_clinic.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow = table_clinic.rowAtPoint(point);
				table_clinic.setRowSelectionInterval(selectedRow, selectedRow);

			}
		});

		w_scrollClinic.setViewportView(table_clinic);

		JLabel lbl_clinicName = new JLabel("Clinic Name");
		lbl_clinicName.setFont(new Font("Serif", Font.PLAIN, 16));
		lbl_clinicName.setBounds(258, 11, 112, 27);
		w_clinic.add(lbl_clinicName);

		fld_clinicName = new JTextField();
		fld_clinicName.setColumns(10);
		fld_clinicName.setBounds(258, 37, 112, 20);
		w_clinic.add(fld_clinicName);

		JButton btn_addClinic = new JButton("ADD");
		btn_addClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_clinicName.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						if (clinic.addClinic(fld_clinicName.getText())) {
							Helper.showMsg("success");
							fld_clinicName.setText(null);
							updateClinicModel();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

			}
		});
		btn_addClinic.setFont(new Font("Serif", Font.PLAIN, 13));
		btn_addClinic.setBounds(258, 68, 112, 23);
		w_clinic.add(btn_addClinic);

		JScrollPane w_scrollWorker = new JScrollPane();
		w_scrollWorker.setBounds(379, 11, 180, 254);
		w_clinic.add(w_scrollWorker);

		table_worker = new JTable();
		w_scrollWorker.setViewportView(table_worker);

		final JComboBox select_doctor = new JComboBox();
		select_doctor.setBounds(258, 199, 112, 27);
		for (int i = 0; i < chiefphysician.getDoctorList().size(); i++) {
			select_doctor.addItem(new Item(chiefphysician.getDoctorList().get(i).getId(),
					chiefphysician.getDoctorList().get(i).getName()));
		}
		select_doctor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox c = (JComboBox) e.getSource();
				Item item = (Item) c.getSelectedItem();
				System.out.println(item.getKey() + " : " + item.getValue());
			}
		});
		w_clinic.add(select_doctor);

		JButton btn_addWorker = new JButton("ADD");
		btn_addWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_clinic.getSelectedRow();
				if (selRow >= 0) {
					String selClinic = table_clinic.getModel().getValueAt(selRow, 0).toString();
					int selClinicID = Integer.parseInt(selClinic);
					Item doctorItem = (Item) select_doctor.getSelectedItem();
					try {
						boolean control = chiefphysician.addWorker(doctorItem.getKey(), selClinicID);
						if (control) {
							Helper.showMsg("success");
							DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
							clearModel.setRowCount(0);
							for (int i = 0; i < chiefphysician.getClinicDoctorList(selClinicID).size(); i++) {
								workerData[0] = chiefphysician.getClinicDoctorList(selClinicID).get(i).getId();
								workerData[1] = chiefphysician.getClinicDoctorList(selClinicID).get(i).getName();
								workerModel.addRow(workerData);
							}
							table_worker.setModel(workerModel);
						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					Helper.showMsg("Please choose a clinic!");
				}
			}
		});
		btn_addWorker.setFont(new Font("Serif", Font.PLAIN, 13));
		btn_addWorker.setBounds(260, 237, 112, 23);
		w_clinic.add(btn_addWorker);

		JLabel label_4 = new JLabel("Clinic Name");
		label_4.setFont(new Font("Serif", Font.PLAIN, 16));
		label_4.setBounds(258, 109, 112, 27);
		w_clinic.add(label_4);

		JButton btnChoose = new JButton("SELECT");
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_clinic.getSelectedRow();
				if (selRow >= 0) {
					String selClinic = table_clinic.getModel().getValueAt(selRow, 0).toString();
					int selClinicID = Integer.parseInt(selClinic);
					DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
					clearModel.setRowCount(0);
					try {
						for (int i = 0; i < chiefphysician.getClinicDoctorList(selClinicID).size(); i++) {
							workerData[0] = chiefphysician.getClinicDoctorList(selClinicID).get(i).getId();
							workerData[1] = chiefphysician.getClinicDoctorList(selClinicID).get(i).getName();
							workerModel.addRow(workerData);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					table_worker.setModel(workerModel);
				} else {
					Helper.showMsg("Please choose a clinic!");
				}
			}
		});
		btnChoose.setFont(new Font("Serif", Font.PLAIN, 13));
		btnChoose.setBounds(258, 140, 112, 23);
		w_clinic.add(btnChoose);
	}

	public void updateDoctorModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < chiefphysician.getDoctorList().size(); i++) {
			doctorData[0] = chiefphysician.getDoctorList().get(i).getId();
			doctorData[1] = chiefphysician.getDoctorList().get(i).getName();
			doctorData[2] = chiefphysician.getDoctorList().get(i).getIdno();
			doctorData[3] = chiefphysician.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
		}
	}

	public void updateClinicModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_clinic.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < clinic.getList().size(); i++) {
			clinicData[0] = clinic.getList().get(i).getId();
			clinicData[1] = clinic.getList().get(i).getName();
			clinicModel.addRow(clinicData);
		}
	}
}
