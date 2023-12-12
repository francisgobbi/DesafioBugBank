package Tasks;

import Framework.Utils.FakersGenerations;
import PageObjects.CadastroPage;
import PageObjects.LoginPage;
import PageObjects.TransferenciaPage;
import Validations.*;
import org.openqa.selenium.WebDriver;
import Framework.Utils.FilesOperation;

import java.io.IOException;

public class TaskCriarContasTransferenciaDeValorEntreContas {

    private WebDriver driver;
    private CadastroPage cadastroPage;
    private LoginPage loginPage;
    private TransferenciaPage transferenciaPage;
    private CadastrarContaValidations cadastrarContaValidations;
    private SaldoContaValidations saldoContaValidations;
    private BemVindoValidations bemVindoValidations;
    private LoginSenhaIvalido loginSenhaIvalido;
    protected FakersGenerations fakersGenerations;

    private TransferenciaRealizadaSucessoValidations transferenciaRealizadaSucessoValidations;


    public TaskCriarContasTransferenciaDeValorEntreContas(WebDriver driver){

        this.driver =driver;
        cadastroPage = new CadastroPage(this.driver);
        loginPage = new LoginPage(this.driver);
        transferenciaPage = new TransferenciaPage(this.driver);
        cadastrarContaValidations = new CadastrarContaValidations(this.driver);
        saldoContaValidations = new SaldoContaValidations(this.driver);
        bemVindoValidations = new BemVindoValidations(this.driver);
        loginSenhaIvalido = new LoginSenhaIvalido(this.driver);
        transferenciaRealizadaSucessoValidations = new TransferenciaRealizadaSucessoValidations(this.driver);
        fakersGenerations = new FakersGenerations();

    }

    public void criarDuasContasComSaldosESalvarOsSeusDados() throws IOException {

        String emailPrimeiraConta = fakersGenerations.getEmail();
        String senhaPrimeiraConta = fakersGenerations.getSenha();
        String nomeTitularPrimeiraConta = fakersGenerations.getName();
        String emailSegundaConta = fakersGenerations.getSegundoEmail();
        String senhaSegundaConta = fakersGenerations.getSegundaSenha();
        String nomeTitularSegundaConta = fakersGenerations.getSegundoName();

        cadastroPage.getBotaoRegistrar().click();
        cadastroPage.getEmail().sendKeys(emailPrimeiraConta);
        cadastroPage.getNome().sendKeys(nomeTitularPrimeiraConta);
        cadastroPage.getSenha().sendKeys(senhaPrimeiraConta);
        cadastroPage.getConfirmaSenha().sendKeys(senhaPrimeiraConta);
        cadastroPage.getCriarContaComSaldo().click();
        cadastroPage.getBotaoCadastrar().click();

        // Assert conta criada com sucesso
        String numeroConta = cadastroPage.getNumeroDaConta().getText();
        String[] separarNumeroDaConta = numeroConta.split("-");
        String apenasNumeroDaConta = separarNumeroDaConta[0].replaceAll("[^0-9]", "");
        String digitoDaConta = separarNumeroDaConta[1].replaceAll("[^0-9]", "");

        FilesOperation.setProperty("dadosPrimeiraContaBanco", "nomeTitularPrimeiraConta", nomeTitularPrimeiraConta);
        FilesOperation.setProperty("dadosPrimeiraContaBanco", "numeroPrimeiraContaBanco", apenasNumeroDaConta);
        FilesOperation.setProperty("dadosPrimeiraContaBanco", "digitoPrimeiraContaBanco", digitoDaConta);
        FilesOperation.setProperty("dadosPrimeiraContaBanco", "senhaPrimeiraContaBanco", senhaPrimeiraConta);
        FilesOperation.setProperty("dadosPrimeiraContaBanco", "emailPrimeiraContaBanco", emailPrimeiraConta);

        System.out.println(numeroConta);
        cadastrarContaValidations.CadastrarContaValidationsOK();
        cadastroPage.getBotaoFechar().click();
        saldoContaValidations.SaldoContaValidationsOK(emailPrimeiraConta,senhaPrimeiraConta);
        transferenciaPage.getBotatoSair().click();

        // Realiza Cadastro com Saldo na conta

        cadastroPage.getBotaoRegistrar().click();
        cadastroPage.getEmail().clear();
        cadastroPage.getEmail().sendKeys(emailSegundaConta);
        cadastroPage.getNome().clear();
        cadastroPage.getNome().sendKeys(nomeTitularSegundaConta);
        cadastroPage.getSenha().clear();
        cadastroPage.getSenha().sendKeys(senhaSegundaConta);
        cadastroPage.getConfirmaSenha().clear();
        cadastroPage.getConfirmaSenha().sendKeys(senhaSegundaConta);
        cadastroPage.getCriarContaComSaldo().click();
        cadastroPage.getBotaoCadastrar().click();

        String numeroConta2 = cadastroPage.getNumeroDaConta().getText();
        String[] separarNumeroDaConta2 = numeroConta2.split("-");

        String apenasNumeroDaConta2 = separarNumeroDaConta2[0].replaceAll("[^0-9]", "");
        String digitoDaConta2 = separarNumeroDaConta2[1].replaceAll("[^0-9]", "");

        FilesOperation.setProperty("dadosSegundaContaBanco", "nomeTitularSegundaConta", nomeTitularSegundaConta);
        FilesOperation.setProperty("dadosSegundaContaBanco", "numeroSegundaContaBanco", apenasNumeroDaConta2);
        FilesOperation.setProperty("dadosSegundaContaBanco", "digitoSegundaContaBanco", digitoDaConta2);
        FilesOperation.setProperty("dadosSegundaContaBanco", "senhaSegundaContaBanco", senhaSegundaConta );
        FilesOperation.setProperty("dadosSegundaContaBanco", "emailSegundaContaBanco", emailSegundaConta );

        System.out.println(numeroConta2);
        System.out.println("");
        cadastrarContaValidations.CadastrarContaValidationsOK();
        cadastroPage.getBotaoFechar().click();
        saldoContaValidations.SaldoContaValidationsOK(emailSegundaConta,senhaSegundaConta );
        transferenciaPage.getBotatoSair().click();
    }

