package chairgame;

import java.io.BufferedReader;
import java.io.InputStreamReader;
/**
 * Example of a two-person zero-sum game, which can be played through console input.
 * The machine player uses minimax algorithm to decide which move to make. 
 * 
 * Rules:
 *  There are 14 chairs positioned in a circle, and 7 boys and 7 girls. One player sits the boys,
 *  the other sits the girls on the chairs. The players sit the boys and girls in turns, however 
 *  there should not be sit a boy and a girl right next to each other. The player who sits the 
 *  last person wins the game.
 *
 * @author Laszlo Szoboszlai
 *
 */
public class GamePlay {

	
	/**
	 * Method to print out the rules of the game. 
	 */
	public static void writeRules(){
		String newLine = System.getProperties().getProperty( "line.separator" );
		StringBuffer sb = new StringBuffer();
		sb.append( "Rules: ").append( newLine );
		sb.append( "There are 14 chairs positioned in a circle, and 7 boys and 7 girls. One player" + newLine );
		sb.append( "sits the boys, the other sits the girls on the chairs. The players"+ newLine );
		sb.append( "sit the boys and girls in turns, however there should not be sit a boy and a girl"+ newLine );
		sb.append( "right next to each other. The player who sits the last person wins the game."+ newLine );

		System.out.println( sb.toString() );
	}

	public static void main( String[] args ) {
		
		/**
		 * Start state of the game.
		 */
		ChairGame state = new ChairGame();


		GamePlay.writeRules();
		try {			
			String name = "Player", temp;
			BufferedReader	in = new BufferedReader( new InputStreamReader( System.in ) );
			System.out.println( "Please enter your name: " );
			temp = in.readLine();
			if ( ! temp.equals( "" ) ){
				name = temp;
			}
			
			
			//playerB is the minimax player, playerA is the human player. 
			Player	playerB = new MinimaxPlayer( Identity.PLAYER_B,5, new ChairEvaluationFunction3() );
			Player	playerA = new ConsolePlayer( name, Identity.PLAYER_A );
			
			//print the chairs out
			System.out.println( state );

			while ( ! state .isTerminal() ) {

				Player	player = null;
				switch ( state.getNext() ) {
				case PLAYER_A:	player = playerA;	break;
				case PLAYER_B:	player = playerB;	break;
				default:
					break;
				}

				Operator operator = null;

				do {
					try {
						System.out.printf("%s's turn: ", player.getName());
						operator = player.getOperator(state);
						break;
					} catch( GameException e ) {
						if ( ! player.isInteractive() ){ throw e;}
						else System.out.println( e.getMessage() );
					}
				} while ( true );

				if ( operator == null ){break;}
				state.apply(operator);
				String side = player.getIdentity().value() == -1 ? "Girl" : "Boy";

				System.out.printf("%s has sat ("+side+") to place "+ operator +" %n", player.getName());
				System.out.printf("%n%s%n", state);
			}
			if (state.isTerminal()) {
				switch(state.getWinner()) {
				case PLAYER_A:	System.out.printf("%s won!%n", playerA.getName());	break;
				case PLAYER_B:	System.out.printf("%s won!%n", playerB.getName());	break;
				case NONE:	System.out.println("Draw???");	break;
				}
			}
		} catch(GameException e) {
			System.out.println("Error!");
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	} 
}
