package TestCases;

import Framework.TestBase;
import PageObjects.CadastroPage;
import PageObjects.LoginPage;
import Tasks.TaskCriarContasTransferenciaDeValorEntreContas;
import Validations.BemVindoValidations;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class PositiveLoginTest  extends TestBase {

    private WebDriver driver = getDriverManager();

    String emailFer = "fernanda@automatizado.com";
    String senhaFer = "1234";
    CadastroPage cadastroPage = new CadastroPage(driver);
    BemVindoValidations bemVindoValidations = new BemVindoValidations(driver);

    LoginPage loginPage = new LoginPage(driver);
    @Test
    public void PositiveLogin() throws IOException {
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
    }
}
