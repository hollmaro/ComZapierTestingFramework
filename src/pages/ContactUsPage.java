package pages;

import com.gargoylesoftware.htmlunit.html.Keyboard;
import libs.ConfigData;
import libs.WebElementOnPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;

/**
 * Created by roman on 1/24/16.
 */
public class ContactUsPage {
    WebDriver driver;
    WebDriverWait wait;
    WebElementOnPage webElementOnPage;
    Logger log;

    /**
     * CONSTRUCTOR for ContactUsPage
     * @param externalDriver
     */
    public ContactUsPage(WebDriver externalDriver){
        this.driver = externalDriver;
        log = Logger.getLogger(getClass());
        wait = new WebDriverWait(driver, 30);
        webElementOnPage = new WebElementOnPage(driver);
    }

    /**
     * Method opens page Contact Us and browser
     */
    public void openContactUsPageAndBrowser(){
        try {
            webElementOnPage.openBrowseAndURL(ConfigData.getCfgValue("MAIN_URL") + "/app/contact-us");
            log.info("Page " + ConfigData.getCfgValue("MAIN_URL") + "/app/contact-us" + " was opened!");
        } catch (Exception e) {
        	log.error(e);

        }
    }

    public void closeContactUsPageAndBrowser(){
        try {
            webElementOnPage.closeBrowser();
            log.info("ContactUs page was closed!");
        } catch (Exception e) {
            log.error(e);
        }
    }

    /**
     * Method selects type of question from DD
     * @return
     */
    public boolean selectQuestionFromDDByText(){
        try {

            webElementOnPage.wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(".//*[@class='fill-button']"))));
            driver.findElement(By.xpath(".//*[@class='fill-button']")).click();
            webElementOnPage.clickLink("ContactUs.QuestionType.DD");


            //WebElement element = driver.findElement(by);
//            webElementOnPage.wait.until(ExpectedConditions.elementToBeClickable(element));

           // boolean tempElement = driver.findElement(by);
            log.info("Item was selected from DD: ");
            return true;
        } catch (Exception e) {
            log.error(e);
            return false;
        }
    }

    /**
     * Method types text into input questionText
     * @param questionText
     * @return
     */
    public boolean typeQuestionText(String questionText){
        try {
            //webElementOnPage.wait.until(ExpectedConditions.elementToBeClickable(ConfigData.ui("ContactUs.QuestionText.Input")));
            webElementOnPage.clickLink("ContactUs.QuestionText.Input");
            boolean tempElement =
                    webElementOnPage.typeTextIntoInput(questionText, "ContactUs.QuestionText.Input");
            log.info("Text \"" + questionText + "\" was typed into input questionText: " + tempElement);
            return true;
        }catch (Exception e){
            log.error(e);
            return false;
        }
    }

    /**
     * Method clicks button "Next"
     * @return
     */
    public boolean clickNextButton(){
        try {
            boolean tempElement =
                    webElementOnPage.clickButton("ContactUs.Next.Button");
            log.info("Button NEXT was clicked: " + tempElement);
            return true;
        }catch (Exception e) {
            log.error(e);
            return false;
        }
    }

    /**
     * Method checks is button "Next" clickable on ContactUs page
     * @return
     */
    public boolean isButtonNextIsClickableOnContactUsPage(){
        try {
            boolean tempElement =
                    webElementOnPage.clickButton("ContactUs.Next.Button");
            log.info("Button NEXT is present and clickable: " + tempElement) ;
            return true;
        }catch (Exception e) {
            log.error("Button NEXT is not clickable! error here: " + e);
            return false;
        }
    }

}


