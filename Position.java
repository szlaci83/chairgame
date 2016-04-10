package chairgame;

import java.util.Collection;

/**
 * Interface to create two-person games.
 */
public interface Position extends Cloneable {

	/**
	 * Returns the next player.
	 *
	 * @return The next player.
	 */
	public Identity getNext();

	/**
	 * Returns if the game ended.
	 *
	 * @return <code>true</code>, if the game ended,<code>false</code> otherwise.
	 */
	public boolean isTerminal();

	/**
	 * At the end of the game it returns the winner of the game.
	 *
	 * @return The object representing the winning player, or NONE if the game finished with a draw.
	 *  The return value depends on implementation, if the game has not ended.
	 */
	public Identity getWinner();

	/**
	 * Applying the given move on the game state.
	 *
	 * @param operator The object representing a move.
	 */
	public void apply(Operator operator);

	/**
	 * Returns if the given move can be applied to the game state.
	 *
	 * @param operator The object representing the move.
	 * @return <code>true</code>, if the pre-condition of the application of the operator is met, <code>false</code> otherwise.
	 */
	public boolean isApplicable(Operator operator);

	/**
	 * Returns the applicable operators on the game state as a list.
	 *
	 * @return The list of operators can be applied on the game state.
	 */
	public Collection<? extends Operator> operators();

	/**		
 	 * Creating a copy of the actual game state.
	 *
	 * @return Reference for the newly created object.
	 */
	public Position clone();

}
