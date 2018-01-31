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
    private final Set<Integer> pressed = new HashSet<Integer>();
    private String operand1="";
    private String operator = "";
    private String operand2="";
    //if true op1, false op2
    private boolean operandSwitch = true;
    private boolean buttonOn = true;
    private boolean butCombo = false;

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

        //set JCalculator.png as program icon
// ********change path once you submit
        ImageIcon programIcon = new ImageIcon("C:\\Users\\antho\\Documents\\IntelliJ_GitHub\\CS245Calculator\\src\\JCalculator.png");
        jfrm.setIconImage(programIcon.getImage());

        jfrm.add(displaySection, BorderLayout.NORTH);
        jfrm.add(buttonSection);
        jfrm.setLocationRelativeTo(null);

        //set = as default button
        rootPane = jfrm.getRootPane();
        rootPane.setDefaultButton(button[14]);

        //set mnemonic
        button[12].setMnemonic('c');


        buttonSection.addKeyListener(this);
        buttonSection.setFocusable(true);
        buttonSection.requestFocusInWindow();

        //solve nonfocusable issue
        for(int i = 0; i < buttonSym.length; i++) {
            button[i].setFocusable(false);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        pressed.add(e.getKeyCode());
        //System.out.println(e.getKeyCode());
        if(pressed.size() > 1) {
            //System.out.println("hit it");
            butCombo = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
            pressed.remove(e.getKeyCode());
            if(pressed.size() == 0){
                //System.out.println("removed");
            }
    }

    //if true op1, false op2
    public void actionPerformed(ActionEvent ae) {
        String value = ae.getActionCommand();
        if (value.equals("c") && butCombo==true) {
            printToDisplay("(c) 2018 Anthony Vu");
            butCombo=false;
            disableButtons();
        } else if(value.equals("c")) {
            resetCalculator();
        } else if(value.equals("/") || value.equals("*") ||
           value.equals("-") ||value.equals("+")) {
            operandSwitch = false;
            operator = value;
            //System.out.println(operandSwitch);
        } else if (value.equals("=")){
            printToDisplay(getAnswer());
        } else if(operand1.length() < 10 && operandSwitch==true) {
            operand1 += value;
            printToDisplay(operand1);
        } else if(operand2.length() < 10 && operandSwitch==false) {
            operand2 += value;
            printToDisplay(operand2);
        }
    }

    private Long operation(){
        switch (operator) {
            case "/":
                try{
                    System.out.println(operand1);
                    return Long.valueOf(operand1) / Long.valueOf(operand2);
                } catch (ArithmeticException a){
                    printToDisplay("Div by 0");
                    disableButtons();
                }
                return Long.valueOf(operand1) / Long.valueOf(operand2);
            case "*":
                return Long.valueOf(operand1) * Long.valueOf(operand2);
            case "-":
                return Long.valueOf(operand1) - Long.valueOf(operand2);
            case "+":
                return Long.valueOf(operand1) + Long.valueOf(operand2);
            default:
                System.out.println("Error in switch statement");
                return Long.valueOf(0);
        }
    }

    private String getAnswer() {
        Long value = operation();
        System.out.println(value + " = " + operand1 + " " + operator + " " + operand2);
        resetCalculator();
        operand1 = Long.toString(value);
        if(operand1.length() > 10) {
            disableButtons();
            return "Overflow";
        }
        return operand1;
    }

    private void disableButtons() {
        buttonOn = false;
        for(int i = 0; i < button.length; i++) {
            if(i != 12)
                button[i].setEnabled(false);
        }
    }

    private void enableButtons() {
        buttonOn = true;
        for(int i = 0; i < button.length; i++)
            button[i].setEnabled(true);
    }

    private void resetCalculator() {
        if(buttonOn == false)
            enableButtons();

        operand1 = "";
        operator = "";
        operand2 = "";
        operandSwitch = true;
        printToDisplay("0");
    }

    private void printToDisplay(String value) {
        display.setText("<html><span style='font-size:40px'>" + value + "</span></html>");
    }

    public static void main(String args[]){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {new JCalculator();}
        });
    }

}
