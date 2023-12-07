package Validations;

import Framework.Report.Report;
import Framework.Report.Screenshot;
import PageObjects.CadastroPage;
import com.aventstack.extentreports.Status;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class CadastrarContaValidations {

    private WebDriver driver;

    private CadastroPage cadastroPage;

    public CadastrarContaValidations(WebDriver driver){
       this.driver = driver;
       cadastroPage = new CadastroPage(this.driver);
    }

    public void CadastrarContaValidationsOK(){
        try{
            Assertions.assertTrue(cadastroPage.getAlertCadastroConta().isDisplayed());
            Report.log(Status.PASS, "Login efetuado com sucesso.", Screenshot.captureBase64(driver));
        } catch (Exception e) {
            Report.log(Status.FAIL, e.getMessage(), Screenshot.captureBase64(driver));
     }
    }

}
