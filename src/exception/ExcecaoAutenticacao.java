package exception;

public class ExcecaoAutenticacao extends Exception {
    private static final long serialVersionUID = 1L;

    public ExcecaoAutenticacao(String mensagem) {
        super(mensagem);
    }

    public ExcecaoAutenticacao(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}
