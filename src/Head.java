 import java.util.LinkedList;

// TODO: Auto-generated Javadoc
/**
 * The Class Head.
 */
public class Head {
	
	/** The tape the head reads. */
	LinkedList<String> tape = new LinkedList<String>();
	
	/** The position of the head. */
	int position;
	
	/**
	 * Instantiates a new head.
	 *
	 * @param tape the tape
	 */
	Head(String[] tape){
		setup(tape);
	}
	
	/**
	 * Sets up the tape.
	 *
	 * @param tape the new up
	 */
	void setup(String[] tape) {
		for (String value : tape) {
			this.tape.add(value);
		}
		position = 0;
	}
	
	/**
	 * Moves the head.
	 *
	 * @param direction the direction
	 */
	void move(char direction){
		System.out.println(printTape() + " " + direction);
		if (direction == 'R') {
			position++;
			if((position) >= tape.size())
				tape.add(".");
		}
		else if (direction == 'L') {
			position--;
			if((position) < 0) {
				position++;
				tape.addFirst(".");
			}
		}
		else if (direction != 'S')
			throw new IllegalArgumentException("Illegal tape movement " + direction);
	}
	
	/**
	 * Write.
	 *
	 * @param input the input
	 */
	void write(String input) {
		tape.set(position, input);
	}
	
	/**
	 * Read.
	 *
	 * @return the string
	 */
	String read() {
		return tape.get(position);
		
	}
	
	/**
	 * Prints the tape.
	 *
	 * @return the string
	 */
	String printTape() {
		String result = "";
		for(String value : tape) {
			result+=value;
		}
		return result;
	}
}
