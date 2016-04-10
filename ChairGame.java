package chairgame;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class to represent a game state.
 * 
 * @author Laszlo Szoboszlai
 *
 */
public class ChairGame implements Position {


	/**
	 * The default number of chairs
	 */
	public static final int	NUMBER_OF_CHAIRS = 14;
	/**
	 * The byte array storing the numbers representing the chairs.
	 */
	private byte[] chairs;

	private Identity	player = Identity.PLAYER_A;

	/**
	 * Constructor to create the game with default number of chairs.
	 */
	ChairGame(){
		chairs = new byte[NUMBER_OF_CHAIRS];
	}

	/**
	 * Returns the byte array storing the values representing the chairs.
	 * 
	 * @return byte array representing chairs.
	 */
	public byte[] getChairs(){
		return chairs;
	}

	public Identity getNext() {
		return player;
	}

	/**
	 * Checks if there is any possible move left.
	 * 
	 * @return <code>true</code> if there is a possible move left <code>false</code> otherwise.
	 */
	public boolean isTerminal() {
		return this.operators().isEmpty();
	}

	/**
	 * Returns the winner of the game.
	 * 
	 * @return the winner, the opponent of the actual player.
	 */
	public Identity getWinner() {
		return player.opponent();
	}

	/**
	 * Helper method to clone ChairGame objects.
	 * 
	 * @return a copy of the object.
	 */
	public ChairGame clone() { 
		ChairGame copy = null;
		try {
			copy = (ChairGame) super.clone();
		} catch (CloneNotSupportedException cne) {}

		copy.chairs = chairs.clone();		

		return copy;
	}

	/**
	 * Returns a specific chair's value from the ring of chairs.
	 * 
	 * @param i The index of the chair we need the value of.
	 * @return The value of the chair.
	 */
	public byte get(int i){
		return chairs[i];
	}

	/**
	 * Returns the value of the chair on the left of a given chair.
	 * 
	 * @param i The index of the chair we need the value of the chair on the left. 
	 * @return The chair's value on the left of the given chair.
	 */
	public int getLeft( int i ){
		if ( i==0 ) { return chairs[NUMBER_OF_CHAIRS-1]; }  	
		return chairs[ i - 1 ];
	} 

	/**
	 * Returns the value of the chair on the right of a given chair.
	 * 
	 * @param i The index of the chair we need the value of the chair on the left. 
	 * @return The chair's value on the right of the given chair.
	 */
	public int getRight( int i ){
		if ( i == NUMBER_OF_CHAIRS-1 ) {return chairs[0];}  	
		return chairs[ i + 1 ];
	}

	/**
	 * Returns the value of the chair which is second on the left of a given chair.
	 * 
	 * @param i The index of the given chair. 
	 * @return The value of the chair which is second on the left.
	 */
	public int getSecondLeft(int i){
		if ( i==1 ) {return chairs[NUMBER_OF_CHAIRS-1];}  	
		if ( i==0 ) {return chairs[NUMBER_OF_CHAIRS-2];} 
		return chairs[i-2];
	} 

	/**
	 *Returns the value of the chair which is second on the right of a given chair.
	 * 
	 * @param i The index of the given chair. 
	 * @return The value of the chair which is second on the right.
	 */
	public int getSecondRight(int i){
		if (i==NUMBER_OF_CHAIRS-2) {return chairs[0];}  	
		if (i==NUMBER_OF_CHAIRS-1) {return chairs[1];} 
		return chairs[i+2];
	}

	/**
	 * Sets the value of a given chair.
	 * 
	 * @param The index of the chair.
	 * @param value
	 */
	public void set(int i,byte value){
		chairs[i]=value;
	}

	/**
	 * Puts the actual player's sign on a chair.
	 * 
	 * @param i The index of the chair.
	 */
	public void apply(int i) {
		set(i,(byte)player.value());
		player = player.opponent();

	}

	/**
	 * Applies the operator on a given chair.
	 *
	 * @param op The operator to apply.
	 */
	public void apply(Operator op) {
		apply((( Sit) op).getI());
	}

	/**
	 * Checks if the actual player can sit their sign on a given chair.
	 * 
	 * @param i The index of the chair.
	 * @return <code>true</code> if the actual player can put their sign on the chair <code>false</code> otherwise.
	 */
	public boolean isApplicable(int i) {

		if ((i<0) || (i>=NUMBER_OF_CHAIRS) || (get(i)!=0)) {
			return false;
		} 
		if (i == 0) {
			return get(i+1)!=player.opponent().value() && get(NUMBER_OF_CHAIRS-1)!=player.opponent().value();
		} 
		if (i == NUMBER_OF_CHAIRS-1) {
			return get(i-1)!=player.opponent().value() && get(0)!=player.opponent().value();
		}
		return get(i+1)!=player.opponent().value() && get(i-1)!=player.opponent().value();

	}


	/**
	 * Checks if a given operator can be applied.
	 * 
	 * @return <code>true</code> if the the operator can be applied, <code>false</code> otherwise.
	 */
	public boolean isApplicable(Operator op) {
		return (op instanceof Sit) && isApplicable((( Sit ) op).getI());
	}

	/**
	 * Returns an ArrayList of the applicable operators.
	 * 
	 * @return ArrayList of applicable operators.
	 */
	public ArrayList<Sit> operators() {
		ArrayList<Sit> operators = new ArrayList<Sit>();
		for (int op = 0; op < NUMBER_OF_CHAIRS; ++op) {
			if (isApplicable(op)) {
				operators.add(new Sit(op));
			}
		}
		return operators;

	} 

	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (o == null || ! getClass().equals(o.getClass())) return false;
		ChairGame that = (ChairGame) o;
		return  that.player == player && Arrays.equals(that.chairs, chairs);

	}
	
	/**
	 * Overridden toString method: -1 means G(irl), 1 means B(oy).
	 */
	@Override
	public String toString() {
		final String newLine = System.getProperties().getProperty("line.separator");
		StringBuilder	sb = new StringBuilder();
		for( int i=0 ; i<NUMBER_OF_CHAIRS ; i++ ){
			sb.append(i+1);
			sb.append(":");    
			switch (this.chairs[i]){
			case -1: sb.append("G");break;
			case  1: sb.append("B");break;
			default : sb.append("_");
			}
			sb.append(newLine);
		}

		return sb.toString();

	}
}
