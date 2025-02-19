package exception;

public class ExcecaoPagamento extends Exception {
    private static final long serialVersionUID = 1L;

    public ExcecaoPagamento(String mensagem) {
        super(mensagem);
    }

    public ExcecaoPagamento(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}
