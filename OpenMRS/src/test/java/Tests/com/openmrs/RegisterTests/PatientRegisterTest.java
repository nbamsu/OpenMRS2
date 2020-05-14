package Tests.com.openmrs.RegisterTests;

import Pages.OpenMRSHomePage;
import Pages.OpenMRSLoginPage;
import Pages.OpenMRSRegisterPage;
import Tests.TestBase;
import Utils.BrowserUtils;
import Utils.ConfigReader;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PatientRegisterTest extends TestBase {

    OpenMRSRegisterPage registerPage=new OpenMRSRegisterPage();
    OpenMRSHomePage homePage=new OpenMRSHomePage();

    @DataProvider(name = "patientRegistration")
    public Object[][] getPatientInfo(){

        return new Object[][] {
                {"Robert","Smith","Male","10","April","2001","2200 Devon Ave","222346678","Child","David" },
                {"Maria","Garcia","Female","14","January","1999","Chicago IL","875456789","Sibling","Obama"},
                {"James", "Anderson","Male","22","March","1980","Des Plaines IL","23567576","Supervisor","Jennifer"}
        };
    }

    @BeforeClass
    public void logInMRS(){
        OpenMRSLoginPage loginPage=new OpenMRSLoginPage();

        driver.get("https://demo.openmrs.org/openmrs/login.htm");

        loginPage.logIn(ConfigReader.getProperty("username"),ConfigReader.getProperty("password"));

        homePage.clickApp("Register a patient");
    }

    @Test(invocationCount=5)
    public void validateTitle(){
        extentTest=extentReports.createTest("This is for title validation");
        String expectedTitle="OpenMRS Electronic Medical Record";
        String actualTitle=driver.getTitle();
        extentTest.log(Status.INFO,"This is before title validation");
        Assert.assertEquals(actualTitle, expectedTitle);
        extentTest.log(Status.INFO,"Title is validated");
       // Assert.assertTrue(false);

    }

    @Test
    public void validateHeaders(){
        extentTest=extentReports.createTest("This is for header validation");

        String actualRegisterText=registerPage.registerText.getText().trim();
        String expectedRegisterText="Register a patient";
        softAssert.assertEquals(actualRegisterText, expectedRegisterText);
        extentTest.log(Status.INFO,"Register text is validated");

        String actualQuestionText=registerPage.patientNameQuestion.getText().trim();
        String expectedQuestionText="What's the patient's name?";
        softAssert.assertEquals(actualQuestionText, expectedQuestionText);
        extentTest.log(Status.INFO,"Question text is validated");

        softAssert.assertEquals(registerPage.nameHeaders.get(0).getText(),"Given (required)" );
        softAssert.assertEquals(registerPage.nameHeaders.get(1).getText(),"Middle" );
        softAssert.assertEquals(registerPage.nameHeaders.get(2).getText(),"Family Name (required)" );
        extentTest.log(Status.INFO,"Name Headers are validated");

        softAssert.assertAll();

    }

    @Test
    public void validateDemographics(){
        extentTest=extentReports.createTest("Validation of the Demographics");

        // --normally it should come from API response or DB
        List<String> expectedHeader=OpenMRSRegisterPage.getData();

        for(int i=0;i<expectedHeader.size();i++){

            String actualHeader=registerPage.demographicsName.get(i).getText();

            softAssert.assertEquals( actualHeader, expectedHeader.get(i) );

        }
        extentTest.log(Status.INFO,"Demographic header is validated");

        softAssert.assertAll();
    }


    @Test(dependsOnMethods = "validateDemographics")
    public void validatePatientRegistration(){

        extentTest=extentReports.createTest("Validation of the Single Patient Registration");

        registerPage.registerPatient("Adam","Lee","Male","22","May",
                "2002","2200 Devon Ave","2243002244","Child","Jackson");
        extentTest.log(Status.INFO,"Patient register information is filled");

        List<String> expectedConfirmData=OpenMRSRegisterPage.getConfirmationData();
        for(int i=0;i<expectedConfirmData.size();i++){
            String actualData=registerPage.confirmationPage.get(i).getText();

            softAssert.assertEquals(actualData,expectedConfirmData.get(i));

        }
        extentTest.log(Status.INFO,"Registration is validated");

        softAssert.assertAll();

        registerPage.submitButton.click();
        extentTest.log(Status.INFO,"Registration confirm button is clicked");
    }


    @Test(dataProvider = "patientRegistration", dependsOnMethods = "validatePatientRegistration")
    public void validateMultiplePatientRegistration(String name, String familyName, String gender, String day, String month, String year, String address,
                                                    String phoneNumber, String relationShip, String relationName){

        extentTest=extentReports.createTest("Validation of the Multiple Patient Registration");

        BrowserUtils.waitForSec(2);
        driver.get("https://demo.openmrs.org/openmrs/index.htm");
        homePage.clickApp("Register a patient");
        extentTest.log(Status.INFO, "Register a patient button clicked");

        registerPage.registerPatient(name, familyName,gender,day,month,year,address,phoneNumber,relationShip,relationName);
        extentTest.log(Status.INFO, "Patient register is completed");

        registerPage.submitButton.click();
        extentTest.log(Status.INFO,"Registration confirm button is clicked");

    }



    @AfterMethod
    public void takeScreenShot(ITestResult result) throws IOException {

        if(result.getStatus()==ITestResult.FAILURE){
            extentTest.log(Status.FAIL,"TEST EXECUTION IS FAILED "+ result.getName());
            extentTest.log(Status.FAIL,"THE NAME OF THE ERROR IS "+ result.getThrowable());
            String dest=BrowserUtils.takeScreenShot();
            extentTest.fail("The test is failes").addScreenCaptureFromPath(dest);
            //extentTest.addScreenCaptureFromPath(dest);
        }
    }

}
