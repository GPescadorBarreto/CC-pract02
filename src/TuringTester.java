import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

// TODO: Auto-generated Javadoc
/**
 * The Class AutomatonTester.
 */
public class TuringTester {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws IOException {
		if (args.length != 2 && args.length != 3)
			throw new IllegalArgumentException("Incorrect program arguments, check documentation");

		TuringMachine turingMachine = new TuringMachine(new File(args[0]));

		String[] input = null;
		if (args[1].equals("y")) {
			System.out.println("Insert all your input tape, separated by spaces:");
			Scanner scan = new Scanner(System.in);
			String line = scan.nextLine();
			scan.close();
			input = line.split("\\s+");
		} else if (args[1].equals("n")) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(new File(args[2])));
				input = br.readLine().split("\\s+");
				br.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else
			throw new IllegalArgumentException(
					"Incorrect program arguments. To setup input tape from console, write 'y', and 'n' if you want it to be read from a file. Argument used: "
							+ args[1]);
		if (!input[0].isEmpty())
			turingMachine.SetupInput(input);
		if (turingMachine.Execute())
			System.out.println("The machine stopped in a final state. Resulting tape: " + turingMachine.printTape());
		else
			System.out.println("The machine didn't function properly and stopped abruptly. Input may not be correct.");
	}

}
