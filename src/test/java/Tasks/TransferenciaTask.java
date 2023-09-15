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

    public void realizaCadastro1() throws IOException {

        cadastroPage.clicBotaoRegistrar().click();
        cadastroPage.getemail().sendKeys("francis@automatizado.com");
        cadastroPage.getNome().sendKeys("Francis");
        cadastroPage.getSenha().sendKeys("1234");
        cadastroPage.getconfirmaSenha().sendKeys("1234");
        cadastroPage.clicBotaoCadastrar().click();
        String numeroDaConta = cadastroPage.numeroDaConta().getText();
        //System.out.println("Nome do Titular da Conta: Francis " );
        //System.out.println("Numero da Conta : " + numeroDaConta);
        // Conat criada com saldo zerado
        //System.out.println("Saldo da Conta : " + cadastroPage.getCriarContaComSaldo().getText());
        cadastroPage.btnFechar().click();

    }

    public void realizarCadastroeTransferencia() throws IOException {

        cadastroPage.clicBotaoRegistrar().click();
        cadastroPage.getemail().sendKeys("fernanda@automatizado.com");
        cadastroPage.getNome().sendKeys("Fernanda");
        cadastroPage.getSenha().sendKeys("1234");
        cadastroPage.getconfirmaSenha().sendKeys("1234");
        cadastroPage.getCriarContaComSaldo().click();
        cadastroPage.clicBotaoCadastrar().click();

        String numeroDaConta = cadastroPage.numeroDaConta().getText();

        String[] splitNumeroDaConta = numeroDaConta.split("-");
        String numeroDaContaFormatado = splitNumeroDaConta[0].replaceAll("[^0-9]", "");
        String digitoDaConta = splitNumeroDaConta[1].replaceAll("[^0-9]", "");

        FilesOperation.setProperty("conta", "numeroConta", numeroDaContaFormatado);
        FilesOperation.setProperty("conta", "digitoConta", digitoDaConta);

        System.out.println("Nome do Titular da Conta: Fernanda " );
        System.out.println("Numero da Conta : " + numeroDaContaFormatado);
        System.out.println("Digito Da Conta : " + digitoDaConta);
        System.out.println("Saldo da Conta : R$ 1000,00");

        // Realizara Transferencia

        loginPage.saldo().getText();
        transferenciaPage.btnTransferencia().click();
        String saldoConta1 = loginPage.saldo().getText();

        //String numeroDaContaFormatado = null;
        //String digitoDaConta = null;

        numeroDaContaFormatado = FilesOperation.getProperties("conta").getProperty("numeroConta");
        digitoDaConta = FilesOperation.getProperties("conta").getProperty("digitoConta");
        String saldoConta = transferenciaPage.saldoConta1().getText();

        transferenciaPage.numeroDaConta().sendKeys(numeroDaContaFormatado);
        transferenciaPage.digito().sendKeys(digitoDaConta);
        transferenciaPage.valorDaTransferencia().sendKeys("1000");
        transferenciaPage.descricao().sendKeys("Teste Transferencia Automatizada");
        transferenciaPage.btnTransferirAgora().click();
        transferenciaPage.txtTransferenciaRealizada().isDisplayed();
        String mensagem = "Transferencia realizada com sucesso";
        Assert.assertEquals(mensagem, transferenciaPage.txtTransferenciaRealizada().getText());
        transferenciaPage.btnFechar().click();
        transferenciaPage.btnVoltar().click();
        transferenciaPage.btnExtrato().click();
        transferenciaPage.saldoConta1().getText();


        String mensagem1 = "Transferencia realizada com sucesso";
        Assert.assertEquals(mensagem1, transferenciaPage.txtTransferenciaRealizada().getText());
        transferenciaPage.btnFechar().click();
        transferenciaPage.btnVoltar().click();
        transferenciaPage.btnExtrato().click();
        transferenciaPage.saldoConta1().getText();


        //String validaSaldo1 = "R$ 1000,00";
        //Assert.assertEquals(validaSaldo1,transferenciaPage.saldoConta1().getText());

        transferenciaPage.clicBotatoSair().click();

       // cadastroPage.btnFechar().click();
    }

    public void realizarLogin2() throws IOException {
        loginPage.getEmailLogin().sendKeys("francis@automatizado.com");
        loginPage.getSenhaLogin().sendKeys("1234");
        loginPage.clicBotaoAcessar().click();
        loginPage.textoBemVindo().getText();

        String msg = "bem vindo ao BugBank :)";
        Assert.assertEquals(msg, loginPage.textoBemVindo().getText());
        loginPage.saldo().getText();

        String saldoConta = loginPage.saldo().getText();
        System.out.println("Email do Titutlar da conta 2 é :" + loginPage.getEmailLogin().getText());
        System.out.println("O Saldo da conta 2 é :" + saldoConta);

        String validaSaldo2 = "Saldo em conta " + saldoConta;
        Assert.assertEquals(validaSaldo2, loginPage.saldo().getText());

    }
}
