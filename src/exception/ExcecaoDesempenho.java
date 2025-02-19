package exception;

public class ExcecaoDesempenho extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ExcecaoDesempenho(String message) {
        super(message);
    }

    public ExcecaoDesempenho(String message, Throwable cause) {
        super(message, cause);
    }
}
