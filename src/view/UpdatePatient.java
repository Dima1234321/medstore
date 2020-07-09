package view;

import controller.PatientController;
import model.Patient;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.*;
import java.util.List;

public class UpdatePatient extends JFrame implements ActionListener
{
    JFrame jf;
	JLabel l1,l2,l3,l4,l5,l6,l7,l6_6,l7_7;
	JTextField t1,t2,t3,t4,t5,t6,t7;
	JButton b0,b1,b2,b3;
	GridBagLayout gbl;
	GridBagConstraints gbc;
	Font f;

    Connection con;
	PreparedStatement ps;
	Statement stmt;

	ResultSet rs;
    DefaultTableModel model = new DefaultTableModel();
    JTable tabGrid = new JTable(model);
    JScrollPane scrlPane = new JScrollPane(tabGrid);

	private final PatientController pc;


	UpdatePatient()
	{
		this.pc = PatientController.getInstance();

		jf=new JFrame();
		Dimension screen= Toolkit.getDefaultToolkit().getScreenSize();
		f = new Font("Times New Roman",Font.BOLD,20);
		jf.setLayout(null);

	    l6=new JLabel("Update Patient");
	    l6.setFont(new Font("Times New Roman",Font.BOLD,25));
	    l6.setBounds(300,50,300,40);l6.setForeground(Color.blue);
	    jf.add(l6);

		l1= new JLabel("Patient id");
		//l1.setFont(f);
 l1.setBounds(150,120,130,25);
		jf.add(l1);

		t1=new JTextField(20);
		t1.setBounds(320,120,100,25);t1.setToolTipText("Enter patient id");
		jf.add(t1);

		l2 = new JLabel("Patient name");
		//l2.setFont(f);
  l2.setBounds(150,160,170,25);
		jf.add(l2);

		t2=new JTextField(20);
		t2.setBounds(320,160,200,25);t2.setToolTipText("Enter patient name");
		jf.add(t2);

		l3 = new JLabel("Patient birth date");
		//l3.setFont(f);
  l3.setBounds(150,200,170,25);
		jf.add(l3);

		t3=new JTextField(20);
		t3.setBounds(320,200,250,25);t3.setToolTipText("Enter birth date");
		jf.add(t3);

		l4 = new JLabel("Patient phone number");
		//l4.setFont(f);
  l4.setBounds(150,240,170,25);
		jf.add(l4);

		t4=new JTextField(20);
		t4.setBounds(320,240,100,25);t4.setToolTipText("Enter phone number");
		jf.add(t4);

		l5 = new JLabel("Patient address");
		//l5.setFont(f);
  		l5.setBounds(150,280,170,25);
		jf.add(l5);

		t5=new JTextField(20);
		t5.setBounds(320,280,200,25);t5.setToolTipText("Enter patient address");
		jf.add(t5);


		l6 = new JLabel("Patient status");
		l6.setBounds(150,320,170,25);
		jf.add(l6);

		t6=new JTextField(20);
		t6.setBounds(320,320,200,25);t6.setToolTipText("Enter patient status");
		jf.add(t6);
		l6_6 = new JLabel("");
		l6_6.setBounds(540,320,170,25);
		jf.add(l6_6);
		t6.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
			};
			public void focusLost(FocusEvent e) {
				if (!e.isTemporary()) {
					String status = t6.getText();
					String s = Integer.parseInt(status) == 1 ? "infected" : "recovered";
					l6_6.setText(s);
				}
			}
		});

		l7 = new JLabel("Patient type");
		l7.setBounds(150,360,170,25);
		jf.add(l7);

		t7=new JTextField(20);
		t7.setBounds(320,360,200,25);t7.setToolTipText("Enter patient type");
		jf.add(t7);
		l7_7 = new JLabel("");
		l7_7.setBounds(540,360,170,25);
		jf.add(l7_7);
		t7.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
			};
			public void focusLost(FocusEvent e) {
				if (!e.isTemporary()) {
					String type = t7.getText();
					String s = Integer.parseInt(type) == 1 ? "private person" : "public place";
					l7_7.setText(s);
				}
			}
		});


        b0 = new JButton("Add",new ImageIcon(getClass().getResource("..//images//addnew.png")));
	    b0.setBounds(150,400,110,35);b0.setToolTipText("click to add supplier details");
	    jf.add(b0); b0.addActionListener(this);

		b1 = new JButton("Update",new ImageIcon(getClass().getResource("..//images//update.png")));
		b1.setBounds(300,400,110,35);b1.setToolTipText("click to update supplier details");
		jf.add(b1);b1.addActionListener(this);

		b2 = new JButton("Delete",new ImageIcon(getClass().getResource("..//images//delete.png")));
		b2.setBounds(450,400,110,35);b2.setToolTipText("click to delete all textfilds");
	    jf.add(b2);b2.addActionListener(this);

	   	b3 = new JButton("Show",new ImageIcon(getClass().getResource("..//images//search.png")));
		b3.setBounds(600,400,110,35);b3.setToolTipText("click to view all supplier details in grid below");
		jf.add(b3); b3.addActionListener(this);

	    scrlPane.setBounds(0,450,900,300);
        jf.add(scrlPane);
        tabGrid.setFont(new Font ("Times New Roman",0,15));

        model.addColumn("ID");
        model.addColumn("NAME");
        model.addColumn("BIRTHDATE");
        model.addColumn("PHONE");
        model.addColumn("LOCATION");
		model.addColumn("STATUS1");
		model.addColumn("TYPE1");
		model.addColumn("STATUS");
		model.addColumn("TYPE");
		tabGrid.getColumnModel().getColumn(5).setMinWidth(0);
		tabGrid.getColumnModel().getColumn(5).setMaxWidth(0);
		tabGrid.getColumnModel().getColumn(6).setMinWidth(0);
		tabGrid.getColumnModel().getColumn(6).setMaxWidth(0);

		ListSelectionModel lsm = tabGrid.getSelectionModel();
		lsm.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				int r = tabGrid.getSelectedRow();
				if(r < 0)
					return;
				t1.setText(tabGrid.getValueAt(r, 0).toString());
				t2.setText(tabGrid.getValueAt(r, 1).toString());
				t3.setText(tabGrid.getValueAt(r, 2) == null ? "" : tabGrid.getValueAt(r, 2).toString());
				t4.setText(tabGrid.getValueAt(r, 3).toString());
				t5.setText(tabGrid.getValueAt(r, 4).toString());
				t6.setText(tabGrid.getValueAt(r, 5).toString());
				t7.setText(tabGrid.getValueAt(r, 6).toString());
				l6_6.setText(tabGrid.getValueAt(r, 7).toString());
				l7_7.setText(tabGrid.getValueAt(r, 8).toString());
			}
		});


	     jf.setTitle("Update Patient");
	     jf.setSize(900,800);
		 jf.setLocation(20,20);
		 jf.setResizable(false);
		 jf.getContentPane().setBackground(new Color(204, 255, 204));
	     jf.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae)
	{
      if(ae.getSource()==b0)
      {
      	  if(!t1.getText().isEmpty())
		  	addPatient(t1.getText(),t2.getText(),t3.getText(),t4.getText(),t5.getText(),t6.getText(),t7.getText());
      }
     else if(ae.getSource()==b1) {//update
		  int r = tabGrid.getSelectedRow();
		  if(r < 0)
			  return;
		  editPatient(r,t1.getText(),t2.getText(),t3.getText(),t4.getText(),t5.getText(),t6.getText(),t7.getText());
 	}
  	else if(ae.getSource()==b2) {//clear
		  int r = tabGrid.getSelectedRow();
		  if(r < 0)
			  return;
		  removePatient(r);
  	}
    else if(ae.getSource()==b3) {//list
		showPatient(t1.getText(),t2.getText(),t3.getText(),t4.getText(),t5.getText(),t6.getText(),t7.getText());
	}
  }

	public void addPatient(String id, String name, String birthdate, String phone, String location, String status, String type){
		try
		{
			Patient p = pc.insertPatient(id,name,birthdate,phone,location,status,type);
			JOptionPane.showMessageDialog(null,"Successfully added");
			model.addRow(new Object[] { id, name, birthdate, phone, location, status, type, p.status_descr() , p.type_descr() });
		}
		catch(Exception se) {
			System.out.println(se);
			JOptionPane.showMessageDialog(null,se);
		}
	}

	public void editPatient(int r, String id, String name, String birthdate, String phone, String location, String status, String type){
		try
		{
			Patient p = pc.updatePatient(id,name,birthdate,phone,location,status,type);
			JOptionPane.showMessageDialog(null,"Successfully updated");
			tabGrid.getModel().setValueAt(p.getId(), r, 0);
			tabGrid.getModel().setValueAt(p.getName(), r, 1);
			tabGrid.getModel().setValueAt(p.getBirthdate(), r, 2);
			tabGrid.getModel().setValueAt(p.getPhone(), r, 3);
			tabGrid.getModel().setValueAt(p.getLocation(), r, 4);
			tabGrid.getModel().setValueAt(p.getStatus(), r, 5);
			tabGrid.getModel().setValueAt(p.getType(), r, 6);
			tabGrid.getModel().setValueAt(p.status_descr() , r, 7);
			l6_6.setText(p.status_descr());
			tabGrid.getModel().setValueAt(p.type_descr() , r, 8);
			l7_7.setText(p.type_descr());
		}
		catch(Exception se) {
			System.out.println(se);
			JOptionPane.showMessageDialog(null,se);
		}
	}

  public void removePatient(int r){
		  try
		  {
			  String id = tabGrid.getModel().getValueAt(r, 0).toString();
			  pc.deletePatient(id);
			  JOptionPane.showMessageDialog(null, "Successfully deleted");
			  model.removeRow(r);
		  }
		  catch(Exception se) {
			  System.out.println(se);
			  JOptionPane.showMessageDialog(null,se);
		  }
  }
  	public void showPatient(String id, String name, String birthdate, String phone, String location, String status, String type){
		try
		{
	  		List<Patient> patients = pc.getPatient(id, name, birthdate, phone, location, status, type);
			if (model.getRowCount() > 0) {
				for (int i = model.getRowCount() - 1; i > -1; i--) {
					model.removeRow(i);
				}
			}
			int r = 0;
			for (Patient p  : patients) {
				model.insertRow(r++,new Object[]{ p.getId() ,p.getName(), p.getBirthdate(), p.getPhone(), p.getLocation(),
						p.getStatus(), p.getType() , p.status_descr(), p.type_descr()});
			}
		}
		catch(Exception se) {
			System.out.println(se);
			JOptionPane.showMessageDialog(null,se);
		}
	}
	public static void main(String args[])
	{
	    new UpdatePatient();
	}
}

