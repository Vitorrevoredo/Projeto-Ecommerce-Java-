package exception;

public class ExcecaoArmazenamento extends Exception {
    private static final long serialVersionUID = 1L;

    public ExcecaoArmazenamento(String mensagem) {
        super(mensagem);
    }

    public ExcecaoArmazenamento(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}
