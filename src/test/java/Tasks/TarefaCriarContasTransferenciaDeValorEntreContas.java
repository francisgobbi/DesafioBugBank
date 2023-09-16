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


    public void criarDuasContasComSaldosESalvarOsSeusDados() throws IOException {

        cadastroPage.clicBotaoRegistrar().click();
        cadastroPage.getemail().sendKeys("francis@automatizado.com");
        cadastroPage.getNome().sendKeys("Francis");
        cadastroPage.getSenha().sendKeys("1234");
        cadastroPage.getconfirmaSenha().sendKeys("1234");
        cadastroPage.getCriarContaComSaldo().click();
        cadastroPage.clicBotaoCadastrar().click();

        String numeroConta = cadastroPage.numeroDaConta().getText();
        String[] separarNumeroDaConta = numeroConta.split("-");

        String apenasNumeroDaConta = separarNumeroDaConta[0].replaceAll("[^0-9]", "");
        String digitoDaConta = separarNumeroDaConta[1].replaceAll("[^0-9]", "");

        FilesOperation.setProperty("contasbanco", "numeroContaBanco", apenasNumeroDaConta);
        FilesOperation.setProperty("contasbanco", "digitoContaBanco", digitoDaConta);

        System.out.println(numeroConta);
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
        cadastroPage.getCriarContaComSaldo().click();
        cadastroPage.clicBotaoCadastrar().click();

        String numeroConta1 = cadastroPage.numeroDaConta().getText();
        String[] separarNumeroDaConta1 = numeroConta1.split("-");

        String apenasNumeroDaConta1 = separarNumeroDaConta1[0].replaceAll("[^0-9]", "");
        String digitoDaConta1 = separarNumeroDaConta1[1].replaceAll("[^0-9]", "");

        FilesOperation.setProperty("contasbanco", "numeroContaBanco1", apenasNumeroDaConta1);
        FilesOperation.setProperty("contasbanco", "digitoContaBanco1", digitoDaConta1);

        System.out.println(numeroConta1);
        cadastroPage.clicBotaoFechar().click();
    }

    public void RealizarUmaTransferenciaDeValorParaOutra() throws IOException {

        String numeroDaContaFormatado = null;
        String digitoDaConta = null;

        numeroDaContaFormatado = FilesOperation.getProperties("contasbanco").getProperty("numeroContaBanco");
        digitoDaConta = FilesOperation.getProperties("contasbanco").getProperty("digitoContaBanco");

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
        System.out.println("");
        System.out.println("O nome do titular da conta é :" + nomeTitularConta );
        System.out.println("O saldo atual da conta que recebeu transferencia é :" + saldoConta);
        System.out.println("Numero da Conta : " + loginPage.numeroComDigitoConta().getText() );
        System.out.println("");
        String validaSaldo2 = "Saldo em conta " + saldoConta;
        Assert.assertEquals(validaSaldo2, loginPage.saldo().getText());

        transferenciaPage.clicBotaoExtrato().click();
    }

    public void wvalidarSaidaDaContaQueRealizouATransferencia(){
        try{
            loginPage.getEmailLogin().sendKeys("fernanda@automatizado.com");
            loginPage.getSenhaLogin().sendKeys("1234");
            loginPage.clicBotaoAcessar().click();
            loginPage.textoBemVindo().getText();

            String msg = "bem vindo ao BugBank :)";
            Assert.assertEquals(msg, loginPage.textoBemVindo().getText());
            loginPage.saldo().getText();

            String saldoConta = loginPage.saldoSegundaConta().getText();
            String nomeTitularConta = loginPage.nameTitularConta().getText();

            System.out.println("Nome do Titular da Conta : " + nomeTitularConta);
            System.out.println("Saldo da conta que realizou a trasnferencia : " + saldoConta);
            System.out.println("Numero da Conta : " + loginPage.numeroComDigitoConta().getText() );

            String validaSaldo2 = "Saldo em conta " + saldoConta;
            Assert.assertEquals(validaSaldo2, loginPage.saldo().getText());
        }catch(Exception e){
            System.out.println(e);

        }

    }

}
