package Validations;

import PageObjects.CadastroPage;
import PageObjects.TransferenciaPage;
import Framework.Report.Report;
import com.aventstack.extentreports.Status;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import Framework.Report.Screenshot;

public class TransferenciaRealizadaSucessoValidations  {

    private WebDriver driver;
    private CadastroPage cadastroPage;
    private  TransferenciaPage transferenciaPage;

    public TransferenciaRealizadaSucessoValidations(WebDriver driver){
        this.driver = driver;
        cadastroPage = new CadastroPage(this.driver);
    }

    public void TransferenciaRealizadaSucessoOK(){
        try{
            String mensagem = "Transferencia realizada com sucesso";
            Assertions.assertEquals(mensagem, transferenciaPage.getTextoTransferenciaRealizada().getText());
            Report.log(Status.PASS, "Transferencia realizada com sucesso.", Screenshot.captureBase64(driver));
        } catch (Exception e) {
            Report.log(Status.FAIL, e.getMessage(), Screenshot.captureBase64(driver));
        }
    }


}
