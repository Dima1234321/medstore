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

public class PatientSapplyList extends JFrame implements ActionListener
 {
    JFrame jf=new JFrame();

    JLabel ln;
    Connection con;
    PreparedStatement ps;
    Statement stmt;
    ResultSet rs;
 	DefaultTableModel model = new DefaultTableModel();
    JTable tabGrid = new JTable(model);
    JScrollPane scrlPane = new JScrollPane(tabGrid);

     JLabel ln1;
     Connection con1;
     PreparedStatement ps1;
     Statement stmt1;
     ResultSet rs1;
     DefaultTableModel model1 = new DefaultTableModel();
     JTable tabGrid1 = new JTable(model1);
     JScrollPane scrlPane1 = new JScrollPane(tabGrid1);

     String selectedId = "";
     JButton bPlus,bMinus,bNoNeedAnymore;



public PatientSapplyList()
  {
    jf.setLayout(null);

      bPlus = new JButton(" + ",new ImageIcon("images//open.png"));
      bPlus.setBounds(900,425,50,20); bPlus.setToolTipText("click to add needed devices");
      jf.add(bPlus); bPlus.addActionListener(this);
      bMinus = new JButton(" - ",new ImageIcon("images//open.png"));
      bMinus.setBounds(1000,425,50,20);bMinus.setToolTipText("click to remove unused devices");
      jf.add(bMinus); bMinus.addActionListener(this);
      bNoNeedAnymore = new JButton(" ~ ",new ImageIcon("images//open.png"));
      bNoNeedAnymore.setBounds(1100,425,50,20);bNoNeedAnymore.setToolTipText("click if no need anymore");
      jf.add(bNoNeedAnymore); bNoNeedAnymore.addActionListener(this);

      ListSelectionModel lsm = tabGrid.getSelectionModel();
      lsm.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                // do some actions here, for example
                // print first column value from selected row
                selectedId =  tabGrid.getValueAt(tabGrid.getSelectedRow(), 0).toString();
                showSupplyForPatient(selectedId);
                //int i = lsm.getMinSelectionIndex();
                //JOptionPane.showMessageDialog(null, "selected row " + id);
            }
      });



  	ln = new JLabel("Patients with their supplies");
    ln.setFont(new Font("Times New Roman",Font.BOLD,25));
    ln.setForeground(Color.blue);
    ln.setBounds(300,30,350,25);
    jf.add(ln);

    scrlPane.setBounds(0,80,1200,300);
    jf.add(scrlPane);
    tabGrid.setFont(new Font ("Times New Roman",0,15));

   	model.addColumn("ID");
   	model.addColumn("NAME");
  	model.addColumn("BIRTHDAY");
  	model.addColumn("PHONE");
  	model.addColumn("LOCATION");
  	model.addColumn("STATUS");
  	model.addColumn("TYPE");

  	/*-------------- 2 ---------------------------*/
      ln1 = new JLabel("Supply");
      ln1.setFont(new Font("Times New Roman",Font.BOLD,25));
      ln1.setForeground(Color.blue);
      ln1.setBounds(300,400,350,25);
      jf.add(ln1);

      scrlPane1.setBounds(0,450,1200,300);
      jf.add(scrlPane1);
      tabGrid1.setFont(new Font ("Times New Roman",0,15));

      model1.addColumn("PATIENT NAME");
      model1.addColumn("DEVICE NAME");
      model1.addColumn("PATIENT LOCATION");
      model1.addColumn("DEVICE LOCATION");
      model1.addColumn("NEED AMOUNT");
      model1.addColumn("EXISTS");
      model1.addColumn("s_id");
      model1.addColumn("s_name");
      model1.addColumn("id");
      tabGrid1.getColumnModel().getColumn(6).setMinWidth(0);
      tabGrid1.getColumnModel().getColumn(6).setMaxWidth(0);
      tabGrid1.getColumnModel().getColumn(7).setMinWidth(0);
      tabGrid1.getColumnModel().getColumn(7).setMaxWidth(0);
      tabGrid1.getColumnModel().getColumn(8).setMinWidth(0);
      tabGrid1.getColumnModel().getColumn(8).setMaxWidth(0);
