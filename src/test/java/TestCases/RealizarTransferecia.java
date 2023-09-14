package TestCases;
import Tasks.TransferenciaTask;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import Framework.TestBase;

import java.io.IOException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class RealizarTransferecia extends TestBase{

    private WebDriver driver = getDriver();
    TransferenciaTask transferenciaTask = new TransferenciaTask(driver);


    @Test
    public void arealizarCadastro(){
        transferenciaTask.realizaCadastro1();
    }

    @Test
    public void brealizarCadastro2() throws IOException {
      transferenciaTask.realizarCadastro2();
    }


    @Test()
    public void crealizarTransferencia() throws IOException {
        transferenciaTask.realizarTransferencia();
    }

    @Test()
    public void drealizarLogin2() throws IOException {
        transferenciaTask.realizarLogin2();
    }

}
