package final_project;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PanelTest extends JFrame {

    Container contentPane;

    public PanelTest()  {
       super("Changing JPanel inside a JFrame");
       contentPane=getContentPane();
    }

    public void createChangePanel() {
       contentPane.removeAll();
       JPanel newPanel=new JPanel();
       contentPane.add(newPanel);
       System.out.println("new panel created");//for debugging purposes
       validate();
       setVisible(true);
    }
}