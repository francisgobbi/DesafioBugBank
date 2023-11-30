package Validations;

import PageObjects.CadastroPage;
import PageObjects.TransferenciaPage;
import Report.Report;
import com.aventstack.extentreports.Status;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import Report.Screenshot;

public class TransferenciaRealizadaSucessoValidations {

    private static WebDriver driver;

    private static CadastroPage cadastroPage;

    private static TransferenciaPage transferenciaPage;

    public void TransferenciaRealizadaSucesso(WebDriver driver){
        this.driver = driver;
        cadastroPage = new CadastroPage(this.driver);

    }

    public static void TransferenciaRealizadaSucessoOK(){
        try{
            String mensagem = "Transferencia realizada com sucesso";
            Assertions.assertEquals(mensagem, transferenciaPage.getTextoTransferenciaRealizada().getText());
            Report.log(Status.PASS, "Transferencia realizada com sucesso.", Screenshot.captureBase64(driver));
        } catch (Exception e) {
            Report.log(Status.FAIL, e.getMessage(), Screenshot.captureBase64(driver));
        }
    }


}
