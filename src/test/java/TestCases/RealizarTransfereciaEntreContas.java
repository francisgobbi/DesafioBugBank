package TestCases;
import Tasks.TarefaCriarContasTransferenciaDeValorEntreContas;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import Framework.TestOpenLinkBank;

import java.io.IOException;


public class RealizarTransfereciaEntreContas extends TestOpenLinkBank {

    private WebDriver driver = getDriver();
    TarefaCriarContasTransferenciaDeValorEntreContas transferencia = new TarefaCriarContasTransferenciaDeValorEntreContas(driver);


    @Test
    public void acriarDuasContasComSaldoESalvarOsSeusDados() throws IOException {
        try {
            transferencia.acriarDuasContasComSaldosESalvarOsSeusDados();
        }catch (Exception m){
            System.out.println(m);
        }
    }
    @Test
    public void brealizarUmaTransferenciaDeValorParaOutra() throws IOException {
      try {
          transferencia.brealizarLogin();
          transferencia.brealizarUmaTransferenciaDeValorParaOutra();
      }catch (Exception m){
          System.out.println(m);
        }

    }
    @Test
    public void xvalidarEntradaDaContaEnvolvida() throws IOException {
        try {
            transferencia.xrealizarLoginResultadoTransferencia();
        }catch (Exception m){
            System.out.println(m);
        }
    }
    @Test
    public void zvalidarSaidaDaContaQueRealizouATransferencia() throws IOException {
        try {
            transferencia.wvalidarSaidaDaContaQueRealizouATransferencia();
        }catch (Exception m){
            System.out.println(m);
        }
    }

}
