package final_project;


import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/* 
 * ButtonDemo.java requires the following files:
 *   images/right.gif
 *   images/middle.gif
 *   images/left.gif
 */
public class ButtonDemo extends JPanel
                        implements ActionListener {
	//private static LoopDemo myContentPane;

	//private static LoopDemo myContentPane2;
	
	private static JFrame frame2;
	
	public JPanel mycontainer;
	
    protected JButton b1, b2, b3;

    public ButtonDemo() {
        ImageIcon leftButtonIcon = createImageIcon("images/right.gif");
        ImageIcon middleButtonIcon = createImageIcon("images/middle.gif");
        ImageIcon rightButtonIcon = createImageIcon("left.jpg");
    	this.setPreferredSize(new Dimension(500,500));
        b1 = new JButton("Disable middle button", leftButtonIcon);
        b1.setVerticalTextPosition(AbstractButton.CENTER);
        b1.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
        b1.setMnemonic(KeyEvent.VK_D);
        b1.setActionCommand("disable");

        b2 = new JButton("Middle button");
        b2.setVerticalTextPosition(AbstractButton.BOTTOM);
        b2.setHorizontalTextPosition(AbstractButton.CENTER);
        b2.setMnemonic(KeyEvent.VK_M);
        b2.setActionCommand("sayhello");
        
        b3 = new JButton("Enable middle button");
        //Use the default text position of CENTER, TRAILING (RIGHT).
        b3.setMnemonic(KeyEvent.VK_E);
        b3.setActionCommand("enable");
        b3.setEnabled(false);

        //Listen for actions on buttons 1 and 3.
        b1.addActionListener(this);
        b3.addActionListener(this);
        b2.addActionListener(this);

        b1.setToolTipText("Click this button to disable the middle button.");
        b2.setToolTipText("This middle button does nothing when you click it.");
        b3.setToolTipText("Click this button to enable the middle button.");

        //Add Components to this container, using the default FlowLayout.
        add(b1);
        add(b2);
        add(b3);
    }

    public void actionPerformed(ActionEvent e) {
        if ("disable".equals(e.getActionCommand())) {
            b2.setEnabled(false);
            b1.setEnabled(false);
            b3.setEnabled(true);
        }
        else if ("sayhello".equals(e.getActionCommand())) {
        	showScreen2();
        }
        else {
            b2.setEnabled(true);
            b1.setEnabled(true);
            b3.setEnabled(false);
        }
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = ButtonDemo.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Create the GUI and show it.  For thread safety, 
     * this method should be invoked from the 
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {

    	//Create and set up the window.
    	JFrame frame = new JFrame("ButtonDemo");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    	//Create and set up the content pane.
    	ButtonDemo newContentPane = new ButtonDemo();
    	newContentPane.setOpaque(true); //content panes must be opaque
    	frame.setContentPane(newContentPane);



    	//Display the window.
    	frame.pack();
    	frame.setVisible(true);
    	//JOptionPane mypane = new JOptionPane();
    	/*
    	JOptionPane.showMessageDialog(null,
    			"Eggs are not supposed to be green.");
    	*/
        int n = JOptionPane.showConfirmDialog(
        	    null,
        	    "Would you like to see screen 2?",
        	    "An Inane Question",
        	    JOptionPane.YES_NO_OPTION);
    	if (n == 0) {
    		showScreen2();
    	}
    }
    public static int reallyClose() {
    	
    	return 2;
    }
    public static void showScreen2() {
        JFrame frame2 = new JFrame("Frame2");
        //frame2.setDefaultCloseOperation(reallyClose());
        
        // Setup multiple panels
        JPanel mycontainer = new JPanel();
        mycontainer.setLayout(new BoxLayout(mycontainer, BoxLayout.X_AXIS));
        
        LoopDemo myContentPane2 = new LoopDemo(1, 1);
        myContentPane2.setOpaque(true);
        myContentPane2.setSize(500,500);
        
        LoopDemo myContentPane = new LoopDemo(1, 1);
        myContentPane.setOpaque(true);
        myContentPane.setSize(500,500);
        
        mycontainer.add(myContentPane);
        mycontainer.add(myContentPane2);
        
        //frame2.setContentPane(myContentPane);
        frame2.setContentPane(mycontainer);
        frame2.setTitle("my loop demo");
        frame2.setResizable(false);
        frame2.pack();
        frame2.setVisible(true);
        
    }
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(); 
            }
        });
    }
}