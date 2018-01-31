import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

//
//Name:       Vu, Anthony
//Project:    1
//Course:     CS-245-01-w18
//
//Description: This program will implement an integer calculator GUI.
//


public class JCalculator implements ActionListener, KeyListener {

    private JFrame jfrm;
    private JPanel displaySection;
    private JPanel buttonSection;
    private JLabel display;
    private JButton[] button = new JButton[16];
    private JRootPane rootPane;
    private final Set<Character> pressed = new HashSet<Character>();
    private String operand1="";
    private String operator = "";
    private String operand2="";
    //if true op1, false op2
    private boolean operandSwitch = true;

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

        display = new JLabel();
        printToDisplay("0");
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

    //if true op1, false op2
    public void actionPerformed(ActionEvent ae) {
        String value = ae.getActionCommand();
        if(value.equals("c")) {
            resetCalculator();
        } else if(value.equals("/") || value.equals("*") ||
           value.equals("-") ||value.equals("+")) {
            operandSwitch = false;
            operator = value;
            //System.out.println(operandSwitch);
        } else if (value.equals("=")){
            int sum = operation();
            System.out.println(sum + " = " + operand1 + " " + operator + " " + operand2);
            operand1 = Integer.toString(sum);
            printToDisplay(operand1);
        } else if(operand1.length() < 10 && operandSwitch==true) {
            operand1 += value;
            printToDisplay(operand1);
        } else if(operand2.length() < 10 && operandSwitch==false) {
            operand2 += value;
            printToDisplay(operand2);
        }

    }



    private int operation(){
        switch (operator) {
            case "/":
                return Integer.parseInt(operand1) / Integer.parseInt(operand2);
            case "*":
                return Integer.parseInt(operand1) * Integer.parseInt(operand2);
            case "-":
                return Integer.parseInt(operand1) - Integer.parseInt(operand2);
            case "+":
                return Integer.parseInt(operand1) + Integer.parseInt(operand2);
            default:
                System.out.println("Error in switch statment");
                return 0;
        }
    }

    private void resetCalculator() {
        operand1 = "";
        operator = "";
        operand2 = "";
        operandSwitch = true;
        printToDisplay("0");
    }

    private void printToDisplay(String value) {
        display.setText("<html><span style='font-size:40px'>" + value + "</span></html>");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //not needed
    }

    @Override
    public void keyPressed(KeyEvent e) {
        pressed.add(e.getKeyChar());
        if(pressed.size() > 1){
            //do shit
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressed.remove(e.getKeyChar());
    }

    public static void main(String args[]){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {new JCalculator();}
        });
    }
}
