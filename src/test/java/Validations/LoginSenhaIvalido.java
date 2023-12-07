package Validations;

import PageObjects.CadastroPage;
import PageObjects.LoginPage;
import Framework.Report.Report;
import com.aventstack.extentreports.Status;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import Framework.Report.Screenshot;
public class LoginSenhaIvalido {

    private WebDriver driver;
    private CadastroPage cadastroPage;
    private LoginPage loginPage;

    public LoginSenhaIvalido(WebDriver driver){
        this.driver = driver;
        cadastroPage = new CadastroPage(this.driver);
        loginPage = new LoginPage(this.driver);
    }

    public void LoginSenhaIvalidoOK(){
        try{
            String msg = "Usuário ou senha inválido. Tente novamente ou verifique suas informações!";
            Assertions.assertEquals(msg, loginPage.getMensagemLoginIvalido().getText());
            Report.log(Status.PASS, "Login ivalido.", Screenshot.captureBase64(driver));
        } catch (Exception e) {
            Report.log(Status.FAIL, e.getMessage(), Screenshot.captureBase64(driver));
        }
    }

}
