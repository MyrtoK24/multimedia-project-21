package hangman_1;


public class InvalidCountExeception extends Exception{
    
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidCountExeception(String errorMessage) {
		super(errorMessage);
		}
	}