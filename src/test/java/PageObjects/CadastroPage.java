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

    public WebElement clicBotaoRegistrar(){
        return waits.visibilityOfElement(By.xpath("//button[contains(text(), 'Registrar') and contains(@type, 'button')]"));
    }

    public WebElement getMailCadastro(){
        return waits.visibilityOfElement(By.xpath(" //form[@class='style__ContainerFormLogin-sc-1wbjw6k-0 eTrcYr']//div//input[@type='email' and @class='input__default' and @name='email']\n"));
    }

    public WebElement getemail(){
        return waits.visibilityOfElement(By.xpath("//form[@class='styles__ContainerFormRegister-sc-7fhc7g-0 keVbpY']//div//input[@type='email' and @class='input__default' and @name='email']"));
    }

    public WebElement getNome(){
        return waits.visibilityOfElement(By.xpath("//input[@name='name']"));
    }

    public WebElement getSenha(){
        return waits.visibilityOfElement(By.xpath("//form[@class='styles__ContainerFormRegister-sc-7fhc7g-0 keVbpY']//div//div//input[@name='password']"));
    }

    public WebElement getconfirmaSenha(){
        return waits.visibilityOfElement(By.xpath("//div//input[@placeholder='Informe a confirmação da senha']"));
    }

    public WebElement getCriarContaComSaldo(){
        return driver.findElement(By.xpath("//div//label//label[@id='toggleAddBalance']"));
    }

    public WebElement clicBotaoCadastrar(){
        return waits.visibilityOfElement(By.xpath("//button[normalize-space()='Cadastrar']"));
    }

    public WebElement numeroDaConta(){
        return waits.visibilityOfElement(By.id("modalText"));
    }

    public WebElement digitoDaConta(){
        return waits.visibilityOfElement(By.id("modalText"));
    }

    public WebElement clicBotaoFechar(){
        return waits.visibilityOfElement(By.xpath("//a[@id='btnCloseModal' and @class='styles__Button-sc-8zteav-5 gyHUvN']"));
    }

}
