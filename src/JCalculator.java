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

    public JCalculator(){
        JFrame jfrm = new JFrame("Calculator");
        jfrm.setLayout(new GridLayout(4,4));
        jfrm.setSize(700,700);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.setVisible(true);

        for(int i = 0; i < button.length; i++) {
            //button[i] =
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
