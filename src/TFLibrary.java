import java.util.Vector;

// TODO: Auto-generated Javadoc
/**
 * The Class TFLibrary.
 */
public class TFLibrary {

	/** The library. */
	private Vector<TransitionFunction> library = new Vector<TransitionFunction>();

	/**
	 * Instantiates a new TF library.
	 */
	TFLibrary() {
	}

	/**
	 * Instantiates a new TF library.
	 *
	 * @param library
	 *            the library
	 */
	TFLibrary(TFLibrary library) {
		this.library.addAll(library.getLibrary());
	}

	/**
	 * Adds a new transition function.
	 *
	 * @param TFsymbols
	 *            the function's symbols
	 */
	void add(String[] TFsymbols) {
		library.add(
				new TransitionFunction(new State(TFsymbols[0]), TFsymbols[1], new State(TFsymbols[2]), TFsymbols[3], TFsymbols[4].charAt(0)));
	}

	/**
	 * Viable functions.
	 *
	 * @param currentState            the current state
	 * @param nextInputSymbol            the next input symbol
	 * @return the vector
	 */
	Vector<TransitionFunction> viableFunctions(State currentState, String nextInputSymbol) {
		Vector<TransitionFunction> result = new Vector<TransitionFunction>();
		for (TransitionFunction function : library) {
			if (function.matches(currentState, nextInputSymbol)) {
				TransitionFunction matchingFunction = new TransitionFunction(function);
				result.add(matchingFunction);
			}
		}
		return result;
	}

	/**
	 * Gets the library.
	 *
	 * @return the library
	 */
	protected Vector<TransitionFunction> getLibrary() {
		return library;
	}
}
