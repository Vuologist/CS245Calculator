import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

//
//Name:       Vu, Anthony
//Project:    1
//Course:     CS-245-01-w18
//
//Description: This program will implement an integer calculator GUI.
//


public class JCalculator implements ActionListener {

    private JFrame jfrm;
    private JPanel displaySection;
    private JPanel buttonSection;
    private JLabel display;
    private JButton[] button = new JButton[16];
    private JRootPane rootPane;

    private  JCalculator(){
        jfrm = new JFrame("Calculator");
        jfrm.setSize(400,400);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.setVisible(true);

        Border border = BorderFactory.createLineBorder(Color.BLACK,5);
        displaySection = new JPanel();
        displaySection.setLayout(new GridLayout(1,0));
        displaySection.setBorder(new EmptyBorder(0,10,10,10));
        displaySection.setBorder(border);

        buttonSection = new JPanel();
        buttonSection.setLayout(new GridLayout(4,4));
        buttonSection.setBorder(new EmptyBorder(0,10,10,10));

        String[] buttonSym = {"7","8","9","/",
                              "4","5","6","*",
                              "1","2","3","-",
                              "c","0","=","+"};

        display = new JLabel("<html><span style='font-size:40px'>Something</span></html>");
        display.setHorizontalAlignment(JLabel.RIGHT);
        displaySection.add(display);

        for(int i = 0; i < buttonSym.length; i++) {
            button[i] = new JButton(buttonSym[i]);
            button[i].addActionListener(this);
            buttonSection.add(button[i]);
        }

        //set = as default button
        rootPane = jfrm.getRootPane();
        rootPane.setDefaultButton(button[14]);

        //set JCalculator.png as program icon
// ********change path once you submit
        ImageIcon programIcon = new ImageIcon("C:\\Users\\antho\\Documents\\IntelliJ_GitHub\\CS245Calculator\\src\\JCalculator.png");
        jfrm.setIconImage(programIcon.getImage());

        jfrm.add(displaySection, BorderLayout.NORTH);
        jfrm.add(buttonSection);
        jfrm.setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent ae) {

    }

    public static void main(String args[]){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {new JCalculator();}
        });
    }
}
