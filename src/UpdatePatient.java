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


	UpdatePatient()
	{
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
		t1.setBounds(320,120,100,25);t1.setToolTipText("Enter supplier id");
		jf.add(t1);

		l2 = new JLabel("Patient name*");
		//l2.setFont(f);
  l2.setBounds(150,160,170,25);
		jf.add(l2);

		t2=new JTextField(20);
		t2.setBounds(320,160,200,25);t2.setToolTipText("Enter supplier name");
		jf.add(t2);

		l3 = new JLabel("Patient location*");
		//l3.setFont(f);
  l3.setBounds(150,200,170,25);
		jf.add(l3);

		t3=new JTextField(20);
		t3.setBounds(320,200,250,25);t3.setToolTipText("Enter supplier location");
		jf.add(t3);

		l4 = new JLabel("Patient device name");
		//l4.setFont(f);
  l4.setBounds(150,240,170,25);
		jf.add(l4);

		t4=new JTextField(20);
		t4.setBounds(320,240,100,25);t4.setToolTipText("Enter supplier device name");
		jf.add(t4);

		l5 = new JLabel("Patient amount");
		//l5.setFont(f);
  		l5.setBounds(150,280,170,25);
		jf.add(l5);

		t5=new JTextField(20);
		t5.setBounds(320,280,200,25);t5.setToolTipText("Enter supplier amount");
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


        b0 = new JButton("Add",new ImageIcon("images//open.png"));
	    b0.setBounds(150,400,110,35);b0.setToolTipText("click to add supplier details");
	    jf.add(b0); b0.addActionListener(this);

		b1 = new JButton("Update",new ImageIcon("images//update.png"));
		b1.setBounds(300,400,110,35);b1.setToolTipText("click to update supplier details");
		jf.add(b1);b1.addActionListener(this);

		b2 = new JButton("Delete",new ImageIcon("images//clear.png"));
		b2.setBounds(450,400,110,35);b2.setToolTipText("click to delete all textfilds");
	    jf.add(b2);b2.addActionListener(this);

	   	b3 = new JButton("Show",new ImageIcon("images//all.png"));
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
		model.addColumn("STATUS");
		model.addColumn("TYPE");
		model.addColumn("STATUS_DESCR");
		model.addColumn("TYPE_DESCR");
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
		 jf.getContentPane().setBackground(Color.cyan);
	     jf.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae)
	{
      if(ae.getSource()==b0)
      {
		  insertPatient(t1.getText(),t2.getText(),t3.getText(),t4.getText(),t5.getText(),t6.getText(),t7.getText());
    }
     else if(ae.getSource()==b1) {//update
		  int r = tabGrid.getSelectedRow();
		  if(r < 0)
			  return;
		  updatePatient(r,t1.getText(),t2.getText(),t3.getText(),t4.getText(),t5.getText(),t6.getText(),t7.getText());
 	}
  	else if(ae.getSource()==b2) {//clear
		  int r = tabGrid.getSelectedRow();
		  if(r < 0)
			  return;
		  deletePatient(r);
  	}
    else if(ae.getSource()==b3) {//list
		showPatient(t1.getText(),t2.getText(),t3.getText(),t4.getText(),t5.getText(),t6.getText(),t7.getText());
	}
  }

	public void insertPatient(String id, String name, String birthdate, String phone, String location, String status, String type){
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/medical_store?serverTimezone=UTC","root","1111");
			System.out.println("Connected to database.");
			ps=con.prepareStatement("insert into patient (id, name, birthdate, phone, location, status, type) values(?,?,?,?,?,?,?)");
			ps.setString(1,id);
			ps.setString(2,name);
			ps.setString(3,birthdate.isEmpty() ? null : birthdate);
			ps.setString(4,phone);
			ps.setString(5,location);
			ps.setString(6,status);
			ps.setString(7,type);
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null,"Successfully added");
			String s1 = Integer.parseInt(status) == 1 ? "infected" : "recovered";
			String s = Integer.parseInt(type) == 1 ? "private person" : "public place";
			model.addRow(new Object[] { id, name, birthdate, phone, location, status, type, s1, s });
			con.close();
		}
		catch(SQLException se)
		{
			System.out.println(se);
			JOptionPane.showMessageDialog(null,"SQL Error:"+se);
		}
		catch(Exception e)
		{
			System.out.println(e);
			JOptionPane.showMessageDialog(null,"Error:"+e);
		}
	}

	public void updatePatient(int r, String id, String name, String birthdate, String phone, String location, String status, String type){
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/medical_store?serverTimezone=UTC","root","1111");
			System.out.println("Connected to database.");
			stmt=con.createStatement();
			String str="UPDATE patient SET id=" + id + ", name='" + name + "', birthdate='" + birthdate + "', phone='" + phone + "', location='" + location + "', status=" + status + ", type=" + type + " where id=" + id;
			stmt.executeUpdate(str);
			JOptionPane.showMessageDialog(null,"Successfully updated");
			tabGrid.getModel().setValueAt(id, r, 0);
			tabGrid.getModel().setValueAt(name, r, 1);
			tabGrid.getModel().setValueAt(birthdate, r, 2);
			tabGrid.getModel().setValueAt(phone, r, 3);
			tabGrid.getModel().setValueAt(location, r, 4);
			tabGrid.getModel().setValueAt(status, r, 5);
			tabGrid.getModel().setValueAt(type, r, 6);
			String s1 = Integer.parseInt(status) == 1 ? "infected" : "recovered";
			tabGrid.getModel().setValueAt(s1, r, 7);
			l6_6.setText(s1);
			String s = Integer.parseInt(type) == 1 ? "private person" : "public place";
			tabGrid.getModel().setValueAt(s, r, 8);
			l7_7.setText(s);
			con.close();
		}
		catch(SQLException se)
		{
			System.out.println(se);
			JOptionPane.showMessageDialog(null,"SQL Error:"+se);
		}
		catch(Exception e)
		{
			System.out.println(e);
			JOptionPane.showMessageDialog(null,"Error:"+e);
		}
	}

  public void deletePatient(int r){
		  try
		  {
			  String id = tabGrid.getModel().getValueAt(r, 0).toString();

			  Class.forName("com.mysql.jdbc.Driver");
			  con=DriverManager.getConnection("jdbc:mysql://localhost:3306/medical_store?serverTimezone=UTC","root","1111");
			  System.out.println("Connected to database.");
			  stmt=con.createStatement();
			  String str1="delete from patient where id=" + id;
			  stmt.executeUpdate(str1);
			  JOptionPane.showMessageDialog(null, "Successfully deleted");
			  model.removeRow(r);
			  con.close();
		  }
		  catch(SQLException se)
		  {
			  System.out.println(se);
			  JOptionPane.showMessageDialog(null,"SQL Error:"+se);
		  }
		  catch(Exception e)
		  {
			  System.out.println(e);
			  JOptionPane.showMessageDialog(null,"Error:"+e);
		  }
  }
  public void showPatient(String id, String name, String birthdate, String phone, String location, String status, String type){
		//////////////  2  /////////////
		int r1 = 0;
		try
		{
			String where_clause = " where 1=1 ";
			if(!id.isEmpty())
				where_clause = where_clause + " and id=" + id;
			if(!name.isEmpty())
				where_clause = where_clause + " and name like '%" + name + "%'";
			if(!birthdate.isEmpty())
				where_clause = where_clause + " and birthdate like '%" + birthdate + "%'";
			if(!phone.isEmpty())
				where_clause = where_clause + " and phone like '%" + phone + "%'";
			if(!location.isEmpty())
				where_clause = where_clause + " and location like '%" + location + "%'";
			if(!status.isEmpty())
				where_clause = where_clause + " and status=" + status;
			if(!type.isEmpty())
				where_clause = where_clause + " and type=" + type;

			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/medical_store?serverTimezone=UTC","root","1111");
			System.out.println("Connected to database.");
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery("SELECT id, name, birthdate, phone, location, status, type, (case when status = 1 then \"infected\" else \"recovered\" end) status_descr, (case when type = 1 then \"private person\" else \"public place\" end) type_descr FROM patient " + where_clause);
			if (model.getRowCount() > 0) {
				for (int i = model.getRowCount() - 1; i > -1; i--) {
					model.removeRow(i);
				}
			}
			while(rs.next())
			{
				model.insertRow(r1++,new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)});
			}

			con.close();
		}
		catch(SQLException se)
		{
			System.out.println(se);
			JOptionPane.showMessageDialog(null,"SQL Error:"+se);
		}
		catch(Exception e)
		{
			System.out.println(e);
			JOptionPane.showMessageDialog(null,"Error:"+e);
		}

		//////////////////////////////
	}

	public static void main(String args[])
	{
	    new UpdatePatient();
	}
}

