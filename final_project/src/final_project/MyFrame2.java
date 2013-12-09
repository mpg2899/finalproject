package final_project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import final_project.LoopDemo.addButtonListener2;

// This is the main class that does the heavy lifting
public class MyFrame2 extends JFrame{

	// Declare buttons and panels to be added to our frame.
    private JButton startBut, nextMonthBut, previousMonthBut, allAppointmentsBut;
    private JPanel startPanel, calPanel, controlPanel;
    
    // Testing buttons and panels below.
    private JButton but,but2,but3, but4; 
    private JPanel panel1, panel2, panel3;

    //Constructor.
    public MyFrame2()
    {
    	createStartPanel();
    	createControlPanel();
    	createCalPanel();
    	addControlButtons();
    	addStartPanels();
    	
    	// Testing below.
    	/*
       createPanel();
       addPanel();
       */
    }
    
    private void createStartPanel() {
    	// Initialize our main menu panel.
    	//startPanel = new JPanel();
    	int[] testarr = {1, 2, 4};
    	//startPanel = new CalPanel(2, 28, testarr, 11, 2013, 0);
    	// Find Users function goes here.
    	// Need an array or object.
    	findUsers();
    	// For loop to display all user buttons.
    	// Buttons when clicked will start calendar.
    	/* 
    	 for (int i = 0; i < 10; i++) {
    	 	addUserButton(userid, username);
    	 }
    	 */
    	// Create new user button goes here.
    	
    }
    
    private void createControlPanel() {
    	// Initialize our control Panel.
    	// Probably doesn't need it's own class.
    	controlPanel = new ControlPanel();
    }
    
    private void createCalPanel() {
    	
    }
    
    private void addControlButtons() {
    	// Add buttons that go on the control bar below the calendar.
    	startBut = new JButton("Main Menu");
    	startBut.addActionListener(new mainMenuButtonListener());
        controlPanel.add(startBut);
    	
    }
    
    private void addStartPanels() {
    	// Add the startPanel to the content pane.
    	getContentPane().add(startPanel);
    	repaint();
    	printAll(getGraphics());
    }
    
    private void addUserButton(int userid, String username) {
    	// Create User Buttons given user name and user id.
    	selectUserButtonListener mybutton = new selectUserButtonListener();
    	mybutton.userid = userid;
    }
    private void drawCalendar(int userid, int month) {
    	// calPanel should be initialized by this point.
    }
    
    private void findUsers() {
    	// Connect to DB to fetch user names and IDs.
    }
    
    class mainMenuButtonListener implements ActionListener {
    	// We remove the calendar and control panel to return to start.
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			getContentPane().remove(calPanel);
			getContentPane().remove(controlPanel);
			getContentPane().add(startPanel);
		}
    	
    }
    
    class selectUserButtonListener implements ActionListener {
    	public int userid;
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			// So, here we remove the start panel
			// And add the calendar panel using drawCalendar function.
		}
    	
    }
    // Testing functions below.
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
    	panel3 = new LoopDemo(0, 31);
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
        //panel2.add(but2);
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