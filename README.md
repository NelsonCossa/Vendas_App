# Sistema de Gestão de Pedidos 

Este projeto consiste numa aplicação backend desenvolvida em Java com o objetivo de gerir produtos, pedidos, carrinho de compras e pagamentos. 
É voltado para um sistema com dois tipos de utilizadores: Cliente e Administrador. A aplicação oferece operações CRUD e autenticação básica para cada tipo de utilizador.

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

   # 🔵 USER CONTROLLER – /users

# ➕ Criar Utilizador
http POST :8080/users name="Nelson" email="nelson@example.com" phone="841234567"

# 📄 Listar Todos os Utilizadores
http GET :8080/users

# 🔍 Buscar Utilizador por ID
http GET :8080/users/1

# ♻️ Atualizar Utilizador
http PUT :8080/users/1 name="Nelson Atualizado" email="nelson.novo@example.com" phone="848888888"

# ❌ Remover Utilizador
http DELETE :8080/users/1


# 🟢 PRODUCT CONTROLLER – /products

# 📄 Listar Todos os Produtos
http GET :8080/products

# 🔍 Buscar Produto por ID
http GET :8080/products/1


# 🟡 CATEGORY CONTROLLER – /categories

# 📄 Listar Todas as Categorias
http GET :8080/categories

# 🔍 Buscar Categoria por ID
http GET :8080/categories/1


# 🟠 ORDER CONTROLLER – /orders

# 📄 Listar Todos os Pedidos
http GET :8080/orders

# 🔍 Buscar Pedido por ID
http GET :8080/orders/1