///////////////////////////////////////////////////////////


  		int r = 0;
     try
     {

     	Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/medical_store?serverTimezone=UTC","root","1111");
		System.out.println("Connected to database.");
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        rs = stmt.executeQuery("select * from patient");
          while(rs.next())
            {
            	model.insertRow(r++,new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)});
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


    jf.setTitle("Supplier List");
    jf.setSize(1200,800);
    jf.setLocation(20,20);
	jf.setResizable(false);
    jf.getContentPane().setBackground(Color.cyan);
    jf.setVisible(true);
  }


  public void showSupplyForPatient(String patientId){
      //////////////  2  /////////////
      int r1 = 0;
      try
      {

          Class.forName("com.mysql.jdbc.Driver");
          con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/medical_store?serverTimezone=UTC","root","1111");
          System.out.println("Connected to database.");
          stmt1 = con1.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
          rs1 = stmt1.executeQuery("select name, device_name, location_patient, location_supply, needed, exist, s_id, s_name, id from patient_supply_view where id = " + patientId);
          if (model1.getRowCount() > 0) {
              for (int i = model1.getRowCount() - 1; i > -1; i--) {
                  model1.removeRow(i);
              }
          }
          while(rs1.next())
          {
              model1.insertRow(r1++,new Object[]{rs1.getString(1),rs1.getString(2),rs1.getString(3),rs1.getString(4),rs1.getString(5),rs1.getString(6),rs1.getString(7),rs1.getString(8), rs1.getString(9)});

          }

          con1.close();
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
     @Override
     public void actionPerformed(ActionEvent actionEvent) {
         int r = tabGrid1.getSelectedRow();
         if(r < 0)
             return;
         int need = Integer.parseInt(tabGrid1.getModel().getValueAt(r, 4).toString());
         int exist = Integer.parseInt(tabGrid1.getModel().getValueAt(r, 5).toString());
         String p_id = tabGrid1.getModel().getValueAt(r, 8).toString();
         String s_id = tabGrid1.getModel().getValueAt(r, 6).toString();

         Object supply_record[] = getSupplyRecors(tabGrid1.getModel().getValueAt(r, 6).toString());
         int amount = Integer.parseInt(supply_record[4].toString());

         if(actionEvent.getSource() == bPlus) {
             if(amount < 1) {
                 JOptionPane.showMessageDialog(null,supply_record[2] +" not exist to vendor " + supply_record[1]);
                 return;
             }
             else if(need > 0) {
                 need = need - 1;
                 exist = exist + 1;
                 amount = amount - 1;
             }
             else {
                 JOptionPane.showMessageDialog(null,"not need, why to add? another people need! ");
                 return;
             }
         }

         try
         {
             Class.forName("com.mysql.jdbc.Driver");
             con=DriverManager.getConnection("jdbc:mysql://localhost:3306/medical_store?serverTimezone=UTC","root","1111");
             System.out.println("Connected to database.");
             stmt=con.createStatement();
             String str1="UPDATE patient_supply SET needed=" + need + ", exist=" + exist + " where id_patient=" + p_id + " and id_supply=" + s_id + ";";
             String str2=" UPDATE supply SET amount=" + amount + " where id=" + s_id + ";";
             stmt.executeUpdate(str1 );
             stmt.executeUpdate(str2 );
             JOptionPane.showMessageDialog(null, "Record is updated");
             tabGrid1.getModel().setValueAt(need, r, 4);
             tabGrid1.getModel().setValueAt(exist, r, 5);
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

     public Object[] getSupplyRecors(String supplyId){
         //////////////  2  /////////////
         Object[] obj = null;
         try
         {

             Class.forName("com.mysql.jdbc.Driver");
             con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/medical_store?serverTimezone=UTC","root","1111");
             System.out.println("Connected to database.");
             stmt1 = con1.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
             rs1 = stmt1.executeQuery("select id, name, device_name, location, amount from supply where id = " + supplyId);
             rs1.next();
             obj =  new Object[]{rs1.getString(1),rs1.getString(2),rs1.getString(3),rs1.getString(4),rs1.getString(5)};
             con1.close();
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

         return obj;
     }

  public static void main(String args[])
    {
    	new PatientSapplyList();
    }
 }
