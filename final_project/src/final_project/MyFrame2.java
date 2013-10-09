package final_project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MyFrame2 extends JFrame{
    private JPanel panel1, panel2, panel3;
    private JButton but,but2,but3, but4; 
    public MyFrame2()
    {
       createPanel();
       addPanel();
    }
    private void createPanel()
    {
       /* panel1 = new LoopDemo();
        but = new JButton("TestButton");
        but.addActionListener(new addButtonListener());
        but.setBounds(50, 90, 190, 30);//There are example values but remember about setting size
        */
    	createPanel3();
        panel2 = new JPanel();
        but2 = new JButton("TestButton2");
        but2.setBounds(50, 50, 90, 30);//There are example values but remember about setting size
    }
    
    private void createPanel3() {
    	// pass in variables here.
    	panel3 = new LoopDemo();
    	but3 = new JButton("TestButton");
        but3.addActionListener(new addButtonListener());
        but3.setBounds(50, 90, 190, 30);//There are example values but remember about setting size
        
    	but4 = new JButton("Read Activity");
        but4.addActionListener(new addButtonListener3());
        but4.setBounds(50, 90, 190, 30);//There are example values but remember about setting size
    }
    private void addPanel()
    {
        panel3.add(but3);
        panel3.add(but4);
        panel2.add(but2);
        add(panel3);
    }

    class addButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ae) 
        {
        	/*
            getContentPane().removeAll();
            getContentPane().add(panel2);//Adding to content pane, not to Frame
            repaint();
            printAll(getGraphics());//Extort print all content
            */
        	getContentPane().remove(panel3);
        	getContentPane().add(panel3);
        	repaint();
        	printAll(getGraphics());
        }
    }
    class addButtonListener3 implements ActionListener
    {
        public void actionPerformed(ActionEvent ae) 
        {
        	CalendarActivity myActivity = DBConnect.readDB();
    		System.out.println(myActivity.name);
        }
    }



}