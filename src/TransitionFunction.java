
// TODO: Auto-generated Javadoc
/**
 * The Class TransitionFunction.
 */
public class TransitionFunction {
	
	/** The current state. */
	private State currentState;
	
	/** The input symbol. */
	private String inputSymbol;
	
	/** The final state. */
	private State outputState;
	
	/** The final stack symbols. */
	private String outputSymbol;
	
	/** The movement. */
	private char movement;
	
	/**
	 * Instantiates a new transition function.
	 *
	 * @param currentState the current state
	 * @param inputSymbol the input symbol
	 * @param outputState the final state
	 * @param outputSymbol the output symbol
	 * @param movement the movement
	 */
	public TransitionFunction(State currentState, String inputSymbol, State outputState, String outputSymbol, char movement){
		this.currentState = currentState;
		this.inputSymbol = inputSymbol;
		this.outputState = outputState;
		this.outputSymbol = outputSymbol;
		this.movement = movement;
	}
	
	/**
	 * Instantiates a new transition function.
	 *
	 * @param function the function
	 */
	public TransitionFunction(TransitionFunction function) {
		this(function.getCurrentState(), function.getInputSymbol(), function.outputState(), function.outputSymbol(), function.movement());
	}

	/**
	 * Movement.
	 *
	 * @return the char
	 */
	protected char movement() {
		// TODO Auto-generated method stub
		return movement;
	}

	/**
	 * Gets the input symbol.
	 *
	 * @return the input symbol
	 */
	protected String getInputSymbol() {
		return inputSymbol;
	}

	/**
	 * Gets the current state.
	 *
	 * @return the current state
	 */
	protected State getCurrentState() {
		return currentState;
	}

	/**
	 * Checks if the function matches the parameters.
	 *
	 * @param currentState the current state
	 * @param inputSymbol the input symbol
	 * @return true, if successful
	 */
	boolean matches(State currentState, String inputSymbol) {
		if(this.currentState.equals(currentState) && this.inputSymbol.equals(inputSymbol))
			return true;
		return false;
	}
	
	/**
	 * Result state.
	 *
	 * @return the state
	 */
	State outputState() {
		return outputState;
	}
	
	/**
	 * Result stack symbol.
	 *
	 * @return the string[]
	 */
	String outputSymbol() {
		return outputSymbol;
	}
	
	/** 
	 * Represents the function as a string.
	 * 
	 * @return the result
	 */
	public String toString() {
		String result = ""+currentState.getName()+" "+inputSymbol+" "+outputState.getName()+" "+outputSymbol;		
		return result;
	}
}
