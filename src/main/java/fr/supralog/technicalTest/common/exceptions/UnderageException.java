package fr.supralog.technicalTest.common.exceptions;

/**
 * @author fadili
 * this is an Exception that would be thrown 
 */
public class UnderageException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnderageException(String message) {
        super(message);
    }
}
