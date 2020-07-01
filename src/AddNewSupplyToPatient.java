import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddNewSupplyToPatient extends JFrame implements ActionListener
{
	JFrame jf;
	JTextField t1,t2,t3,t4,t5,tr,tq;
	JLabel l1,l2,l3,l4,l5,l6;
	JButton b0,b1,b2,addToPatient;
	Font f;
    Connection con;
	PreparedStatement ps;
	Statement stmt;
	ResultSet rs;
	DefaultTableModel model = new DefaultTableModel();
    JTable tabGrid = new JTable(model);
    JScrollPane scrlPane = new JScrollPane(tabGrid);
	String id_patient;

	AddNewSupplyToPatient()
	{
		id_patient = "";
		init();
	}
	AddNewSupplyToPatient(String id_p)
	{
		super();
		id_patient = id_p;
		init();
	}
public void init(){
	jf=new JFrame();
	f = new Font("Times New Roman",Font.BOLD,20);
	jf.setLayout(null);

	l6=new JLabel("Add Supplier to Patient");
	l6.setFont(new Font("Times New Roman",Font.BOLD,25));
	l6.setBounds(250,50,300,40);l6.setForeground(Color.blue);
	jf.add(l6);

	//	l1= new JLabel("Supplier id ");
	//	l1.setFont(f);l1.setBounds(150,120,130,25);
	//	jf.add(l1);

	//	t1=new JTextField(20);t1.setEditable(false);
	//	t1.setBounds(320,120,100,25);
	//	jf.add(t1);

	l2 = new JLabel("Supplier name");
	//l2.setFont(f);
	l2.setBounds(150,160,170,25);
	jf.add(l2);

	t2=new JTextField(20);
	t2.setBounds(320,160,200,25);t2.setToolTipText("Enter supplier name");
	jf.add(t2);

	l3 = new JLabel("Device name*");
	//l3.setFont(f);
	l3.setBounds(150,200,170,25);
	jf.add(l3);

	t3=new JTextField(20);
	t3.setBounds(320,200,250,25);t3.setToolTipText("Enter device name");
	jf.add(t3);

	b2 = new JButton("show",new ImageIcon(getClass().getResource("images//all.png")));
	b2.setBounds(460,260,110,35);b2.setToolTipText("click to view");
	jf.add(b2); b2.addActionListener(this);

	addToPatient = new JButton("add to patient",new ImageIcon(getClass().getResource("images//all.png")));
	addToPatient.setBounds(700,590,150,35);addToPatient.setToolTipText("add to patient");
	jf.add(addToPatient); addToPatient.addActionListener(this);

	l1 = new JLabel("Need amount: ");
	//l2.setFont(f);
	l1.setBounds(550,590,170,35);
	jf.add(l1);

	t1=new JTextField(5);
	t1.setText("1");
	t1.setBounds(640,590,40,35);t1.setToolTipText("Enter needed amount");
	jf.add(t1);

	scrlPane.setBounds(0,350,900,230);
	jf.add(scrlPane);
	tabGrid.setFont(new Font ("Times New Roman",0,15));

	model.addColumn("ID");
	model.addColumn("MANUFACTURER");
	model.addColumn("LOCATION");
	model.addColumn("DEVICE NAME");
	model.addColumn("AMOUNT");

	jf.setTitle("Add Supplier to Patient");
	jf.setSize(900,700);
	jf.setLocation(20,20);
	jf.setResizable(false);
	jf.getContentPane().setBackground(new Color(102, 204, 255));
	jf.setVisible(true);
}
public void actionPerformed(ActionEvent ae)
	{
	if(ae.getSource()==addToPatient)
	 {
		 int r = tabGrid.getSelectedRow();
		 if(r < 0)
			 return;
		 String id_supply = tabGrid.getModel().getValueAt(r, 0).toString();
		 //String id_patient = "313108342";
		 String needed = t1.getText();
		 if(needed.isEmpty())
			needed = "0";
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		 LocalDateTime now = LocalDateTime.now();
		 String d = dtf.format(now);

			  try
			  	 {
			Class.forName("com.mysql.jdbc.Driver");
		    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/medical_store?serverTimezone=UTC","root","1111");
			System.out.println("Connected to database.");
			String statement = "insert into patient_supply (id_patient, id_supply, needed, exist, need_from) values (200441467, 4, 3, 2, date('2020-06-29'));";
            ps=con.prepareStatement("insert into patient_supply (id_patient, id_supply, needed, exist, need_from) values(?,?,?,?,?)");
            ps.setString(1,id_patient);
		    ps.setString(2,id_supply);
		    ps.setString(3,needed);
		    ps.setString(4,"0");
			ps.setString(5,d);
		  	ps.executeUpdate();

  //int reply=JOptionPane.showConfirmDialog(null,"Supplier added successfully.Do you want add more supplier?","Added Supplier",JOptionPane.YES_NO_OPTION);

	            //if (reply == JOptionPane.YES_OPTION)
	   			//{
	   		      // jf.setVisible(false);
	   		       //new AddNewSupplyToPatient();
	   		    //}
	   		 // else if (reply == JOptionPane.NO_OPTION)
	   			//{
				JOptionPane.showMessageDialog(null,"Successfully added");
	   			  jf.setVisible(false);
		        //}
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
  else if(ae.getSource()==b1)
     {//clear
          //t1.setText("");
          t2.setText("");
          t3.setText("");
          t4.setText("");
          t5.setText("");
      }
    else if(ae.getSource()==b2)
    {//list
		getSupply(t2.getText(), t3.getText());
	}
 }

 private void getSupply(String manufacturer, String device_name){
	 int r = 0;
	 try
	 {
	 	String where_clause = "1=1";
	 	if(!(manufacturer.isBlank() || manufacturer.isEmpty())){
	 		where_clause += " and name like '%" + manufacturer + "%'";
		}
	 	if(!(device_name.isBlank() || device_name.isEmpty())){
			where_clause += " and device_name like '%" + device_name + "%'";
		}
		 Class.forName("com.mysql.jdbc.Driver");
		 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/medical_store?serverTimezone=UTC","root","1111");
		 System.out.println("Connected to database.");
		 stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		 rs = stmt.executeQuery("SELECT id, name, location, device_name, amount FROM supply where " + where_clause);
		 if (model.getRowCount() > 0) {
			 for (int i = model.getRowCount() - 1; i > -1; i--) {
				 model.removeRow(i);
			 }
		 }
		 while(rs.next())
		 {
			 model.insertRow(r++, new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5) });
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
 }
public static void main(String args[])
	{
	      new AddNewSupplyToPatient();
	}
}

