package Pages;

import Utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OpenMRSHomePage {

    public OpenMRSHomePage(){
        PageFactory.initElements(Driver.getDriver("chrome"),this);
    }

    @FindBy(xpath = "//h4")
    @CacheLookup
    public WebElement textMessage;

    @FindBy(xpath = "//div[@id='apps']//a")
    public List<WebElement> homePageApps;


    public void clickApp(String appName){

        for(WebElement element : homePageApps){

            if(element.getText().trim().equals(appName)){

                element.click();
                break;
            }
        }
    }




}
