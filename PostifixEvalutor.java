package PostifixRelation;
// import all class and objects that are neccessary that isn't included
import java.util.*;

// Scanner object
import java.util.Scanner;

// Stack object
import java.util.Stack;

// import EmptyStackException
import java.util.EmptyStackException;

public class PostfixEvaluator {
    // constants variables
    private final static char ADD      = '+';
    private final static char SUBTRACT = '-';
    private final static char MULTIPLY = '*';
    private final static char DIVIDE = '/';
    private final static char MODULUS = '%';
    private final static char POWER = '^';
    private final static char UNARYMINUS = '~';  
    private final static char FACTORIALS = '!';  
    
    
    // stack variable
    private Stack<Integer> stack;
    
    // PostfixEvaluator constructor
    public PostfixEvaluator() {
        stack = new Stack<Integer>();
    } // end of PostfixEvaluator constructor
    
    int op1, op2, result = 0;
    public int evaluate(String expr) {
        String token = "";
        Scanner parser = new Scanner(expr);

        while (parser.hasNext()) {
            token = parser.next();
            boolean isPostfix = false;
            if (isOperator(token)) {
                op2 = stack.pop();
                op1 = stack.pop();
                result = evaluateSingleOperator(token.charAt(0), op1, op2, isPostfix);
                stack.push(result);
            } // end of if statement
            else if(isUnaryOperator(token)) {
            	  op2 = 0;
                  op1 = stack.pop();
                  result = evaluateSingleOperator(token.charAt(0), op1, op2, isPostfix);
                  stack.push(result);
            }
            else 
                stack.push(new Integer(Integer.parseInt(token)));
        } // end of while loop
        parser.close();
        
        if(stack.size() > 1) {
    		throw new NotEnoughOperationsException();
        }
        return (stack.pop());
    } // end of evaluate method
     
     private boolean isOperator(String token) {
        return ( token.equals("+") || token.equals("-") || 
                 token.equals("*") || token.equals("/") || token.equals("%") || token.equals("^"));
        // OR return ("+-*/".indexOf(token) >= 0);
    } // end of isOperator method
     
     private boolean isUnaryOperator (String token) {
    	 
    	 return (token.equals("~") || token.equals("!"));
     }
    
    private int evaluateSingleOperator(char operation, int op1, int op2, boolean isPostfix) {
        int result = 0;

        switch (operation) {
            case ADD:
                result = op1 + op2;
                break;
                
            case SUBTRACT:
                result = op1 - op2;
                break;
                
            case MULTIPLY:
                result = op1 * op2;
                break;
                
            case DIVIDE:
                result = op1 / op2;
                break;
            case MODULUS:
                result = op1 % op2;
                break;
            case POWER:
                result = (int)Math.pow(op1, op2);
                break;
            case UNARYMINUS: 
                result = op1 + (-op2);
                break;
            case FACTORIALS: 
            	result = 1;
            	for(int i = 1; i <= op1; i++) {
                	result = result * i;
                }
                break;
        } // end of switch statement
        return result;
    } // end of evaluateSingleOperator method 
} // end of PostfixEvaluator class