import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateSupply extends JFrame implements ActionListener
{
    JFrame jf;
	JLabel l1,l2,l3,l4,l5,l6;
	JTextField t1,t2,t3,t4,t5;
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


	UpdateSupply()
	{
		jf=new JFrame();
		Dimension screen= Toolkit.getDefaultToolkit().getScreenSize();
		f = new Font("Times New Roman",Font.BOLD,20);
		jf.setLayout(null);

	    l6=new JLabel("Update Supplier");
	    l6.setFont(new Font("Times New Roman",Font.BOLD,25));
	    l6.setBounds(300,50,300,40);l6.setForeground(Color.blue);
	    jf.add(l6);

		l1= new JLabel("Supplier id *");
		//l1.setFont(f);
 l1.setBounds(150,120,130,25);
		jf.add(l1);

		t1=new JTextField(20);
		t1.setBounds(320,120,100,25);t1.setToolTipText("Enter supplier id");
		jf.add(t1);

		l2 = new JLabel("Supplier name*");
		//l2.setFont(f);
  l2.setBounds(150,160,170,25);
		jf.add(l2);

		t2=new JTextField(20);
		t2.setBounds(320,160,200,25);t2.setToolTipText("Enter supplier name");
		jf.add(t2);

		l3 = new JLabel("Supplier location*");
		//l3.setFont(f);
  l3.setBounds(150,200,170,25);
		jf.add(l3);

		t3=new JTextField(20);
		t3.setBounds(320,200,250,25);t3.setToolTipText("Enter supplier location");
		jf.add(t3);

		l4 = new JLabel("Supplier device name");
		//l4.setFont(f);
  l4.setBounds(150,240,170,25);
		jf.add(l4);

		t4=new JTextField(20);
		t4.setBounds(320,240,100,25);t4.setToolTipText("Enter supplier device name");
		jf.add(t4);

		l5 = new JLabel("Supplier amount");
		//l5.setFont(f);
  l5.setBounds(150,280,170,25);
		jf.add(l5);

		t5=new JTextField(20);
		t5.setBounds(320,280,200,25);t5.setToolTipText("Enter supplier amount");
		jf.add(t5);


        b0 = new JButton("Add",new ImageIcon("images//open.png"));
	    b0.setBounds(150,330,110,35);b0.setToolTipText("click to add supplier details");
	    jf.add(b0); b0.addActionListener(this);

		b1 = new JButton("Update",new ImageIcon("images//update.png"));
		b1.setBounds(300,330,110,35);b1.setToolTipText("click to update supplier details");
		jf.add(b1);b1.addActionListener(this);

		b2 = new JButton("Delete",new ImageIcon("images//clear.png"));
		b2.setBounds(450,330,110,35);b2.setToolTipText("click to delete all textfilds");
	    jf.add(b2);b2.addActionListener(this);

	   	b3 = new JButton("Show",new ImageIcon("images//all.png"));
		b3.setBounds(600,330,110,35);b3.setToolTipText("click to view all supplier details in grid below");
		jf.add(b3); b3.addActionListener(this);

	    scrlPane.setBounds(0,380,900,600);
        jf.add(scrlPane);
        tabGrid.setFont(new Font ("Times New Roman",0,15));

        model.addColumn("ID");
        model.addColumn("NAME");
        model.addColumn("LOCATION");
        model.addColumn("DEVICE");
        model.addColumn("AMOUNT");


		ListSelectionModel lsm = tabGrid.getSelectionModel();
		lsm.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				int r = tabGrid.getSelectedRow();
				if(r < 0)
					return;
				t1.setText(tabGrid.getValueAt(r, 0).toString());
				t2.setText(tabGrid.getValueAt(r, 1).toString());
				t3.setText(tabGrid.getValueAt(r, 2).toString());
				t4.setText(tabGrid.getValueAt(r, 3).toString());
				t5.setText(tabGrid.getValueAt(r, 4).toString());
			}
		});


	     jf.setTitle("Update Supplier");
	     jf.setSize(900,700);
		 jf.setLocation(20,20);
		 jf.setResizable(false);
		 jf.getContentPane().setBackground(Color.cyan);
	     jf.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae)
	{
      if(ae.getSource()==b0)
      {
		  insertSupply(t1.getText(),t2.getText(),t3.getText(),t4.getText(),t5.getText());
    }
     else if(ae.getSource()==b1) {//update
		  int r = tabGrid.getSelectedRow();
		  if(r < 0)
			  return;
		  updateSupply(r,t1.getText(),t2.getText(),t3.getText(),t4.getText(),t5.getText());
 	}
  	else if(ae.getSource()==b2) {//clear
		  int r = tabGrid.getSelectedRow();
		  if(r < 0)
			  return;
		  deleteSupply(r);
  	}
    else if(ae.getSource()==b3) {//list
		showSupply(t1.getText(),t2.getText(),t3.getText(),t4.getText(),t5.getText());
	}
  }

	public void insertSupply(String id, String name, String location, String device_name, String amount){
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/medical_store?serverTimezone=UTC","root","1111");
			System.out.println("Connected to database.");
			String statement = "insert into patient_supply (id_patient, id_supply, needed, exist, need_from) values (200441467, 4, 3, 2, date('2020-06-29'));";
			ps=con.prepareStatement("insert into supply (id, name, location, device_name, amount) values(?,?,?,?,?)");
			ps.setString(1,id);
			ps.setString(2,name);
			ps.setString(3,location);
			ps.setString(4,device_name);
			ps.setString(5,amount);
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null,"Successfully added");
			model.addRow(new Object[] { id, name, location, device_name, amount });
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

	public void updateSupply(int r, String id, String name, String location, String device_name, String amount){
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/medical_store?serverTimezone=UTC","root","1111");
			System.out.println("Connected to database.");
			stmt=con.createStatement();
			String str="UPDATE supply SET id=" + id + ", name='" + name + "', location='" + location + "', device_name='" + device_name + "', amount=" + amount + " where id=" + id;
			stmt.executeUpdate(str);
			JOptionPane.showMessageDialog(null,"Successfully updated");
			tabGrid.getModel().setValueAt(id, r, 0);
			tabGrid.getModel().setValueAt(name, r, 1);
			tabGrid.getModel().setValueAt(location, r, 2);
			tabGrid.getModel().setValueAt(device_name, r, 3);
			tabGrid.getModel().setValueAt(amount, r, 4);
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

  public void deleteSupply(int r){
		  try
		  {
			  String id = tabGrid.getModel().getValueAt(r, 0).toString();

			  Class.forName("com.mysql.jdbc.Driver");
			  con=DriverManager.getConnection("jdbc:mysql://localhost:3306/medical_store?serverTimezone=UTC","root","1111");
			  System.out.println("Connected to database.");
			  stmt=con.createStatement();
			  String str1="delete from supply where id=" + id;
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
  public void showSupply(String id, String name, String location, String device_name, String amount){
		//////////////  2  /////////////
		int r1 = 0;
		try
		{
			String where_clause = " where 1=1 ";
			if(!id.isEmpty())
				where_clause = where_clause + " and id=" + id;
			if(!name.isEmpty())
				where_clause = where_clause + " and name like '%" + name + "%'";
			if(!location.isEmpty())
				where_clause = where_clause + " and location like '%" + location + "%'";
			if(!device_name.isEmpty())
				where_clause = where_clause + " and device_name like '%" + device_name + "%'";

			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/medical_store?serverTimezone=UTC","root","1111");
			System.out.println("Connected to database.");
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery("SELECT id, name, location, device_name, amount FROM supply " + where_clause);
			if (model.getRowCount() > 0) {
				for (int i = model.getRowCount() - 1; i > -1; i--) {
					model.removeRow(i);
				}
			}
			while(rs.next())
			{
				model.insertRow(r1++,new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)});

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
	    new UpdateSupply();
	}
}

