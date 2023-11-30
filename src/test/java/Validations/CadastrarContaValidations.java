package Validations;

import Report.Report;
import Report.Screenshot;
import PageObjects.CadastroPage;
import com.aventstack.extentreports.Status;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class CadastrarContaValidations {

    private static WebDriver driver;

    private static CadastroPage cadastroPage;

    public void CadastrarContaValidations(WebDriver driver){
       this.driver = driver;
       cadastroPage = new CadastroPage(this.driver);

    }

    public static void CadastrarContaValidationsOK(){
        try{
            Assertions.assertTrue(cadastroPage.getAlertCadastroConta().isDisplayed());
             Report.log(Status.PASS, "Login efetuado com sucesso.", Screenshot.captureBase64(driver));
        } catch (Exception e) {
             Report.log(Status.FAIL, e.getMessage(), Screenshot.captureBase64(driver));
     }
    }

}
