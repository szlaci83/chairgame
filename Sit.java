package chairgame;
/**
 * Operator we can use in the chairgame to sit a person on a chair.
 *
 */

public class Sit extends OperatorImp{
	protected int	i;
	
	
	public Sit(int i) {
		this.i = i;
	}
	
	public int getI() {
		return i;
	}
	
	public void setI(int i) {
		this.i = i;
	}
	
	/**
	 * Apply the operator to a given position.
	 *
	 * @param position to apply the operator on.
	 * @return the new position, after the operator applied. 
	 */
	public Position applyTo(Position pos) {
		return OperatorImp.applyTo(this, pos);
	}
     
	/**
	 * Returns true if the operator can be applied on a given gamestate.
	 * 
	 * @param position we want to know if the operator can be applied.
	 * @return <code>true</code> if the operator can be applied, <code>false</code> otherwise.
	 */
	public boolean isApplicableTo(Position pos) {
		return OperatorImp.isApplicableTo(this, pos);
	}	
	
	@Override
	public String toString() {
		return new StringBuilder().append("(").append(i+1).append(")").toString();
	}
}
