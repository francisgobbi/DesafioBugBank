package Tasks;

import PageObjects.CadastroPage;
import PageObjects.LoginPage;
import PageObjects.TransferenciaPage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import Framework.Utils.FilesOperation;

import java.io.IOException;

public class TarefaCriarContasTransferenciaDeValorEntreContas {

    private WebDriver driver;
    private CadastroPage cadastroPage;
    private LoginPage loginPage;
    private TransferenciaPage transferenciaPage;

    public TarefaCriarContasTransferenciaDeValorEntreContas(WebDriver driver){

        this.driver =driver;
        cadastroPage = new CadastroPage(this.driver);
        loginPage = new LoginPage(this.driver);
        transferenciaPage = new TransferenciaPage(this.driver);
    }


    public void acriarDuasContasComSaldosESalvarOsSeusDados() throws IOException {

        cadastroPage.getBotaoRegistrar().click();
        cadastroPage.getemail().sendKeys("francis@automatizado.com");
        cadastroPage.getNome().sendKeys("Francis");
        String nomeTitularPrimeiraConta =  "Francis";
        cadastroPage.getSenha().sendKeys("1234");
        cadastroPage.getconfirmaSenha().sendKeys("1234");
        cadastroPage.getCriarContaComSaldo().click();
        cadastroPage.getBotaoCadastrar().click();

        String numeroConta = cadastroPage.getnumeroDaConta().getText();
        String[] separarNumeroDaConta = numeroConta.split("-");
        String apenasNumeroDaConta = separarNumeroDaConta[0].replaceAll("[^0-9]", "");
        String digitoDaConta = separarNumeroDaConta[1].replaceAll("[^0-9]", "");

        FilesOperation.setProperty("dadosPrimeiraContaBanco", "nomeTitularPrimeiraConta", nomeTitularPrimeiraConta);
        FilesOperation.setProperty("dadosPrimeiraContaBanco", "numeroPrimeiraContaBanco", apenasNumeroDaConta);
        FilesOperation.setProperty("dadosPrimeiraContaBanco", "digitoPrimeiraContaBanco", digitoDaConta);
        FilesOperation.setProperty("dadosPrimeiraContaBanco", "saldoPrimeiraContaBanco", "R$ 1.000,00");

        System.out.println(numeroConta);
        cadastroPage.getBotaoFechar().click();

        // Realiza Cadastro com Saldo na conta
        cadastroPage.getBotaoRegistrar().click();
        cadastroPage.getemail().clear();
        cadastroPage.getemail().sendKeys("fernanda@automatizado.com");
        cadastroPage.getNome().clear();
        cadastroPage.getNome().sendKeys("Fernanda");
        String nomeTitularSegundaConta = "Fernanda";
        cadastroPage.getSenha().clear();
        cadastroPage.getSenha().sendKeys("1234");
        cadastroPage.getconfirmaSenha().clear();
        cadastroPage.getconfirmaSenha().sendKeys("1234");
        cadastroPage.getCriarContaComSaldo().click();
        cadastroPage.getCriarContaComSaldo().click();
        cadastroPage.getBotaoCadastrar().click();

        String numeroConta1 = cadastroPage.getnumeroDaConta().getText();
        String[] separarNumeroDaConta1 = numeroConta1.split("-");

        String apenasNumeroDaConta1 = separarNumeroDaConta1[0].replaceAll("[^0-9]", "");
        String digitoDaConta1 = separarNumeroDaConta1[1].replaceAll("[^0-9]", "");

        FilesOperation.setProperty("dadosSegundaContaBanco", "nomeTitularSegundaConta", nomeTitularSegundaConta);
        FilesOperation.setProperty("dadosSegundaContaBanco", "numeroSegundaContaBanco", apenasNumeroDaConta1);
        FilesOperation.setProperty("dadosSegundaContaBanco", "digitoSegundaContaBanco", digitoDaConta1);
        FilesOperation.setProperty("dadosSegundaContaBanco", "saldoSegundaContaBanco", "R$ 1.000,00");

        System.out.println(numeroConta1);
        System.out.println("");
        cadastroPage.getBotaoFechar().click();
    }

