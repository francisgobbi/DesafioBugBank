package PageObjects;

import Framework.Browser.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    private WebDriver driver;
    private Waits waits;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        waits = new Waits(this.driver);
    }

    public WebElement getEmailLogin(){
        return waits.visibilityOfElement(By.name("email"));
    }

    public WebElement getSenhaLogin(){
        return waits.visibilityOfElement(By.xpath("(//input[@type='password' and @class='input__default'])[1]"));
    }
    public WebElement getTextoBemVindo(){
        return waits.visibilityOfElement(By.xpath("//p[contains(text(), 'bem vindo ao BugBank')]"));
    }
    public WebElement getSaldo(){
        return waits.visibilityOfElement((By.xpath("//p[@id= 'textBalance']")));
    }

    public WebElement getSaldoConta(){
        return waits.visibilityOfElement((By.xpath("//div//div//p[@id='textBalance']//span")));

    }

    public WebElement getNameTitularConta(){
        return waits.visibilityOfElement((By.xpath(" //div[@class='home__ContainerText-sc-1auj767-7 iDA-Ddb']//p[@id='textName']")));

    }

    public WebElement getNumeroComDigitoConta(){
        return waits.visibilityOfElement((By.xpath(" //p[@id='textAccountNumber']//span")));

    }

    public WebElement getTextSaldoConta(){
        return waits.visibilityOfElement((By.xpath("//p[@id='textBalance']")));

    }

    public WebElement getBotaoAcessar(){
        return waits.visibilityOfElement(By.xpath("//button[contains(text(), 'Acessar') and contains(@type, 'submit')]"));
    }

}
