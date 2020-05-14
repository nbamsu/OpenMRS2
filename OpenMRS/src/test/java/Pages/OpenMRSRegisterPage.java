package Pages;

import Utils.BrowserUtils;
import Utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.util.ArrayList;
import java.util.List;

public class OpenMRSRegisterPage {

    public OpenMRSRegisterPage(){

        PageFactory.initElements(Driver.getDriver("chrome"),this);
    }


    @FindBy(xpath = "//h2")
    public WebElement registerText;

    @FindBy(xpath = "//fieldset[@id='demographics-name']//h3")
    public WebElement patientNameQuestion;

    @FindBy(xpath = "//fieldset[@id='demographics-name']//p//label")
    public List<WebElement> nameHeaders;

    @FindBy(xpath = "//ul[@id='formBreadcrumb']//span")
    public List<WebElement> demographicsName;

    @FindBy(name = "givenName")
    public WebElement givenName;

    @FindBy(name = "familyName")
    public WebElement familyName;

    @FindBy(xpath = "//span[@id='genderLabel']")
    public WebElement genderLabel;

    @FindBy(id="gender-field")
    public WebElement genderField;

    @FindBy(xpath = "//span[@id='birthdateLabel']")
    public WebElement birthDateLabel;

    @FindBy(id="birthdateDay-field")
    public WebElement birthdateDayField;

    @FindBy(id="birthdateMonth-field")
    public WebElement birthdateMonthField;

    @FindBy(id="birthdateYear-field")
    public WebElement birthdateYearField;

    @FindBy(xpath = "//span[.='Address']")
    public WebElement AddressButton;

    @FindBy(id="address1")
    public WebElement address;

    @FindBy(xpath = "//span[.='Phone Number']")
    public WebElement PhoneNumber;

    @FindBy(name = "phoneNumber")
    public WebElement phoneNumberBox;

    @FindBy(xpath = "//span[.='Relatives']")
    public WebElement Relatives;

    @FindBy(id="relationship_type")
    public WebElement relationshipType;

    @FindBy(xpath = "//input[@ng-model='relationship.name']")
    public WebElement relationShipName;

    @FindBy(id="confirmation_label")
    public WebElement confirmationLabel;

    @FindBy(xpath ="//div[@id='dataCanvas']//p" )
    public List<WebElement> confirmationPage;

    @FindBy(id="submit")
    public WebElement submitButton;

    @FindBy(xpath = "//ul[@id='breadcrumbs']//a")
    public WebElement homeButton;



    public static List<String> getData(){
        List<String> headers=new ArrayList<String>();
        headers.add("Demographics");
        headers.add("Name");
        headers.add("Gender");
        headers.add("Birthdate");
        headers.add("Contact Info");
        headers.add("Address");
        headers.add("Phone Number");
        headers.add("Relationships");
        headers.add("Relatives");
        headers.add("Confirm");
        return headers;
    }

    public static List<String> getConfirmationData(){

        List<String> headers=new ArrayList<String>();
        headers.add("Name: Adam, Lee");
        headers.add("Gender: Male");
        headers.add("Birthdate: 22, May, 2002");
        headers.add("Address: 2200 Devon Ave");
        headers.add("Phone Number: 2243002244");
        headers.add("Relatives: Jackson - Child");

        return headers;
    }


    public void registerPatient(String name, String familyName, String gender, String day, String month, String year, String address,
                                String phoneNumber, String relationShip, String relationName){

        givenName.sendKeys(name);

        this.familyName.sendKeys(familyName);

        genderLabel.click();
        BrowserUtils.selectByVisibleText(genderField,gender);
        birthDateLabel.click();
        birthdateDayField.sendKeys(day);
        BrowserUtils.selectByVisibleText(birthdateMonthField,month);
        birthdateYearField.sendKeys(year);
        AddressButton.click();

        this.address.sendKeys(address);

        PhoneNumber.click();
        phoneNumberBox.sendKeys(phoneNumber);
        Relatives.click();
        BrowserUtils.selectByVisibleText(relationshipType, relationShip);
        relationShipName.sendKeys(relationName);
        confirmationLabel.click();

    }
}

