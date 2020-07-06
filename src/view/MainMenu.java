package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MainMenu extends JFrame implements ActionListener
{   JFrame jf;
	JMenuBar mbar;
	JMenu m1,m2,m3,m4,m5;
	JMenuItem m1_1,m1_2,m1_3,m1_4,m1_5,m2_1,m2_2,m2_3,m2_4,m2_5,m3_1,m3_2,m4_1,m5_1;
	JLabel l1,LogoColl;
	GridBagLayout gbl;

	public MainMenu()
	{
        jf=new JFrame();
		gbl=new GridBagLayout();
		jf.setLayout(null);

		l1=new JLabel("WELCOME TO PATIENT - MEDICAL SUPPLIES MANAGEMENT SYSTEM");
		l1.setFont(new Font("Times New Roman",Font.BOLD,20));
		l1.setBounds(100,30,900,100);
		jf.add(l1);

		mbar = new JMenuBar();
		jf.setJMenuBar(mbar);

		m1=new JMenu("Actions");
		mbar.add(m1);
		m1_1 = new JMenuItem("Add New Supplier",new ImageIcon(getClass().getResource("..//images//addnew.png")));
		//m1.add(m1_1);
		m1_2 = new JMenuItem("search Supplier",new ImageIcon(getClass().getResource("..//images//search.png")));
		//m1.add(m1_2);
		m1_3 = new JMenuItem("Supplier",new ImageIcon(getClass().getResource("..//images//update.png")));
		m1.add(m1_3);
		m1_4 = new JMenuItem("Patient",new ImageIcon(getClass().getResource("..//images//users.png")));
		m1.add(m1_4);
	    m1_5 = new JMenuItem("Patient & Supply",new ImageIcon(getClass().getResource("..//images//all.png")));
		m1.add(m1_5);

		m2=new JMenu("Medicine");
		//mbar.add(m2);
		m2_1 = new JMenuItem("Add New Medicine",new ImageIcon(getClass().getResource("..//images//addnew.png")));
		m2.add(m2_1);
		m2_2 = new JMenuItem("search Medicine",new ImageIcon(getClass().getResource("..//images//search.png")));
		m2.add(m2_2);
		m2_3 = new JMenuItem("Update Medicine",new ImageIcon(getClass().getResource("..//images//update.png")));
		m2.add(m2_3);
		m2_4 = new JMenuItem("Delete Medicine",new ImageIcon(getClass().getResource("..//images//delete.png")));
		m2.add(m2_4);
	    m2_5 = new JMenuItem("Stock of Medicine",new ImageIcon(getClass().getResource("..//images//all.png")));
		m2.add(m2_5);


		m3=new JMenu("Report");
	    //mbar.add(m3);
		m3_1 = new JMenuItem("Daily Purchase Report",new ImageIcon(getClass().getResource("..//images//report.png")));
		m3.add(m3_1);

		m3_2 = new JMenuItem("Suplier wise medicine Report",new ImageIcon(getClass().getResource("..//images//report.png")));
		m3.add(m3_2);

		m4=new JMenu("About");
		mbar.add(m4);
		m4_1 = new JMenuItem("About System",new ImageIcon(getClass().getResource("..//images//help.png")));
		m4.add(m4_1);

		//m5=new JMenu("Exit");
		//mbar.add(m5);
		m5_1 = new JMenuItem("Exit",new ImageIcon(getClass().getResource("..//images//exit.png")));
		//m5.add(m5_1);
		m4.add(m5_1);

        m1_1.addActionListener(this);
		m1_2.addActionListener(this);
		m1_3.addActionListener(this);
		m1_4.addActionListener(this);
    	m1_5.addActionListener(this);

		/*m2_1.addActionListener(this);
		m2_2.addActionListener(this);
		m2_3.addActionListener(this);
		m2_4.addActionListener(this);
	    m2_5.addActionListener(this);

		m3_1.addActionListener(this);
		m3_2.addActionListener(this);*/
		m4_1.addActionListener(this);
		m5_1.addActionListener(this);

		jf.setTitle("Main Menu");
		jf.setLocation(20,20);
	    jf.setSize(900,700);
	    jf.setResizable(false);
		jf.getContentPane().setBackground(new Color(102, 204, 255));

		BufferedImage img = null;
		try {
			img = ImageIO.read(getClass().getResource("..//images//MainMenuImage.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		JLabel label = new JLabel(imageIcon);
		label.setBounds(100,10,700,700);
		jf.add(label);

		jf.setVisible(true);

	}

	public void actionPerformed(ActionEvent ae)
	{

	  if(ae.getSource()==m1_3)
		{
			new UpdateSupply();
		}
		else if(ae.getSource()==m1_4)
		{
			try {
				new UpdatePatient();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	    else if(ae.getSource()==m1_5)
		{
			new PatientSapplyList();
		}
		else if(ae.getSource()==m4_1)
		{
	        new About();
		}

		else if(ae.getSource()==m5_1)
		{
		  System.exit(0);
		}

  }

//	public static void main(String args[])
//	{
//		new MainMenu();
//	}
}