    public void realizarUmaTransferenciaDeValorParaOutra() throws IOException {

        String numeroDaContaFormatado = null;
        String digitoDaConta = null;

        numeroDaContaFormatado = FilesOperation.getProperties("dadosPrimeiraContaBanco").getProperty("numeroPrimeiraContaBanco");
        digitoDaConta = FilesOperation.getProperties("dadosPrimeiraContaBanco").getProperty("digitoPrimeiraContaBanco");

        // Realizara Transferencia
        String getSaldoConta = loginPage.getSaldo().getText();
        if (getSaldoConta.equals("Saldo em conta R$ 0,00"))  {
            System.out.println("Conta com saldo insuficiente para realizar transferencia!");

        } else {
            transferenciaPage.getBotaoTransferencia().click();
            transferenciaPage.getNumeroConta().sendKeys(numeroDaContaFormatado);
            transferenciaPage.getDigito().sendKeys(digitoDaConta);
            transferenciaPage.getValorDaTransferencia().sendKeys("910");
            transferenciaPage.getDescricao().sendKeys("Teste transferencia automatizada realizada com sucesso");
            transferenciaPage.getBotaoTransferirAgora().click();
            transferenciaPage.getTextoTransferenciaRealizada().isDisplayed();
            transferenciaRealizadaSucessoValidations.TransferenciaRealizadaSucessoOK();
            transferenciaPage.getBotaoFechar().click();
            transferenciaPage.getBotaoVoltar().click();
            transferenciaPage.getBotaoExtrato().click();
            transferenciaPage.getSaldoConta().getText();
            transferenciaPage.getBotatoSair().click();
        }
    }

    public void realizarLogin() throws IOException {

        String emailSegundaConta = FilesOperation.getProperties("dadosSegundaContaBanco").getProperty("emailSegundaContaBanco");
        String senhaSegundaConta = FilesOperation.getProperties("dadosSegundaContaBanco").getProperty("senhaSegundaContaBanco");

        loginPage.getEmailLogin().sendKeys(emailSegundaConta);
        loginPage.getSenhaLogin().sendKeys(senhaSegundaConta);
        loginPage.getBotaoAcessar().click();
        loginPage.getTextoBemVindo().getText();

        bemVindoValidations.BemVindoValidationsOK();

    }
    public void realizarLoginCadastrar() throws IOException {

        String emailSegundaConta = FilesOperation.getProperties("dadosSegundaContaBanco").getProperty("emailSegundaContaBanco");
        String senhaSegundaConta = FilesOperation.getProperties("dadosSegundaContaBanco").getProperty("senhaSegundaContaBanco");

        loginPage.getEmailLogin().sendKeys(emailSegundaConta);
        loginPage.getSenhaLogin().sendKeys(senhaSegundaConta);
        loginPage.getBotaoAcessar().click();
        loginPage.getTextoBemVindo().getText();

        bemVindoValidations.BemVindoValidationsOK();
        cadastroPage.getBotaoCadastrar().click();
    }

