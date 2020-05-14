package Tests.com.openmrs.HomeTests;

import Pages.OpenMRSHomePage;
import Pages.OpenMRSLoginPage;
import Tests.TestBase;
import Utils.ConfigReader;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class OpenMRSHomePageTest extends TestBase {

    OpenMRSHomePage page= new OpenMRSHomePage();

    @BeforeClass
    public void logInMRS(){
        OpenMRSLoginPage loginPage=new OpenMRSLoginPage();

        driver.get("https://demo.openmrs.org/openmrs/login.htm");

        loginPage.logIn(ConfigReader.getProperty("username"),ConfigReader.getProperty("password"));

    }

    @Test
    public void validateMessage(){

        String expected="Logged in as Super User (admin) at Pharmacy.";
        String actualMessage=page.textMessage.getText();

        Assert.assertEquals(actualMessage,expected);
    }

    @Test
    public void validateURL(){

        String expectedURL="https://demo.openmrs.org/openmrs/referenceapplication/home.page";
        String actualURL=driver.getCurrentUrl();

        Assert.assertEquals(actualURL,expectedURL);
    }

    @Test
    public void validateHomeApps(){

        for(WebElement element : page.homePageApps){

            Assert.assertTrue(element.isDisplayed());

        }

    }
}
