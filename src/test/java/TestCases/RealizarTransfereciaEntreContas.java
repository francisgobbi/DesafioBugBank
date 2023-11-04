package TestCases;
import Framework.Report.Report;
import Framework.Report.ReportType;
import Framework.Report.Screenshot;
import Tasks.TarefaCriarContasTransferenciaDeValorEntreContas;
import com.aventstack.extentreports.Status;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import Framework.TestOpenLinkBank;

import java.io.IOException;


public class RealizarTransfereciaEntreContas extends TestOpenLinkBank {

    private WebDriver driver = getDriver();
    TarefaCriarContasTransferenciaDeValorEntreContas transferencia = new TarefaCriarContasTransferenciaDeValorEntreContas(driver);


    @Test
    public void criarDuasContasComSaldoERealizarTransferenciaDeValorEntreContas() throws Exception {
        try {
            Report.createTest("Criar duas contas com saldo", ReportType.SINGLE);
            Report.createStep("Realizar login Usuário");
            transferencia.criarDuasContasComSaldosESalvarOsSeusDados();
            Report.log(Status.PASS, "Contas criadas com sucesso.", Screenshot.captureBase64(driver));
            transferencia.realizarLogin();
            transferencia.realizarUmaTransferenciaDeValorParaOutra();
            transferencia.realizarLoginResultadoTransferencia();
            transferencia.validarSaidaDaContaQueRealizouATransferencia();

        }catch (Exception m){
            System.out.println(m);
        }
    }


}
