package final_project;


public class StartGui {

	public static void createAndShowGUI() {

		MainFrame mainFrame = new MainFrame();
		//MyFrame2 mainFrame = new MyFrame2();
	/*	LoopDemo myContentPane2 = new LoopDemo();
        myContentPane2.setOpaque(true);
        myContentPane2.setSize(500,500);
        */
       // mainFrame.add(myContentPane2);
		//mainFrame.setResizable(false);
        mainFrame.pack();
    	mainFrame.setVisible(true);
    	//mainFrame.createChangePanel();
    	// 
    	//mainFrame.createPanel();
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