package final_project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import final_project.MyFrame2.mainMenuButtonListener;
import final_project.MyFrame2.selectUserButtonListener;

public class MainFrame  extends JFrame{
	
	// Declare buttons and panels to be added to our frame.
    private JButton startBut, nextMonthBut, previousMonthBut, allAppointmentsBut;
    private JPanel startPanel, calPanel, controlPanel, containerPanel;
    
    // Testing buttons and panels below.
    private JButton but,but2,but3, but4; 
    private JPanel panel1, panel2, panel3;
    
    // User info
    private int USERID, MONTH, YEAR;
    private String USERNAME;
    
    //DB Object
    private DBConn db;

    //Constructor.
    public MainFrame()
    {
    	
    	// Connect to the database or exit
    	try {
			db = new DBConn();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
    	
    	// Create the start panel.
    	createStartPanel();
    	addStartPanels();
    	createContainerPanel();
    	// Testing below.
    	/*
       createPanel();
       addPanel();
       */
    }
    
    private void createStartPanel() {
    	// Initialize our main menu panel.
    	startPanel = new JPanel();
    	startPanel.setPreferredSize(new Dimension(200,200));
    	// Find Users function goes here.
    	// Need an array or object.
    	findUsers();
    	newUser();
    }
    
    private void createControlPanel() {
    	// Initialize our control Panel.
    	// Probably doesn't need it's own class.
    	controlPanel = new ControlPanel();
    	addControlButtons();
    	controlPanel.setPreferredSize(new Dimension(570,50));
    }
    
    private void createContainerPanel() {
    	containerPanel = new JPanel();
    	containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
    	//containerPanel.setPreferredSize(new Dimension(570,100));
    }
    
    private void createCalPanel() {
    	// Create monthInfo object to get start day and number of days in month.
    	monthInformation monthInfo = new monthInformation(MONTH, YEAR);
    	
    	// Connect to database to determine which days have active events for the user.
    	int[] activedays = {1, 2, 4};
    	
    	// CalPanel(int sday, int mday, int[] activedays, int month, int year, int userid)
    	calPanel = new CalPanel(monthInfo.startDay(), monthInfo.getDays(), activedays, MONTH, YEAR, USERID);
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
    	String[][] userinfo = db.getUsers();
    	
    	for (int i = 0; i < userinfo[0].length; i++) {
    		System.out.println(userinfo[0][i]);
    		addUserButton(userinfo[0][i], userinfo[1][i]);
    	}
    	// For loop to display all user buttons.
    	// Buttons when clicked will start calendar.
    	/* 
    	 for (int i = 0; i < 10; i++) {
    	 	addUserButton(userid, username);
    	 }
    	 */
    }
    
    private void newUser() {
    	// Create new user button goes here.
    	JButton newuserb = new JButton("New");
    	newuserb.setVerticalTextPosition(SwingConstants.CENTER);
    	newuserb.setHorizontalTextPosition(SwingConstants.LEADING); //aka LEFT, for left-to-right locales
    	newuserb.setMnemonic(KeyEvent.VK_D);
    	newuserb.setPreferredSize(new Dimension(75,50));
    	newUserButtonListener mylisten = new newUserButtonListener();
		newuserb.addActionListener(mylisten);
		newuserb.setBackground(Color.GREEN);
		startPanel.add(newuserb);
    }
    
    private void addUserButton(String userid, String username) {
		JButton userb = new JButton(username);
		userb.setVerticalTextPosition(SwingConstants.CENTER);
		userb.setHorizontalTextPosition(SwingConstants.LEADING); //aka LEFT, for left-to-right locales
		userb.setMnemonic(KeyEvent.VK_D);
		userb.setPreferredSize(new Dimension(75,50));
		selectUserButtonListener mylisten = new selectUserButtonListener();
		userb.addActionListener(mylisten);
		userb.setBackground(Color.WHITE);
		mylisten.userid = Integer.parseInt(userid);
		mylisten.username = username;
		startPanel.add(userb);
    }
    
     //--------------------------
    // Buttons
   //--------------------------
    
    class mainMenuButtonListener implements ActionListener {
    	// We remove the calendar and control panel to return to start.
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			containerPanel.remove(calPanel);
			containerPanel.remove(controlPanel);
			getContentPane().remove(containerPanel);
			addStartPanels();
			pack();
	    	repaint();
		}
    	
    }
    
    class newUserButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			// Launch popup
			
			// Add fields to popup
			
			// Add save button to popup
	    	
		}
    }
    
    class saveNewUserButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			// Save entry into DB
			
			
			// Repaint start panel
			getContentPane().remove(startPanel);
			createStartPanel();
			containerPanel.remove(calPanel);
			containerPanel.remove(controlPanel);
			getContentPane().remove(containerPanel);
			addStartPanels();
			pack();
	    	repaint();
		}
    }
    
    class selectUserButtonListener implements ActionListener {
    	public int userid;
    	public String username;
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			// So, here we remove the start panel
			// And add the calendar panel using drawCalendar function.
			USERNAME = this.username;
			USERID = this.userid;
	    	createControlPanel();
	    	createCalPanel();
			getContentPane().remove(startPanel);
			containerPanel.add(calPanel);
			containerPanel.add(controlPanel);
			getContentPane().add(containerPanel);
			pack();
	    	repaint();
	    	printAll(getGraphics());
	    	
		}
    	
    }
}
