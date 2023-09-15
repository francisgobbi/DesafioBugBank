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
    public void realizarCadastro() throws IOException {
        try {
            transferenciaTask.realizaCadastro();
        }catch (Exception m){
            System.out.println(m);
        }
    }

    @Test
    public void realizarTransferencia() throws IOException {
      try {
          transferenciaTask.realizarLogin();
          transferenciaTask.realizarTransferencia();
          transferenciaTask.realizarLoginResultado();
      }catch (Exception m){
          System.out.println(m);
        }

    }


}
