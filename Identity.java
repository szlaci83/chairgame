package chairgame;

/**
 * Enumerator to represent the players.
 */
public enum Identity {

	/**
	 * First player.
	 */
	PLAYER_A(1),

	/**
	 * Second player.
	 */
	PLAYER_B(-1),

	NONE(0);

	/**
	 * The value representing each player.
	 */
	private final int	value;

	/**
	 * @param value The value representing the player.
	 */
	private Identity(int value) {
		this.value = value;
	}

	/**
	 * Returns the value representing the player.
	 *
	 * @return Value representing the player.
	 */
	public int value() {
		return value;
	}

	/**
	 * Returns the opponent of the player.
	 *
	 * @return The opponent.
	 */
	public Identity opponent() {
		switch(this) {
		case PLAYER_A:	return PLAYER_B;
		case PLAYER_B:	return PLAYER_A;
		default:
			break;
		}
		return NONE;
	}

}
