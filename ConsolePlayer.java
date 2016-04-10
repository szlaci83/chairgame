package chairgame;

/**
 * Class to represent a human player.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsolePlayer extends PlayerImp {
	private BufferedReader	in = new BufferedReader(new InputStreamReader(System.in));

	public ConsolePlayer(Identity identity) {
		super(identity);
	}

	public ConsolePlayer(String name, Identity identity) {
		super(name, identity);
	}

	/**
	 * Always returns True as human players are interactive.
	 */
	public boolean isInteractive() {
		return true;
	}

	/**
	 * Method to get the operator from console input, and create an Operator object.
	 * 
	 * 	@param The position the operator is needed for.
	 *  @return Operator object created from user input.
	 */
	public Operator getOperator(Position position) throws GameException {
		if (position.getNext() != getIdentity()) throw new GameException("It is not " + getIdentity() + "'s turn");
		if (position.isTerminal()) throw new GameException("Game over");
		String	line = null;
		try {
			if ((line = in.readLine()) == null) return null;

			Sit	op;

			int i=(Integer.parseInt(line));
			if ((i>=1) && (i<=ChairGame.NUMBER_OF_CHAIRS)){	
				op = new Sit(i-1);
			}
			else throw new GameException("Invalid move");
			if (! ((ChairGame) position).isApplicable(op)) throw new GameException("Invalid move");
			return (Operator)op;

		}catch(IOException e) {
			throw new GameException("I/O Error", e);
		} catch(NumberFormatException e) {
			throw new GameException("Invalid move");
		}	
	}
}
