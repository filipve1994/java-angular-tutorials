package be.fve.javaspringtutorials.modules.jpatutorials.jpaonebyone;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TutorialNotFoundException extends RuntimeException {

    public TutorialNotFoundException(String message) {
        super(message);
    }
}
