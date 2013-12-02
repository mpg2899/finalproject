package final_project;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;





import final_project.MyFrame2.addButtonListener;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

/* 
 * ButtonDemo.java requires the following files:
 *   images/right.gif
 *   images/middle.gif
 *   images/left.gif
 */
public class LoopDemo extends JPanel
                        implements ActionListener {
    protected JButton b1, b2, b3;
    int START_DAY, MONTH_DAYS;
    
    public LoopDemo(int sday, int mdays) {
    	// We need to pass in an array that contains which days have events
    	// We need to pass in the number of days in the month
    	// We need to pass in what day of the week the calendar starts on.
    	System.out.println();
    	START_DAY = sday;
    	MONTH_DAYS = mdays;
    	
    	Map<String, String> mymap = new HashMap<String,String>();
    	for (int v = 0; v < 10; v++) {
    		 
    	}
    	
    	int leftover = ((START_DAY + MONTH_DAYS) % 7);
    	if (leftover != 0) {
    		leftover = 7 - leftover;
    	}
    	//System.out.println(leftover);
    	this.setPreferredSize(new Dimension(570,500));
    	String[] myarray = {"Sun","Mon","Tues","Wed","Thur","Fri","Sat"};
    	for (int s = 0; s < 7; s++) {
    		b1 = new JButton(myarray[s]);
            b1.setVerticalTextPosition(AbstractButton.CENTER);
            b1.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
            b1.setMnemonic(KeyEvent.VK_D);
            b1.setPreferredSize(new Dimension(75,20));
            b1.setBackground(Color.WHITE);
            add(b1);
    	}
    	for (int s = 0; s < START_DAY; s++) {
    		b1 = new JButton("...");
            b1.setVerticalTextPosition(AbstractButton.CENTER);
            b1.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
            b1.setMnemonic(KeyEvent.VK_D);
            b1.setPreferredSize(new Dimension(75,50));
            // If date is in array, set one command
            addButtonListener2 mylisten = new addButtonListener2();
            mylisten.var1 = s;
            b1.addActionListener(mylisten);
            b1.setBackground(Color.GRAY);
            add(b1);
    	}
    	for (int i = 1; i <= MONTH_DAYS; i++) {
    	b1 = new JButton("Day" + i);
        b1.setVerticalTextPosition(AbstractButton.CENTER);
        b1.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
        b1.setMnemonic(KeyEvent.VK_D);
        b1.setPreferredSize(new Dimension(75,50));
        
        
        // If date is in array, set one command
        launchNewDay mylisten = new launchNewDay();
        mylisten.var1 = i;
        b1.addActionListener(mylisten);
        b1.setBackground(Color.GREEN);
        b1.addActionListener(this);
        // if date is not in array, set other command:
        /*
         else b1.setActionCommand("createitem");
         */
        
        add(b1);
    	}
    	for (int s = 0; s < leftover; s++) {
    		b1 = new JButton("...");
            b1.setVerticalTextPosition(AbstractButton.CENTER);
            b1.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
            b1.setMnemonic(KeyEvent.VK_D);
            b1.setPreferredSize(new Dimension(75,50));
            // If date is in array, set one command
            final int s1 = s;
            b1.addActionListener(new ActionListener() {
            	 @Override
                 public void actionPerformed(ActionEvent e) {
                     System.out.println("in");
                     b1.putClientProperty("id", Integer.valueOf(s1));
                     System.out.println(b1.getClientProperty("id"));
                 }
            });
            b1.setBackground(Color.GRAY);
            add(b1);
    	}
    }

    class addButtonListener2 implements ActionListener
    {
    	public int var1;
        public void actionPerformed(ActionEvent ae) 
        {
        	/*
            getContentPane().removeAll();
            getContentPane().add(panel2);//Adding to content pane, not to Frame
            repaint();
            printAll(getGraphics());//Extort print all content
            */
        	System.out.println(var1);
        }
    }
    class launchNewDay implements ActionListener
    {
    	public int var1;
        public void actionPerformed(ActionEvent ae) 
        {
        	/*
            getContentPane().removeAll();
            getContentPane().add(panel2);//Adding to content pane, not to Frame
            repaint();
            printAll(getGraphics());//Extort print all content
            */
        	System.out.println(var1);
        }
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		 if (e.getActionCommand().startsWith("show")) {
	            System.out.println(e.getActionCommand());
	            String mystring = e.getActionCommand().substring(9);
	            System.out.println(mystring);
	            
	        }

	}
}