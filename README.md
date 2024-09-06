Desafio Autorizador

# Autorizador de Transações de Cartão de Crédito

Este projeto implementa um autorizador de transações de cartão de crédito com base em regras de MCC (Merchant Category Code) e saldo por categoria. O projeto foi desenvolvido utilizando a **Arquitetura Hexagonal** e o **Spring Boot**, com persistência de dados em um banco de dados **H2**.

## Requisitos

Antes de executar o projeto, certifique-se de que você tenha instalado os seguintes componentes:

- Java 17+
- Maven 3.8+
- Postman ou outra ferramenta para testar as requisições HTTP

## Instruções para Executar

### 1. Clonar o Repositório

Clone o repositório do projeto para sua máquina local:

```bash
git clone https://github.com/seu-usuario/autorizador-transacoes.git

### 2. Compilar o Projeto
Dentro do diretório do projeto, execute o comando abaixo para compilar o código e baixar as dependências:

bash
Copiar código
mvn clean install

### 3. Executar a Aplicação
Após compilar, execute a aplicação usando o Maven:

bash
Copiar código
mvn spring-boot:run
A aplicação estará disponível na porta 8080 por padrão. Para verificar, acesse:

Copiar código
http://localhost:8080

### 4. Testar a API com o Postman
Você pode testar o funcionamento da API de autorização de transações enviando requisições POST com payloads JSON via Postman ou outro cliente HTTP.

Exemplo de Payload
json
Copiar código
{
  "accountId": "123e4567-e89b-12d3-a456-426614174000",
  "amount": 100.00,
  "mcc": "5811",
  "merchant": "PADARIA DO ZE               SAO PAULO BR"
}

Endpoints Disponíveis
Método	Endpoint	Descrição
POST	/transacao	Processa uma transação de cartão

Resposta de Sucesso (code: 00): A transação foi aprovada e o saldo foi debitado corretamente.
Resposta de Saldo Insuficiente (code: 51): Não há saldo suficiente para aprovar a transação.
Resposta de Erro Geral (code: 07): Qualquer outro erro de processamento.

### 5. Verificando o Banco de Dados H2
A aplicação utiliza um banco de dados em memória H2 para armazenar os dados. Para acessar o console do H2 e visualizar as tabelas e registros:

Acesse o seguinte endereço no navegador:

bash
Copiar código
http://localhost:8080/h2-console
Use as seguintes credenciais:

JDBC URL: jdbc:h2:mem:testdb
User: sa
Password: (deixe em branco)

### 6. Arquivos SQL de Inicialização
Os dados da conta e saldos iniciais podem ser inseridos automaticamente por meio de um arquivo data.sql localizado em src/main/resources. Caso queira modificar esses dados ou inserir novas contas, edite este arquivo conforme necessário.

Exemplo de data.sql:
sql
Copiar código
INSERT INTO CONTA (id) VALUES ('123e4567-e89b-12d3-a456-426614174000');
INSERT INTO SALDO (id, categoria, saldo, conta_id) VALUES 
  (1, 'FOOD', 500.00, '123e4567-e89b-12d3-a456-426614174000'),
  (2, 'MEAL', 300.00, '123e4567-e89b-12d3-a456-426614174000'),
  (3, 'CASH', 1000.00, '123e4567-e89b-12d3-a456-426614174000');

### 7. Executar Testes
O projeto contém testes unitários que podem ser executados com o seguinte comando:

bash
Copiar código
mvn test

### 8. Tecnologias Utilizadas
Spring Boot - Framework principal para a aplicação
Spring Data JPA - Para persistência de dados
H2 Database - Banco de dados em memória para desenvolvimento e testes
Lombok - Redução de código boilerplate (como getters, setters, etc.)
Maven - Gerenciamento de dependências e ciclo de vida do projeto
