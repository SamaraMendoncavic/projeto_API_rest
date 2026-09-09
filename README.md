# Projeto API REST — Front-End

Front-end (HTML, CSS e JavaScript puro) de uma plataforma de cadastro e login de usuários, com seleção de gêneros literários favoritos. Esta interface consome a API REST desenvolvida em Spring Boot disponível no repositório de back-end.

**Repositório do Back-End:** [projeto_API_rest_Back_End](https://github.com/SamaraMendoncavic/projeto_API_rest_Back_End.git)

## Estrutura do projeto (Arquivos)

```
projeto_API_rest/
├── css/
│   └── style.css
└── frontend/
    ├── index.html        # Página inicial
    ├── index_2.html      # Página inicial (variação)
    ├── login.html        # Tela de login
    ├── cadastro.html     # Tela de cadastro de usuário
    ├── js/
    │   └── sessao.js     # Controle de sessão (sessionStorage)
    └── assets/           # Imagens e ícones
```

## Como executar

Este projeto é HTML/CSS/JS estático. Basta um navegador e um servidor local simples para servir os arquivos (necessário porque as páginas fazem requisições `fetch` para a API).

### Pré-requisitos

- Um navegador atualizado (Chrome, Firefox, Edge etc.)
- **O back-end (API) precisa estar rodando em `http://localhost:8080`** antes de usar login e cadastro — veja o repositório do back-end linkado acima.

### Passo a passo

1. Clone este repositório:
   ```bash
   git clone https://github.com/SamaraMendoncavic/projeto_API_rest.git
   cd projeto_API_rest/frontend
   ```

2. Suba um servidor local na pasta `frontend`. Algumas opções:

   **Usando a extensão Live Server (VS Code) — recomendado**
   - Abra a pasta do projeto no VS Code.
   - Clique com o botão direito em `index.html` (ou `login.html`) → **"Open with Live Server"**.

3. Acesse no navegador o endereço indicado pelo servidor (ex.: `http://localhost:5500`) e abra `login.html` ou `cadastro.html`.

> Não abra os arquivos `.html` diretamente com duplo clique (`file://`) — algumas chamadas à API podem ser bloqueadas pelo navegador. Sempre sirva os arquivos por um servidor HTTP local.

## Integração com a API

As páginas consomem os seguintes endpoints da API (base `http://localhost:8080`):

| Página          | Endpoint                | Método |
|------------------|--------------------------|--------|
| `login.html`     | `/usuarios/autenticar`  | POST   |
| `cadastro.html`  | `/generos-literarios`   | GET    |
| `cadastro.html`  | `/usuarios/cadastrar`   | POST   |

Certifique-se de que a API do back-end esteja em execução antes de testar login e cadastro. Instruções completas em: [projeto_API_rest_Back_End](https://github.com/SamaraMendoncavic/projeto_API_rest_Back_End).