package tests.signUpTests;

import libs.ConfigData;
import libs.ExcelDriver;
import libs.WebElementOnPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Actions;

import com.gargoylesoftware.htmlunit.html.Keyboard;

//import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

import pages.ContactUsPage;
import pages.SignUpPage;
//import tests.contactUsTests.TC10;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

/**
 * Created by roman on 1/23/16.
 */
public class TrashTryToDo {


    //@SuppressWarnings("null")
	public static void main(String[] args) throws Exception {
        WebDriver driver = new FirefoxDriver();
        SignUpPage signUpPage = new SignUpPage(driver);
        WebElementOnPage webElementOnPage = new WebElementOnPage(driver);
        ContactUsPage contactUsPage = new ContactUsPage(driver);
        contactUsPage.openContactUsPageAndBrowser();
            contactUsPage.selectQuestionFromDDByText();

        
        Actions action = new Actions(driver);


//        WebElement element = driver.findElement(ConfigData.ui("ContactUs.QuestionText.Input"));
//          element.click();
                contactUsPage.typeQuestionText("12312312312");
// webElementOnPage.typeTextIntoInput("123123123","ContactUs.QuestionText.Input");
//          action.sendKeys("1232342356").build().perform();
            //action.sendKeys(Keys.TAB);
//            element.sendKeys(Keys.TAB);
//            webElementOnPage.clickLink("SignUP.LastName.Input");
        //contactUsPage.typeQuestionText("wrbwaqdbwrb");
//        action.sendKeys(Keys.TAB).build().perform();
//            action.sendKeys(Keys.TAB).build().perform();
//            action.sendKeys(Keys.TAB).build().perform();
//            action.sendKeys(Keys.TAB).build().perform();
//            action.sendKeys(Keys.TAB).build().perform();
//            action.sendKeys(Keys.ENTER).build().perform();
        //action.sendKeys(driver.findElement(ConfigData.ui("ContactUs.QuestionText.Input")), "sifhbvpiwshb");
        //contactUsPage.isButtonNextIsClickableOnContactUsPage();
        //signUpPage.pressTabOnPage(5);
        //webElementOnPage.pressTabKey(5);
        
       
        System.out.println();





    }

}
