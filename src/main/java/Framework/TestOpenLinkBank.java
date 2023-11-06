package Framework;
import Framework.Browser.DriverManager;
import Framework.Browser.TypeBrowser;
import Framework.Utils.FilesOperation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import java.io.IOException;

public class TestOpenLinkBank extends DriverManager {
    private static WebDriver driver;
    private static FilesOperation filesOperation = new FilesOperation();

    public static WebDriver getDriverManager(){
        driver = getDriver(TypeBrowser.CHROME);
        return driver;
    }

    @BeforeEach
    public void openLinkBank()  throws IOException {
        String linkUrl = filesOperation.getProperties("urlbanco").getProperty("urlbanco");
        getDriverManager().get(linkUrl);
    }

    @AfterEach
    public void finishDriver(){
            if (driver != null) {
                driver.close();
            }

    }
}
