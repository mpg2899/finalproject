package final_project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import final_project.LoopDemo.launchNewDay;

public class CalPanel extends JPanel implements ActionListener{

	int DAY, MONTH, YEAR, USERID, EID;
	int START_DAY, MONTH_DAYS;
	JButton labelb, blankb, dayb;
	DBConn db;
	JPanel SubCalPanel;
	JFrame listPopup;
	EventList EOB = new EventList();
	JTextField etitlef, edetailsf;
	//JComboBox  estarthourf, estartminf, estophourf, estopminf;
	String[] ehours = {"0","1","2","3","4","5","6","7","8","9",
			"10","11","12","13","14","15","16","17","18","19","20","21","22","23"};
	String[] emin = {"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14",
			"15","16","17","18","19","20","21","22","23","24","25","26","27","28","29",
			"30","31","32","33","34","35","36","37","38","39","40","41","42","43","44",
			"45","46","47","48","49","50","51","52","53","54","55","56","57","58","59"};
	
	// Edit/Add event fields.
	JLabel titlel;
	JComboBox<String> estarthour;
	JComboBox<String> estartmin;
	JComboBox<String> estophour;
	JComboBox<String> estopmin;
	JTextField titlef;
	JTextArea detailsf;
	
	// Constructor
	public CalPanel(int sday, int mday, int month, int year, int userid, DBConn mydb) {
		db = mydb;
		MONTH = month;
		YEAR = year;
		USERID = userid;
		START_DAY = sday;
		MONTH_DAYS = mday;
		this.setPreferredSize(new Dimension(570,420));
		createSubPanel();
	}
	public void createSubPanel() {
		SubCalPanel = new JPanel();
		SubCalPanel.setPreferredSize(new Dimension(570, 420));
		db.activeDates(USERID, MONTH, YEAR);
		addDayLabels();
		addDays(db.activeDatesList);
		add(SubCalPanel);
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
            SubCalPanel.add(labelb);
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
    		dayb = new JButton(""+i);
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
    			dayb.addActionListener(mylisten);
    			dayb.setBackground(Color.GREEN);
    			dayb.addActionListener(this);
    		}
        // If the day is not in the array, set the other command
    		else {
    			launchNewDay mylisten = new launchNewDay();
    			mylisten.eventDay = i;
    			dayb.addActionListener(mylisten);
    			dayb.setBackground(Color.WHITE);
    			dayb.addActionListener(this);
    		}

