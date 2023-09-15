package Tasks;

import PageObjects.CadastroPage;
import PageObjects.LoginPage;
import PageObjects.TransferenciaPage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import Framework.Utils.FilesOperation;

import java.io.IOException;
import java.sql.SQLOutput;

public class TransferenciaTask {

    private WebDriver driver;
    private CadastroPage cadastroPage;
    private LoginPage loginPage;
    private TransferenciaPage transferenciaPage;

    public TransferenciaTask(WebDriver driver){

        this.driver =driver;
        cadastroPage = new CadastroPage(this.driver);
        loginPage = new LoginPage(this.driver);
        transferenciaPage = new TransferenciaPage(this.driver);
    }

    public void realizaCadastro() throws IOException {

        // Realiza Cadatsro com Saldo zerado
        cadastroPage.clicBotaoRegistrar().click();
        cadastroPage.getemail().sendKeys("francis@automatizado.com");
        cadastroPage.getNome().sendKeys("Francis");
        cadastroPage.getSenha().sendKeys("1234");
        cadastroPage.getconfirmaSenha().sendKeys("1234");
        cadastroPage.clicBotaoCadastrar().click();

        String numeroDaConta = cadastroPage.numeroDaConta().getText();
        String numeroConta = cadastroPage.numeroDaConta().getText();
        String[] splitNumeroDaConta = numeroConta.split("-");

        String numeroDaContaFormatado = splitNumeroDaConta[0].replaceAll("[^0-9]", "");
        String digitoDaConta = splitNumeroDaConta[1].replaceAll("[^0-9]", "");
        try {
            FilesOperation.setProperty("conta", "numeroConta", numeroDaContaFormatado);
            FilesOperation.setProperty("conta", "digitoConta", digitoDaConta);
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
        System.out.println("Nome do Titular da Conta: Francis " );
        System.out.println("Numero da Conta : " + numeroDaConta + "-" + digitoDaConta );
        // Conta criada com saldo zerado
        System.out.println("Saldo da Conta : " + cadastroPage.getCriarContaComSaldo().getText());
        cadastroPage.btnFechar().click();


        // Realiza Cadastro com Saldo na conta
        cadastroPage.clicBotaoRegistrar().click();
        cadastroPage.getemail().clear();
        cadastroPage.getemail().sendKeys("fernanda@automatizado.com");
        cadastroPage.getNome().clear();
        cadastroPage.getNome().sendKeys("Fernanda");
        cadastroPage.getSenha().clear();
        cadastroPage.getSenha().sendKeys("1234");
        cadastroPage.getconfirmaSenha().clear();
        cadastroPage.getconfirmaSenha().sendKeys("1234");
        cadastroPage.getCriarContaComSaldo().click();
        cadastroPage.clicBotaoCadastrar().click();
        cadastroPage.numeroDaConta().getText();
        cadastroPage.digitoDaConta().getText();
        cadastroPage.btnFechar().click();


    }

    public void realizarTransferencia() throws IOException {

        String numeroDaContaFormatado = null;
        String digitoDaConta = null;
        try {
            numeroDaContaFormatado = FilesOperation.getProperties("conta").getProperty("numeroConta");
            digitoDaConta = FilesOperation.getProperties("conta").getProperty("digitoConta");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Realizara Transferencia
        loginPage.saldo().getText();
        transferenciaPage.btnTransferencia().click();
        transferenciaPage.numeroDaConta().sendKeys(numeroDaContaFormatado);
        transferenciaPage.digito().sendKeys(digitoDaConta);
        transferenciaPage.valorDaTransferencia().sendKeys("1000");
        transferenciaPage.descricao().sendKeys("Teste Transferencia Automatizada realizada com sucesso");
        transferenciaPage.btnTransferirAgora().click();
        transferenciaPage.txtTransferenciaRealizada().isDisplayed();
        String mensagem = "Transferencia realizada com sucesso";
        Assert.assertEquals(mensagem, transferenciaPage.txtTransferenciaRealizada().getText());
        transferenciaPage.btnFechar().click();
        transferenciaPage.btnVoltar().click();
        transferenciaPage.btnExtrato().click();
        transferenciaPage.saldoConta1().getText();
        transferenciaPage.clicBotatoSair().click();

    }

    public void realizarLogin() throws IOException {

        loginPage.getEmailLogin().sendKeys("fernanda@automatizado.com");
        loginPage.getSenhaLogin().sendKeys("1234");
        loginPage.clicBotaoAcessar().click();
        loginPage.textoBemVindo().getText();

        String msg = "bem vindo ao BugBank :)";
        Assert.assertEquals(msg, loginPage.textoBemVindo().getText());
        loginPage.saldo().getText();

        String saldoConta = loginPage.saldoSegundaConta().getText();
        String nomeTitularConta = loginPage.nameTitularConta().getText();
        System.out.println("Email do Titutlar da conta 2 é :" + nomeTitularConta );
        System.out.println("O Saldo da conta 2 é :" + saldoConta);

        String validaSaldo2 = "Saldo em conta " + saldoConta;
        Assert.assertEquals(validaSaldo2, loginPage.saldo().getText());

    }

    public void realizarLoginResultado() throws IOException {

        loginPage.getEmailLogin().sendKeys("francis@automatizado.com");
        loginPage.getSenhaLogin().sendKeys("1234");
        loginPage.clicBotaoAcessar().click();
        loginPage.textoBemVindo().getText();

        String msg = "bem vindo ao BugBank :)";
        Assert.assertEquals(msg, loginPage.textoBemVindo().getText());
        loginPage.saldo().getText();

        String saldoConta = loginPage.saldoSegundaConta().getText();
        String nomeTitularConta = loginPage.nameTitularConta().getText();
        System.out.println("Email do Titutlar da conta 2 é :" + nomeTitularConta );
        System.out.println("O Saldo da conta 2 é :" + saldoConta);

        String validaSaldo2 = "Saldo em conta " + saldoConta;
        Assert.assertEquals(validaSaldo2, loginPage.saldo().getText());

        transferenciaPage.btnExtrato().click();
    }

}
