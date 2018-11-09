import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

// TODO: Auto-generated Javadoc
/**
 * The Class TuringMachine.
 */
public class TuringMachine {

	/** The automaton stack. */
	Head tmHead;

	/** The input alphabet. */
	Alphabet inputAlphabet;

	/** The output alphabet. */
	Alphabet outputAlphabet;

	/** The total alphabet. */
	Alphabet totalAlphabet;

	/** The white space. */
	String whiteSpace;

	/** The state set. */
	StateSet stateSet;

	/** The transition functions. */
	TFLibrary transitionFunctions = new TFLibrary();

	/** The Constant MOVESYMBOLS. */
	static final List<String> MOVESYMBOLS = Arrays.asList("R", "L", "S");

	/**
	 * Instantiates a new turing machine.
	 *
	 * @param configPath
	 *            the config path
	 */
	TuringMachine(File configPath) {
		try (BufferedReader br = new BufferedReader(new FileReader(configPath))) {
			String line = br.readLine();
			String[] symbols;
			while (line.startsWith("#"))
				line = br.readLine();
			stateSet = new StateSet(line);
			line = br.readLine();
			symbols = line.split("\\s+");
			inputAlphabet = new Alphabet(symbols);
			line = br.readLine();
			symbols = line.split("\\s+");
			outputAlphabet = new Alphabet(symbols);
			totalAlphabet = new Alphabet(inputAlphabet);
			totalAlphabet.union(outputAlphabet);
			line = br.readLine();
			symbols = line.split("\\s+");
			if (symbols.length > 1)
				throw new IllegalArgumentException("There's more than one initial state");
			else if (stateSet.contains(symbols[0]))
				stateSet.setCurrentState(symbols[0]);
			else
				throw new IllegalArgumentException("Initial state " + symbols[0] + " isn't part of the state set");
			line = br.readLine();
			symbols = line.split("\\s+");
			if (symbols.length > 1)
				throw new IllegalArgumentException("There's more than one whitespace symbol");
			else
				whiteSpace = symbols[0];
			line = br.readLine();
			symbols = line.split("\\s+");
			for (String symbol : symbols) {
				stateSet.setFinalState(symbol);
			}
			while ((line = br.readLine()) != null) {
				symbols = line.split("\\s+");
				if ((symbols.length - 3) % 2 != 0)
					throw new IllegalArgumentException("Incorrect number of transition function elements");
				else if (stateSet.contains(symbols[0]) && totalAlphabet.contains(symbols[1])
						&& stateSet.contains(symbols[2]) && outputAlphabet.contains(symbols[3])
						&& MOVESYMBOLS.contains(symbols[4]))
					transitionFunctions.add(symbols);
				else
					throw new IllegalArgumentException("Incorrect symbols in transition functions");
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.printf("File not found, check path.");
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.out.printf("Config file not formatted correctly");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Setup input.
	 *
	 * @param input
	 *            the input
	 */
	public void SetupInput(String[] input) {
		tmHead = new Head(input);
	}

	/**
	 * Execute.
	 *
	 * @return true, if successful
	 */
	public boolean Execute() {
		Vector<TransitionFunction> possibilities;
		while (!stateSet.getCurrentState().isFinal()) {
			possibilities = transitionFunctions.viableFunctions(stateSet.getCurrentState(), tmHead.read());
			if (possibilities.size() == 0)
				return false;
			performFunction(possibilities.firstElement());
		}
		return true;
	}

	/**
	 * Perform function.
	 *
	 * @param function
	 *            the transition function
	 */
	private void performFunction(TransitionFunction function) {
		stateSet.setCurrentState(function.outputState());
		tmHead.write(function.outputSymbol());
		tmHead.move(function.movement());
	}
	
	/**
	 * Prints the tape.
	 *
	 * @return the string
	 */
	public String printTape() {
		return tmHead.printTape();
	}
}
