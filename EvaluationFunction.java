package chairgame;

/**
 * Gives evaluation for a given game state and player.
 */
public interface EvaluationFunction<P extends Position> {

	/**
	 * Returns the value of the evaluation function for a given game state and player.
	 *
	 * @param position The game state.
	 * @param player The player we want to get the goodness of the game state of.
	 * @return The value of the evaluation function of the game state.
	 */
	public int getValue(P position, Identity player);

}
