# Pós-Tech Arquitetura e Desenvolvimento Java
- Fase 1: Nessa primeira fase o objetivo era criar API para o CRUD de Pessoas, Endereços e Eletrodoméstico focado na integridade dados.

## Índice

- [Pré-requisitos](#pré-requisitos)
- [Instalação](#instalação)
- [Uso](#uso)
- [Relatório técnico](#relatório-técnico)

## Pré-requisitos
Para rodar o projeto na sua máquina é necessário: 
- Java 17
- Maven

## Instalação
Siga as etapas abaixo para configurar e executar o projeto em seu ambiente local:
1. Clone o repositório
   ```sh
   git clone https://github.com/mayaravlima/tech-challenge-fase-2
   ```  
2. Navegue até o diretório do projeto:
   ```sh
   cd tech-challenge-fase-2
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
Foi utilizado o Swagger para documentar a API. Para acessar a documentação basta acessar o endpoint:
    
```sh
    http://localhost:8080/swagger-ui/index.html#/
```


## Relatório Técnico
Dependências novas utilizadas:
- Para essa fase optei em utilizar o banco de dados H2, pois ele é incorporado diretamente na API, não exigindo uma instalação separada ou configurações complexas.
- À medida que a quantidade de endpoints se expandia, optei por adotar o Swagger OpenAPI para automatizar a geração da documentação.


Estrutura da API:
- A arquitetura do banco de dados pensanda foi: 

![img.png](img.png)

Parti do princípio que um usuário tem várias propriedades, e em cada propriedade há um endereço com várias pessoas e vários eletrodomésticos.
- Um usuário tem associado várias pessoas e ele mesmo é salvo com uma pessoa do tipo OWNER.
- Um usuário tem associado vários endereços.
- Um usuário tem associado vários eletrodomésticos.
- Uma pessoa tem associado vários endereços.
- Um endereço tem associado vários eletrodomésticos.
- Um eletrodoméstico tem associado apenas um endereço.

O seguinte fluxo de cadastro foi pensando:
- 1º Cadastrar um usuário com uma pessoa do tipo OWNER.
- 2º Cadastrar pessoas associadas a esse usuário.
- 3º Cadastrar endereços associados a esse usuário.
- 4º Cadastrar eletrodomésticos associados a esse usuário.
- 5º Associar eletrodomésticos a um endereço.
- 6º Associar pessoas a um endereço.

Decisões de projeto:
- Somente o usuário controla o sistema. Ou seja, todas as operações de CRUD são feitas através do username dele.
- Se um usuário é deletado, todas as pessoas, endereços e eletrodomésticos associados a ele também são deletados.
- Se um endereço é deletado, todos os eletrodomésticos associados a ele também são deletados. Ou seja, o endereço é o dono da relação de eletrodomésticos. A relacão entre eletrodomésticos e pessoas é feita através do endereço. Simulando um casa que tem seus eletrodomésticos que pode ser utilizado por todas as pessoas que moram nela.
- Um usuário só pode ter uma pessoas cadastrada como PARTNER ou SPOUSE, e duas pessoas como PARENT.
- O número de CPF é único para cada pessoa.
- O email e username é único para cada usuário.
