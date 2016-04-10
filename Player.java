package chairgame;

/**
 * Interface to create classes representing players.
 */
public interface Player {

	/**
	 * Returns the name of the player
	 *
	 * @return The player's name.
	 */
	public String getName();

	/**
	 * Sets the player's name
	 *
	 * @param name The player's name.
	 */
	public void setName(String name);

	/**
	 * Returns the player's identity.
	 *
	 * @return The identity the player is playing.
	 */
	public Identity getIdentity();

	/**
	 * Sets the player's identity.
	 *
	 * @param identity The identity the player to be set.
	 */
	public void setIdentity(Identity identity);

	/**
	 * Returns if the player plays interactively. (Machine players do not play interactively.)
	 *
	 * @return <code>true</code> if the player plays interactively <code>false</code> otherwise.
	 */
	public boolean isInteractive();

	/**
	 * Returns the player's next move in a given game state.
	 *
	 * @param position The game state where the player needs to move.
	 * @return The player's move in the given game state, or <code>null</code>, if next step can not be defined.
	 * @throws GameException If there is an error during defining the next step.
	 */
	public Operator getOperator(Position position) throws GameException;

}
