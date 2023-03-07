package hangman_1;

public class UnbalancedException extends Exception{
    
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnbalancedException(String errorMessage) {
		super(errorMessage);
		}
}