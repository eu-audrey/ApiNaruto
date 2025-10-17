# 🥷 API Naruto

Este projeto é uma API RESTful baseada no universo de Naruto, criada como um exercício de aprendizado para aprofundar conhecimentos em desenvolvimento backend com o ecossistema Spring.

A API irá gerenciar entidades como Ninjas, Hokages e Missões, incluindo o relacionamento entre elas.

## 📜 Objetivos de Aprendizagem

- Construção de uma API REST com Spring Boot.
- Autenticação e autorização com Spring Security (ex: apenas Hokages podem realizar certas ações).
- Mapeamento de relacionamento entre tabelas com JPA/Hibernate.
- Gerenciamento de banco de dados com PostgreSQL rodando em Docker.
- Versionamento de schema do banco de dados com Flyway (Migrations).
- Empacotamento da aplicação com Maven.
- Preparação para deploy em um ambiente de nuvem (AWS).

## ⚙️ Tecnologias Utilizadas

- **Backend:** Java 17, Spring Boot
- **Documentação:** Swagger (OpenAPI) - Para documentar e testar os endpoints da API.
- **Gerenciador de Pacotes:** Maven
- **Banco de Dados:** PostgreSQL 🐘
- **Containerização:** Docker 🐳
- **Migrations:** Flyway
- **Deploy Alvo:** AWS ☁️

## 🚀 Como Começar

### Pré-requisitos

- Java (JDK 17 ou superior)
- Maven
- Docker e Docker Compose

### Instalação e Execução

1.  **Clone o repositório:**
    ```sh
    git clone <url aqui do projeto>
    cd ApiNaruto
    ```

2.  **Inicie o banco de dados PostgreSQL com Docker:**
   👉Necessário docker-desktop instalado e inciado.

    O comando abaixo irá subir um contêiner com o banco de dados configurado conforme o `docker-compose.yml`.
    ```sh
    docker-compose up -d
    ```

4.  **Execute a aplicação Spring Boot:**
    ```sh
    mvn spring-boot:run
    ```

5.  A aplicação estará disponível em `http://localhost:8080`.

## 🔗 Endpoints da API

### Ninjas

| Método | Endpoint | Descrição |
|--------|----------|----------|
| `GET` | `/api/ninjas` | Lista todos os ninjas |
| `GET` | `/api/ninjas/{id}` | Busca ninja por ID |
| `POST` | `/api/ninjas` | Cria novo ninja |
| `PUT` | `/api/ninjas/{id}` | Atualiza ninja existente |
| `DELETE` | `/api/ninjas/{id}` | Remove ninja por ID |

### Exemplo de Payload (Ninja)

```json
{
  "nome": "Naruto Uzumaki",
  "aldeia": "Konohagakure",
  "elemento": "Vento",
  "imgUrl": "https://example.com/naruto.jpg"
}
```

## 📊 Status do Projeto

### ✅ Implementado
- [x] Entidade Ninja com JPA/Hibernate
- [x] CRUD completo para Ninjas
- [x] Repository com Spring Data JPA
- [x] Service Layer com regras de negócio
- [x] Controller REST com ResponseEntity
- [x] Tratamento de erros (404, validações)
- [x] Configuração PostgreSQL + Docker
- [x] Estrutura Maven

### 🚧 Em Desenvolvimento
- [ ] Entidades Hokage e Missão
- [ ] Relacionamentos entre entidades
- [ ] Spring Security (autenticação/autorização)
- [ ] Flyway Migrations
- [ ] Documentação Swagger/OpenAPI
- [ ] Testes unitários e integração
- [ ] Deploy AWS
