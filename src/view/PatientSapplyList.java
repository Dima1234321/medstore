package view;

import controller.PatientController;
import controller.SupplyController;
import model.Patient;
import model.PatientSupply;
import model.Supply;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;
import java.util.List;

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
     JButton bPlus,bMinus,bNoNeedAnymore,bDeletePatientSupply, bAddPatientSupply;
     JCheckBox cbNeed;

     private final SupplyController sc;
     private final PatientController pc;

     public PatientSapplyList()
  {
      this.sc = SupplyController.getInstance();
      this.pc = PatientController.getInstance();

      jf.setLayout(null);

      bPlus = new JButton(" + ",new ImageIcon(getClass().getResource("..//images//open.png")));
      bPlus.setBounds(900,425,50,20); bPlus.setToolTipText("click to add needed devices");
      jf.add(bPlus); bPlus.addActionListener(this);
      bMinus = new JButton(" - ",new ImageIcon(getClass().getResource("..//images//open.png")));
      bMinus.setBounds(1000,425,50,20);bMinus.setToolTipText("click to remove unused devices");
      jf.add(bMinus); bMinus.addActionListener(this);
      bNoNeedAnymore = new JButton(" ~ ",new ImageIcon(getClass().getResource("..//images//open.png")));
      bNoNeedAnymore.setBounds(1100,425,50,20);bNoNeedAnymore.setToolTipText("click if no need anymore");
      jf.add(bNoNeedAnymore); bNoNeedAnymore.addActionListener(this);
      bDeletePatientSupply = new JButton("delete",new ImageIcon(getClass().getResource("..//images//open.png")));
      bDeletePatientSupply.setBounds(1050,660,100,20);bDeletePatientSupply.setToolTipText("click to delete");
      jf.add(bDeletePatientSupply); bDeletePatientSupply.addActionListener(this);
      bAddPatientSupply = new JButton("add",new ImageIcon(getClass().getResource("..//images//open.png")));
      bAddPatientSupply.setBounds(900,660,100,20);bAddPatientSupply.setToolTipText("click to add");
      jf.add(bAddPatientSupply); bAddPatientSupply.addActionListener(this);



      ListSelectionModel lsm = tabGrid.getSelectionModel();
      lsm.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                int r = tabGrid.getSelectedRow();
                if(r != -1) {
                    selectedId = tabGrid.getValueAt(r, 0).toString();
                    showSupplyForPatient(selectedId);
                }
                else {
                    showSupplyForPatient("-1");
                }
            }
      });



  	ln = new JLabel("Patients with their supplies");
    ln.setFont(new Font("Times New Roman",Font.BOLD,25));
    ln.setForeground(Color.blue);
    ln.setBounds(300,30,350,25);
    jf.add(ln);

    cbNeed = new JCheckBox("Only persons or organization that need supply");
    cbNeed.setBounds(800,30, 330,25);
    jf.add(cbNeed);

      cbNeed.addItemListener(new ItemListener() {
          public void itemStateChanged(ItemEvent e) {
              showPatient(e.getStateChange());
          }
      });


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
  	model.addColumn("NEED");

  	/*-------------- 2 ---------------------------*/
      ln1 = new JLabel("Supply");
      ln1.setFont(new Font("Times New Roman",Font.BOLD,25));
      ln1.setForeground(Color.blue);
      ln1.setBounds(300,400,350,25);
      jf.add(ln1);

      scrlPane1.setBounds(0,450,1200,200);
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


      showPatient(0);


    jf.setTitle("Supplier List");
    jf.setSize(1200,800);
    jf.setLocation(20,20);
	jf.setResizable(false);
    jf.getContentPane().setBackground(new Color(204, 255, 255));
    jf.setVisible(true);
  }


  public void showPatient(int isNeed){
      try  {
          List<Patient> patients = pc.getPatientIsNeed(isNeed);
          if (model.getRowCount() > 0) {
              for (int i = model.getRowCount() - 1; i > -1; i--) {
                  model.removeRow(i);
              }
          }
          int r = 0;
          for (Patient p  : patients) {
              model.insertRow(r++,new Object[]{ p.id ,p.name, p.birthdate, p.phone, p.location, p.status_descr(), p.type_descr(), p.needed});
          }
      }
      catch(Exception se) {
          System.out.println(se);
          JOptionPane.showMessageDialog(null,se);
      }
  }

  public void showSupplyForPatient(String patientId) {
      try {
          List<PatientSupply> p_s = sc.getSupplyForPatient(patientId);
          if (model1.getRowCount() > 0) {
              for (int i = model1.getRowCount() - 1; i > -1; i--) {
                  model1.removeRow(i);
              }
          }
          int r = 0;
          for (PatientSupply s  : p_s) {
              model1.insertRow(r++,new Object[]{ s.name ,s.device_name, s.location_patient, s.location_supply, s.needed, s.exist, s.s_id, s.s_name, s.id });
          }
      }
      catch(Exception se) {
          System.out.println(se);
          JOptionPane.showMessageDialog(null,se);
      }
  }
     @Override
     public void actionPerformed(ActionEvent actionEvent) {
         int rr = tabGrid.getSelectedRow();
         if(rr < 0)
             return;
         String p_id = tabGrid.getModel().getValueAt(rr, 0).toString();
         if(actionEvent.getSource() == bAddPatientSupply) {
             //p_id
             new AddNewSupplyToPatient(p_id);
         }

         int r = tabGrid1.getSelectedRow();
         if(r < 0)
             return;
         int need = Integer.parseInt(tabGrid1.getModel().getValueAt(r, 4).toString());
         int exist = Integer.parseInt(tabGrid1.getModel().getValueAt(r, 5).toString());

         String s_id = tabGrid1.getModel().getValueAt(r, 6).toString();

         Supply s = getSupplyRecords(tabGrid1.getModel().getValueAt(r, 6).toString());
         int amount = Integer.parseInt(s.amount);

         if(actionEvent.getSource() == bPlus) {
             if(amount < 1) {
                 JOptionPane.showMessageDialog(null,s.device_name +" not exist to vendor " + s.name);
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

         if(actionEvent.getSource() == bMinus) {
             if(exist > 0) {
                 need = need + 1;
                 exist = exist - 1;
                 amount = amount + 1;
             }
             else {
                 JOptionPane.showMessageDialog(null,"they already have nothing. why try to take away?!");
                 return;
             }
         }

         if(actionEvent.getSource() == bNoNeedAnymore) {
             if(exist > 0 || need > 0) {
                 need = 0;
                 exist = 0;
                 amount = amount + exist;
             }
             else {
                 JOptionPane.showMessageDialog(null,"the patient not need and have not anything no longer");
                 return;
             }
         }

         if(actionEvent.getSource() == bDeletePatientSupply) {
             if(exist > 0 || need > 0) {
                 JOptionPane.showMessageDialog(null,"the patient need or have some devices. no actions performed");
                 return;
             }
             else {
                 try
                 {
                     sc.deletePatientSupply(p_id, s_id);
                     model1.removeRow(r);
                     return;
                 }
                 catch(Exception se) {
                     System.out.println(se);
                     JOptionPane.showMessageDialog(null,se);
                 }
             }
         }

         try
         {
             sc.updatePatientSupply(Integer.toString(need) , Integer.toString(exist), p_id, s_id, Integer.toString(amount));
             tabGrid1.getModel().setValueAt(need, r, 4);
             tabGrid1.getModel().setValueAt(exist, r, 5);
         }
         catch(Exception se) {
             System.out.println(se);
             JOptionPane.showMessageDialog(null,se);
         }
     }

     public Supply getSupplyRecords(String supplyId){
         List<Supply> supplies = null;
         try  {
             supplies = sc.getSupply(supplyId, "", "", "", "");
         }
         catch(Exception se) {
             System.out.println(se);
             JOptionPane.showMessageDialog(null, se);
         }
         return supplies.get(0);
     }

  public static void main(String args[])
    {
    	new PatientSapplyList();
    }
 }
