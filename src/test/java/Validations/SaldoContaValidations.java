package Validations;

import PageObjects.CadastroPage;
import PageObjects.LoginPage;
import Framework.Report.Report;
import Framework.Report.Screenshot;
import PageObjects.TransferenciaPage;
import com.aventstack.extentreports.Status;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;


public class SaldoContaValidations  {
    private WebDriver driver;
    public CadastroPage cadastroPage;
    private TransferenciaPage transferenciaPage;
    public LoginPage loginPage;

    private TransferenciaRealizadaSucessoValidations transferenciaRealizadaSucessoValidations;

    public SaldoContaValidations(WebDriver driver){
        this.driver = driver;
        cadastroPage = new CadastroPage(this.driver);
        loginPage = new LoginPage(this.driver);
    }

    public void SaldoContaValidationsOK(String email, String senha){

        try{
            loginPage.getEmailLogin().sendKeys(email);
            loginPage.getSenhaLogin().sendKeys(senha);
            loginPage.getBotaoAcessar().click();

            String saldoConta = loginPage.getSaldoConta().getText();
            String validaSaldo = "Saldo em conta " + saldoConta;
            Assertions.assertEquals(validaSaldo, loginPage.getTextSaldoConta().getText());
            Assertions.assertTrue(loginPage.getSaldoConta().isDisplayed());
            Report.log(Status.PASS, "Saldo em conta realizado com sucesso.", Screenshot.captureBase64(driver));
        } catch (Exception e) {
            Report.log(Status.FAIL, e.getMessage(), Screenshot.captureBase64(driver));
        }
    }
}
