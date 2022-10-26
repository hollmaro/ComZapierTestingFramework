package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import userLibs.ConfigData;
import userLibs.LoggerWrapper;
import userLibs.WebElementOnPage;
import userLibs.myUtil;

import java.io.IOException;

public class SignUpPage {

	WebDriver driver;
	WebElementOnPage webElementOnPage;
	Logger log;

	/**
	 * CONSTRUCTOR for SignUpPage
	 * @param externalDriver
	 */
	public SignUpPage(WebDriver externalDriver){
		this.driver = externalDriver;
		log = LoggerWrapper.loggerForThisClass();
		webElementOnPage = new WebElementOnPage(driver);
	}
	/**
	 * Method opens page SignUp
	 */
	public void openSignUpPage() {
		try {
			webElementOnPage.openBrowseAndURL(ConfigData.getCfgValue("MAIN_URL") + "/sign-up/");
			log.info("Browser and url " +
					ConfigData.getCfgValue("MAIN_URL") + "/sign-up/" + "was opened!");
		} catch (IOException e) {
			log.error(e);
		}
	}
	/**
	 * Method closes page SignUP
	 */
	public void closeSignUpPage(){
		webElementOnPage.closeBrowser();
		log.info("Page SignUP and browser was closed!");
	}
	/**
	 * Method types first name into input FirstName
	 * @param firstName
	 * @return
	 */
	public boolean typeFirstNameIntoInputFirstName(String firstName) {
		webElementOnPage.wait.until(ExpectedConditions.elementToBeClickable(
				driver.findElement(ConfigData.ui("SignUP.FirstName.Input"))));
		boolean tempElement=
				webElementOnPage.typeTextIntoInput(firstName, "SignUP.FirstName.Input");
		log.info("First name was typed in input FirstName: " + tempElement);
		return tempElement;
	}
	/**
	 * Method types last name into input LastName
	 * @param lastName
	 * @return
	 */
	public boolean typeLastNameIntoInputLastName(String lastName) {
		boolean tempElement=
				webElementOnPage.typeTextIntoInput(lastName, "SignUP.LastName.Input");
		webElementOnPage.clickLink("SignUP.LastName.Input");
		log.info("Last name was typed into input LastName: " + tempElement);
		return tempElement;
	}
	/**
	 * Method type email into input Email
	 * @param email
	 * @return
	 */
	public boolean typeEmailIntoInputEmail(String email) {
		boolean tempElement=
				webElementOnPage.typeTextIntoInput(email, "SignUP.Email.Input");
		log.info("Email was typed into input Email: " + tempElement);
		return tempElement;
	}

	/**
	 * Method types random email into input Email
	 * @return
	 */
	public boolean typeRandomEmailIntoInputEmail(){
		String randomEmail = String.valueOf(myUtil.getNumFromDate()) + "@gmail.com";
		boolean tempElement=
				webElementOnPage.typeTextIntoInput(randomEmail, "SignUP.Email.Input");
		log.info("Random email " + randomEmail + " was typed into input email: " + tempElement);
		return tempElement;
	}
	/**
	 * Method types  password into input Password
	 * @param passw
	 * @return
	 */
	public boolean typePasswIntoInputPassword(String passw) {
		boolean tempElement=
				webElementOnPage.typeTextIntoInput(passw, "SignUP.Password.Input");
		log.info("Password was typed into input Password: " + tempElement);
		return tempElement;
	}
	/**
	 * Method have checked checkbox Updates
	 * @return
	 */
	public boolean checkUpdatesCheckBox() {
		boolean tempElement=
				webElementOnPage.setActionInCheckBox("Check", "SignUP.CheckBoxUpdates.Input");
		log.info("Check box \"Updates\" was checked: " + tempElement);
		return tempElement;
	}
	/**
	 * Method have unchecked checkbox Updates
	 * @return
	 */
	public boolean unCheckUpdatesCheckBox() {
		boolean tempElement=
				webElementOnPage.setActionInCheckBox("Uncheck", "SignUP.CheckBoxUpdates.Input");
		log.info("Check box \"Updates\" was unchecked: " + tempElement);
		return tempElement;
	}
	/**
	 * Method clicks NewFeatures link
	 * @return
	 */
	public boolean clickNewFeaturesLink() {
		boolean tempElement=
				webElementOnPage.clickLink("SignUP.NewFeatures.Link");
		log.info("Link NewFeatures was clicked: " + tempElement);
		return tempElement;
	}
	/**
	 * Method clicks on SignUP button
	 * @return
	 */
	public boolean clickSignUPButton() {
		boolean tempElement=
				webElementOnPage.clickButton("SignUP.SignUpSubmit.Button");
		log.info("Button \"Sign Up\" was clicked: " + tempElement);
		return tempElement;
	}
	/**
	 * Method clicks on TermsOfService link
	 * @return
	 */
	public boolean clickTermsOfServiceLink() {
		boolean tempElement=
				webElementOnPage.clickLink("SignUP.TermsOfService.Link");
		log.info("Link \"Terms of Service\" was clicked: " + tempElement);
		return tempElement;
	}
	/**
	 * Method clicks on GetHelp button
	 * @return
	 */
	public boolean clickGetHelpButton() {
		boolean tempElement=
				webElementOnPage.clickButton("SignUP.GetHelp.Button");
		log.info("BUtton \"GetHelp\" was clicked: " + tempElement);
		return tempElement;
	}
	/**
	 * Method checks is ErrorMessages on page
	 * @return
	 */
	public boolean isErrorMessages() {
		boolean tempElement=
				webElementOnPage.isElementOnPage("SignUP.Error.Message");
		log.info("Error message on page: " + tempElement);
		return tempElement;
	}
	/**
	 * Method press TAB "numerosity" times
	 * @param numerosity
	 * @return
	 */
	public boolean pressTabOnPage(int numerosity){
		webElementOnPage.pressTabKey(numerosity);
		log.info("TAB was pressed " + numerosity + "times");
		return true;
	}
	/**
	 * Method press ENTER "numerosity" times
	 * @param numerosity
	 * @return
	 */
	public boolean pressEnterOnPage(int numerosity){
		webElementOnPage.pressEnter(numerosity);
		log.info("Enter was pressed " + numerosity + "times");
		return true;
	}

	public boolean isAppSelectionOnPage() {
		webElementOnPage.wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(ConfigData.ui("SignUP.SearchApp.Input"))));
		return webElementOnPage.isElementOnPage("SignUP.SearchApp.Input");
	}

	/**
	 * Method clicks button Continue
	 */
	public boolean clickContinueButton() {
		return webElementOnPage.clickButton("SignUP.Continue.Button");

	}

	/**
	 * Method clicks button "No Thanks"
	 */
	public boolean clickNoThanksButton() {
		return webElementOnPage.clickButton("SignUP.NoThanks.Button");
	}

	public boolean isUserIconOnPage() {
		return webElementOnPage.isElementOnPage("SignUP.UserIcon.Img");
	}
}