    public void NegativeLogin() {

        loginPage.getEmailLogin().sendKeys(fakersGenerations.getEmail());
        loginPage.getSenhaLogin().sendKeys(fakersGenerations.getSenha());
        loginPage.getBotaoAcessar().click();
        loginSenhaIvalido.LoginSenhaIvalidoOK();
        cadastroPage.getBotaoFechar().click();

    }

    public void realizarLoginSaldoEmConta() throws IOException {

        String emailSegundaConta = FilesOperation.getProperties("dadosSegundaContaBanco").getProperty("emailSegundaContaBanco");
        String senhaSegundaConta = FilesOperation.getProperties("dadosSegundaContaBanco").getProperty("senhaSegundaContaBanco");

        loginPage.getEmailLogin().sendKeys(emailSegundaConta);
        loginPage.getSenhaLogin().sendKeys(senhaSegundaConta);
        loginPage.getBotaoAcessar().click();
        loginPage.getTextoBemVindo().getText();

        bemVindoValidations.BemVindoValidationsOK();
        saldoContaValidations.SaldoContaValidationsOK(emailSegundaConta,senhaSegundaConta);

    }

    public void realizarLoginSaldoEmContaF() throws IOException {

        String emailPrimeiraConta = FilesOperation.getProperties("dadosPrimeiraContaBanco").getProperty("emailPrimeiraContaBanco");
        String senhaPrimeiraConta = FilesOperation.getProperties("dadosPrimeiraContaBanco").getProperty("senhaPrimeiraContaBanco");

        loginPage.getEmailLogin().sendKeys(emailPrimeiraConta);
        loginPage.getSenhaLogin().sendKeys(senhaPrimeiraConta);
        loginPage.getBotaoAcessar().click();
        loginPage.getTextoBemVindo().getText();

        bemVindoValidations.BemVindoValidationsOK();
        saldoContaValidations.SaldoContaValidationsOK(emailPrimeiraConta,senhaPrimeiraConta);

    }


    public void realizarLoginResultadoTransferencia() throws IOException {

        String emailPrimeiraConta = FilesOperation.getProperties("dadosPrimeiraContaBanco").getProperty("emailPrimeiraContaBanco");
        String senhaPrimeiraConta = FilesOperation.getProperties("dadosPrimeiraContaBanco").getProperty("senhaPrimeiraContaBanco");

        realizarLoginSaldoEmContaF();

        String saldoConta = loginPage.getSaldoConta().getText();
        String nomeTitularConta = loginPage.getNameTitularConta().getText();
        System.out.println("");
        System.out.println("O nome do titular da conta é :" + nomeTitularConta );
        System.out.println("O saldo atual da conta que recebeu transferencia é :" + saldoConta);
        System.out.println("Numero da Conta : " + loginPage.getNumeroComDigitoConta().getText() );
        System.out.println("");

        saldoContaValidations.SaldoContaValidationsOK(emailPrimeiraConta,senhaPrimeiraConta);
        transferenciaPage.getBotaoExtrato().click();
        transferenciaPage.getBotatoSair().click();
    }

    public void validarSaidaDaContaQueRealizouATransferencia() throws IOException {

        String emailSegundaConta = FilesOperation.getProperties("dadosSegundaContaBanco").getProperty("emailSegundaContaBanco");
        String senhaSegundaConta = FilesOperation.getProperties("dadosSegundaContaBanco").getProperty("senhaSegundaContaBanco");
        realizarLoginSaldoEmConta();
        String saldoConta = loginPage.getSaldoConta().getText();
        String nomeTitularConta = loginPage.getNameTitularConta().getText();
        System.out.println("Nome do Titular da Conta : " + nomeTitularConta);
        System.out.println("Saldo da conta que realizou a trasnferencia : " + saldoConta);
        System.out.println("Numero da Conta : " + loginPage.getNumeroComDigitoConta().getText() );

        saldoContaValidations.SaldoContaValidationsOK(emailSegundaConta,senhaSegundaConta);
        cadastroPage.getBotaoCadastrar().click();
    }
}
