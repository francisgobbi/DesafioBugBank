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

    public WebElement getBotaoRegistrar(){
        return waits.visibilityOfElement(By.xpath("//button[contains(text(), 'Registrar') and contains(@type, 'button')]"));
    }

    public WebElement getEmail(){
        return waits.visibilityOfElement(By.xpath("//form[@class='styles__ContainerFormRegister-sc-7fhc7g-0 keVbpY']//div//input[@type='email' and @class='input__default' and @name='email']"));
    }

    public WebElement getNome(){
        return waits.visibilityOfElement(By.xpath("//input[@name='name']"));
    }

    public WebElement getSenha(){
        return waits.visibilityOfElement(By.xpath("//form[@class='styles__ContainerFormRegister-sc-7fhc7g-0 keVbpY']//div//div//input[@name='password']"));
    }

    public WebElement getConfirmaSenha(){
        return waits.visibilityOfElement(By.xpath("//div//input[@placeholder='Informe a confirmação da senha']"));
    }

    public WebElement getCriarContaComSaldo(){
        return driver.findElement(By.xpath("//label[@id='toggleAddBalance']"));
    }

    public WebElement getBotaoCadastrar(){
        return waits.visibilityOfElement(By.xpath("//button[normalize-space()='Cadastrar']"));
    }

    public WebElement getNumeroDaConta(){
        return waits.visibilityOfElement(By.id("modalText"));
    }

    public WebElement getBotaoFechar(){
        return waits.visibilityOfElement(By.xpath("//a[@id='btnCloseModal' and @class='styles__Button-sc-8zteav-5 gyHUvN']"));
    }

    public WebElement getAlertCadastroConta(){
        return waits.visibilityOfElement(By.xpath("//p[@id='modalText']"));
    }


}
