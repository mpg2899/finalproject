package final_project;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MyFrame extends JFrame {
	Container contentPane;
	
	public void MyFrame() {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = getContentPane();
	}
	
	public void createChangePanel() {
		int n = JOptionPane.showConfirmDialog(
        	    null,
        	    "Would you like to see screen 2?",
        	    "An Inane Question",
        	    JOptionPane.YES_NO_OPTION);
	}

}
