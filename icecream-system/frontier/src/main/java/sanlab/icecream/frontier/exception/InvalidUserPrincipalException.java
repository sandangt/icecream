package sanlab.icecream.frontier.exception;

import java.io.Serial;

public class InvalidUserPrincipalException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidUserPrincipalException(String message) {
        super(message);
    }

    public static InvalidUserPrincipalException defaultInstance() {
        return new InvalidUserPrincipalException("Invalid user principal");
    }

}
