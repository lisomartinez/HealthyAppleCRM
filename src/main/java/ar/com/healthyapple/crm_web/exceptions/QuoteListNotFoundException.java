package ar.com.healthyapple.crm_web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class QuoteListNotFoundException extends RuntimeException {
       public static final String DESCRIPTION = "Quote List Not Found";

        private static final long serialVersionUID = 12093582894015L;

        public QuoteListNotFoundException() {
            super(DESCRIPTION);
        }

        public QuoteListNotFoundException(String detail) {
            super(DESCRIPTION + ". " + detail);
        }
    }


