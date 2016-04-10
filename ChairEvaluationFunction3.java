package chairgame;
/**
 * Class to estimate how good each game state is.    
 */

public class ChairEvaluationFunction3 implements EvaluationFunction<ChairGame>{

	/**
	 * Method to return the gameState's goodness for a given player.
	 * 
	 * @param gameState The gameState we want to evaluate.
	 * @param player The player we want to check the state's goodness for.
	 */
	public int getValue(ChairGame gameState, Identity player) {
		/**
		 * The number of chairs the player can place a sign.
		 */
		int goodChair = 0;
		
		/**
		 * The number of chairs the opponent can place a sign.
		 */
		int badChair = 0; 
 
		for( int i = 0; i < gameState.getChairs().length; i++ )
			if ( gameState.get(i) == player.value() ){
				if( gameState.getRight(i) == 0 ){
					goodChair++;
					if  ( gameState.getSecondRight(i) == 0 ) {goodChair += 2;}     
				}

				if(  gameState.getLeft(i) == 0 ){
					goodChair++;
					if ( gameState.getSecondLeft(i) == 0 )   { goodChair+=2;}     
				}

				if ( gameState.get(i) == player.opponent().value() ){

					if( gameState.getRight(i) == 0 ){
						badChair++;
						if  ( gameState.getSecondRight(i) == 0 ) {badChair += 2;}     
					}

					if(  gameState.getLeft(i) == 0 ){
						badChair++;
						if ( gameState.getSecondLeft(i) == 0 )   { badChair+=2;}     
					}
				} 

			}
		//the value is the value of chairs the player can place 
		//minus the value of chairs the opponent can place  
		return goodChair-badChair;
	}
}
