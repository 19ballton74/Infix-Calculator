/*
 * Author: Brock A. Allton
 * Date: November 4, 2018
 * Purpose: setup the evaluation class that will run the calcularion on the 
 * infix expression provided in the InfixCalculator class.
 */
package infixcalculator;

import java.util.*;
import javax.swing.JOptionPane;

public class InfixEvaluation {
    
    //Stack for numbers
    private static Stack <String> numberStack = new Stack <>();
    //Stack for operators (+-/*)
    private static Stack <String> operatorStack = new Stack <>();
   
    public static int evaluate(String expression) throws DivideByZeroException{
        numberStack = new Stack<>();
        operatorStack = new Stack<>();
        int evaluation = 0;
        
        //Read the input and remove white spaces
        String readExpression = expression;
        readExpression = readExpression.replaceAll(" ","");
        
        //Tokenize the expression that was read in and use +,-,* etc as 
        //delimiters
        StringTokenizer tokens = new StringTokenizer(readExpression,"+-*/()", 
                true );
                
        //While there are still tokens to be read, get the next one
        while(tokens.hasMoreTokens()){
            String token = tokens.nextToken();
            
            //if it's an operand, push it onto the numStack. If the token is not
            //an opertoar (+-*/), then assume it is an operand
            if(!token.equals("+")&&!token.equals("-")&&!token.equals("*")&&
                    !token.equals("/")&&!token.equals("(")&&!token.equals(")")){
                numberStack.push(token);
            }
            
            //If token is a "(", push it onto the opStack
            else if(token.equals("(")){
                operatorStack.push(token);
            }
            
            //If the token is a ")", and top of opStack does contain a "(", pop
            //one opStack two from the numStack and run the calculation
            else if(token.equals(")")){
                while(!operatorStack.peek().equals("(")){
                    
                    //holders for the first and second tokens from numStack
                    int a = 0;
                    int b = 0;
                    String operator = "";//holder for operator from opStack
                    
                    //Parse a and b into an int from a String
                    a = Integer.parseInt(numberStack.pop());
                    b = Integer.parseInt(numberStack.pop());
                    operator = operatorStack.pop();
                    
                    //Perform the calculation, convert back to a String and push
                    //it on numStack
                    int calculation = calcExpression(a, b, operator);
                    numberStack.push(Integer.toString(calculation));
                }//End while loop
                
                //pop the "(" off the stack
                operatorStack.pop();
            }//End else-if loop
            
            //If the token is an operator
            else if(token.equals("+")||token.equals("-")||token.equals("*")||
                    token.equals("/")){
                //while opStack is not empty and the operator at the top of 
                //numStack has higher or same precedence than the curent operator
                while(operatorStack.isEmpty()== false && 
                        setPrecedence(operatorStack.peek(),
                         token) == true){
                    //pop off two from numStack and one from opStack
                    //create holders for first and second number from numStack
                    int a = 0;
                    int b = 0;
                    String operator = "";//hold operator popped from opStack
                    
                    //parse a and b into int from String after popping from numStack
                    //then pop operator from opStack
                    a = Integer.parseInt(numberStack.pop());
                    b = Integer.parseInt(numberStack.pop());
                    operator = operatorStack.pop();
                    //Do calculation and push it onto the numStack after converting
                    //back to String
                    int calculation = calcExpression(a, b, operator);
                    numberStack.push(Integer.toString(calculation));              
                }//End while loop           
                operatorStack.push(token);
            }//End else-if loop
                           
        }//End while has more tokens
        
        //While the opStack is not Empty
        while(operatorStack.isEmpty()==false){
            //pop off two operands and one opertor
            //create holders and do conversions as above
            int a = 0;
            int b = 0;
            String operator = "";
            
            a = Integer.parseInt(numberStack.pop());
            b = Integer.parseInt(numberStack.pop());
            operator = operatorStack.pop();
            
            int calculation = calcExpression(a, b, operator);
            numberStack.push(Integer.toString(calculation));
        }
        evaluation = Integer.parseInt(numberStack.pop());  
        return evaluation;
    }//End public evaluate
    
    public static int calcExpression(int a, int b, String operator) 
                throws DivideByZeroException{
            int calcResult = 0;
            if(operator.equals("+")){
                calcResult = b+a;
            }
            else if(operator.equals("-")){
                calcResult = b-a;
            }
            else if(operator.equals("*")){
                calcResult = b*a;
            }
            else if(operator.equals("/")){
                if(a == 0){
                    throw new DivideByZeroException ("Cannot Divide By Zero!"
                        + " Tear in Space/Time Resulting!");
                }
                else{
                    calcResult = b/a;
                }
            }
            else{
                 JOptionPane.showMessageDialog(null, "You must input a valid"
                         + "opertor! Examples: '+', '-', '*', '/'");
            }
            return calcResult;
            
    }//End public calcExpression
    
    public static boolean setPrecedence (String topOp, String currentOp){
        if(topOp.equals("*")||(topOp.equals("/"))){
            return true;
        }
        else if(currentOp.equals("+")||currentOp.equals("-")){
            return false;
        }
        else if(topOp.equals("+")||topOp.equals("-")&&currentOp.equals("*")
                ||currentOp.equals("/")){
            return false;
        }
        return false;
    }//End public boolean setPrecedence
    
}//End class InfixEvaluation

    