    public void brealizarUmaTransferenciaDeValorParaOutra() throws IOException {

        String numeroDaContaFormatado = null;
        String digitoDaConta = null;

        numeroDaContaFormatado = FilesOperation.getProperties("dadosPrimeiraContaBanco").getProperty("numeroPrimeiraContaBanco");
        digitoDaConta = FilesOperation.getProperties("dadosPrimeiraContaBanco").getProperty("digitoPrimeiraContaBanco");

        // Realizara Transferencia
        loginPage.getsaldo().getText();
        transferenciaPage.getBotaoTransferencia().click();
        transferenciaPage.getnumeroConta().sendKeys(numeroDaContaFormatado);
        transferenciaPage.getdigito().sendKeys(digitoDaConta);
        transferenciaPage.getvalorDaTransferencia().sendKeys("910");
        transferenciaPage.getdescricao().sendKeys("Teste transferencia automatizada realizada com sucesso");
        transferenciaPage.getBotaoTransferirAgora().click();
        transferenciaPage.getTextoTransferenciaRealizada().isDisplayed();
        String mensagem = "Transferencia realizada com sucesso";
        Assert.assertEquals(mensagem, transferenciaPage.getTextoTransferenciaRealizada().getText());
        transferenciaPage.getBotaoFechar().click();
        transferenciaPage.getBotaoVoltar().click();
        transferenciaPage.getBotaoExtrato().click();
        transferenciaPage.getSaldoConta().getText();
        transferenciaPage.getBotatoSair().click();

    }

    public void brealizarLogin() throws IOException {

        loginPage.getEmailLogin().sendKeys("fernanda@automatizado.com");
        loginPage.getSenhaLogin().sendKeys("1234");
        loginPage.getBotaoAcessar().click();
        loginPage.gettextoBemVindo().getText();

        String msg = "bem vindo ao BugBank :)";
        Assert.assertEquals(msg, loginPage.gettextoBemVindo().getText());
        loginPage.getsaldo().getText();

        String saldoConta = loginPage.getsaldoSegundaConta().getText();
        String nomeTitularConta = loginPage.getnameTitularConta().getText();

        String validaSaldo2 = "Saldo em conta " + saldoConta;
        Assert.assertEquals(validaSaldo2, loginPage.getsaldo().getText());

    }

    public void xrealizarLoginResultadoTransferencia() throws IOException {

        loginPage.getEmailLogin().sendKeys("francis@automatizado.com");
        loginPage.getSenhaLogin().sendKeys("1234");
        loginPage.getBotaoAcessar().click();
        loginPage.gettextoBemVindo().getText();

        String msg = "bem vindo ao BugBank :)";
        Assert.assertEquals(msg, loginPage.gettextoBemVindo().getText());
        loginPage.getsaldo().getText();

        String saldoConta = loginPage.getsaldoSegundaConta().getText();
        String nomeTitularConta = loginPage.getnameTitularConta().getText();
        System.out.println("");
        System.out.println("O nome do titular da conta é :" + nomeTitularConta );
        System.out.println("O saldo atual da conta que recebeu transferencia é :" + saldoConta);
        System.out.println("Numero da Conta : " + loginPage.getnumeroComDigitoConta().getText() );
        System.out.println("");
        String validaSaldo2 = "Saldo em conta " + saldoConta;
        Assert.assertEquals(validaSaldo2, loginPage.getsaldo().getText());

        transferenciaPage.getBotaoExtrato().click();
    }

    public void wvalidarSaidaDaContaQueRealizouATransferencia(){
        try{
            loginPage.getEmailLogin().sendKeys("fernanda@automatizado.com");
            loginPage.getSenhaLogin().sendKeys("1234");
            loginPage.getBotaoAcessar().click();
            loginPage.gettextoBemVindo().getText();

            String msg = "bem vindo ao BugBank :)";
            Assert.assertEquals(msg, loginPage.gettextoBemVindo().getText());
            loginPage.getsaldo().getText();

            String saldoConta = loginPage.getsaldoSegundaConta().getText();
            String nomeTitularConta = loginPage.getnameTitularConta().getText();

            System.out.println("Nome do Titular da Conta : " + nomeTitularConta);
            System.out.println("Saldo da conta que realizou a trasnferencia : " + saldoConta);
            System.out.println("Numero da Conta : " + loginPage.getnumeroComDigitoConta().getText() );

            String validaSaldo2 = "Saldo em conta " + saldoConta;
            Assert.assertEquals(validaSaldo2, loginPage.getsaldo().getText());
        }catch(Exception e){
            System.out.println(e);

        }


    }

}
