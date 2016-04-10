package chairgame;

/**
 * AI for two-person zero-sum games. It calculates the computer's next move using minimax algorithm.
 */
public class MinimaxPlayer extends PlayerImp {

	/**
	 * The default name of the machine player.
	 */
	private static final String	NAME = "Computer";

	/**
	 * The value of the evaluation function for the best game state.
	 */
	private static final int	MAX_SCORE = Integer.MAX_VALUE - 1;

	/**
	 * The value of the evaluation function for the worst game state.
	 */
	private static final int	MIN_SCORE = Integer.MIN_VALUE + 1;

	/**
	 * Default value of the maximum depth of the game states' decision tree.
	 */ 
	private static final int	MAX_DEPTH = 6;

	/**
	 * The maximum depth of the game states' decision tree.
	 */
	private int	maxDepth = MAX_DEPTH;

	/**
	 * The evaluation function used by the algorithm.
	 */
	private EvaluationFunction	ef;

	/**
	 * Constructor to create a machine player.
	 */
	public MinimaxPlayer() {
		super(NAME, Identity.PLAYER_A);
	}

	/**
	 * Constructor to create a machine player.
	 *
	 * @param identity The player we intend to calculate a move for.
	 * @param ef The evaluation function to use.
	 */
	public MinimaxPlayer(Identity identity, EvaluationFunction ef) {
		this(NAME, identity, ef);
	}


	/**
	 * Constructor to create a machine player.
	 *
     * @param identity The player we intend to calculate a move for.
	 * @param maxDepth The maximum depth of the game states' decision tree.
	 * @param ef The evaluation function to use.
	 */
	public MinimaxPlayer(Identity identity, int maxDepth, EvaluationFunction ef) {
		this(NAME, identity, maxDepth, ef);
	}

	/**
	 * Constructor to create a machine player.
	 *
	 * @param name The name of the player.
     * @param identity The player we intend to calculate a move for.
	 * @param ef The evaluation function to use.
	 */
	public MinimaxPlayer(String name, Identity identity, EvaluationFunction ef) {
		super(name, identity);
		this.ef = ef;
	}

	/**
	 * Constructor to create a machine player.
	 *
	 * @param name The name of the player.
	 * @param identity The player we intend to calculate a move for.
	 * @param maxDepth The maximum depth of the game states' decision tree.
	 * @param ef The evaluation function to use.
	 */
	public MinimaxPlayer(String name, Identity identity, int maxDepth, EvaluationFunction ef) {
		super(name, identity);
		this.maxDepth = maxDepth;
		this.ef = ef;
	}
	
	/**
	 * @return <code>false</code> as the machine player is not interactive
	 */
	public boolean isInteractive() {
		return false;
	}

	/**
	 * Returns the maximum depth of the game states' decision tree.
	 *
	 * @return The maximum depth of the game states' decision tree.
	 */
	public int getMaxDepth() {
		return maxDepth;
	}

	/**
	 * Sets the maximum depth of the game states' decision tree.
	 *
	 * @param maxDepth The maximum depth of the game states' decision tree.
	 */
	public void setMaxDepth(int maxDepth) {
		this.maxDepth = maxDepth;
	}

	/**
	 * Returns the evaluation function used by the machine player.
	 *
	 * @return the evaluation function used by the machine player.
	 */
	public EvaluationFunction getEvaluationFunction() {
		return ef;
	}

	/**
	 * Sets the evaluation function used by the machine player.
	 *
	 * @param ef the evaluation function used by the machine player.
	 */
	public void setEvaluationFunction(EvaluationFunction ef) {
		this.ef = ef;
	}

	/**
	 * Calculating the move for the player for a given game state using minimax algorithm.
	 * 
	 * @param position The game state the move is calculated for.
	 * @return The move calculated by minimax algorithm.
	 */
	public Operator getOperator(Position position) throws GameException {
		if (position.isTerminal() || position.getNext() != identity || maxDepth < 1) {
			throw new GameException();
		}
		Operator	bestOperator = null;
		int	score = 0, bestScore = Integer.MIN_VALUE;
		for (Operator operator : position.operators()) {
			Position	newPosition = operator.applyTo(position);
			if ((score = evaluate(newPosition, maxDepth - 1)) > bestScore) {
				bestScore = score;
				bestOperator = operator; 
			}
		}
		return bestOperator;
	}

	/**
	 * Evaluating a game state.
	 *
	 * @param position The game state.
	 * @param depth 
	 * @return The value of the evaluation of the given state.
	 */
	private int evaluate(Position position, int depth) throws GameException {
		if (position.isTerminal()) {
			if (position.getWinner() == identity)
				return MAX_SCORE;
			else if (position.getWinner() == identity.opponent())
				return MIN_SCORE;
			else
				return 0;
		}
		if (depth == 0) return ef.getValue(position, identity);
		return position.getNext() == identity ? evaluateMax(position, depth) : evaluateMin(position, depth);
	}

	/**
	 * Evaluating a game state for Min player.
	 *
	 * @param position The game state.
	 * @param depth 
	 * @return The value of the evaluation of the given state.
	 */
	private int evaluateMin(Position position, int depth) throws GameException {
		int	score, bestScore = Integer.MAX_VALUE;
		for (Operator operator : position.operators()) {
			Position	newPosition = operator.applyTo(position);
			if ((score = evaluate(newPosition, depth - 1)) < bestScore) bestScore = score;
		}
		return bestScore;
	}

	/**
	 * Evaluating a game state for Max player.
	 *
	 * @param position The game state.
	 * @param depth 
	 * @return The value of the evaluation of the given state.
	 */
	private int evaluateMax(Position position, int depth) throws GameException {
		int	score, bestScore = Integer.MIN_VALUE;
		for (Operator operator : position.operators()) {
			Position	newPosition = operator.applyTo(position);
			if ((score = evaluate(newPosition, depth - 1)) > bestScore) bestScore = score;
		}
		return bestScore;
	}

}
