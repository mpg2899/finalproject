package final_project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ControlPanel extends JPanel implements ActionListener {

	ControlPanel(int month, int year) {
		String sMonth = null;
		switch (month) {
		case 0:
			sMonth = "January";
			break;
		case 1:
			sMonth = "February";
			break;
		case 2:
			sMonth = "March";
			break;
		case 3:
			sMonth = "April";
			break;
		case 4:
			sMonth = "May";
			break;
		case 5:
			sMonth = "June";
			break;
		case 6:
			sMonth = "July";
			break;
		case 7:
			sMonth = "August";
			break;
		case 8:
			sMonth = "September";
			break;
		case 9:
			sMonth = "October";
			break;
		case 10:
			sMonth = "November";
			break;
		case 11:
			sMonth = "December";
			break;
		}
		JButton b1 = new JButton(sMonth + " " + year);
		add(b1);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
