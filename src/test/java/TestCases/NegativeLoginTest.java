package TestCases;

import Framework.Report.Report;
import Framework.Report.ReportType;
import Framework.Report.Screenshot;
import Framework.TestBase;
import Tasks.TaskCriarContasTransferenciaDeValorEntreContas;
import com.aventstack.extentreports.Status;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class NegativeLoginTest  extends TestBase {

    private WebDriver driver = getDriverManager();

    TaskCriarContasTransferenciaDeValorEntreContas transferencia = new TaskCriarContasTransferenciaDeValorEntreContas(driver);

    @Test
    public void NegativeLogin() throws IOException {

        try {
            Report.createTest("Realizar login de usuário que não existe.", ReportType.SINGLE);
            Report.createStep("Realizar login de usuário que não existe.");
            transferencia.NegativeLogin();
        } catch (Exception e) {
            Report.log(Status.FAIL, e.getMessage(), Screenshot.capture(driver));
        }
        System.out.println();
    }

}
