package Tasks;

import PageObjects.CadastroPage;
import PageObjects.LoginPage;
import PageObjects.TransferenciaPage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import Framework.Utils.FilesOperation;

import java.io.IOException;

public class TarefaTransferencia {

    private WebDriver driver;
    private CadastroPage cadastroPage;
    private LoginPage loginPage;
    private TransferenciaPage transferenciaPage;

    public TarefaTransferencia(WebDriver driver){

        this.driver =driver;
        cadastroPage = new CadastroPage(this.driver);
        loginPage = new LoginPage(this.driver);
        transferenciaPage = new TransferenciaPage(this.driver);
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

        String validaSaldo2 = "Saldo em conta " + saldoConta;
        Assert.assertEquals(validaSaldo2, loginPage.saldo().getText());

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

        FilesOperation.setProperty("contabanco", "numeroContaBanco", numeroDaContaFormatado);
        FilesOperation.setProperty("contabanco", "digitoContaBanco", digitoDaConta);

        System.out.println(numeroDaConta);
        cadastroPage.clicBotaoFechar().click();

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
        cadastroPage.clicBotaoFechar().click();
    }

    public void realizarTransferencia() throws IOException {

        String numeroDaContaFormatado = null;
        String digitoDaConta = null;

        numeroDaContaFormatado = FilesOperation.getProperties("contabanco").getProperty("numeroContaBanco");
        digitoDaConta = FilesOperation.getProperties("contabanco").getProperty("digitoContaBanco");

        // Realizara Transferencia
        loginPage.saldo().getText();
        transferenciaPage.clicBotaoTransferencia().click();
        transferenciaPage.numeroConta().sendKeys(numeroDaContaFormatado);
        transferenciaPage.digito().sendKeys(digitoDaConta);
        transferenciaPage.valorDaTransferencia().sendKeys("955");
        transferenciaPage.descricao().sendKeys("Teste transferencia automatizada realizada com sucesso");
        transferenciaPage.clicBotaoTransferirAgora().click();
        transferenciaPage.textoTransferenciaRealizada().isDisplayed();
        String mensagem = "Transferencia realizada com sucesso";
        Assert.assertEquals(mensagem, transferenciaPage.textoTransferenciaRealizada().getText());
        transferenciaPage.clicBotaoFechar().click();
        transferenciaPage.clicBotaoVoltar().click();
        transferenciaPage.clicBotaoExtrato().click();
        transferenciaPage.saldoConta().getText();
        transferenciaPage.clicBotatoSair().click();

    }

    public void realizarLoginResultadoTransferencia() throws IOException {

        loginPage.getEmailLogin().sendKeys("francis@automatizado.com");
        loginPage.getSenhaLogin().sendKeys("1234");
        loginPage.clicBotaoAcessar().click();
        loginPage.textoBemVindo().getText();

        String msg = "bem vindo ao BugBank :)";
        Assert.assertEquals(msg, loginPage.textoBemVindo().getText());
        loginPage.saldo().getText();

        String saldoConta = loginPage.saldoSegundaConta().getText();
        String nomeTitularConta = loginPage.nameTitularConta().getText();
        System.out.println("O nome do titular da conta é :" + nomeTitularConta );
        System.out.println("O Saldo da conta é :" + saldoConta);

        String validaSaldo2 = "Saldo em conta " + saldoConta;
        Assert.assertEquals(validaSaldo2, loginPage.saldo().getText());

        transferenciaPage.clicBotaoExtrato().click();
    }

}
