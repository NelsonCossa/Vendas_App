# Sistema de Gestão de Pedidos 

Este projeto consiste numa aplicação backend desenvolvida em Java com o objetivo de gerir produtos, pedidos, carrinho de compras e pagamentos. 
É voltado para um sistema com dois tipos de utilizadores: Cliente e Administrador. A aplicação oferece operações CRUD para cada utilizador

## Tecnologias Utilizadas

- Java
- Spring Boot
- Spring MVC
- Spring Data JPA
- MySQL (Base de dados disponivel em  src/main/resources )
- Swagger com SpringDoc OpenAPI 3

## Práticas Adotadas

- Princípios SOLID
- DRY (Don't Repeat Yourself)
- Injeção de Dependências
- Tratamento de erros
- Documentação automática com Swagger (OpenAPI 3)

## Funcionalidades

- Cadastro de utilizadores
- Consulta e gestão de produtos
- Adição de produtos ao carrinho
- Criação de pedidos com pagamentos associados

- Visualização de histórico de pedidos (cliente)
- Consulta de pedidos do sistema (admin)
- Gestão de categorias de produtos (CRUD)

## Como Executar

1. `**Clonar o repositório**
   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
   cd seu-repositorio
   A API poderá ser acessada em localhost:7707. O Swagger poderá ser visualizado em localhost:7707/swagger-ui.html


2.**API Endpoints**

 Claro! Vou ampliar e organizar o teu README com base nos comandos HTTP que forneceste, adicionando os endpoints que faltam para cada controller e mantendo o padrão de comandos HTTP com exemplos do `httpie`. Ficará assim:

---

# 📌 Endpoints da API

---

## 🔵 USER CONTROLLER – `/users`

➕ **Criar Utilizador**

```bash
http POST :7707/users name="Nelson" email="nelson@example.com" phone="841234567"
```

📄 **Listar Todos os Utilizadores**

```bash
http GET :7707/users
```

🔍 **Buscar Utilizador por ID**

```bash
http GET :7707/users/1
```

♻️ **Atualizar Utilizador**

```bash
http PUT :7707/users/1 name="Nelson Atualizado" email="nelson.novo@example.com" phone="848888888"
```

❌ **Remover Utilizador**

```bash
http DELETE :7707/users/1
```

---

## 🟢 PRODUCT CONTROLLER – `/products`

📄 **Listar Todos os Produtos**

```bash
http GET :7707/products
```

🔍 **Buscar Produto por ID**

```bash
http GET :7707/products/1
```

➕ **Criar Produto**

```bash
http POST :7707/products name="Produto Exemplo" price:=100 stock:=50 category_id:=3
```

♻️ **Atualizar Produto**

```bash
http PUT :7707/products/1 name="Produto Atualizado" price:=120 stock:=40
```

❌ **Remover Produto**

```bash
http DELETE :7707/products/1
```

---

## 🟡 CATEGORY CONTROLLER – `/categories`

📄 **Listar Todas as Categorias**

```bash
http GET :7707/categories
```

🔍 **Buscar Categoria por ID**

```bash
http GET :7707/categories/1
```

➕ **Criar Categoria**

```bash
http POST :7707/categories name="Categoria Exemplo"
```

♻️ **Atualizar Categoria**

```bash
http PUT :7707/categories/1 name="Categoria Atualizada"
```

❌ **Remover Categoria**

```bash
http DELETE :7707/categories/1
```

---

## 🟠 ORDER CONTROLLER – `/orders`

📄 **Listar Todos os Pedidos**

```bash
http GET :7707/orders
```

🔍 **Buscar Pedido por ID**

```bash
http GET :7707/orders/1
```

➕ **Criar Pedido**

```bash
http POST :7707/orders user_id:=1 product_ids:='[1, 2]' total:=300 status:=1
```

♻️ **Atualizar Pedido**

```bash
http PUT :7707/orders/1 status:=2
```

❌ **Remover Pedido**

```bash
http DELETE :7707/orders/1
```

🔎 **Buscar Pedidos por Estado**

```bash
http GET :7707/orders/status?code=1


