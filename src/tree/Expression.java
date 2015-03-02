package tree;

/**
 * Class for representing simple arithmetic expressions.
 * @author James Park
 * @version Feb 10, 2015
 */
public class Expression {
    Tree<String> expressionTree;
    
    /**
     * Constructs a Tree<String> representing the given arithmetic expression,
     * then verifies that the newly created Tree is valid as an expression.
     * If the Tree is invalid, throws an IllegalArgumentException.<br>
     * Here are the validity rules:<ul>
     * <li>The value of each node must be one of "+", "-", "*", "/",
     *     or a String representing an unsigned integer.</li>
     * <li>If a node has value "+" or "*", it must have two or more children.</li>
     * <li>If a node has value "-" or "/", it must have exactly two children.</li>
     * <li>If a node contains a numeric string, it must be a leaf.</li></ul>
     * Note that the input parameter uses prefix notation, for example:
     * "+ (5 10 -( *(15 20) 25) 30)"
     * @param expression The String representation of the expression to be constructed.
     */
    public Expression(String expression) {
        expressionTree = Tree.parse(expression);
        if (!valid(expressionTree)) {
            throw new IllegalArgumentException("Invalid expression: " + expression);
        }
    }

    
    /**
     * Tests whether the given String input is a number
     * @param input string
     * @return <code>true</code> if the input is a number
     */
    public boolean isNumber(String input){
    	for(int i = 0; i < input.length(); i++){
    		if(!Character.isDigit(input.charAt(i))){
    			return false;
    		}
    	}
    	return true;
    }
    /**
     * Tests whether the given Tree represents a valid Expression.
     * @param tree The input tree.
     * @return <code>true</code> iff the Tree is a valid Expression.
     */
    private boolean valid(Tree<String> tree) {
    	String value = tree.getValue();
    	if(!value.equals("+") && !value.equals("-") && !value.equals("*") && 
        		!value.equals("/") && !isNumber(value)){
        	return false;
        }	
        if(value.equals("+") || value.equals("*")){
           	if(tree.getNumberOfChildren() < 2){
           		return false;
           	}
        }
        if(value.equals("-") || value.equals("/")){
           	if(tree.getNumberOfChildren() != 2){
           		return false;
           	}
        }
        if(isNumber(value)){
        	if(tree.getNumberOfChildren() != 0){
        		return false;
        	}
        }
        for(int i = 0; i < tree.getNumberOfChildren(); i++){
            if(tree.getChild(i) != null){
           		if(!valid(tree.getChild(i))){
           			return false;
           		}
           	}
        }
    	return true;
    }
    
    /**
     * Evaluates this Expression.
     * @return The value of this Expression.
     */
    public int evaluate() {
        return evaluate(expressionTree);
    }
    
    /**
     * Evaluates the given Tree, which must represent a valid Expression.
     * @return The value of this Expression.
     */
    private int evaluate(Tree<String> tree) {
        // Helper method for evaluate()       
    	String value = tree.getValue();
    	int answer = 0;
    	if(value.equals("+") || value.equals("-") || value.equals("/") || value.equals("*")){
    		if(value.equals("+")){
    			answer = 0;
    			for(int i = 0; i < tree.getNumberOfChildren(); i++){
    				answer += evaluate(tree.getChild(i));
    			}
    		}
    		if(value.equals("*")){
    			answer = 1;
    			for(int i = 0; i < tree.getNumberOfChildren(); i++){
    				answer = answer * evaluate(tree.getChild(i));
    			}
    		}
    		if(value.equals("-")){
    			answer = 0;
    			answer = evaluate(tree.getChild(0)) - evaluate(tree.getChild(1));
        	}
    		if(value.equals("/")){
    			answer = 1;
    			answer = evaluate(tree.getChild(0))/evaluate(tree.getChild(1));
        	}
    	}
    	else{
    		answer = Integer.parseInt(value);
    	}
    	return answer;
    }	
    
    /* 
     * @see java.lang.Object#toString()
     * 
     */
    @Override
    public String toString() {
        return toString(expressionTree);
    }
    
    private static String toString(Tree<String> tree) {
        // Helper method for toString()
    	String value = tree.getValue();
    	if(value.equals("+") || value.equals("-") || value.equals("/") || value.equals("*")){
    		String tempString = "(";
    		if(value.equals("+") || value.equals("*")){
    			for(int i = 0; i < tree.getNumberOfChildren(); i++){
    				if(i != tree.getNumberOfChildren() - 1)
    					tempString = tempString + " " + toString(tree.getChild(i)) + " " + value;
    				else{
    					value = tempString + " " + toString(tree.getChild(i)) + " )";
    				}
    			}
    		}
    		else{
   				tempString = tempString + " " + toString(tree.getChild(0)) + " " + value;
   				value = tempString + " " + toString(tree.getChild(1)) + " )";
   			}
    	}
    	return value;
    }	
}