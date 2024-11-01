# Launch Service API


## Descrição
Este microserviço atua como um gateway para a gestão de lançamentos financeiros, permitindo a realização de operações CRUD (Criar, Ler, Atualizar, Excluir) em lançamentos e fornecendo relatórios consolidados.

## Requisitos Funcionais

1. **Gerenciamento de Lançamentos**
    - **Criar Lançamento**: Permitir a criação de um novo lançamento financeiro.
    - **Listar Lançamentos**: Fornecer uma lista de todos os lançamentos financeiros.
    - **Atualizar Lançamento**: Permitir a atualização das informações de um lançamento existente.
    - **Excluir Lançamento**: Permitir a exclusão de um lançamento específico.

2. **Consultas de Relatórios**
    - **Obter Relatório Consolidado**: Fornecer um relatório consolidado de lançamentos em um intervalo de datas específico.

3. **Autenticação e Autorização**
    - **Login de Usuário**: Permitir que os usuários façam login para acessar as funcionalidades do microserviço.

## Requisitos Não Funcionais

1. **Desempenho**
    - O sistema deve responder a qualquer requisição em menos de 200ms sob carga normal.

2. **Escalabilidade**
    - O microserviço deve ser capaz de lidar com um aumento de até 1000 requisições por segundo.

3. **Segurança**
    - Os dados sensíveis (como informações financeiras) devem ser criptografados em trânsito e em repouso.
    - A autenticação deve ser realizada utilizando tokens JWT.

4. **Manutenibilidade**
    - O código deve seguir as melhores práticas de desenvolvimento e estar bem documentado para facilitar a manutenção futura.

5. **Disponibilidade**
    - O sistema deve estar disponível 99.9% do tempo em um período de 30 dias.

## Tecnologias Utilizadas
- Java 21
- Spring Boot 3.3.4
- MySQL
- Docker
- Swagger para documentação da API

## Como Executar o Projeto

Para executar o microserviço localmente, siga os passos abaixo:

### Pré-requisitos
- **Java JDK**: Certifique-se de ter o JDK instalado.
- **Maven**: Verifique se o Maven está instalado.

### Instruções

1. **Navegue até o diretório do projeto**:
   ```bash
   cd /caminho/para/seu/projeto/launch_service_api
### Construa o projeto: 

- **Execute o seguinte comando para construir o projeto:**
 ```bash
mvn clean install
 ```
- **Execute o microserviço: Inicie o microserviço com o comando:**
 ```bash
mvn spring-boot:run
 ```

- **Parar o Microserviço e os Contêineres do Kafka:**

Para parar a execução do microserviço, pressione Ctrl + C no terminal onde o microserviço está em execução.
Para parar os contêineres do Kafka, execute o seguinte comando no terminal, no diretório onde o docker-compose.yml está localizado:
 ```bash
docker-compose down
 ```
Esse comando irá parar e remover todos os contêineres definidos no arquivo docker-compose.yml, liberando os recursos utilizados.

- **Acesse o microserviço:** 
- Após iniciar, você pode acessar o microserviço no seguinte endereço:
http://localhost:8080


**Parar o Microserviço: Para parar a execução, pressione Ctrl + C no terminal**

Agora está tudo em um único bloco, sem interrupções. Se precisar de mais algo ou se tiver outras solicitações, estou aqui para ajudar!





