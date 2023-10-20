package customExceptions;

public class EmptyFieldException extends Exception {
    public EmptyFieldException() {
        super("There is a field left empty");
    }
}