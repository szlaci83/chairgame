package chairgame;


/**
 * Exception class created for two-person games
 */
public class GameException extends Exception {

	private static final long	serialVersionUID = -351561778484043083L;

	public GameException() {
	}

	public GameException(String message) {
		super(message);
	}

	public GameException(String message, Throwable cause) {
		super(message, cause);
	}
}