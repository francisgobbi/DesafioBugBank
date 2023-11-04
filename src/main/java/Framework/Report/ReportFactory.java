package Framework.Report;

import Framework.Utils.CreateFolders;
import Framework.Utils.DateTime;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class ReportFactory {

    public static WebDriver driver;

    public static final String PATH_REPORT = System.getProperty("user.dir") + File.separator + "Report" + File.separator + "Report_" + DateTime.getDateTimeFormatReport();

    public static ExtentSparkReporter extentParkReporter;
    public static ExtentReports extentReports;

    public static void configReportExtent() {
        CreateFolders.createFolderRepost(PATH_REPORT);
        extentParkReporter = new ExtentSparkReporter(PATH_REPORT + File.separator + "Report_" + DateTime.getDateTimeFormatReport() + ".html");
        extentParkReporter.config().setDocumentTitle("Relatorio de Execucao Automacao Teste");
        extentParkReporter.config().setReportName("Relatorio de Execucao de Testes");
        extentParkReporter.config().setTheme(Theme.DARK);
        extentParkReporter.config().setEncoding("UTF-8");
        extentParkReporter.config().setTimeStampFormat("EEEE , MMMM dd, yyyy , hh-mm a '('zzz')'");
        extentParkReporter.config().setCss(".nav-wrapper { background-color: #4B0082 !important; } .nav-logo{margin-top: 10px;}.brand-logo { background-color: #4B0082 !important; padding: 0 10px 0 0 !important; margin: 0 !important; position: absolute !important } .report-name { margin-left: 80px !important } .blue.darken-3 { background-color:#4B0082 !important; color: #FFF !important;}");
        extentParkReporter.config().setJs("$('.nav-logo').html('<img src=\\\"https://i.imgur.com/HkrrWKH.png\\\" class=\\\"banri-logo\\\"/>');");

        extentReports = new ExtentReports();
        extentReports.attachReporter(extentParkReporter);
    }

    protected synchronized static ExtentReports getInstance() {
        if(extentReports==null) {
            configReportExtent();
        }
        return extentReports;
    }

}
