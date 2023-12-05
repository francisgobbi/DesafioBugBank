package TestCases;

import Framework.TestBase;
import Tasks.TaskCriarContasTransferenciaDeValorEntreContas;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class NegativeLoginTest  extends TestBase {

    private WebDriver driver = getDriverManager();

    TaskCriarContasTransferenciaDeValorEntreContas transferencia = new TaskCriarContasTransferenciaDeValorEntreContas(driver);

    @Test
    public void NegativeLogin() throws IOException {
        transferencia.NegativeLogin();
    }

}
