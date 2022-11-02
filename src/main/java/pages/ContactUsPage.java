package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import userLibs.ConfigData;
import userLibs.LoggerWrapper;
import userLibs.WebElementOnPage;

import java.util.NoSuchElementException;

public class ContactUsPage {
    WebDriver driver;
    WebElementOnPage webElementOnPage;
    Logger log;

    /**
     * CONSTRUCTOR for ContactUsPage
     * @param externalDriver
     */
    public ContactUsPage(WebDriver externalDriver){
        this.driver = externalDriver;
        log = LoggerWrapper.loggerForThisClass();
        webElementOnPage = new WebElementOnPage(driver);
    }

    /**
     * Method opens page ContactUs and browser
     */
    public void openContactUsPageAndBrowser(){
        try {
            webElementOnPage.openBrowseAndURL(ConfigData.getCfgValue("MAIN_URL") + "/app/contact-us");
            log.info("Page " + ConfigData.getCfgValue("MAIN_URL") + "/app/contact-us" + " was opened!");
        } catch (Exception e) {
            log.error(e);

        }
    }

    /**
     * Method closes ContactUs page and browser
     */
    public void closeContactUsPageAndBrowser(){
        try {
            webElementOnPage.closeBrowser();
            log.info("ContactUs page was closed!");
        } catch (Exception e) {
            log.error(e);
        }
    }

    /**
     * Method selects type of question from DD by questionNum (1-6)
     * @return
     */
    public boolean selectQuestionFromDDByQuestionNum(int questionNum){
        try {

            webElementOnPage.wait.until(ExpectedConditions.elementToBeClickable(ConfigData.ui("ContactUs.TypeQuestion.Button")));
            webElementOnPage.clickLink("ContactUs.TypeQuestion.Button");
            boolean tempElement = webElementOnPage.clickLink("ContactUs.QuestionType" + questionNum + ".DD");
            log.info("Item was selected from DD: " + tempElement);
            return true;
        } catch (Exception e) {
            log.error(e);
            return false;
        }
    }
    /**
     * Method selects NO for having account DD
     * @return
     */
    public boolean selectNoForHavingAccountFromDD(){
        try {
            webElementOnPage.clickLink("ContactUs.ButtonYesNo.DD");
            boolean tempElement = webElementOnPage.clickLink("ContactUs.HaveAccountNo.DD");
            log.info("Select NO for having account: " + tempElement);
            return true;
        } catch (NoSuchElementException e) {
            log.error(e);
            return false;
        }
    }
    /**
     * Method selects YES from having account DD
     * @return
     */
    public boolean selectYesForHavingAccountFromDD() {
        try {
            webElementOnPage.clickLink("ContactUs.ButtonYesNo.DD");
            boolean tempElement = webElementOnPage.clickLink("ContactUs.HaveAccountYes.DD");
            log.info("Select YES for having account: " + tempElement);
            return true;
        } catch (NoSuchElementException e) {
            log.error(e);
            return false;
        }
    }
    /**
     * Method clicks button "Send Email"
     * @return
     */
    public boolean clickSendEmailButton(){
        try {
            boolean tempElement = webElementOnPage.clickButton("ContactUs.SendEmailDisabled.Button");
            if (tempElement==false)
                webElementOnPage.clickButton("ContactUs.SendEmailAnabled.Button");
            log.info("Button Send Email was clicked: " + tempElement);
            return true;
        }catch (Exception e) {
            log.error(e);
            return false;
        }
    }

    /**
     * Method checks is PopUp error on page
     * @return
     */
    public boolean isPopUpErrorOnPage(){
        try {
            boolean tempElement = webElementOnPage.isElementOnPage("ContactUs.Error.PopUp");
            log.info("PopUp error is on page: " + tempElement);
            return true;
        } catch (NoSuchElementException e) {
            log.error(e);
            return false;
        }
    }

    public boolean isInputEmailOnPage(){
        try {
            webElementOnPage.wait.until(ExpectedConditions.elementToBeClickable(
                    driver.findElement(ConfigData.ui("ContactUs.Email.Input"))));
            boolean tempElement = webElementOnPage.isElementOnPage("ContactUs.Email.Input");
            log.info("Input email on page: " + tempElement);
            return true;
        } catch (NoSuchElementException e) {
            log.error(e);
            return false;
        }
    }

}


