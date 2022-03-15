package fr.af.offerpoc.commun.exception;


/**
 * Technical Exception
 * @Author TGI
 */
public class OfferTechnicalException extends Exception{

    public OfferTechnicalException (String message, Exception e) {
        super(message, e);
    }
}
