package Framework;

import Framework.Browser.DriverManager;
import Framework.Browser.TypeBrowser;
import Framework.Utils.FilesOperation;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class TestBase extends DriverManager {
    private static WebDriver driver;
    private static FilesOperation filesOperation = new FilesOperation();

    public static WebDriver getDriver(){
        driver = getDriver(TypeBrowser.CHROME);
        return driver;
    }

    @Before
    public void setUp()  throws IOException {
        String URL = filesOperation.getProperties("url").getProperty("url.index");
        getDriver().get(URL);
    }

    @AfterEach
    public void finish(){
            quitDriver();
    }


}
