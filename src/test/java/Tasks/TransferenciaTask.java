package Tasks;

import PageObjects.CadastroPage;
import PageObjects.LoginPage;
import PageObjects.TransferenciaPage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import Framework.Utils.FilesOperation;

import java.io.IOException;

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

    public void realizaCadastro1(){

        cadastroPage.btnRegistrar().click();
        cadastroPage.getemail().sendKeys("francis@automacao.com.br");
        cadastroPage.getNome().sendKeys("Francis");
        cadastroPage.getSenha().sendKeys("1234");
        cadastroPage.getconfirmaSenha().sendKeys("1234");
        cadastroPage.getSaldo().click();
        cadastroPage.btnCadastrar().click();
        cadastroPage.numeroDaConta().getText();

        cadastroPage.btnFechar().click();

    }

    public void realizarCadastro2() throws IOException {

        cadastroPage.btnRegistrar().click();
        cadastroPage.getemail().sendKeys("fernanda@automacao.com.br");
        cadastroPage.getNome().sendKeys("Fernanda");
        cadastroPage.getSenha().sendKeys("1234");
        cadastroPage.getconfirmaSenha().sendKeys("1234");
        cadastroPage.btnCadastrar().click();
        cadastroPage.numeroDaConta().getText();
        cadastroPage.digitoDaConta().getText();

        String numeroDaConta = cadastroPage.numeroDaConta().getText();
        String[] splitNumeroDaConta = numeroDaConta.split("-");

        String numeroDaContaFormatado = splitNumeroDaConta[0].replaceAll("[^0-9]", "");
        String digitoDaConta = splitNumeroDaConta[1].replaceAll("[^0-9]", "");

        FilesOperation.setProperty("conta", "numeroConta", numeroDaContaFormatado);
        FilesOperation.setProperty("conta", "digitoConta", digitoDaConta);

        cadastroPage.btnFechar().click();
    }

    public void realizarTransferencia() throws IOException {

        loginPage.getEmailLogin().sendKeys("francis@automacao.com.br");
        loginPage.getSenhaLogin().sendKeys("1234");
        loginPage.btnAcessar().click();
        loginPage.txtBemVindo().getText();
        String msg = "bem vindo ao BugBank :)";
        Assert.assertEquals(msg, loginPage.txtBemVindo().getText());
        loginPage.saldo().getText();
        transferenciaPage.btnTransferencia().click();

        String numeroDaContaFormatado = null;
        String digitoDaConta = null;

        numeroDaContaFormatado = FilesOperation.getProperties("conta").getProperty("numeroConta");
        digitoDaConta = FilesOperation.getProperties("conta").getProperty("digitoConta");


        transferenciaPage.numeroDaConta().sendKeys(numeroDaContaFormatado);
        transferenciaPage.digito().sendKeys(digitoDaConta);
        transferenciaPage.valorDaTransferencia().sendKeys("1000");
        transferenciaPage.descricao().sendKeys("Teste Automação");
        transferenciaPage.btnTransferirAgora().click();
        transferenciaPage.txtTransferenciaRealizada().isDisplayed();
        String mensagem = "Transferencia realizada com sucesso";
        Assert.assertEquals(mensagem, transferenciaPage.txtTransferenciaRealizada().getText());
        transferenciaPage.btnFechar().click();
        transferenciaPage.btnVoltar().click();
        transferenciaPage.btnExtrato().click();
        transferenciaPage.saldoConta1().getText();


        String saldoConta1 = transferenciaPage.saldoConta1().getText();
        System.out.println("O Saldo da conta 1 é :" + saldoConta1);

        String[] splitSaldoConta1 = saldoConta1.split("-");
        String saldoFormatado = splitSaldoConta1[0].replaceAll("[^0-9]", "");
        FilesOperation.setProperty("saldo", "saldoConta1", saldoFormatado);

        String validaSaldo1 = "R$ 1000,00";
        Assert.assertEquals(validaSaldo1,transferenciaPage.saldoConta1().getText());

        transferenciaPage.btnSair().click();

    }

    public void realizarLogin2() throws IOException {
        loginPage.getEmailLogin().sendKeys("fernanda@automacao.com.br");
        loginPage.getSenhaLogin().sendKeys("1234");
        loginPage.btnAcessar().click();
        loginPage.txtBemVindo().getText();

        String msg = "bem vindo ao BugBank :)";
        Assert.assertEquals(msg, loginPage.txtBemVindo().getText());
        loginPage.saldo().getText();

        String saldoConta2 = loginPage.saldo().getText();
        System.out.println("O Saldo da conta 2 é :" + saldoConta2);

        String[] splitSaldoConta2 = saldoConta2.split("-");

        String saldoFormatado = splitSaldoConta2[0].replaceAll("[^0-9]", "");
        FilesOperation.setProperty("saldo", "saldoConta2", saldoFormatado);

        String validaSaldo2 = "Saldo em conta R$ 1000,00";
        //Assert.assertEquals(validaSaldo2, loginPage.saldo().getText());

    }
}
