package chairgame;

/**
 * Abstract implementing Player interface  
 */
public abstract class PlayerImp implements Player {

	/**
	 * Player's name.
	 */
	protected String	name;

	/**
	 * PLayer's identity.
	 */
	protected Identity	identity;

	public PlayerImp() {
	}

	public PlayerImp(String name) {
		this.name = name;
	}

	public PlayerImp(Identity identity) {
		this.identity = identity;
	}

	public PlayerImp(String name, Identity identity) {
		this.name = name;
		this.identity = identity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Identity getIdentity() {
		return identity;
	}

	public void setIdentity(Identity identity) {
		this.identity = identity;
	}

	public abstract boolean isInteractive();

	public abstract Operator getOperator(Position position) throws GameException;

}