    		 SubCalPanel.add(dayb);
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
        SubCalPanel.add(blankb);
	}
	
	
	// Create popup frame for listing events.
	
	class ListPopup extends JFrame {
		ListPopup() {
			db.getDaysEvents(USERID, DAY, MONTH, YEAR);
			JPanel listPane = new JPanel();
			listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
			JLabel label = new JLabel("Existing Events");
			
			JButton newEventB = new JButton("New Event");
			editEvent mylistennew = new editEvent();
			mylistennew.eOB = new EventList();
			mylistennew.eOB.eid = -1;
			newEventB.addActionListener(mylistennew);
			listPane.add(newEventB);
			listPane.add(label);

			//this.setPreferredSize(new Dimension(570,0));
			
			for (int i=0; i < db.eventList.size(); i++) {
				JPanel itemPanel = new JPanel();
				itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.PAGE_AXIS));
				JPanel detailsPanel = new JPanel();
				JPanel titlePanel = new JPanel();
				detailsPanel.setOpaque(false);
				titlePanel.setOpaque(false);
				
				titlePanel.setPreferredSize(new Dimension(570,30));
				// Set alternating colors for events.
				if (i % 2 == 0) {
					itemPanel.setOpaque(true);
					itemPanel.setBackground(Color.white);
				}
				// Format minutes so there are two digits.
				String startmin, stopmin;
				if (db.eventList.get(i).startmin == 0) {
					startmin = "00";
				}
				else startmin = String.valueOf(db.eventList.get(i).startmin);
				if (db.eventList.get(i).stopmin == 0) {
					stopmin = "00";
				}
				else stopmin = String.valueOf(db.eventList.get(i).stopmin);
				
				
				JLabel timeLabel = new JLabel(db.eventList.get(i).starthour +
						":" + startmin + " - " +
						db.eventList.get(i).stophour + ":" +
						stopmin);
				JLabel titleLabel = new JLabel(db.eventList.get(i).title);
				JLabel detailsLabel = new JLabel(db.eventList.get(i).details);
				//JButton viewButton = new JButton("" + db.eventList.get(i).eid);
				JButton viewButton = new JButton("Edit");
				editEvent mylisten = new editEvent();
				mylisten.eOB = db.eventList.get(i);
				viewButton.addActionListener(mylisten);
				titlePanel.add(timeLabel);
				titlePanel.add(titleLabel);
				titlePanel.add(viewButton);

				detailsPanel.add(detailsLabel);
				itemPanel.add(titlePanel);
				itemPanel.add(detailsPanel);
				listPane.add(itemPanel);
			}
			
			this.setVisible(true);
			this.add(listPane);
			this.pack();
			this.repaint();
		}
	}
	
	class EventPopup extends JFrame {
		EventPopup() {
			JPanel editPane = new JPanel();
			Box box = Box.createHorizontalBox();
			editPane.setLayout(new BoxLayout(editPane, BoxLayout.PAGE_AXIS));
			titlef = new JTextField(EOB.title);
			titlel = new JLabel("Title:");
			JLabel starttimel = new JLabel("Start Time:");
			JLabel stoptimel = new JLabel("Stop Time:");
			JLabel colonl = new JLabel(":");
			
			estarthour = new JComboBox<String>(ehours);
			estartmin = new JComboBox<String>(emin);
			estarthour.setSelectedIndex(EOB.starthour);
			estartmin.setSelectedIndex(EOB.startmin);
			
			estophour = new JComboBox<String>(ehours);
			estopmin = new JComboBox<String>(emin);
			estophour.setSelectedIndex(EOB.stophour);
			estopmin.setSelectedIndex(EOB.stopmin);
			
			box.add(titlel);
			box.add(titlef);
			editPane.add(box);
			box = Box.createHorizontalBox();
			
			box.add(starttimel);
			box.add(estarthour);
			box.add(colonl);
			box.add(estartmin);
			editPane.add(box);
			
			box = Box.createHorizontalBox();
			colonl = new JLabel(":");
			box.add(stoptimel);
			box.add(estophour);
			box.add(colonl);
			box.add(estopmin);
			editPane.add(box);
			
			
			JLabel detailsl = new JLabel("Details:");
			detailsf = new JTextArea(EOB.details);
			detailsf.setPreferredSize(new Dimension(220, 250));
			
			editPane.add(detailsl);
			editPane.add(detailsf);
			
			JButton esaveb = new JButton("Save");
			saveEvent mylisten = new saveEvent();
			esaveb.addActionListener(mylisten);
			editPane.add(esaveb);
			
			this.add(editPane);
			this.setVisible(true);
			this.pack();
			this.repaint();
		}
	}
	

	// Implement button actions

    class launchNewDay implements ActionListener
    {

    	public int eventDay;
        public void actionPerformed(ActionEvent ae) 
        {
        	EOB = new EventList();
        	DAY = eventDay;
        	listPopup = new ListPopup();
        	
        }
    }
    class editDay implements ActionListener
    {

    	public int eventDay;
        public void actionPerformed(ActionEvent ae) 
        {
        	DAY = eventDay;
        	listPopup = new ListPopup();

        }
    }

    class editEvent implements ActionListener
    {

    	public EventList eOB;
        public void actionPerformed(ActionEvent ae) 
        {
        	EOB = eOB;
        	listPopup.dispose();
        	listPopup = new EventPopup();
        }
    }
    
    class saveEvent implements ActionListener
    {

        public void actionPerformed(ActionEvent ae) 
        {
        	if (titlef.getText().length() >= 1) {
        	EOB.title = titlef.getText();
        	EOB.details = detailsf.getText();
        	
        	EOB.starthour = Integer.parseInt(estarthour.getSelectedItem().toString());
        	EOB.startmin = Integer.parseInt(estartmin.getSelectedItem().toString());
        	EOB.stophour = Integer.parseInt(estophour.getSelectedItem().toString());
        	EOB.stopmin = Integer.parseInt(estopmin.getSelectedItem().toString());
        	
        	db.saveEvent(EOB, USERID, DAY, MONTH, YEAR);

			
			SubCalPanel.setVisible(false);

        	listPopup.setVisible(false);
        	createSubPanel();
			getRootPane().getContentPane().repaint();
	    	printAll(getGraphics());
        	
        	}
        	else {
        		JOptionPane.showMessageDialog(null, "Please a title for the event");
        	}
        }
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
