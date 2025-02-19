package model;

public class Pagamento {

    public int getValor() {
        return 0;
    }

    // Enum para as formas de pagamento
    public enum FormaDePagamento {
        CARTAO_CREDITO,
        CARTAO_DEBITO,
        PIX,
        PAGAMENTO_ENTREGA
    }

    private FormaDePagamento formaDePagamento;

    public Pagamento(FormaDePagamento formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }

    public void realizarPagamento(double valor) {
        switch (formaDePagamento) {
            case CARTAO_CREDITO:
                System.out.println("Pagamento de R$" + valor + " realizado com Cartão de Crédito.");
                break;
            case CARTAO_DEBITO:
                System.out.println("Pagamento de R$" + valor + " realizado com Cartão de Débito.");
                break;
            case PIX:
                System.out.println("Pagamento de R$" + valor + " realizado via PIX.");
                break;
            case PAGAMENTO_ENTREGA:
                System.out.println("Pagamento de R$" + valor + " será realizado na entrega.");
                break;
            default:
                System.out.println("Forma de pagamento inválida.");
        }
    }
}
