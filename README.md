# Desafio BugBanck.
Os sub tópicos abaixo descrevem algumas decisões tomadas na estruturação do projeto.

## Tecnologia Utilizadas

- Java  https://www.oracle.com/java/technologies/javase/jdk14-archive-downloads.html
- Selenium Web Driver
  https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java
- Maven  https://maven.apache.org/
- Criar Readme   https://stackedit.io/
- ChromeDriver - WebDriver 

##	Configurações de pastas do projeto:

##  Folder project:
- A pasta **src** tem a seguinte estrutura informada, **main** --> **java** --> **Framework**, encontram-se as funções para abrir o site BugBank, navegar e waits informada no enunciado do desafio.

- A pasta **src** tem a seguinte estrutura informada, **main** --> **java** --> **resources** --> **Properties**, encontram-se os valores das contas criadas no momento da executação.

- A pasta **src** tem a seguinte estrutura informada, **test** --> **java** --> **PageObjects**, **Tasks**, **TestCases**, **TestSuites**, **Validations** mesma estrutura informado no enunciado do desafio.

## POM dependecy:
- Adicionar no pom.xml dependecy.
- https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager

## Testes Automatizados
Testes automatizados para simular o cadastro de duas contas, com saldo no link: https://bugbank.netlify.app/ do banco BugBank, acessar uma conta e realizar uma transferência de valor para outra. Validar a saída e entrada das contas envolvidas.

## Observação
- O saldo atual das contas sera exibido no momento da execução.
- Para clonar o projeto BugBank em seu computador e executar, realize estes passos abaixo :
- - Open Git Bash here
- - Digitar: git clone https://github.com/francisgobbi/DesafioBugBank.git
- - Projeto sera clonado no seu computador.
- Para executar o projeto, realize estes passo :
- - Maven no IntelliJ
- - Reload all Maven Project
- - Executar a funcão na pasta **src** -> **test** -> **TestCases** -->  **RealizarTrasnferenciaEntreContas**

- Caso não conseguir executar o projeto com sucesso, realize os passos abaixo:
- - mvn clean
- - mvn install

## Notas Gerais
- BeforeEach e AfterEach (Itens comuns a todos)
- Design Patterns chamado page objetos. Foi criado classes específicas para cada página que você tem na aplicação. Para resolver um problema de reaproveitamento de código.
- Primeiro princípio do PageObject. Tenha um atributo da classe que seja WebDrive.
- Segundo tenha um construtor que recebe o estado atual do seu navegador de fora e jogue pra dentro deste navegador. Métodos de interação com cada método da tela. Fluente Page. 
