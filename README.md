# Desafio Programador Fullstack Unoesc - Java

Esse é o nosso desafio para a vaga de programador fullstack Java na [Unoesc](https://www.unoesc.edu.br/). Serão testadas as habilidades e qualidade de código ao transformar requisitos limitados em uma aplicação web.
Faça o fork deste repositório e implemente o desafio. 

## DESAFIO

Você terá que desenvolver uma aplicação web responsável por registrar pessoas de um time e seus dados de formação. As pessoas serão exibidas em cards dispostos em um mural e cada uma possui um fato aleatório.

### ESCOPO DO DESAFIO

**Requisitos**
- O cadastro de time deve conter: Nome e Setor.
- O cadastro de pessoa deve conter Nome, CPF, data de nascimento, gênero, telefone e e-mail.
  - CPF e telefone deve possuir validação e máscara;
  - O e-mail precisa ser um e-mail válido;
  - Gênero deve ser uma lista com valores pré-definidos;
- O cadastro de formação deve conter:  Nome do curso, nível do curso, data de conclusão e nome da instituição de ensino.
  - Um nível de curso se refere ao grau de instrução dele: ensino médio, técnico, bacharelado, especialização, mestrado, entre outros. 
- Um time é formado por uma ou muitas pessoas.
- Cada pessoa pode possuir nenhuma ou muitas formações e pode estar inserida em somente um time.
- Deve ser possível consultar, editar e excluir os cadastros.
- A aplicação deve ser protegida por um login. Porém, não é necessário que exista uma tela para cadastro de usuários.

**Mural dos times**
- O sistema deve possuir um mural onde os integrantes do time selecionado são dispostos em cards.
- Cada card deve conter o nome da pessoa e um fato aleatório.
  - Toda vez que a tela for carregada os fatos deve ser atualizados.
  - Os fatos aleatórios devem ser consultados através da API:  https://uselessfacts.jsph.pl/ através de endpoint: `/api/v2/facts/random`.
  - Os fatos não precisam ser traduzidos.
- O mural deve ter como base o seguinte protótipo: ![mural (Pequeno)](https://github.com/user-attachments/assets/ea7bbcb9-ccb4-4c36-a648-88df8ac56edc)
- Lembre-se que este é somente o protótipo do mural, seja livre para fazer as alterações que preferir. Quanto as demais telas de cadastros e apresentações não há limitações no desing, ficando a seu critério como serão construídas. 

Para auxiliar no entendimento, elaboramos um diagrama de Entidade-Relacionamento: ![image](https://github.com/user-attachments/assets/741c1fba-1c6f-4f8f-a7d0-3abf6c90702f)

**Tecnologias obrigatórias a serem utilizadas:**
- Java;
- Spring Boot;
- Maven;
- Thymeleaf;
- MySQL 5.7.X;
- GIT;

### AVALIAÇÃO

**O código será avaliado de acordo com os critérios:**
- Build e execução da aplicação;
- Completude das funcionalidades;
- Qualidade de código (design pattern, manutenibilidade, clareza); 
- Histórico do GIT; 
- Boas práticas de UI (Interface com o Usuário);
- Sentido e coerência nas respostas aos questionamentos na entrevista de apresentação do desafio realizada pelo candidato.

**Não esqueça de documentar o processo necessário para rodar a aplicação.**
