/*
 * Author: Brock A. Allton
 * Date: November 4, 2018
 * Purpose: setup a GUI interface to input an infix expression for evaluation 
 * using Stacks
 */
package infixcalculator;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class InfixCalculator extends JFrame {
    
    private final JFrame displayFrame;
    private final JPanel topPanel, centerPanel, bottomPanel;
    private final JLabel expressionLabel, resultLabel;
    private final JTextField expressionField, resultField;
    private final JButton evaluateButton;
    private static final int WIDTH = 300, HEIGHT = 150;
    
    public InfixCalculator() {
    
        //Set up the frame parameters to start accepting the panels
        displayFrame = new JFrame();
        displayFrame.setTitle ("Infix Expression Evaluator");  
        displayFrame.setSize (WIDTH, HEIGHT); 
        displayFrame.setResizable (false); 
        
        //Set so window opens in the middle of the monitor screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int w = getSize().width;
        int h = getSize().height;
        int x = (dim.width-w)/2;
        int y = (dim.height-h)/2;
        setLocation(x,y);
        
         //Close window upon exiting the GUI
        displayFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //setup the top panel of the GUI whcih will contain the Expression 
        //label and the expression field to input the infix expression
        topPanel = new JPanel();
        expressionLabel = new JLabel ("Enter Infix Expression");
        expressionField = new JTextField(); 
        expressionField.setPreferredSize(new Dimension(250,20));
        topPanel.add(expressionLabel,BorderLayout.WEST);
        topPanel.add(expressionField, BorderLayout.EAST);
        
        //Setup the central panel that will hold the Evaluate button
        centerPanel = new JPanel();
        evaluateButton = new JButton ("Evaluate");
        evaluateButton.addActionListener (new ButtonListener());
        centerPanel.add(evaluateButton, BorderLayout.CENTER);
        
        //Setup the bottom panel which will contain the Result label and the 
        //result field to show the output of the evaluation
        bottomPanel = new JPanel();
        resultLabel = new JLabel ("Result");
        resultField = new JTextField();
        resultField.setEditable(false);
        resultField.setPreferredSize(new Dimension (150,20));
        bottomPanel.add(resultLabel, BorderLayout.WEST);
        bottomPanel.add(resultField, BorderLayout.EAST);
        
        //Add all 3 panels to the displayFrame
        displayFrame.add(topPanel, BorderLayout.NORTH);
        displayFrame.add(centerPanel, BorderLayout.CENTER);
        displayFrame.add(bottomPanel, BorderLayout.SOUTH);
        
        //Pack the frame, set to open in the middle of the monitor, and set 
        //visible
        displayFrame.setLocationRelativeTo(null);
        displayFrame.pack();
        displayFrame.setVisible(true);
    
}

    private class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed (ActionEvent e){
            
            Object source = e.getSource();
            String expression = expressionField.getText();
            int result;
            
            if(source == evaluateButton){
                try{
                    result=InfixEvaluation.evaluate(expression);
                    resultField.setText(String.valueOf(result));
                }
                catch(DivideByZeroException e1){
                    JOptionPane.showMessageDialog(null, "Error!\nYou Cannot "
                            + "Divide By Zero!\nRip in Space/Time!");
                    e1.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
        new InfixCalculator();
    }
    
}
