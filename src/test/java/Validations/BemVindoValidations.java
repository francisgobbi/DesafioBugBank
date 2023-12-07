package Validations;

import PageObjects.CadastroPage;
import PageObjects.LoginPage;
import Framework.Report.Report;
import com.aventstack.extentreports.Status;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import Framework.Report.Screenshot;
public class BemVindoValidations  {

    private WebDriver driver;
    private CadastroPage cadastroPage;
    private LoginPage loginPage;

    public BemVindoValidations(WebDriver driver){
        this.driver = driver;
        cadastroPage = new CadastroPage(this.driver);
        loginPage = new LoginPage(this.driver);
    }

    public void BemVindoValidationsOK(){
        try{
            String msg = "bem vindo ao BugBank :)";
            Assertions.assertEquals(msg, loginPage.getTextoBemVindo().getText());
            loginPage.getSaldo().getText();
            Report.log(Status.PASS, "Login realizada com sucesso.", Screenshot.captureBase64(driver));
        } catch (Exception e) {
            Report.log(Status.FAIL, e.getMessage(), Screenshot.captureBase64(driver));
        }
    }

}
