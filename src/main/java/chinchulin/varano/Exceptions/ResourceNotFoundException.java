package chinchulin.varano.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.NOT_FOUND)

public class ResourceNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String message){
        super(message);
    }

    // class for verify if this resource exists or no.
    /*
     * Fuente
     * https://www.youtube.com/watch?v=o_HV_FCs-Z0
     */

}
