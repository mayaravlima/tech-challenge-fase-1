# Pós-Tech Arquitetura e Desenvolvimento Java
- Fase 1:  Nessa primeira fase o objetivo era criar API para o CRUD de Pessoas, Endereços e Eletrodoméstico focado na integridade dados.

## Índice

- [Pré-requisitos](#pré-requisitos)
- [Instalação](#instalação)
- [Uso](#uso)

## Pré-requisitos
Para rodar o projeto em sua máquina é necessário: 
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
1. Cadastro de Pessoas
   
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
             
2. Listar todas as pessoas

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
         },
        ]
       ```

3. Buscar pessoa pelo id

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

4. Deletar pessoa pelo id

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
5. Atualizar Pessoa

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



