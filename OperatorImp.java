package chairgame;

/**
 * abstract class implementing the Operator interface
 */ 
public abstract class OperatorImp implements Operator {

	public int getCost(Position position) {
		return 1;
	}

	public Position applyTo(Position position) {
		return applyTo(this, position);
	}

	public boolean isApplicableTo(Position position) {
		return isApplicableTo(this, position);
	}

	/**
	 * Applying the operator for a given state.
	 *
	 * @param operator The operator to be applied.
	 * @param position The game state which the operator to be applied.
	 * @return The new game state created by applying the operator.
	 */
	public static Position applyTo(Operator operator, Position position) {
		Position	newPosition = position.clone();
		newPosition.apply(operator);
		return newPosition;
	}

	/**
	 * Returns if the operator can be applied to a given game state.
	 *
	 * @param operator The operator to be checked.
	 * @param position The game state to be checked on.
	 * @return <code>true</code>, if the operator can be applied to a given state, <code>false</code>
	 * otherwise.
	 */
	public static boolean isApplicableTo(Operator operator, Position position) {
		return position.isApplicable(operator);
	}

}
