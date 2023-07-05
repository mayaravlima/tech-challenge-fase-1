# Pós-Tech Arquitetura e Desenvolvimento Java
- Fase 1:  Nessa primeira fase o objetivo era criar API para o CRUD de Pessoas, Endereços e Eletrodoméstico focado na integridade dados.

## Índice

- [Pré-requisitos](#pré-requisitos)
- [Instalação](#instalação)
- [Uso](#uso)
    - [API Pessoas](#api-pessoas)
        - [Cadastro de Pessoas](#cadastro-de-pessoas)
        - [Listar todas as pessoas](#listar-todas-as-pessoas)
        - [Buscar pessoa pelo id](#buscar-pessoa-pelo-id)
        - [Atualizar pessoa](#atualizar-pessoa)
        - [Deletar pessoa](#deletar-pessoa-pelo-id)
    - [API Endereços](#api-endereços)
        - [Cadastro de Endereços](#cadastro-de-endereços)
        - [Listar todos os endereços](#listar-todos-os-endereços)
        - [Buscar endereço pelo id](#buscar-endereço-pelo-id)
        - [Atualizar endereço](#atualizar-endereço)
        - [Deletar endereço](#deletar-um-endereço-pelo-id)
    - [API Eletrodomésticos](#api-eletrodomésticos)
        - [Cadastro de Eletrodomésticos](#cadastro-de-eletrodomésticos)
        - [Listar todos os eletrodomésticos](#listar-todas-os-eletrodomésticos-cadastrados)
        - [Buscar eletrodoméstico pelo id](#buscar-eletrodoméstico-pelo-id)
        - [Atualizar eletrodoméstico](#atualizar-eletrodoméstico)
        - [Deletar eletrodoméstico](#deletar-um-eletrodoméstico-pelo-id)
- [Relatório técnico](#relatório-técnico)

## Pré-requisitos
Para rodar o projeto na sua máquina é necessário: 
- Java 17
- Maven

## Instalação
Siga as etapas abaixo para configurar e executar o projeto em seu ambiente local:
1. Clone o repositório
   ```sh
   git clone https://github.com/mayaravlima/tech-challenge-fase-1
   ```  
2. Navegue até o diretório do projeto:
   ```sh
   cd tech-challenge-fase-1
   ```
3. Instale as dependências
   ```sh
    mvn clean install
    ```
4. Rode o projeto
   ```sh
   mvn spring-boot:run
   ```
7. Acesse os endpoints 
   ```sh
   localhost:8080/
   ```
## Uso

### API Pessoas
#### Cadastro de Pessoas
   
   Endpoint para salvar o nome(name), data de nascimento(date_of_birth), genêro(gender) e relacionamento(relationship) de uma pessoa.
   
   - Endpoint:
     ```sh
     POST localhost:8080/person
     ```
   - Body:
     ```JSON
     {
         "name": "Mayara Lima",
         "date_of_birth":"26/08/1994",
         "gender": "FEMALE",
         "relationship": "OWNER"
      }
     ```
   - Validações:
       1. "name": O nome da pessoa não pode ser nulo e deve conter entre 3 a 50 caracteres
       2. "date_of_birth": A data de nascimento não pode ser nula e deve ser uma data do passado
       3. "gender": O genêro deve ser **"MALE"** para masculino, ou **"FEMALE"** para feminino. Pode ser nulo.
       4. "Relationship": Não pode ser nulo e deve ser um dos seguintes valores:
          - **SPOUSE**:  (Cônjuge) Representa o cônjuge da pessoa.
          - **SIBLING**: (Irmão/Irmã) Representa um irmão ou irmã da pessoa.
          - **PARTNER**: (Parceiro) Representa um pareceiro de união estável.
          - **RELATIVE**: (Parente) Representa um parente em geral (além dos pais e irmãos).
          - **PARENT**: (Pais) Representa um dos pais da pessoa.
          - **OWNER**: (Dono) Representa o dono do imovél.
         
   - Retornos:
        1. Registro salvo com sucesso:
      
            Status Code: **201 Created**
              ```JSON
             {
                "id": 1,
                "name": "Mayara Lima",
                "gender": "FEMALE",
                "relationship": "OWNER",
                "date_of_birth": "26/08/1994"
              }
             ```
        2. Registro já existe:

           É validado se já existe um registro para o mesmo *name* e *data_of_birth*. Se houver o seguinte erro é recebido:

           Status Code: **409 Conflict**
           ```JSON
           {
              "error": "This person already exists"
           }
           ```
        3. Genêro não aceito:

           É validade se o genêro *gender* recebido é válido. Se não for o seguinte error é recebido:

           Status Code: **400 Bad Request**
           ```JSON
           {
              "error": "Gender must be one of the values accepted: [FEMALE, MALE]"
           }
           ```
        5. Relacionamento não aceito:

            É validado se o relacionamento *relationship* recebido é válido. Se não for o seguinte error é recebido:

           Status Code: **400 Bad Request**
           ```JSON
           {
              "error": "Relationship must be one of the values accepted: [OWNER, PARENT,SPOUSE, SIBLING, PARTNER, RELATIVE]"
           }
           ```

        6. Dados recebidos não estão de acordo com as validações
           - *name* é nulo:
           
             Status Code: **400 Bad Request**
             ```JSON
             {
                "error": "Name can't be empty or null"
             }
             ```
           - *name* com tamanho menor que 3 e maior que 50:
             
             Status Code: **400 Bad Request**
             ```JSON
             {
                "error": "Name must be between 5 and 50 characters"
             }
             ```
     
             - *data_of_birth* é nulo:
          
             Status Code: **400 Bad Request**
             ```JSON
             {
                "error": "Date of birth name can't be empty or null"
             }
             ```

           - *data_of_birth* é uma data do futuro ou presente:
          
             Status Code: **400 Bad Request**
             ```JSON
             {
                "error": "Date of birth must be in the past"
             }
             ```
             
#### Listar todas as pessoas

  Endpoint para retornar uma lista de todas as pessoas cadastradas.
   
- Endpoint:
  ```sh
  GET localhost:8080/person
  ```
- Resposta:

   Status Code: **200 Ok**
    ```JSON
    [
     {
         "id": 1,
         "name": "Mayara Lima",
         "gender": "FEMALE",
         "relationship": "OWNER",
         "date_of_birth": "26/08/1994"
     },
     {
          "id": 2,
          "name": "Luna Lima",
          "gender": "FEMALE",
          "relationship": "SIBLING",
          "date_of_birth": "20/10/2020"
      }
     ]
    ```

#### Buscar pessoa pelo id

Endpoint para buscar uma pessoa pelo seu id.
   
- Endpoint:
  ```sh
  GET localhost:8080/person/{id}
  ```
- Resposta:
    1. Caso seja encontrado:
          
        Status Code: **200 Ok**
        ```JSON
         {
             "id": 1,
             "name": "Mayara Lima",
             "gender": "FEMALE",
             "relationship": "OWNER",
             "date_of_birth": "26/08/1994"
         }
        ```

    2. Se não for encontrado o registro com o id informado:
          
        Status Code: **404 Not Found**
        ```JSON
         {
             "error": "Person not found"
         }
        ```

#### Deletar pessoa pelo id

Endpoint para deletar uma pessoa pelo id
   
- Endpoint:
  ```sh
  DELETE localhost:8080/person/{id}
  ```
- Resposta:
    1. Caso seja encontrado e deletado:
          
        Status Code: **200 Ok**
        ```JSON
         {
           "message": "Person deleted successfully"
         }      
        ```

    2. Se não for encontrado o registro com o id informado:
          
        Status Code: **404 Not Found**
        ```JSON
         {
             "error": "Person not found"
         }
        ```
#### Atualizar Pessoa

Endpoint para atualizar os dados de uma pessoa
   
- Endpoint:
  ```sh
  PUT localhost:8080/person
  ```
- Body:
  ```JSON
  {
      "id": 1,
      "name": "Mayara Veloso Lima",
      "date_of_birth":"26/08/1994",
      "gender": "FEMALE",
      "relationship": "OWNER"
   }
  ```
    
  - Resposta:
      1. Caso seja encontrado e atualizado:
          
          Status Code: **200 Ok**
          ```JSON
           {
             "id": 1,
             "name": "Mayara Veloso Lima",
             "date_of_birth":"26/08/1994",
             "gender": "FEMALE",
             "relationship": "OWNER"
           }     
          ```

      2. Se não for encontrado o registro com o id informado:
          
          Status Code: **404 Not Found**
          ```JSON
           {
               "error": "Person not found"
           }
          ```

### API Endereços
#### Cadastro de endereços

Endpoint para salvar o rua(street), número(number), complemento(complement), bairro(neighborhood), cidade(city) e estado(state).

- Endpoint:
  ```sh
  POST localhost:8080/address
  ```
- Body:
  ```JSON
  {
    "street": "Avenida Wallace Simonsen",
    "number": 5,
    "complement": "Apto 12",
    "neighborhood": "Centro",
    "city": "São Bernardo do Campo",
    "state": "São Paulo"
    }
  ```
- Validações:
    1. "street", "neighborhood", "city" e "state": não podem ser nulo e deve conter entre 3 a 50 caracteres
    2. "number": não pode ser nulo e deve ser maior que 0
    3. "complement": pode ser nulo

- Retornos:
  1. Registro salvo com sucesso:
  
     Status Code: **200 Ok**
     ```JSON
     {
        "id": 1,
        "street": "Avenida Wallace Simonsen",
        "number": 45,
        "complement": "Ap 12",
        "neighborhood": "Centro",
        "city": "São Bernardo do Campo",
        "state": "São Paulo"
     }
     ```
     
    2. Se os dados recebidos não estão de acordo com as validações
       - "street", "neighborhood", "city" e "state" são nulos:
       
         Status Code: **400 Bad Request**
         ```JSON
         {
            "errors": [
                  "Number can't be null",
                  "City can't be null",
                  "State can't be null",
                  "Neighborhood can't be null",
                  "Street can't be null"
                    ]
         }
         ```
       - "street", "neighborhood", "city" e "state" com tamanho menor que 3 e maior que 50:

         Status Code: **400 Bad Request**
         ```JSON
         {
            "errors": [
                "State must be between 3 and 50 characters",
                "City must be between 3 and 50 characters",
                "Neighborhood must be between 3 and 50 characters",
                "Street must be between 3 and 50 characters"
                      ]
         }
         ```

       - "number" é menor ou igual a 0:
      
         Status Code: **400 Bad Request**
         ```JSON
         {
            "error": "Number must be more than 0"
         }
         ```
  
    3. Registro já existe:

          É validado se já existe um registro para com o mesmo "street", "number", "complement", "neighborhood", "city" e "state".
  
          Status Code: **409 Conflict**
          ```JSON
            {
              "error": "This address already exists"
            }
          ```
#### Listar todos os endereços

Endpoint para retornar uma lista de todos os endereços cadastradas.

- Endpoint:
  ```sh
  GET localhost:8080/address
  ```
- Resposta:

  Status Code: **200 Ok**
    ```JSON
    [
     {
        "id": 1,
        "street": "Avenida Wallace Simonsen",
        "number": 5,
        "complement": null,
        "neighborhood": "Centro",
        "city": "São Bernardo do Campo",
        "state": "São Paulo"
     },
     {
        "id": 2,
        "street": "Rua das Andorinhas",
        "number": 5,
        "complement": "Ap 12",
        "neighborhood": "Parque dos Passáros",
        "city": "São Bernardo do Campo",
        "state": "São Paulo"
     }
   ]
    ```


#### Buscar endereço pelo id
Endpoint para buscar um enderço pelo seu id.

- Endpoint:
  ```sh
  GET localhost:8080/address/{id}
  ```
- Resposta:
    1. Caso seja encontrado:

       Status Code: **200 Ok**
        ```JSON
         {
             "id": 1,
             "street": "Avenida Wallace Simonsen",
             "number": 5,
             "complement": null,
             "neighborhood": "Centro",
             "city": "São Bernardo do Campo",
             "state": "São Paulo"
         }
        ```

    2. Se não for encontrado o registro com o id informado:

       Status Code: **404 Not Found**
        ```JSON
         {
             "error": "Address not found"
         }
        ```

#### Deletar um endereço pelo id

Endpoint para deletar um endereço pelo id

- Endpoint:
  ```sh
  DELETE localhost:8080/address/{id}
  ```
- Resposta:
    1. Caso seja encontrado e deletado:

       Status Code: **200 Ok**
        ```JSON
         {
           "message": "Address deleted successfully"
         }      
        ```

    2. Se não for encontrado o registro com o id informado:

       Status Code: **404 Not Found**
        ```JSON
         {
             "error": "Person not found"
         }
        ```
#### Atualizar Endereço

Endpoint para atualizar os dados de um endereço

- Endpoint:
  ```sh
  PUT localhost:8080/address
  ```
- Body:
  ```JSON
  {
      "id": 1,
      "street": "Avenida Wallace Simonsen",
      "number": 201,
      "neighborhood": "Centro",
      "city": "São Bernardo do Campo",
      "state": "São Paulo"
   }
  ```

- Resposta:
    1. Caso seja encontrado e atualizado:

       Status Code: **200 Ok**
        ```JSON
         {
           "id": 1,
           "street": "Avenida Wallace Simonsen",
           "number": 201,
           "neighborhood": "Centro",
           "city": "São Bernardo do Campo",
           "state": "São Paulo"
         }     
        ```

    2. Se não for encontrado o registro com o id informado:

       Status Code: **404 Not Found**
        ```JSON
         {
             "error": "Address not found"
         }
        ```

### API Eletrodómesticos
#### Cadastro de eletrodomésticos

Endpoint para salvar o nome(name), marca(brand), modelo(model) e potência(power) para um equipamento eletrodoméstico

- Endpoint:
  ```sh
  POST localhost:8080/appliance
  ```
- Body:
  ```JSON
  {
    "name": "Microondas",
    "brand": "Electrolux",
    "model": "MI41S",
    "power": 1500
    }
  ```
- Validações:
    1. "name", "brand" e "model": não podem ser nulo e deve conter entre 3 a 50 caracteres
    2. "power": não pode ser nulo e deve ser maior que 0

- Retornos:
    1. Registro salvo com sucesso:

       Status Code: **200 Ok**
       ```JSON
       {
          "id": 1,
          "name": "Microondas",
          "model": "MI41S",
          "brand": "Electrolux",
          "power": 1500
       }
       ```

    2. Se os dados recebidos não estão de acordo com as validações
        - "name", "brand", "model" e "power" são nulos:

          Status Code: **400 Bad Request**
          ```JSON
          {
             "errors": [
                 "Name must be between 3 and 50 characters",
                 "Model must be between 3 and 50 characters",
                 "Brand must be between 3 and 50 characters",
                 "Power must be more than 0"
             ]
          }
          ```
        - "street", "neighborhood", "city" e "state" com tamanho menor que 3 e maior que 50:

          Status Code: **400 Bad Request**
          ```JSON
          {
             "errors": [
                 "Name must be between 3 and 50 characters",
                 "Model must be between 3 and 50 characters",
                 "Brand must be between 3 and 50 characters"
             ]
          }
          ```

        - "power" é menor ou igual a 0:

          Status Code: **400 Bad Request**
          ```JSON
          {
             "error": "Power must be more than 0"
          }
          ```

    3. Registro já existe:

       É validado se já existe um registro com mesmo "name", "model", "brand" e "power".

       Status Code: **409 Conflict**
          ```JSON
            {
              "error": "This appliance already exists"
            }
          ```
#### Listar todas os eletrodomésticos cadastrados

Endpoint para retornar uma lista de todos os eletrodomésticos cadastradas.

- Endpoint:
  ```sh
  GET localhost:8080/appliance
  ```
- Resposta:

  Status Code: **200 Ok**
    ```JSON
    [
       {
        "id": 1,
        "name": "Microondas",
        "model": "MI41S",
        "brand": "Electrolux",
        "power": 1500
       },
       {
        "id": 2,
        "name": "Geladeira",
        "model": "GI41S",
        "brand": "Electrolux",
        "power": 2000
       }
    ]
    ```

#### Buscar eletrodoméstico pelo id

   Endpoint para buscar um eletrodoméstico pelo seu id.

- Endpoint:
  ```sh
  GET localhost:8080/appliance/{id}
  ```
- Resposta:
    1. Caso seja encontrado:

       Status Code: **200 Ok**
        ```JSON
         {
             "id": 1,
             "name": "Microondas",
             "model": "MI41S",
             "brand": "Electrolux",
             "power": 1500
         }
        ```

    2. Se não for encontrado o registro com o id informado:

       Status Code: **404 Not Found**
        ```JSON
         {
             "error": "Appliance not found"
         }
        ```

#### Deletar um eletrodoméstico pelo id

   Endpoint para deletar um eletrodoméstico pelo id

- Endpoint:
  ```sh
  DELETE localhost:8080/appliance/{id}
  ```
- Resposta:
    1. Caso seja encontrado e deletado:

       Status Code: **200 Ok**
        ```JSON
         {
           "message": "Appliance deleted successfully"
         }      
        ```

    2. Se não for encontrado o registro com o id informado:

       Status Code: **404 Not Found**
        ```JSON
         {
             "error": "Appliance not found"
         }
        ```
#### Atualizar Eletrodoméstico

   Endpoint para atualizar os dados de um eletrodoméstico

- Endpoint:
  ```sh
  PUT localhost:8080/address
  ```
- Body:
  ```JSON
  {
      "id": 1,
      "name": "Microondas",
      "brand": "Electrolux",
      "model": "MI41S",
      "power": 2500
   }
  ```

- Resposta:
    1. Caso seja encontrado e atualizado:

       Status Code: **200 Ok**
        ```JSON
         {
           "id": 1,
           "street": "Avenida Wallace Simonsen",
           "number": 201,
           "neighborhood": "Centro",
           "city": "São Bernardo do Campo",
           "state": "São Paulo"
         }     
        ```

    2. Se não for encontrado o registro com o id informado:

       Status Code: **404 Not Found**
        ```JSON
         {
             "error": "Address not found"
         }
        ```

## Relatório Técnico
No projeto foram utilizados:
- Java 17: Optei por utilizar a versão mais recente do Java, a versão 17. Escolhi essa versão porque quero atualizar os meus conhecimentos em relação às novas funcionalidades e melhorias introduzidas no Java.
- Maven: Optei por ser uma ferramenta que já utilizo no meu dia a dia profissional e por ter uma ampla adoção que facilita para encontrar documentações.
- Spring Boot Starter 3.1.0: Utilizei a versão mais recente do Spring Boot Starter para aproveitar as atualizações e melhorias de segurança disponíveis na época em que o projeto foi criado.
- Spring Boot Starter Validation: Essa biblioteca do Spring facilitou a validação dos dados no projeto, fornecendo anotações como @NotNull, @Min, @Max, entre outras. Com isso, foi possível realizar a validação dos dados de forma fácil e consistente, sem a necessidade de escrever manualmente a lógica de validação.
- Lombok: Optei por utilizar a biblioteca Lombok para reduzir a quantidade de código repetitivo, eliminando a necessidade de escrever construtores, getters, setters, equals e hashCode manualmente. Isso tornou o código mais limpo, legível e coeso.
- Spring Boot Starter Web: Utilizei o Spring Boot Starter Web devido à sua configuração fácil e abrangente para o gerenciamento de solicitações HTTP, roteamento de URL e manipulação de respostas, facilitando o desenvolvimento da API.

A arquitetura escolhida para essa primeira entrega foi a DDD (Domain-Driven Design). Optei por essa arquitetura, pois permite a organização do código em torno de conceitos de domínio, favorecendo a modularidade, reutilização de código e facilitando a compreensão do sistema. Como inicialmente apenas o método HTTP POST seria implementado, não vi a necessidade de criar uma API separada para cada entidade manipulada, mantendo o foco na funcionalidade principal do projeto.
Optei em adicionar os outros métodos HTTP para deixar a API mais completa e simular uma manipulação de dados mesmo sem utilizar um banco de dados.

Durante o desenvolvimento, alguns desafios foram enfrentados:

- Validação de Dados: Um dos desafios foi garantir que os dados fornecidos pelos usuários da API fossem válidos e estivessem no formato correto. Para isso, foram utilizadas as anotações de validação como @NotNull, @Size, @Pattern, entre outras. Essas anotações foram aplicadas nas entidades de Endereço, Pessoa e Eletrodoméstico, assegurando a integridade dos dados.
- Enums: Para garantir que o gênero e o relacionamento fornecidos pelos usuários seguissem um padrão, foram criadas classes de enum com os possíveis valores. Além disso, foi implementado o método fromJson, anotado com @JsonCreator, para comparar a string recebida com os possíveis valores do enum ignorando maiúsculas e minúsculas, e assim o código aceitar ambos os formatos.
- Tratamento de Exceções: Mecanismos de tratamento de exceções foram implementados usando a anotação @ExceptionHandler do Spring. Essa abordagem permitiu informar corretamente os erros de validação, retornando os códigos de status apropriados e mensagens de erro específicas para cada tipo de falha.
