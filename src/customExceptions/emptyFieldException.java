package customExceptions;

public class emptyFieldException extends Exception {
    public emptyFieldException() {
        super("There is a field left empty");
    }
}