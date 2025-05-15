# API loja

## Requisitos para o projeto;

### Descrição

Para executar essa API você precisa ter instalado o Java 17
(ou superior) o Docker para execultar o banco de dados, esse
projeto foi desenvolvido usando o Postgres como banca de dados para
retenção de dados então será nessesario instalar a
imagem do postgres, segue abaixo os links para download:

### Links:

#### Java

```
https://www.oracle.com/java/technologies/downloads/
```

#### Docker

```
https://www.docker.com/get-started/
```

#### Imagem Postgres

`````
https://hub.docker.com/_/postgres
`````

---

## Preparação

Antes de executar o projeto, você vai precisar gerar 2 chaves criptografadas,
uma pública e a outra privada, você pode fazer isso pelo seu terminal,
localize a pasta resources usando o comando "cd", abra o terminal do projeto
e digite os comandos abaixo.

#### Va ate a pasta resources

`````
cd ser/main/resources
`````

#### Gerando chave privada

`````
openssl genssa > app.key
`````

#### Gerando chave prublica

`````
openssl rsa -in app.key -pubout -out app.pub
`````

Se você fez tudo certo na sua pasta resources agora tem dois arquivos novos,
o app.key e app.pub, agora você está pronto para executar o projeto sem erros.

---

## Descrição;

Esse projeto foi desenvolvido para desenvolver meus conhecimentos em spring
secrurity, aperfeiçoar minhas habilidades de desenvolvimento de software
backend suando Java. Nele você encontra, além de rotas seguras com token JWT,
rotas que exigem níveis diferentes de cargo. Esse projeto conta com 3 endpoints
principais: AUTH para castrar usuários e fazer login, PRODUCT para cadastrar
e listar produtos da loja e SHOPPLIST que funciona como um carrinho onde o
usuário pode adicionar produtos, listar os produtos salvos e limpar a lista.

## Rotas

### Auth

Rota para registro de usuário de login. Essa rota é pública, não
precisa de autenticação para usar.

#### Register

Função de registro de usuário, é necessário enviar um JSON com username,
passaword e usertype. Os tipos de usuários aceitos são: USER e ADMIN,
ela não retorna body apenas o status **201 Created**.

``
    POST http://localhost:8080/auth/register
``

#### Exemplo de Envio

```
{
    "username": "LeoSUSER2",
    "password": "1234",
    "userType": "USER"
}
```

#### Exemplo de Resposta

```
    Status: 201 Created
```

____

#### Login

Função para fazer login no sistema, é necessário enviar um JSON com o username e
password e ela retorna um JSON com accessToken correspondendo ao token do
usuario e expiresIn correspondendo ao tempo de expiração do token em
segundos.

``
    POST http://localhost:8080/auth/login
``

#### Exemplo de Envio

```
{
    "username": "LeoSADMIN",
    "password": "1234"
}
```

#### Exemplo de Resposta

```
{
    "accessToken": "eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJteWJhY2...",
    "expiresIn": 300
}
```

----

### Product

Rota correspondente aos produtos da loja. Com ela, você pode adicionar e listar os
produtos da loja. Essa rota tem restrição de cargos, apenas o ADMIN pode adicionar
produtos ao banco de dados e você tem que estar logado para listar os produtos.

#### Adicionar

Função para adicionar produto ao banco de dados da loja é necessário enviar um
JSON name, category, price. É necessário estar logado com ADMIN para usá-la,
essa função não retorna body, apenas o status **201 created**.

``
    POST http://localhost:8080/product
``

#### Exemplo de Envio

```
{
    "name": "chapeu",
    "category": "Moda",
    "price": 1000
}
```

#### Exemplo de Resposta

```
status: 201 created
```

_____

#### listar

Função para listar os produtos da loja, é necessário estar logado para acessar,
mas qualquer tipo de usuário pode usá-la, você tem a opção de fazer uma busca
geral ou filtrar por uma categoria específica.

``
    GET http://localhost:8080/product
``

#### Exemplo de Resposta busca geral

```
[
    {
        "name": "Notebook Gamer",
        "category": "Eletrônicos",
        "price": 450000
    },
    {
        "name": "Cafeteira",
        "category": "Eletrodomésticos",
        "price": 35000
    },
    {
        "name": "Tênis Esportivo",
        "category": "Moda",
        "price": 55000
    },
    {
        "name": "Livro de Ficção",
        "category": "Livros",
        "price": 9000
    },
    {
        "name": "Mochila Escolar",
        "category": "Papelaria",
        "price": 20000
    }
]
```

#### Exemplo de Resposta com filtro

``
    GET http://localhost:8080/product?category=Moda
``

```
[
    {
        "name": "Camisa Polo",
        "category": "Moda",
        "price": 12000
    },
    {
        "name": "Jaqueta de Couro",
        "category": "Moda",
        "price": 60000
    },
    {
        "name": "Óculos de Sol",
        "category": "Moda",
        "price": 30000
    },
    {
        "name": "Relógio Elegante",
        "category": "Moda",
        "price": 75000
    }
]
```
-----


### ShoppList
Rota que serve para o usuário salvar itens da loja e saber o valor total que 
pagará por eles. Essa rota tem restrição de cargo, apenas USER pode salvar 
produtos e apenas ADMIN pode excluir listas.


#### Adicionar
Função para adicionar produtos à lista, é necessário enviar um JSON com userId,
productId e quantity, você precisa estar logado como USER para usá-la, ela não
retorna body, só o status **201 created**.

``
    POST http://localhost:8080/shoppList
``

#### Exemplo de Envio

```
{
    "userId": 11,
    "productId": 17,
    "quantity": 2
}
```

#### Exemplo de Resposta

```
status: 201 created
```

#### Listar
Função para listar os itens da lista do usuário logado e o valor total da lista 
de produtos. É necessário estar logado como USER para acessá-la

``
    GET http://localhost:8080/shoppList
``

#### Exemplo de Resposta

```
{    
    "username": "LeoSUSER2",
    "itemList": [
        {
            "itemName": "Máquina de Lavar", 
            "quantity": 1, 
            "price": 250000 
        },
        {
            "itemName": "Tênis Esportivo",
            "quantity": 2,
            "price": 55000
        }
    ],
    "totalValue": 360000
}

```
---

#### Deletar
Função para apagar uma lista de produtos de um usuário. É necessário enviar o id
do usuário, que terá a lista apagada. Você precisa estar logado com ADMIN para 
usá-la. Ela não retorna body apenas o status **200 OK**.

``
    DELETE http://localhost:8080/shoppList
``

#### Exemplo de Resposta

```
status: 200 OK
```
