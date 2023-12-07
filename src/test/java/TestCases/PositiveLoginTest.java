package TestCases;

import Framework.Report.Report;
import Framework.Report.ReportType;
import Framework.Report.Screenshot;
import Framework.TestBase;
import PageObjects.CadastroPage;
import PageObjects.LoginPage;
import Validations.BemVindoValidations;
import com.aventstack.extentreports.Status;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class PositiveLoginTest  extends TestBase {

    private WebDriver driver = getDriverManager();

    String emailFer = "fernanda@automatizado.com";
    String senhaFer = "1234";
    CadastroPage cadastroPage = new CadastroPage(driver);
    BemVindoValidations bemVindoValidations = new BemVindoValidations(driver);
    LoginPage loginPage = new LoginPage(driver);
    @Test
    public void PositiveLogin()  {

        try {
            Report.createTest("Realizar cadastro de usuário.", ReportType.SINGLE);
            Report.createStep("Realizar cadastro novo Usuário");
            cadastroPage.getBotaoRegistrar().click();
            cadastroPage.getEmail().clear();
            cadastroPage.getEmail().sendKeys(emailFer);
            cadastroPage.getNome().clear();
            cadastroPage.getNome().sendKeys("Fernanda");
            cadastroPage.getSenha().clear();
            cadastroPage.getSenha().sendKeys(senhaFer);
            cadastroPage.getConfirmaSenha().clear();
            cadastroPage.getConfirmaSenha().sendKeys(senhaFer);
            cadastroPage.getCriarContaComSaldo().click();
            cadastroPage.getBotaoCadastrar().click();
            cadastroPage.getBotaoFechar().click();
            loginPage.getEmailLogin().sendKeys(emailFer);
            loginPage.getSenhaLogin().sendKeys(senhaFer);
            loginPage.getBotaoAcessar().click();
            loginPage.getTextoBemVindo().getText();

            bemVindoValidations.BemVindoValidationsOK();
        } catch (Exception e){
            Report.log(Status.FAIL, e.getMessage(), Screenshot.capture(driver));
        }
        System.out.println();
    }
}
