package customExceptions;

public class InvalidLikesSharesException extends Exception {
	  public InvalidLikesSharesException() {
	        super("The likes/shares may be negative.");
}
}
