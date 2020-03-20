/*
 * Author: Brock A. Allton
 * Date: November 4, 2018
 * Purpose: setup the Stack class that will be used to store operators and 
 * operands. 
 */
package infixcalculator;

    import java.util.*;
    import javax.swing.JOptionPane;

public class Stack<E> {
    
    private ArrayList<E> elements; 
    private int numElements; 
    private E topElement;
    
    public Stack(){
        elements = new ArrayList<>();
        numElements = 0;
    }
    
    //Check to see if Stack contains elements, return true if empty
    public boolean isEmpty(){
        if (numElements == 0){
            return true;
        }
        else{
            return false;
        }
    }
    
    //Get the size of the elements
    public int getSize(){
        return this.numElements;
    }
    
    //Add new element to the top of the Stack
    public void push (E item){
        elements.add(item);
        numElements++;
    }
    
    //Remove the top element of the stack
    public E pop (){
        if (numElements == 0){
            JOptionPane.showMessageDialog(null, "There are no elements in the "
                    + "Stack");
           
        }
        else{
            topElement=elements.get(elements.size()-1);
            elements.remove(elements.size()-1);
            numElements--;
            
        }
        return topElement;
    }
    
    //Looks at the top element without removing
    public E peek(){
        if(numElements == 0){
            JOptionPane.showMessageDialog(null, "There are no elements in the "
                    + "Stack");
            return null;
        }
        else{
            topElement=elements.get(elements.size()-1);
        }
        return topElement;
    }
    
}// End public class Stack<E>
