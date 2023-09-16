package TestCases;
import Tasks.TarefaTransferencia;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import Framework.TestOpenLinkBank;

import java.io.IOException;


public class RealizarTransferecia extends TestOpenLinkBank {

    private WebDriver driver = getDriver();
    TarefaTransferencia transferencia = new TarefaTransferencia(driver);


    @Test
    public void realizarCadastro() throws IOException {
        try {
            transferencia.realizaCadastro();
        }catch (Exception m){
            System.out.println(m);
        }
    }

    @Test
    public void realizarTransferencia() throws IOException {
      try {
          transferencia.realizarLogin();
          transferencia.realizarTransferencia();
      }catch (Exception m){
          System.out.println(m);
        }

    }
    @Test
    public void validarResultados() throws IOException {
        try {
            transferencia.realizarLoginResultadoTransferencia();
        }catch (Exception m){
            System.out.println(m);
        }
    }

}
