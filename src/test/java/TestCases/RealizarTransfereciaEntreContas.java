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
    public void criarDuasContasComSaldoESalvarOsSeusDados() throws IOException {
        try {
            transferencia.criarDuasContasComSaldosESalvarOsSeusDados();
        }catch (Exception m){
            System.out.println(m);
        }
    }

    @Test
    public void AcessarUmaContaERealizarUmaTransferenciaDeValorParaOutra() throws IOException {
      try {
          transferencia.realizarLogin();
          transferencia.RealizarUmaTransferenciaDeValorParaOutra();
      }catch (Exception m){
          System.out.println(m);
        }

    }
    @Test
    public void validarEntradaESaidaDasContasEnvolvidas() throws IOException {
        try {
            transferencia.realizarLoginResultadoTransferencia();
        }catch (Exception m){
            System.out.println(m);
        }
    }

}