package hercerm.btcontacts.errors;

import java.util.Map;

public class InvalidContactException extends RuntimeException {
    private final Map<String, String> errors;

    public InvalidContactException(Map<String, String> errors) {
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
