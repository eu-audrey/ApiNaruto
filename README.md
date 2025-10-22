# API de Ninjas - Naruto

Uma API RESTful para gerenciamento de ninjas, construída com Spring Boot, que demonstra as melhores práticas de desenvolvimento de APIs, incluindo o uso do padrão DTO, paginação e validação de dados.

## 🚧 Status do Projeto (Roadmap)

O projeto está em desenvolvimento ativo. Abaixo está o status atual das funcionalidades e os próximos passos planejados:

- [x] Estrutura base com Spring Boot 3 e Java 21
- [x] CRUD completo para a entidade `Ninja`
- [x] Implementação do padrão DTO (Request e Response)
- [x] Paginação e ordenação padrão na listagem de ninjas
- [x] Validação de dados de entrada
- [x] Refatoração do ID da entidade de `UUID` para `Long` com auto-incremento
- [x] Adição de um Global Exception Handler com `@ControllerAdvice`
- [ ] Implementação de testes unitários e de integração (JUnit 5 e Mockito)
- [ ] Implementação de documentação com Swagger (OpenAPI)
- [ ] Implementação de segurança com Spring Security e JWT
- [ ] Desenvolvimento de uma interface Front-end
- [ ] Containerização da aplicação com Docker
- [ ] Deploy da aplicação na AWS

## ✨ Funcionalidades Principais

- **CRUD Completo:** Crie, leia, atualize e delete ninjas.
- **Padrão DTO:** Separação clara entre os dados de requisição (`NinjaRequestDTO`) e resposta (`NinjaResponseDTO`), garantindo uma API segura e flexível.
- **Paginação e Ordenação:** A listagem de ninjas é paginada para otimizar a performance, com uma ordenação padrão para garantir resultados consistentes.
- **Validação de Dados:** As requisições de entrada são validadas para garantir a integridade dos dados.
- **Arquitetura em Camadas:** Código organizado em camadas de Controller, Service e Repository.

## 🚀 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3**
- **Spring Data JPA**
- **Maven**
- **Banco de Dados H2 (Padrão)** ou **PostgreSQL**

## 📋 Pré-requisitos

- **JDK 21** ou superior
- **Maven 3.8** ou superior
- Um cliente de API como **Postman** ou **Insomnia**
- (Opcional) Um cliente de banco de dados como **DBeaver**

## ⚙️ Como Executar o Projeto

1.  **Clone o repositório:**
    ```bash
    git clone <url-do-seu-repositorio>
    cd ApiNaruto
    ```

2.  **Configure o Banco de Dados (Opcional):**
    O projeto está configurado para usar o banco de dados em memória H2 por padrão. Se desejar usar o PostgreSQL, descomente e ajuste as seguintes linhas no arquivo `src/main/resources/application.properties`:
    ```properties
    # spring.datasource.url=jdbc:postgresql://localhost:5432/nome_do_banco
    # spring.datasource.username=seu_usuario
    # spring.datasource.password=sua_senha
    # spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
    ```

3.  **Execute a aplicação:**
    ```bash
    mvn spring-boot:run
    ```
    A API estará disponível em `http://localhost:8080`.

## 📖 Endpoints da API

A URL base para todos os endpoints é `http://localhost:8080/api/ninjas`.

---

### 1. Criar um Novo Ninja

Cria um novo registro de ninja.

- **Método:** `POST`
- **URL:** `/`
- **Body (Requisição):**
  ```json
  {
      "nome": "Minato Namikaze",
      "aldeia": "Konohagakure",
      "idade": 24
  }
  ```
- **Resposta (Sucesso - `201 Created`):**
  ```json
  {
      "id": 1,
      "nome": "Minato Namikaze",
      "aldeia": "Konohagakure"
  }
  ```

---

### 2. Listar Ninjas com Paginação

Lista todos os ninjas de forma paginada.

- **Método:** `GET`
- **URL:** `/`
- **Parâmetros de Query (Opcionais):**
  - `page`: Número da página (padrão: `0`).
  - `size`: Quantidade de itens por página (padrão: `10`).
  - `sort`: Campo para ordenação (ex: `nome,asc` ou `idade,desc`). A ordenação padrão é `id,asc`.
- **Exemplo de URL:** `http://localhost:8080/api/ninjas?page=0&size=5&sort=nome,asc`
- **Resposta (Sucesso - `200 OK`):**
  ```json
  {
      "content": [
          {
              "id": 1,
              "nome": "Gaara",
              "aldeia": "Sunagakure"
          },
          // ... mais 4 ninjas
      ],
      "pageable": {
          "pageNumber": 0,
          "pageSize": 5,
          // ...
      },
      "totalPages": 3,
      "totalElements": 15,
      // ... outros metadados de paginação
  }
  ```

---

### 3. Buscar Ninja por ID

Busca um ninja específico pelo seu ID.

- **Método:** `GET`
- **URL:** `/{id}`
- **Exemplo de URL:** `/1`
- **Resposta (Sucesso - `200 OK`):**
  ```json
  {
      "id": 1,
      "nome": "Minato Namikaze",
      "aldeia": "Konohagakure"
  }
  ```
- **Resposta (Erro - `404 Not Found`):** Se o ID não existir.

---

### 4. Atualizar um Ninja

Atualiza (substitui) completamente um ninja existente.

- **Método:** `PUT`
- **URL:** `/{id}`
- **Exemplo de URL:** `/1`
- **Body (Requisição):**
  ```json
  {
      "nome": "Minato Namikaze",
      "aldeia": "Konohagakure",
      "idade": 25
  }
  ```
- **Resposta (Sucesso - `200 OK`):**
  ```json
  {
      "id": 1,
      "nome": "Minato Namikaze",
      "aldeia": "Konohagakure"
  }
  ```

---

### 5. Deletar um Ninja

Deleta um ninja pelo seu ID.

- **Método:** `DELETE`
- **URL:** `/{id}`
- **Exemplo de URL:** `/1`
- **Resposta (Sucesso):** `204 No Content`

## 🏛️ Padrões e Boas Práticas

- **Padrão DTO (Data Transfer Object):** A API utiliza DTOs para desacoplar o contrato da API do modelo de persistência. `NinjaRequestDTO` é usado para entrada de dados, permitindo validações específicas, enquanto `NinjaResponseDTO` é usado para saída, expondo apenas os dados necessários e seguros.
- **Paginação e Ordenação:** A listagem de recursos é paginada usando `Pageable` do Spring Data para garantir a performance e escalabilidade da API. Uma ordenação padrão por ID é definida para garantir a consistência dos resultados.
- **Validação:** A validação dos dados de entrada é feita na camada de DTO com o uso de `jakarta.validation.constraints`, garantindo que dados inválidos sejam rejeitados antes de chegarem à lógica de negócio.
