/*
 * Author: Brock A. Allton
 * Date: November 4, 2018
 * Purpose: setup Division by zero exception in the event someone wants to try
 * and tear a hole in space-time by dividing by zero
 */
package infixcalculator;


public class DivideByZeroException extends ArithmeticException {
    
    public DivideByZeroException(){
        
    }
        public DivideByZeroException(String message){
        super(message);
    }
    
}
