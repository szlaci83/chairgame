package chairgame;

/**
 * Interface to be implemented by the operator classes.
 */
public interface Operator {

	/**
	 * Applying the operator for a given state.
	 *
	 * @param pos The game state which the operator to be applied.
	 * @return The new game state created by applying the operator.
	 */
	public Position applyTo(Position pos);

	/**
	 * Returns if the operator can be applied to a given game state.
	 *
	 * @param pos The game state to be checked.
	 * @return <code>true</code>, if the operator can be applied to a given state, <code>false</code>
	 * otherwise.
	 */
	public boolean isApplicableTo(Position pos);

}
