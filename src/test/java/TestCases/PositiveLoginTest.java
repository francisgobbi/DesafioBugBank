package TestCases;

import Framework.Report.Report;
import Framework.Report.ReportType;
import Framework.Report.Screenshot;
import Framework.TestBase;
import Framework.Utils.FilesOperation;
import PageObjects.CadastroPage;
import PageObjects.LoginPage;
import Validations.BemVindoValidations;
import com.aventstack.extentreports.Status;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static Framework.Browser.TypeBrowser.CHROME;

public class PositiveLoginTest  extends TestBase {

    private WebDriver driver = getDriverManager();

    CadastroPage cadastroPage = new CadastroPage(driver);
    BemVindoValidations bemVindoValidations = new BemVindoValidations(driver);
    LoginPage loginPage = new LoginPage(driver);

    @Test
    public void PositiveLogin()  {

        try {
            String emailPrimeiraConta = FilesOperation.getProperties("dadosPrimeiraContaBanco").getProperty("emailPrimeiraContaBanco");
            String senhaPrimeiraConta = FilesOperation.getProperties("dadosPrimeiraContaBanco").getProperty("senhaPrimeiraContaBanco");
            String nomePrimeiraConta = FilesOperation.getProperties("dadosPrimeiraContaBanco").getProperty("nomeTitularPrimeiraConta");

            Report.createTest("Realizar cadastro de usuário.", ReportType.SINGLE);
            Report.createStep("Realizar cadastro novo Usuário");
            cadastroPage.getBotaoRegistrar().click();
            cadastroPage.getEmail().clear();
            cadastroPage.getEmail().sendKeys(emailPrimeiraConta);
            cadastroPage.getNome().clear();
            cadastroPage.getNome().sendKeys(nomePrimeiraConta);
            cadastroPage.getSenha().clear();
            cadastroPage.getSenha().sendKeys(senhaPrimeiraConta);
            cadastroPage.getConfirmaSenha().clear();
            cadastroPage.getConfirmaSenha().sendKeys(senhaPrimeiraConta);
            cadastroPage.getCriarContaComSaldo().click();
            cadastroPage.getBotaoCadastrar().click();
            cadastroPage.getBotaoFechar().click();
            loginPage.getEmailLogin().sendKeys(emailPrimeiraConta);
            loginPage.getSenhaLogin().sendKeys(senhaPrimeiraConta);
            loginPage.getBotaoAcessar().click();
            loginPage.getTextoBemVindo().getText();
            bemVindoValidations.BemVindoValidationsOK();
        } catch (Exception e){
            Report.log(Status.FAIL, e.getMessage(), Screenshot.capture(driver));
        }
    }
}
