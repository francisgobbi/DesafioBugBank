package Tasks;

import PageObjects.CadastroPage;
import PageObjects.LoginPage;
import PageObjects.TransferenciaPage;
import Validations.BemVindoValidations;
import Validations.CadastrarContaValidations;
import Validations.SaldoContaValidations;
import Validations.TransferenciaRealizadaSucessoValidations;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import Framework.Utils.FilesOperation;

import java.io.IOException;

public class TaskCriarContasTransferenciaDeValorEntreContas {

    private WebDriver driver;
    private CadastroPage cadastroPage;
    private LoginPage loginPage;
    private TransferenciaPage transferenciaPage;

    public TaskCriarContasTransferenciaDeValorEntreContas(WebDriver driver){

        this.driver =driver;
        cadastroPage = new CadastroPage(this.driver);
        loginPage = new LoginPage(this.driver);
        transferenciaPage = new TransferenciaPage(this.driver);
    }


    public void criarDuasContasComSaldosESalvarOsSeusDados() throws IOException {

        cadastroPage.getBotaoRegistrar().click();
        cadastroPage.getEmail().sendKeys("francis@automatizado.com");
        cadastroPage.getNome().sendKeys("Francis");
        String nomeTitularPrimeiraConta =  "Francis";
        cadastroPage.getSenha().sendKeys("1234");
        cadastroPage.getConfirmaSenha().sendKeys("1234");
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
        FilesOperation.setProperty("dadosPrimeiraContaBanco", "saldoPrimeiraContaBanco", "R$ 1.000,00");

        System.out.println(numeroConta);
        CadastrarContaValidations.CadastrarContaValidationsOK();
        cadastroPage.getBotaoFechar().click();

        // Realiza Cadastro com Saldo na conta
        cadastroPage.getBotaoRegistrar().click();
        cadastroPage.getEmail().clear();
        cadastroPage.getEmail().sendKeys("fernanda@automatizado.com");
        cadastroPage.getNome().clear();
        cadastroPage.getNome().sendKeys("Fernanda");
        String nomeTitularSegundaConta = "Fernanda";
        cadastroPage.getSenha().clear();
        cadastroPage.getSenha().sendKeys("1234");
        cadastroPage.getConfirmaSenha().clear();
        cadastroPage.getConfirmaSenha().sendKeys("1234");
        //cadastroPage.getCriarContaComSaldo().click();
        cadastroPage.getBotaoCadastrar().click();

        String numeroConta1 = cadastroPage.getNumeroDaConta().getText();
        String[] separarNumeroDaConta1 = numeroConta1.split("-");

        String apenasNumeroDaConta1 = separarNumeroDaConta1[0].replaceAll("[^0-9]", "");
        String digitoDaConta1 = separarNumeroDaConta1[1].replaceAll("[^0-9]", "");

        FilesOperation.setProperty("dadosSegundaContaBanco", "nomeTitularSegundaConta", nomeTitularSegundaConta);
        FilesOperation.setProperty("dadosSegundaContaBanco", "numeroSegundaContaBanco", apenasNumeroDaConta1);
        FilesOperation.setProperty("dadosSegundaContaBanco", "digitoSegundaContaBanco", digitoDaConta1);
        FilesOperation.setProperty("dadosSegundaContaBanco", "saldoSegundaContaBanco", "R$ 1.000,00");

        System.out.println(numeroConta1);
        System.out.println("");
        CadastrarContaValidations.CadastrarContaValidationsOK();
        cadastroPage.getBotaoFechar().click();
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
            TransferenciaRealizadaSucessoValidations.TransferenciaRealizadaSucessoOK();
            transferenciaPage.getBotaoFechar().click();
            transferenciaPage.getBotaoVoltar().click();
            transferenciaPage.getBotaoExtrato().click();
            transferenciaPage.getSaldoConta().getText();
            transferenciaPage.getBotatoSair().click();
        }

    }

    public void realizarLogin() throws IOException {

        loginPage.getEmailLogin().sendKeys("fernanda@automatizado.com");
        loginPage.getSenhaLogin().sendKeys("1234");
        loginPage.getBotaoAcessar().click();
        loginPage.getTextoBemVindo().getText();

        BemVindoValidations.BemVindoValidationsOK();

        SaldoContaValidations.SaldoContaValidationsOK();

    }

    public void realizarLoginResultadoTransferencia() throws IOException {

        loginPage.getEmailLogin().sendKeys("francis@automatizado.com");
        loginPage.getSenhaLogin().sendKeys("1234");
        loginPage.getBotaoAcessar().click();
        loginPage.getTextoBemVindo().getText();
        BemVindoValidations.BemVindoValidationsOK();

        String saldoConta = loginPage.getSaldoSegundaConta().getText();
        String nomeTitularConta = loginPage.getNameTitularConta().getText();
        System.out.println("");
        System.out.println("O nome do titular da conta é :" + nomeTitularConta );
        System.out.println("O saldo atual da conta que recebeu transferencia é :" + saldoConta);
        System.out.println("Numero da Conta : " + loginPage.getNumeroComDigitoConta().getText() );
        System.out.println("");

        SaldoContaValidations.SaldoContaValidationsOK();
        transferenciaPage.getBotaoExtrato().click();
        transferenciaPage.getBotatoSair().click();
    }

    public void validarSaidaDaContaQueRealizouATransferencia(){
        try{
            loginPage.getEmailLogin().sendKeys("fernanda@automatizado.com");
            loginPage.getSenhaLogin().sendKeys("1234");
            loginPage.getBotaoAcessar().click();
            loginPage.getTextoBemVindo().getText();

            BemVindoValidations.BemVindoValidationsOK();

            String saldoConta = loginPage.getSaldoSegundaConta().getText();
            String nomeTitularConta = loginPage.getNameTitularConta().getText();
            System.out.println("Nome do Titular da Conta : " + nomeTitularConta);
            System.out.println("Saldo da conta que realizou a trasnferencia : " + saldoConta);
            System.out.println("Numero da Conta : " + loginPage.getNumeroComDigitoConta().getText() );

            SaldoContaValidations.SaldoContaValidationsOK();

        }catch(Exception e){
            System.out.println(e);

        }


    }

}
