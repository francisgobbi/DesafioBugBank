package TestCases;
import Framework.Report.Report;
import Framework.Report.ReportType;
import Tasks.TaskCriarContasTransferenciaDeValorEntreContas;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import Framework.TestBase;


public class TestRealizarTransfereciaEntreContas extends TestBase {

    private WebDriver driver = getDriverManager();

    TaskCriarContasTransferenciaDeValorEntreContas transferencia = new TaskCriarContasTransferenciaDeValorEntreContas(driver);

    @Test
    public void criarDuasContasComSaldoERealizarTransferenciaDeValorEntreContas() throws Exception {
        try {
            Report.createTest("Criar duas contas com saldo", ReportType.GROUP);
            Report.createStep("Cadastrar Conta com Saldo");
            transferencia.criarDuasContasComSaldosESalvarOsSeusDados();
            Report.createTest("Realizar Login", ReportType.GROUP);
            Report.createStep("Realizar Login Usuário");
            transferencia.realizarLogin();
            Report.createTest("Realizar Trasnferencia", ReportType.GROUP);
            Report.createStep("Realizar Trasnferencia");
            transferencia.realizarUmaTransferenciaDeValorParaOutra();
            Report.createTest("Realizar Login Resultado Trasnferencia", ReportType.GROUP);
            Report.createStep("Realizar Login Resultado Trasnferencia");
            transferencia.realizarLoginResultadoTransferencia();
            Report.createTest("Validar Saida Resultado Trasnferencia", ReportType.GROUP);
            Report.createStep("Validar Saida  Login Resultado Trasnferencia");
            transferencia.validarSaidaDaContaQueRealizouATransferencia();

        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
