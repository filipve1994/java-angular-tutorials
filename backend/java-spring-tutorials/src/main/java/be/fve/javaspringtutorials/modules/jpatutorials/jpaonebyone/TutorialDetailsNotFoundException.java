package be.fve.javaspringtutorials.modules.jpatutorials.jpaonebyone;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TutorialDetailsNotFoundException extends RuntimeException {

    public TutorialDetailsNotFoundException(String message) {
        super(message);
    }
}
