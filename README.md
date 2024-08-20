# Projeto de Gerenciamento de Vídeos

Este projeto é uma aplicação Java para o gerenciamento de vídeos e seus respectivos gêneros, utilizando a API de persistência de dados db4o. A aplicação inclui uma interface gráfica desenvolvida com Swing e implementa operações de CRUD e consultas complexas.

## Estrutura do Projeto

### Entidades

- **Video**
  - `id`: Identificador único.
  - `titulo`: Título do vídeo.
  - `link`: Link associado ao vídeo.
  - `classificacao`: Classificação do vídeo.
  - `generos`: Lista de gêneros associados ao vídeo.

- **Genero**
  - `nome`: Nome do gênero.
  - `videos`: Lista de vídeos associados ao gênero.

### Principais Funcionalidades

A lógica de negócio da aplicação é centralizada na classe `Fachada`, que provê as seguintes funcionalidades:

- **Inicializar e Finalizar**:
  - `inicializar()`: Abre a conexão com o banco de dados.
  - `finalizar()`: Fecha a conexão com o banco de dados.

- **CRUD de Vídeos**:
  - `criarVideo(String titulo, String link, int classificacao)`: Cria um novo vídeo.
  - `alterarTituloDoVideo(String titulo, String novoTitulo)`: Altera o título de um vídeo existente.
  - `alterarClassificacaoDoVideo(String titulo, int classificacao)`: Altera a classificação de um vídeo existente.
  - `excluirVideo(String titulo)`: Exclui um vídeo, removendo suas associações com os gêneros.

- **CRUD de Gêneros**:
  - `criarGenero(String nome)`: Cria um novo gênero.
  - `excluirGenero(String nome)`: Exclui um gênero, removendo suas associações com os vídeos.

- **Associação de Vídeos e Gêneros**:
  - `categorizarVideo(String titulo, String nome)`: Associa um vídeo a um gênero existente.

- **Consultas**:
  - `listarVideos()`: Retorna uma lista de todos os vídeos.
  - `listarGeneros()`: Retorna uma lista de todos os gêneros.
  - `videosPorClassificacao(int valor)`: Retorna vídeos com a classificação específica.
  - `videosPorGenero(String nome)`: Retorna vídeos associados a um determinado gênero.
  - `generosComMaisVideos(int valor)`: Retorna gêneros que possuem mais de N vídeos.

### Persistência de Dados

A persistência dos dados é gerida pelo banco de dados db4o, que armazena objetos diretamente, proporcionando uma maneira simples e eficiente de gerenciar dados complexos em Java. As classes `DAO`, `DAOGenero` e `DAOVideo` são responsáveis pela conexão, transações e operações CRUD:

- **Conexão e Transações**:
  - `open()`: Abre a conexão com o banco de dados.
  - `close()`: Fecha a conexão.
  - `begin()`: Inicia uma transação.
  - `commit()`: Comita uma transação.
  - `rollback()`: Desfaz uma transação.

- **CRUD Genérico**:
  - `create(T obj)`: Cria um novo objeto.
  - `read(Object chave)`: Lê um objeto baseado em uma chave.
  - `update(T obj)`: Atualiza um objeto existente.
  - `delete(T obj)`: Deleta um objeto existente.
  - `readAll()`: Lê todos os objetos de um tipo.

### Interface Gráfica

O projeto inclui uma interface gráfica desenvolvida com Swing, que permite ao usuário interagir com o sistema de forma intuitiva, realizando operações de CRUD e consultas de forma visual.

## Como Executar

1. **Clonar o repositório**:
    ```sh
    git clone https://github.com/seu-usuario/seu-repositorio.git
    ```

2. **Importar o projeto em uma IDE** como Eclipse ou IntelliJ.

3. **Executar a classe principal**:
   - Inicialize a aplicação executando a classe principal que abre a interface gráfica.

## Requisitos

- **Java 8+**
- **db4o 8.1**

## Contribuições

Sinta-se à vontade para contribuir com este projeto através de pull requests. Para grandes mudanças, por favor abra uma issue primeiro para discutir o que você gostaria de mudar.

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE).
