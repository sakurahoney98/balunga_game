
# Contribuindo com o Balunga

Antes de tudo, obrigado por dedicar um tempo para contribuir! ❤️

ATodos os tipos de contribuição são bem-vindos e valorizados. Veja o [Índice](#índice) para descobrir diferentes formas de ajudar e detalhes sobre como lidamos com cada tipo de contribuição. Leia a seção correspondente antes de enviar sua colaboração. Isso facilita bastante o trabalho de quem mantém o projeto e melhora a experiência para todos os envolvidos. A comunidade agradece a sua participação! 🎉

> E se você curtiu o projeto mas está sem tempo para contribuir, tudo bem! Há outras formas simples de apoiar o projeto e demonstrar sua apreciação, o que também nos deixa muito felizes:

> - Dê uma estrela no repositório
> - Compartilhe no Twitter (ou outra rede)
> - Mencione este projeto no README do seu repositório
> - Fale sobre ele em meetups ou com colegas e amigos


## Índice

- [Tipos de Contribuição](#tipos-de-contribuição)
- [Tenho uma Dúvida](#tenho-uma-dúvida)
- [Quero Contribuir](#quero-contribuir)
  - [Aviso Legal ](#aviso-legal)
  - [Relatando Bugs](#relatando-bugs)
    - [Antes de enviar um relatório de bug](#antes-de-enviar-um-relatório-de-bug)
    - [Como enviar um bom relatório de bug?](#como-enviar-um-bom-relatório-de-bug)
  - [Sugerir Melhorias](#sugerir-melhorias)
    - [Antes de sugerir uma melhoria](#antes-de-sugerir-uma-melhoria)
    - [Como eu envio uma sugestão?](#como-eu-envio-uma-sugestão)
  - [Contribuindo com Código](#contribuindo-com-código)
    - [Antes de contribuir](#antes-de-contribuir)
    - [Como inicio uma alteração?](#como-inicio-uma-alteração)
    - [Como envio um Pull Request?](#como-envio-um-pull-request)
    - [O que fazer depois de enviar um Pull Request?](#o-que-fazer-depois-de-enviar-um-pull-request)
  - [Padrão de Commits e Branches](#padrão-de-commits-e-branches)
    - [Convenção de Branches](#convenção-de-branches)
    - [Convenção de Commits](#convenção-de-commits)




## Tipos de Contribuição

Você pode contribuir de várias formas:

- 📦 Corrigindo bugs
- 🧠 Sugerindo melhorias na lógica do jogo
- 🧪 Escrevendo testes
- 📝 Melhorando a documentação (README, comentários, etc)
- 🐳 Otimizando a configuração Docker/Deploy
- 💬 Abrindo issues com feedbacks ou sugestões

## Tenho uma Dúvida

> Antes de perguntar, assumimos que você já leu a [Documentação](https://github.com/sakurahoney98/balunga_game/tree/53d047b8b8c4db8ceb4a89c2e8290422464e2e6c/docs) disponível.

Antes de abrir uma dúvida, recomendamos procurar nas [issues existentes](https://github.com/sakurahoney98/balunga_game.git/issues), pois talvez alguém já tenha enfrentado o mesmo problema. Se encontrar uma issue relacionada e ainda tiver dúvidas, você pode comentar nela. Também é válido pesquisar na internet antes de perguntar.

Se ainda assim precisar de ajuda, siga estas orientações:

- Abra uma [nova issue](https://github.com/sakurahoney98/balunga_game.git/issues/new).
- Descreva o máximo de contexto possível sobre o que está acontecendo.
- Informe as versões utilizadas (Java, Docker, sistema operacional etc.), conforme for relevante.

Responderemos o mais rápido possível. 💬


## Quero Contribuir

> ### Aviso Legal 
> Ao contribuir com este projeto, você afirma que é o autor do conteúdo, possui os direitos sobre ele, e autoriza seu uso sob a licença do projeto.

### Relatando Bugs

#### Antes de enviar um relatório de bug

Um bom relatório de bug deve ser claro e detalhado, para que possamos reproduzir e corrigir o problema. Siga estas etapas antes de enviar:

- Verifique se está usando a versão mais recente do projeto.
- Certifique-se de que o problema não está relacionado ao seu ambiente (versões incompatíveis, configurações, etc.). Leia a [docuemntação](https://github.com/sakurahoney98/balunga_game/tree/53d047b8b8c4db8ceb4a89c2e8290422464e2e6c/docs) e, se necessário, veja a seção [Tenho uma dúvida](#tenho-uma-dúvida).
- Veja se já existe uma issue para o problema no [rastreador de bug](https://github.com/sakurahoney98/balunga_game.git/issues?q=label%3Abug).
- Reúna informações como:
  - Stack trace (erro completo)
  - Sistema operacional, plataforma e arquitetura
  - Versões do Java, Spring Boot, Docker, etc.
  - Entrada usada e saída obtida
  - O erro é reproduzível? Também ocorre em versões anteriores?

#### Como enviar um bom relatório de bug?

> Nunca compartilhe publicamente informações sensíveis ou falhas de segurança. Para isso, envie um e-mail para: <caroline.santana@ucsal.edu.br>.

Para relatar um bug:

- Abra uma [nova issue](https://github.com/sakurahoney98/balunga_game.git/issues/new).
- Descreva o comportamento esperado e o que ocorreu de fato.
- Inclua um passo a passo para que outras pessoas consigam reproduzir o erro.
- Envie todas as informações que reuniu na etapa anterior.
- Rótule a issue como `bug`.


### Sugerir Melhorias

#### Antes de sugerir uma melhoria

- Verifique se está usando a última versão.
- Confira a [documentação](https://github.com/sakurahoney98/balunga_game/tree/53d047b8b8c4db8ceb4a89c2e8290422464e2e6c/docs)  para garantir que sua sugestão ainda não foi contemplada.
- Pesquise nas [issues](https://github.com/sakurahoney98/balunga_game.git/issues) se a sugestão já foi feita. Se sim, comente nela em vez de criar uma nova.
- Avalie se a ideia está alinhada com o propósito do projeto. Foque em soluções úteis para a maioria dos usuários, não apenas para um caso muito específico.

#### Como eu envio uma sugestão?

- Use um título claro e descritivo.
- Descreva detalhadamente a melhoria ou funcionalidade proposta.
- Explique o comportamento atual e o que você gostaria de ver no lugar.
- Se possível, inclua prints ou vídeos demonstrando o problema ou a sugestão.
- Justifique por que essa melhoria seria útil para a comunidade. Você pode citar como isso é feito em outros projetos similares.

### Contribuindo com Código

#### Antes de contribuir

- Verifique se está usando a última versão.
- Confira a [documentação](https://github.com/sakurahoney98/balunga_game/tree/53d047b8b8c4db8ceb4a89c2e8290422464e2e6c/docs)  para garantir que sua sugestão ainda não foi contemplada.
- Pesquise nas [issues](https://github.com/sakurahoney98/balunga_game.git/issues) se a sugestão já foi feita. Se sim, comente nela em vez de criar uma nova.

#### Como inicio uma alteração?

1. Crie uma issue
- Abra uma [nova issue](https://github.com/sakurahoney98/balunga_game.git/issues/new) descrevendo:
  - O que pretende resolver ou implementar
  - O comportamento atual e o comportamento desejado
- Justifique por que a mudança é relevante
- Use os rótulos adequados 
- Se você pretende resolver a issue, mencione isso no corpo da issue com `Pretendo trabalhar nessa melhoria/bug. Vou abrir uma PR em breve.`

2. Faça um Fork do Repositório
- Clique em `Fork` no topo da página para criar uma cópia do projeto na sua conta GitHub.
- Clone o repositório forkado para sua máquina:
  ```
  git clone https://github.com/<seu-usuario>/balunga_game.git
  cd balunga_game
  ```

3. Crie uma Branch para sua alteração
```
git checkout -b feat/issue-7
```

#### Como envio um Pull Request?

Agora que terminou de trabalhar nas modificações é necessário enviar para a equipe fazer a avaliação.

1.  Valide que o projeto continua buildando corretamente:
    ```sh
      mvn clean install
    ```
2. Envie as alterações para o repositório remoto:
    ```sh
      git push origin feat/issue-7
    ```
3. Volte ao seu projeto no GitHub e clique em "Compare & Pull Request" preenchendo o formulário para envio de PR

#### O que fazer depois de enviar um Pull Request?

- Após o envio do PR a equipe irá revisar sua contribuição
- Feedbacks podem ser enviados para ajustes
- Após aprovação, sua PR será integrada à branch principal


### Padrão de Commits e Branches

#### Convenção de Branches

Ao fazer uma contribuição com código certifique-se que o nome da branch segue o modelo `<tipo>/issue-<identificador-issue>`.

| Tipo      | Quando usar |
|-----------|-------------|
| `feature`      | Para novas funcionalidades |
| `bugfix`      | Correções de bugs |
| `hotfix`      | 	Correções emergenciais em produção |
| `refactor`      | Refatorações de código (sem alterar comportamento) |
| `test`      | 	Adição ou alteração de testes |
| `chore`      | Tarefas que não se encaixam nas demais categorias |

**Exemplo:**
- feature/issue-7
- bugfix/issue-9
- hotfix/issue-11

#### Convenção de Commits

Ao enviar uma alteração para o repositório remoto certifique-se que o comentário do commit segue o modelo `<tipo>: <breve resumo da mudança>`.

| Tipo      | Quando usar |
|-----------|-------------|
| `feat`      | Novas funcionalidades |
| `fix`      | Correção de bugs |
| `refactor`      | Refatoração sem alterar comportamento |
| `doc`      | Alterações na documentação |
| `style`      | Formatação, identação, remoção de espaço em branco |
| `test`      | Adição/modificação de testes |
| `add`      | Adição de arquivos |
| `remove`      | remoção de arquivos |
| `chore`      | Tarefas que não se encaixam nas demais categorias |

**Exemplo:**
- feat: implementar distribuição de cartas
- fix: corrigir erro na ordenação do lado direito da mesa
- docs: adicionar guia de contribuição
- chore: atualizar versão do Spring Boot



