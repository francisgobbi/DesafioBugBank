package Validations;

import PageObjects.CadastroPage;
import PageObjects.LoginPage;
import PageObjects.TransferenciaPage;
import Report.Report;
import com.aventstack.extentreports.Status;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import Report.Screenshot;
public class BemVindoValidations {

    private static WebDriver driver;

    private static CadastroPage cadastroPage;

    private static LoginPage loginPage;

    public void BemVindoValidations(WebDriver driver){
        this.driver = driver;
        cadastroPage = new CadastroPage(this.driver);

    }

    public static void BemVindoValidationsOK(){
        try{
            String msg = "bem vindo ao BugBank :)";
            Assertions.assertEquals(msg, loginPage.getTextoBemVindo().getText());
            loginPage.getSaldo().getText();  Report.log(Status.PASS, "Login realizada com sucesso.", Screenshot.captureBase64(driver));
        } catch (Exception e) {
            Report.log(Status.FAIL, e.getMessage(), Screenshot.captureBase64(driver));
        }
    }

}
