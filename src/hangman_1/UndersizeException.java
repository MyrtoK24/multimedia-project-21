package hangman_1;

public class UndersizeException extends Exception{
    
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	
	public UndersizeException(String errorMessage) {
		super(errorMessage);
		}
	}