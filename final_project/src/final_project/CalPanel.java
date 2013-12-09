package final_project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import final_project.LoopDemo.launchNewDay;

public class CalPanel extends JPanel implements ActionListener{

	int MONTH, YEAR, USERID;
	int START_DAY, MONTH_DAYS;
	JButton labelb, blankb, dayb;
	DBConn db;
	JFrame listPopup;
	
	// Constructor
	public CalPanel(int sday, int mday, int month, int year, int userid, DBConn mydb) {
		db = mydb;
		db.getEventDetails();
		MONTH = month;
		YEAR = year;
		USERID = userid;
		START_DAY = sday;
		MONTH_DAYS = mday;
		this.setPreferredSize(new Dimension(570,420));
		addDayLabels();
		addDays(db.activeDatesList);
	}
	
	// Add Day of Week labels at top.
	public void addDayLabels() {
    	String[] myarray = {"Sun","Mon","Tues","Wed","Thur","Fri","Sat"};
    	for (int s = 0; s < 7; s++) {
    		labelb = new JButton(myarray[s]);
            labelb.setVerticalTextPosition(SwingConstants.CENTER);
            labelb.setHorizontalTextPosition(SwingConstants.LEADING); //aka LEFT, for left-to-right locales
            labelb.setMnemonic(KeyEvent.VK_D);
            labelb.setPreferredSize(new Dimension(75,20));
            labelb.setBackground(Color.WHITE);
            add(labelb);
    	}
	}
	
	// Add Button for each day of week
	public void addDays(List<Integer> activedays) {
		
		// Add blank days at beginning of month.
    	for (int s = 0; s < START_DAY; s++) {
    		addBlank();
    	}
    	
    	// Add days to the calendar
    	for (int i = 1; i <= MONTH_DAYS; i++) {
    		dayb = new JButton("Day" + i);
    		dayb.setVerticalTextPosition(SwingConstants.CENTER);
    		dayb.setHorizontalTextPosition(SwingConstants.LEADING); //aka LEFT, for left-to-right locales
    		dayb.setMnemonic(KeyEvent.VK_D);
    		dayb.setPreferredSize(new Dimension(75,50));
        
    		// Check to see if the day is in the list.
    		boolean inlist = false;
    		for (int l = 0; l < activedays.size(); l++) {
    			if (i == activedays.get(l)) {
    				inlist = true;
    			}
    		}
    		// If date is in array, set one command
    		if (inlist) {
    			editDay mylisten = new editDay();
    			mylisten.eventDay = i;
    			mylisten.eventMonth = MONTH;
    			mylisten.eventYear = YEAR;
    			mylisten.userID = USERID;
    			dayb.addActionListener(mylisten);
    			dayb.setBackground(Color.GREEN);
    			dayb.addActionListener(this);
    		}
        // If the day is not in the array, set the other command
    		else {
    			launchNewDay mylisten = new launchNewDay();
    			mylisten.eventDay = i;
    			mylisten.eventMonth = MONTH;
    			mylisten.eventYear = YEAR;
    			mylisten.userID = USERID;
    			dayb.addActionListener(mylisten);
    			dayb.setBackground(Color.WHITE);
    			dayb.addActionListener(this);
    		}

    		add(dayb);
    	}
    	
		// Calcualte days at end of month
		int leftover = ((START_DAY + MONTH_DAYS) % 7);
    	if (leftover != 0) {
    		leftover = 7 - leftover;
    	}
    	
    	for (int s = 0; s < leftover; s++) {
    		addBlank();
    	}
    	
	}
	
	public void addBlank() {
		blankb = new JButton("...");
        blankb.setVerticalTextPosition(SwingConstants.CENTER);
        blankb.setHorizontalTextPosition(SwingConstants.LEADING); //aka LEFT, for left-to-right locales
       // blankb.setMnemonic(KeyEvent.VK_D);
        blankb.setPreferredSize(new Dimension(75,50));
        blankb.setBackground(Color.GRAY);
        this.add(blankb);
	}
	
	
	// Create popup frame for listing events.
	
	class listPopup extends JFrame {
		
	}
	
	
	// Implement button actions

    class launchNewDay implements ActionListener
    {

    	public int eventYear, eventMonth, eventDay, userID;
        public void actionPerformed(ActionEvent ae) 
        {

        	System.out.println(eventDay);
        }
    }
    class editDay implements ActionListener
    {

    	public int eventYear, eventMonth, eventDay, userID;
        public void actionPerformed(ActionEvent ae) 
        {

        	System.out.println(eventDay);
        }
    }



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
