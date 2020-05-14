package Tests.com.openmrs.LoginTests;

import Pages.OpenMRSLoginPage;
import Tests.TestBase;
import Utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OpenMRSLoginTest extends TestBase {



    @Test
    public void testLogin(){

        driver.get("https://demo.openmrs.org/openmrs/login.htm");
        OpenMRSLoginPage page=new OpenMRSLoginPage();
        String username= ConfigReader.getProperty("username");
        String password= ConfigReader.getProperty("password");
        page.logIn(username, password);
        String expectedTitle="Home";
        String actualTitle=driver.getTitle();

        Assert.assertEquals(actualTitle,expectedTitle);

    }

}
