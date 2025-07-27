

![banner-balunga](https://github.com/sakurahoney98/balunga_game/blob/main/src/main/resources/static/images/background-home.png?raw=true)

# Balunga
![Status](https://img.shields.io/badge/status-finalizado-gree) ![Versão](https://img.shields.io/badge/vers%C3%A3o-0.0.1--SNAPSHOT-blue) ![License](https://img.shields.io/badge/licen%C3%A7a-Apache_License_2.0-yellow)

Balunga é um jogo web de cartas estratégico desenvolvido em Java com Spring Boot. O sistema simula partidas com jogadores humanos e automatizados, controlando todas as regras, turnos, distribuição de cartas, penalidades e descartes.

## Tecnologias

As seguintes tecnologias foram utilizadas na construção do projeto:

### Front-End
- HTML
- CSS
- JavaScript

### Back-End
- Java 21
- Spring Boot

## Pré-requisitos
- Ter instalado o `Docker` e `docker-compose`  ou IDE que suporte Java

## Instalação

Clone o repositório:

```shell
git clone https://github.com/sakurahoney98/balunga_game.git
```


## Execução

Faça uma cópia do **.env.dist**, que está na raiz do projeto, como `.env`e modifique variaveis de acordo com o seu ambiente:

```shell
cd balunga_game
cp env.dist .env
```

### Em ambiente local (com IDE)

1. Importe o projeto para sua IDE de desenvolvimento
2. Execute a classe `BalungaGameApplication`

### Em ambiente local (com Docker)

1. Execute o projeto:
    Linux ou macOS:
    
    ```bash
       chmod +x docker-run.sh
       ./docker-run.sh --local
    ```

### Em produção

1. Execute o projeto:
    Linux ou macOS:
    
    ```bash
       chmod +x docker-run.sh
       ./docker-run.sh --server
    ```

**Acesse o sistema via navegador:**  
[http://localhost:8080](http://localhost:8080) *(ou a porta que estiver definida em `HOST_PORT` no `.env`)*


## Contribuindo

Contribuições são sempre bem-vindas!

Veja o  [manual de contribuição](CONTRIBUTING.md) para saber como começar.


## Conecte-se comigo
[![Email](https://img.shields.io/badge/Email-red?style=for-the-badge&logo=gmail&logoColor=white)](mailto:caroline.santana@ucsal.edu.br) [![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/caroline-santana-36378215a/)
