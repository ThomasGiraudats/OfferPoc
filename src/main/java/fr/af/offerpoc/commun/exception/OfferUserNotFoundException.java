package fr.af.offerpoc.commun.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * Exception throw for controler : user not found
 *
 * @Author TGI
 * @Date 24/03/2022
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such User")
public class OfferUserNotFoundException extends RuntimeException {
}
