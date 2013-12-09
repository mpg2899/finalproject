package final_project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MainFrame  extends JFrame{
	
	// Declare buttons and panels to be added to our frame.
    private JButton startBut, nextMonthBut, previousMonthBut, allAppointmentsBut;
    private JPanel startPanel, calPanel, controlPanel, containerPanel, newUserPanel;
    private JTextField newUserTextField;
    
    // User info
    private int USERID, MONTH, YEAR;
    private String USERNAME;
    
    // Event info
    private int EDAY, ESTART_HOUR, ESTART_MIN, ESTOP_HOUR, ESTOP_MIN;
    private String ETITLE, ETEXT;
    
    //DB Object
    private DBConn db;

    //Constructor.
    public MainFrame() {
    	
    	super("Calendar App");
    	File dbfile = new File("calendar.db");

    	
    	// Connect to the database or exit
    	try {
			db = new DBConn();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
    	
    	// Create the start panel.
    	try {
			createStartPanel();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	addStartPanels();
    	createContainerPanel();
    	setStartMonthYear();
    	// Testing below.
    	/*
       createPanel();
       addPanel();
       */
    }
    
    private void setStartMonthYear() {
		// TODO Auto-generated method stub
    	MONTH = Calendar.getInstance().get(Calendar.MONTH);
    	YEAR = Calendar.getInstance().get(Calendar.YEAR);
	}

	private void createStartPanel() throws SQLException {
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
    	controlPanel = new ControlPanel(MONTH, YEAR);
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
    	System.out.println(USERID);
    	db.activeDates(USERID, MONTH, YEAR);
    	List<Integer> activedays = db.activeDatesList;
    	
    	// Create our Calendar.
    	calPanel = new CalPanel(monthInfo.startDay(), monthInfo.getDays(), MONTH, YEAR, USERID, db);
    }
    
    private void addControlButtons() {
    	// Add buttons that go on the control bar below the calendar.
    	monthButtonListener mylisten = new monthButtonListener();
    	mylisten.dir = -1;
    	JButton leftMonthBut = new JButton("<<");
    	leftMonthBut.addActionListener(mylisten);
    	
    	mylisten = new monthButtonListener();
    	mylisten.dir = 1;
    	JButton rightMonthBut = new JButton(">>");
    	rightMonthBut.addActionListener(mylisten);
    	
    	startBut = new JButton("Main Menu");
    	startBut.addActionListener(new mainMenuButtonListener());
    	controlPanel.add(leftMonthBut);
    	controlPanel.add(rightMonthBut);
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
    
    private void findUsers() throws SQLException {
    	
    	// Connect to DB to fetch user names and IDs.
    	db.findUsers();
    	
    	List<String> userslist = db.usersList;
    	List<Integer> useridslist = db.useridsList;
    	
    	for (int i = 0; i < userslist.size(); i++) {
    		addUserButtonModel(useridslist.get(i), userslist.get(i));
    	}
    }
    
    // New user button model.
    private void newUser() {
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
    
    // Select user button model.
    private void addUserButtonModel(int userid, String username) {
		JButton userb = new JButton(username);
		userb.setVerticalTextPosition(SwingConstants.CENTER);
		userb.setHorizontalTextPosition(SwingConstants.LEADING); //aka LEFT, for left-to-right locales
		userb.setMnemonic(KeyEvent.VK_D);
		userb.setPreferredSize(new Dimension(75,50));
		selectUserButtonListener mylisten = new selectUserButtonListener();
		userb.addActionListener(mylisten);
		userb.setBackground(Color.WHITE);
		mylisten.userid = userid;
		mylisten.username = username;
		startPanel.add(userb);
    }
    
    
    private void saveNewUser() {

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
    
    class monthButtonListener implements ActionListener {
    	// Change the current month.
    	public int dir;
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			MONTH += dir;
			if (MONTH > 11) {
				MONTH = 0;
				YEAR++;
			}
			if (MONTH < 0) {
				MONTH = 11;
				YEAR--;
			}
			getContentPane().remove(containerPanel);
			containerPanel.remove(calPanel);
			containerPanel.remove(controlPanel);
	    	createControlPanel();
	    	createCalPanel();	
	    	getContentPane().add(containerPanel);
			containerPanel.add(calPanel);
			containerPanel.add(controlPanel);
			pack();
	    	repaint();
	    	printAll(getGraphics());
		}
    	
    }
    
    class newUserButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			// Remove other start panel.
			getContentPane().remove(startPanel);
			
			newUserPanel = new JPanel();

			// Add fields to new panel.
			newUserTextField = new JTextField(20);
			newUserPanel.add(newUserTextField);
			// Add save button to panel.
	    	JButton saveuserb = new JButton("Save");
	    	saveuserb.setVerticalTextPosition(SwingConstants.CENTER);
	    	saveuserb.setHorizontalTextPosition(SwingConstants.LEADING); //aka LEFT, for left-to-right locales
	    	saveuserb.setMnemonic(KeyEvent.VK_D);
	    	saveuserb.setPreferredSize(new Dimension(75,50));
	    	saveNewUserButtonListener mylisten = new saveNewUserButtonListener();
	    	//mainMenuButtonListener mylisten2 = new mainMenuButtonListener();
			saveuserb.addActionListener(mylisten);
			//saveuserb.addActionListener(mylisten2);
			saveuserb.setBackground(Color.WHITE);
			//mylisten.newUserName = username;
			newUserPanel.add(saveuserb);
			
			
			// Display everything
			
			getContentPane().add(newUserPanel);
			pack();
	    	repaint();
	    	printAll(getGraphics());
		}
    }
    
    class saveNewUserButtonListener implements ActionListener {
		
		public String newUserName;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			newUserName = null;
			newUserName = newUserTextField.getText();
			if (newUserName.length() >= 4) {
			// Save entry into DB
			db.saveNewUser(newUserName);
			
			
			//end testing
			
			// Repaint start panel
	    	
	    	getContentPane().remove(newUserPanel);
	    	try {
				createStartPanel();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			addStartPanels();
			
			pack();
	    	repaint();
	    	System.out.println("New user saved!");
			}
			else {
				JOptionPane.showMessageDialog(null, "Please enter a user name 4 or more characters long");
			}
			
	    	
			//saveNewUser();
		}
    }
    
    class selectUserButtonListener implements ActionListener {
    	public int userid;
    	public String username;
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			// Set our user variables for the selected user.
			USERNAME = this.username;
			USERID = this.userid;
			
			// So, here we remove the start panel
			// And add the calendar panel using drawCalendar function.
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
