## PicPay Simplificado - API de Pagamentos

**Descrição do Projeto:**
O PicPay Simplificado é uma API desenvolvida em Java utilizando Spring Boot, que tem como objetivo fornecer uma plataforma de pagamentos simplificada. A API permite que usuários realizem depósitos e transferências, de dinheiro entre si. Para isso, cada usuário possui uma carteira virtual onde seu saldo é armazenado. API foi desenvolvida por conta do Desafio Back-end PicPay https://github.com/PicPay/picpay-desafio-backend, com intuito de melhorar meus conhecimentos nas seguintes ferramentas citadas no texto.

**Tecnologias Utilizadas**
- Java 17
- Spring Boot
- Banco de Dados H2 (em memória)
- JUnit, Mockito, JAssert (para testes unitários)
- GitHub Actions (para CI/CD, execução de testes e análise de código)

**Funcionalidades**
- Cadastro de usuários
- Realização de transferências entre usuários
- Depósito de dinheiro na carteira
- Validação de regras específicas para cada tipo de usuário
- Tratamento de exceções e validações de entrada

**Tipos de Usuários**

**A API diferencia dois tipos de usuários:**
- Usuário Comum - Pode enviar e receber dinheiro livremente.
- Usuário Lojista - Apenas pode receber dinheiro, não está autorizado a realizar transferências.

**Regras de Negócio**
O saldo do remetente deve ser suficiente para realizar a transação.
Lojistas não podem realizar transferências, apenas receber pagamentos.
Caso um usuário não seja encontrado, uma exceção é lançada.

**Tratamento de Exceções**
- A API trata diferentes casos de erro, como:
- Usuário não encontrado
- Saldo insuficiente
- Tentativa de transferência por um lojista
