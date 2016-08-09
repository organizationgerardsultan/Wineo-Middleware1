package fr.doranco.wineo.middleware.objetmetier.cave;

/**
 * Exception levée en cas de place insuffisante dans une cave.
 * 
 * @author Snekkja JFDC
 */
public class PlaceInsuffisanteException extends Exception {

	private static final long serialVersionUID = 1L;

	public PlaceInsuffisanteException(String message) {
		super(message);
	}

	public PlaceInsuffisanteException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
