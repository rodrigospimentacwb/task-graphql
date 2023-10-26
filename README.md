# Projeto de AWS com Spring Boot 3, Java 21 e GraphQL

Este é um projeto desenvolvido para a disciplina de AWS na PUC MG, utilizando Spring Boot 3, Java 21 e GraphQL. O projeto consiste em uma aplicação que gerencia usuários e tarefas em um banco de dados PostgreSQL.

## Pré-requisitos

Antes de executar o projeto, certifique-se de que você tenha as seguintes ferramentas instaladas:

- Docker: [Instalação do Docker](https://docs.docker.com/get-docker/)
- Docker Compose: [Instalação do Docker Compose](https://docs.docker.com/compose/install/)

## Configuração

Para iniciar o projeto, siga estas etapas:

1. Clone este repositório em sua máquina local:

   ```shell
   git clone <URL_DO_REPOSITORIO>

2. Navegue até o diretório raiz do projeto:

    ```shell
   cd aws-spring-boot-3-graphql

3. Execute o Docker Compose para iniciar o banco de dados PostgreSQL e a aplicação:

    ```shell
   docker-compose up -d

Isso criará um ambiente de desenvolvimento com o banco de dados e a aplicação configurados e prontos para uso.

4. Acesse a interface do GraphQL em seu navegador:

    http://localhost:8080/graphiql?path=/graphql

# Mapa das APIs Disponíveis

A aplicação oferece as seguintes operações GraphQL:

   ```
      mutation addUsr {
        createUser(email: "user1@hotmail.com", name: "usuario 1") {
          uuid
          name
          email
        }
      }
      
      query listUsers {
        listUsers {
          email
          name
          uuid
        }
      }
      
      mutation addNewTask {
        createTask(
          description: "task 2"
          userId: "043e1945-e737-4dbe-a197-5d2d8a48fae5"
        ) {
          description
          status
          user {
            email
            name
            uuid
          }
          uuid
        }
      }
      
      query listAllTasks {
        listAllTasks {
          description
          status
          user {
            email
            name
            uuid
          }
          uuid
        }
      }
      
      mutation deleteTask {
        deleteTask(uuid: "8adbd4e4-998d-4e98-b3be-0a4e9d461233")
      }
      
      mutation updateTask {
        updateTask(
          taskStatus: COMPLETED
          uuid: "aebabeb2-1883-479a-9262-e1a1263f9362"
          description: "tarefa completa"
        ) {
          description
          status
          user {
            email
            uuid
            name
          }
          uuid
        }
      }
      
      query findTaskByUserId {
        findTasksByUserId(userId: "043e1945-e737-4dbe-a197-5d2d8a48fae5") {
          description
          status
          uuid
          user {
            email
            name
            uuid
          }
        }
      }
      
      query listTaskByStatus {
        listTaskByStatus(taskStatus: NEW) {
          description
          status
          uuid
          user {
            email
            name
            uuid
          }
        }
      }
      
      query taskById {
        taskById(uuid: "14dc639f-28a3-40d3-8355-9603a3c5bb2d") {
          status
          description
          uuid
          user {
            email
            name
            uuid
          }
        }
      }
      
      query userById {
        userById(uuid: "043e1945-e737-4dbe-a197-5d2d8a48fae5") {
          email
          name
          uuid
        }
      }
   ```

## Encerrando o Projeto
Para encerrar o projeto, você pode pressionar Ctrl+C no terminal onde o Docker Compose está sendo executado e, em seguida, executar o seguinte comando para desligar os contêineres:

   ```shell
   docker-compose down

Isso encerrará o ambiente de desenvolvimento.