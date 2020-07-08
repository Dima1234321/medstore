package view;

import javax.swing.*;
import java.awt.*;

class About extends JFrame
{
	JFrame jf;
	JLabel l1,l2,l3,l4,l5,l6,l7,l8;
	JButton b1,b2,b3;

	About()
	{
		jf=new JFrame();

		jf.setLayout(null);

		l1 = new JLabel("Patient - Medical Supplies Management System");
		l1.setFont(new Font("Times New Roman",Font.BOLD,25));
		l1.setBounds(200,30,600,40);l1.setForeground(Color.blue);
		jf.add(l1);



		l2 = new JLabel("This System developed by,");
		//l2.setFont(new Font("Times New Roman",Font.BOLD,20));
		l2.setBounds(100,150,600,40);
		jf.add(l2);

		l3 = new JLabel("Dima Ruven, David Tzukerman, Amit Shani, Alexandra Fedorov");
		l3.setFont(new Font("Times New Roman",Font.BOLD,18));
		l3.setBounds(100,200,800,40);l3.setForeground(Color.red);
		jf.add(l3);

		l4 = new JLabel("Under the software engineering course project, HIT");
		l4.setFont(new Font("Times New Roman",Font.BOLD,18));l4.setForeground(Color.red);
		l4.setBounds(100,250,800,40);
		jf.add(l4);

		l5 = new JLabel("In this system we can add details of Medicines Devices and Patient information.");
		//l5.setFont(new Font("Times New Roman",Font.BOLD,20));
		l5.setBounds(100,300,800,40);
		jf.add(l5);

		l8 = new JLabel("The system provide the Management of medicine devices accordingly of people needs.");
		//l5.setFont(new Font("Times New Roman",Font.BOLD,20));
		l8.setBounds(100,320,800,40);
		jf.add(l8);

		l6 = new JLabel("We can also upadate, delete, insert & make search the existing details. ");
		//l6.setFont(new Font("Times New Roman",Font.BOLD,20));
		l6.setBounds(100,350,800,40);
		jf.add(l6);

		l7 = new JLabel("It helps to be informed and respond quickly to the rapidly changing environment associated with coronavirus");
		//l7.setFont(new Font("Times New Roman",Font.BOLD,20));
		l7.setBounds(100,400,800,40);
		jf.add(l7);

        jf.setTitle("About System");
		jf.setSize(900,700);
		jf.setLocation(20,20);
		jf.setResizable(false);
		jf.getContentPane().setBackground(new Color(102, 204, 255));
		jf.setVisible(true);

	}

	public static void main(String args[])
	{
          new About();

	}
}
