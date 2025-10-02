# SystemUser

 é uma aplicação **Spring Boot** desenvolvida para gerenciar/simular um sistema de usuários e seus dados associados, incluindo informações de login, sistema operacional utilizado e localização via integração com a API pública do [ViaCEP](https://viacep.com.br/).

---  

## Estrutura do Projeto
```
src/main/java/com/dicipline/SystemUser
│── Config/ # Configurações da aplicação (ex: seeding em ambiente test)
│── Entities/ # Entidades JPA (User, DataUser, Localization)
│── Repositories/ # Interfaces Repository (Spring Data JPA)
│── Resources/ # Controllers (exposição de endpoints REST)
│ └── Exception/ # Exception handlers para exceções customizados
│── Services/ # Regras de negócio (Service Layer)
│ └── Exceptions/ # Exceções personalizadas (ex: ResourceNotFoundException)
│
src/main/resources
│── application.properties # Configuração principal
│── application-dev.properties # Configuração ambiente dev
│── application-prod.properties # Configuração ambiente prod
│── application-test.properties # Configuração ambiente test
│
Dockerfile
pom.xml
``` 
## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3+**
  - Spring Web
  - Spring Data JPA
- **Hibernate**
- **PostgreSQL**
- **DB H2**
- **Maven**
- **Postman**
- **OpenAPI/Swagger** (documentação automática dos endpoints)
- **Docker** (build e deploy da aplicação ultilizando o multi-stage build)

## Arquitetura

- **Entities**: representam as tabelas no banco de dados (`User`, `DataUser`, `Localization`).
- **Repositories**: abstraem operações de persistência (Spring Data JPA).
- **Services**: contém as regras de negócio, como cálculo de duração de login e integração com API externa de CEP.
- **Resources (Controllers)**: expõem os endpoints REST para consumo externo.
- **Exception Handling**: tratamento centralizado de erros nos controllers via `ResourceExceptionHandler`.

---

## Entidades 

- **User**
  - id, name, password, birthDate
  - relacionamento `OneToOne` com `DataUser`

- **DataUser**
  - id, cep, systemOperational, login, loginClosed, duration
  - contém um objeto `Localization` preenchido automaticamente via CEP
  - relacionamento `OneToOne` com `User`

- **Localization**
  - cep, logradouro, complemento, bairro, localidade, uf
  - preenchido pela API do ViaCEP
---

##  Endpoints

### Users
- `GET /users` → listar todos os usuários
- `GET /users/{id}` → buscar usuário por ID
- `POST /users` → criar usuário
- `PUT /users/{id}` → atualizar usuário
- `DELETE /users/{id}` → deletar usuário

### DataUsers
- `GET /datas` → listar todos os registros de DataUser
- `GET /datas/{id}` → buscar DataUser por ID
- `POST /datas` → criar novo DataUser (CEP é validado pelo ViaCEP)
- `PUT /datas/{id}` → atualizar DataUser
- `DELETE /datas/{id}` → deletar DataUser

## Banco de Dados

O projeto utiliza **PostgreSQL**.  
 A estrutura do banco foi inicialmente gerada pelo Hibernate em ambiente de desenvolvimento, exportada como `script.sql` e depois executada manualmente no banco de produção (Render).
 O PostgreSQL não possui suporte nativo ao tipo `Duration` do Java.  
 Para resolver isso, foi criada a classe `DurationConverter` com a anotação `@Converter`, responsável por converter automaticamente entre os dois modelos de dados diferentes:

- `Duration` → `String` no banco de dados
- `String` → `Duration` ao recuperar os dados    
       **ambos no formato ISO 8601**

Dessa forma, o uso de `Duration` nas entidades do modelo é totalmente transparente.
## Integração com ViaCEP

O serviço `CepService` consome a API pública do ViaCEP para preencher automaticamente os dados de endereço do Objeto *Localization* (`logradouro`, `bairro`, `localidade`, `uf`) a partir de um CEP válido informado pelo usuário.

### Swagger/SpringDoc
A aplicação disponibiliza a documentação automática dos endpoints via Swagger/OpenAPI.
Após subir o projeto, acesse:http://localhost:8080/swagger-ui.html

