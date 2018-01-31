import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//
//Name:       Vu, Anthony
//Project:    1
//Course:     CS-245-01-w18
//
//Description: This program will implement an integer calculator GUI.
//


public class JCalculator implements ActionListener {

    JButton button[] = new JButton[16];
    JLabel display = new JLabel();

    public JCalculator(){
        JFrame jfrm = new JFrame("Calculator");
        jfrm.setLayout(new GridLayout(4,4));
        jfrm.setSize(400,400);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.setVisible(true);

        String[] buttonSym = {"7","8","9","/",
                            "4","5","6","*",
                            "1","2","3","-",
                            "c","0","=","+"};

        display = new JLabel("something");
        display.setLayout(new GridLayout(1,4));
        display.setHorizontalAlignment(JLabel.RIGHT);
        jfrm.add(display,BorderLayout.NORTH);

        for(int i = 0; i < button.length; i++) {
            button[i] = new JButton(buttonSym[i]);
            button[i].addActionListener(this);
            jfrm.add(button[i]);
        }
    }

    public void actionPerformed(ActionEvent ae) {

    }

    public static void main(String args[]){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {new JCalculator();}
        });
    }
}
