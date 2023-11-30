package Validations;

import PageObjects.CadastroPage;
import PageObjects.LoginPage;
import Report.Report;
import Report.Screenshot;
import com.aventstack.extentreports.Status;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class SaldoContaValidations {

    private static WebDriver driver;
    private static CadastroPage cadastroPage;
    private static LoginPage loginPage;

    public void SaldoContaValidations(WebDriver driver){
        this.driver = driver;
        cadastroPage = new CadastroPage(this.driver);
        loginPage = new LoginPage(this.driver);

    }

    public static void SaldoContaValidationsOK(){
        try{
            String saldoConta = loginPage.getSaldoSegundaConta().getText();
            String validaSaldo2 = "Saldo em conta " + saldoConta;
            Assertions.assertEquals(validaSaldo2, loginPage.getSaldo().getText());
            Assertions.assertTrue(loginPage.getSaldoSegundaConta().isDisplayed());
            Report.log(Status.PASS, "Saldo em conta realizado com sucesso.", Screenshot.captureBase64(driver));
        } catch (Exception e) {
            Report.log(Status.FAIL, e.getMessage(), Screenshot.captureBase64(driver));
        }
    }


}
