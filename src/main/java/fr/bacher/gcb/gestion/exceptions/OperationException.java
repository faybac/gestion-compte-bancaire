package fr.bacher.gcb.gestion.exceptions;

/**
 * @author Fayçal BACHER 
 */
public class OperationException extends Exception {
	private static final long serialVersionUID = -3419952097454891063L;

	 /*
	  * Crée une exception 
	 */
	public OperationException() {
		super();
	}

	/**
	 * Crée une exception de l'opération invalide
	 * 
	 * @param message le message d'erreur.
	 */
	public OperationException(final String message) {
		super(message);
	}

	/**
	 * Crée une exception de l'opération invalide
	 * 
	 * @param cause l'erreur à l'origine de l'exception.
	 */
	public OperationException(final Throwable cause) {
		super(cause);
	}

	/**
	 * Crée une exception de l'opération invalide
	 * 
	 * @param message le message d'erreur.
	 * @param cause   l'erreur à l'origine de l'exception.
	 */
	public OperationException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
