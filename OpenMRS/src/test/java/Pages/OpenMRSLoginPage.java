package Pages;

import Utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OpenMRSLoginPage {


    public OpenMRSLoginPage(){

        PageFactory.initElements(Driver.getDriver("chrome"), this);
    }


    @FindBy(id="username")
    public WebElement username;

    @FindBy(id="password")
    public WebElement password;

    @FindBy(id="Pharmacy")
    public WebElement pharmacy ;

    @FindBy(id="loginButton")
    public WebElement login;

    public void logIn(String username, String password){
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        pharmacy.click();
        login.click();
    }

}
