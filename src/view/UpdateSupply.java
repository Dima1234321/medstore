package view;

import controller.SupplyController;
import model.Supply;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UpdateSupply extends JFrame implements ActionListener
{
    JFrame jf;
	JLabel l1,l2,l3,l4,l5,l6;
	JTextField t1,t2,t3,t4,t5;
	JButton b0,b1,b2,b3;
	GridBagLayout gbl;
	GridBagConstraints gbc;
	Font f;
    DefaultTableModel model = new DefaultTableModel();
		JTable tabGrid = new JTable(model);
    JScrollPane scrlPane = new JScrollPane(tabGrid);
	private final SupplyController sc;

	UpdateSupply()
	{
		this.sc = SupplyController.getInstance();

		jf=new JFrame();
		Dimension screen= Toolkit.getDefaultToolkit().getScreenSize();
		f = new Font("Times New Roman",Font.BOLD,20);
		jf.setLayout(null);

	    l6=new JLabel("Update Supplier");
	    l6.setFont(new Font("Times New Roman",Font.BOLD,25));
	    l6.setBounds(300,50,300,40);l6.setForeground(Color.blue);
	    jf.add(l6);

		l1= new JLabel("Supplier id");
		//l1.setFont(f);
 l1.setBounds(150,120,130,25);
		jf.add(l1);

		t1=new JTextField(20);
		t1.setBounds(320,120,100,25);t1.setToolTipText("Enter supplier id");
		jf.add(t1);

		l2 = new JLabel("Supplier name");
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


        b0 = new JButton("Add",new ImageIcon(getClass().getResource("..//images//addnew.png")));
	    b0.setBounds(150,330,110,35);b0.setToolTipText("click to add supplier details");
	    jf.add(b0); b0.addActionListener(this);

		b1 = new JButton("Update",new ImageIcon(getClass().getResource("..//images//update.png")));
		b1.setBounds(300,330,110,35);b1.setToolTipText("click to update supplier details");
		jf.add(b1);b1.addActionListener(this);

		b2 = new JButton("Delete",new ImageIcon(getClass().getResource("..//images//delete.png")));
		b2.setBounds(450,330,110,35);b2.setToolTipText("click to delete all textfilds");
	    jf.add(b2);b2.addActionListener(this);

	   	b3 = new JButton("Show",new ImageIcon(getClass().getResource("..//images//search.png")));
		b3.setBounds(600,330,110,35);b3.setToolTipText("click to view all supplier details in grid below");
		jf.add(b3); b3.addActionListener(this);

	    scrlPane.setBounds(0,380,900,260);
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
		 jf.getContentPane().setBackground(new Color(204, 204, 153));
	     jf.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae)
	{
      if(ae.getSource()==b0)
      {
		  if(!t1.getText().isEmpty())
		  	addSupply(t1.getText(),t2.getText(),t3.getText(),t4.getText(),t5.getText());
    }
     else if(ae.getSource()==b1) {//update
		  int r = tabGrid.getSelectedRow();
		  if(r < 0)
			  return;
		  editSupply(r,t1.getText(),t2.getText(),t3.getText(),t4.getText(),t5.getText());
 	}
  	else if(ae.getSource()==b2) {//clear
		  int r = tabGrid.getSelectedRow();
		  if(r < 0)
			  return;
		  removeSupply(r);
  	}
    else if(ae.getSource()==b3) {//list
		showSupply(t1.getText(),t2.getText(),t3.getText(),t4.getText(),t5.getText());
	}
  }

	public void addSupply(String id, String name, String location, String device_name, String amount){
		try
		{
			Supply s = sc.insertSupply(id,name,location,device_name,amount);
			JOptionPane.showMessageDialog(null,"Successfully added");
			model.addRow(new Object[] { id, name, location, device_name, amount });
		}
		catch(Exception se) {
			System.out.println(se);
			JOptionPane.showMessageDialog(null,se);
		}
	}

	public void editSupply(int r, String id, String name, String location, String device_name, String amount){
		try
		{
			Supply s = sc.updateSupply(id,name,location,device_name,amount);
			JOptionPane.showMessageDialog(null,"Successfully updated");
			tabGrid.getModel().setValueAt(s.id, r, 0);
			tabGrid.getModel().setValueAt(s.name, r, 1);
			tabGrid.getModel().setValueAt(s.location, r, 2);
			tabGrid.getModel().setValueAt(s.device_name, r, 3);
			tabGrid.getModel().setValueAt(s.amount, r, 4);
		}
		catch(Exception se) {
			System.out.println(se);
			JOptionPane.showMessageDialog(null,se);
		}
	}

  public void removeSupply(int r){
		  try
		  {
			  String id = tabGrid.getModel().getValueAt(r, 0).toString();
			  sc.deleteSupply(id);
			  JOptionPane.showMessageDialog(null, "Successfully deleted");
			  model.removeRow(r);
		  }
		  catch(Exception se) {
			  System.out.println(se);
			  JOptionPane.showMessageDialog(null,se);
		  }
  }
  public void showSupply(String id, String name, String location, String device_name, String amount){
		try
		{
			List<Supply> supplies = sc.getSupply(id, name, location, device_name, amount);
			if (model.getRowCount() > 0) {
				for (int i = model.getRowCount() - 1; i > -1; i--) {
					model.removeRow(i);
				}
			}
			int r = 0;
			for (Supply s  : supplies) {
				model.insertRow(r++,new Object[]{ s.id ,s.name, s.location, s.device_name, s.amount });
			}
		}
		catch(Exception se) {
			System.out.println(se);
			JOptionPane.showMessageDialog(null,se);
		}
	}

	public static void main(String args[])
	{
	    new UpdateSupply();
	}
}

