# E-commerce Gacessórios

Bem-vindo ao projeto **E-commerce Gacessórios**, um sistema de e-commerce desenvolvido em **Java**. O sistema permite o cadastro e autenticação de administradores e clientes, além de gerenciamento de produtos e carrinho de compras.

## Funcionalidades

- **Cadastro de Administradores**: Permite cadastrar administradores com acesso ao sistema de gestão.
- **Cadastro de Clientes**: Clientes podem se cadastrar e realizar login para acessar a loja online.
- **Login e Autenticação**: Sistema de login para clientes e administradores, com validação de credenciais.
- **Gestão de Produtos**: Administradores podem adicionar, editar e remover produtos da loja.
- **Carrinho de Compras**: Clientes podem visualizar produtos e gerenciar o carrinho para realizar compras.

## Tecnologias Utilizadas

- **Java 8+**: Linguagem principal utilizada no desenvolvimento.
- **Banco de Dados**: Armazenamento simples de dados utilizando memória (não implementado um banco de dados persistente, mas pode ser integrado facilmente).

## Estrutura do Projeto

```plaintext
.
├── src
│   ├── main
│   │   ├── controller      # Controladores de lógica de negócios
│   │   ├── exception      # Exceções customizadas
│   │   ├── model          # Modelos de dados
│   │   ├── service        # Serviços de lógica de autenticação, produtos, etc.
│   │   ├── view           # Interfaces de visualização
│   │   └── Main.java      # Ponto de entrada principal do aplicativo
│   └── test               # Testes unitários
└── README.md              # Este arquivo
