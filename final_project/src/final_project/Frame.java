package final_project;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame {

    private Container contain;
    private JPanel reChange,reChange2;
    private JButton reChangeButton;

    public Frame() {
        super("Change a panel");
        setSize(350, 350);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        reChange = new JPanel(null);
        reChange.setBackground(Color.red);
        reChange.setSize(240, 225);
        reChange.setBounds(50, 50, 240, 225);
        add(reChange);

        reChangeButton = new JButton("Change It");
        reChangeButton.setBounds(20, 20, 100, 20);
        add(reChangeButton);

        reChangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("in");
                contain = getContentPane();
                contain.removeAll();
                //System.out.println("in2");

                reChange2 = new JPanel(null);
                reChange2.setBackground(Color.white);
                reChange2.setSize(240, 225);
                reChange2.setBounds(50, 50, 240, 225);
                //System.out.println("in3");

                contain.add(reChange2);
                contain.validate();
                //System.out.println("in4");
                setVisible(true);
                //System.out.println("in5");
            }
        });

    }

    public static void main(String[] args) {
        Frame frame = new Frame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}