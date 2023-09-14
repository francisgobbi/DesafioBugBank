package PageObjects;

import Framework.Browser.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CadastroPage {

    private WebDriver driver;
    private Waits waits;

    public CadastroPage(WebDriver driver){
        this.driver = driver;
        waits = new Waits(this.driver);
    }

    public WebElement btnRegistrar(){
        return waits.visibilityOfElement(By.xpath("//button[contains(text(), 'Registrar') and contains(@class, 'style__ContainerButton')]"));
    }

    public WebElement getemail(){
        return waits.visibilityOfElement(By.xpath("//form[@class='styles__ContainerFormRegister-sc-7fhc7g-0 keVbpY']//input[@type='email' and @class='input__default' and @name='email']"));
    }

    public WebElement getNome(){
        return waits.visibilityOfElement(By.xpath("//input[@placeholder='Informe seu Nome']"));
    }

    public WebElement getSenha(){
        return waits.visibilityOfElement(By.xpath("//form[@class='styles__ContainerFormRegister-sc-7fhc7g-0 keVbpY']//input[@placeholder='Informe sua senha']"));
    }

    public WebElement getconfirmaSenha(){
        return waits.visibilityOfElement(By.xpath("//input[@placeholder='Informe a confirmação da senha']"));
    }

    public WebElement getSaldo(){
        return driver.findElement(By.xpath("//label[@id='toggleAddBalance']"));
    }

    public WebElement btnCadastrar(){
        return waits.visibilityOfElement(By.xpath("//button[normalize-space()='Cadastrar']"));
    }

    public WebElement numeroDaConta(){
        return waits.visibilityOfElement(By.id("modalText"));
    }

    public WebElement digitoDaConta(){
        return waits.visibilityOfElement(By.id("modalText"));
    }

    public WebElement btnFechar(){
        return waits.visibilityOfElement(By.xpath("//a[@id='btnCloseModal' and @class='styles__Button-sc-8zteav-5 gyHUvN']"));
    }
}
