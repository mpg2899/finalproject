package final_project;

import java.sql.SQLException;


public class StartGui {

	public static void createAndShowGUI(){

		MainFrame mainFrame = new MainFrame();
        mainFrame.pack();
    	mainFrame.setVisible(true);
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