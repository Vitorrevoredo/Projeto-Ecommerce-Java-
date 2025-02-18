package exception;

import model.Produto;
import java.util.List;

public class ProdutosPadroesService {

    public void adicionarProdutosPadrao(List<Produto> produtos) {
        // Se a lista de produtos estiver vazia, adiciona os produtos padrão
        if (produtos.isEmpty()) {
            produtos.add(new Produto(1, "Cabo USB C 240W Essager", 80.00, 10, "Cabo USB C de 240W para carregamento rápido", "Cabos"));
            produtos.add(new Produto(2, "Cabo USB C Retrátil Baseus", 50.00, 20, "Cabo USB C retrátil da Baseus", "Cabos"));
            produtos.add(new Produto(3, "Cabo USB C 240W Baseus", 90.00, 15, "Cabo USB C de 240W da Baseus", "Cabos"));
            produtos.add(new Produto(4, "Kit 2 Cabos Lightning Baseus", 45.00, 12, "Kit com 2 cabos Lightning da Baseus", "Cabos"));
            produtos.add(new Produto(5, "Cabo Baseus Usb C 100W", 50.00, 18, "Cabo USB C de 100W da Baseus", "Cabos"));

            produtos.add(new Produto(6, "Carregador Veicular Essager 30W", 30.00, 25, "Carregador veicular Essager de 30W", "Carregadores"));
            produtos.add(new Produto(7, "Carregador Baseus 20W", 50.00, 30, "Carregador Baseus de 20W", "Carregadores"));

            produtos.add(new Produto(8, "Powerbank MagSafe Essager 5000mAh", 110.00, 10, "Powerbank MagSafe Essager com 5000mAh", "Powerbank"));

            produtos.add(new Produto(9, "Combo Baseus 2 USB C", 50.00, 8, "Combo com 2 cabos USB C Baseus", "Combos"));
            produtos.add(new Produto(10, "Combo Baseus 2 Apple", 50.00, 12, "Combo com 2 cabos Apple Baseus", "Combos"));
            produtos.add(new Produto(11, "Combo Essager Veicular", 40.00, 15, "Combo com carregador veicular Essager", "Combos"));
            produtos.add(new Produto(12, "Combo Baseus Apple", 50.00, 10, "Combo com cabos Apple Baseus", "Combos"));

            produtos.add(new Produto(13, "Fone com Fio Baseus HZ20", 15.00, 50, "Fone com fio Baseus modelo HZ20", "Fones"));
            produtos.add(new Produto(14, "Fone com Fio Baseus H17", 15.00, 50, "Fone com fio Baseus modelo H17", "Fones"));

            System.out.println("Produtos padrão cadastrados com sucesso!");
        }
    }
}
