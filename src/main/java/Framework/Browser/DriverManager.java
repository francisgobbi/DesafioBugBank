package Framework.Browser;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

public class DriverManager {

    private static WebDriver driver;

    private static WebDriver getManagerDriver(TypeBrowser type) {

        switch (type) {

            case CHROME :

                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                options.addArguments("--incognito");
                driver = new ChromeDriver(options);
                break;

            case FIREFOX :

                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            case EDGE :

                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;

            case OPERA :

                WebDriverManager.operadriver().setup();
                driver = new OperaDriver();
                break;

        }
        return driver;
    }

    public static WebDriver getDriver(TypeBrowser type) {

        if (driver == null) {
            driver = getManagerDriver(type);
        }
        return driver;
    }

}
