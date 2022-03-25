package fr.af.offerpoc.commun.exception;


/**
 * Technical Exception
 *
 * @Author TGI
 * @Date 24/03/2022
 */
public class OfferTechnicalException extends Exception {

    public OfferTechnicalException(String message, Exception e) {
        super(message, e);
    }
}
