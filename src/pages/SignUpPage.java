package pages;

import libs.myUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static libs.ConfigData.ui;
import static libs.ConfigData.getCfgValue;
import java.io.IOException;
import libs.WebElementOnPage;

public class SignUpPage {
	
	WebDriver driver;
	Logger log;
	WebElementOnPage webElementOnPage;

	/**
	 * CONSTRUCTOR for SignUpPage
	 * @param externalDriver
	 */
	public SignUpPage(WebDriver externalDriver){
		this.driver = externalDriver;
		log = Logger.getLogger(getClass());
		webElementOnPage = new WebElementOnPage(driver);
	}
	/**
	 * Method opens page SignUp
	 */
	public void openSignUpPage() {
		try {
			webElementOnPage.openBrowseAndURL(getCfgValue("MAIN_URL") + "/sign-up/");
			log.info("Browser and url " + 
					getCfgValue("MAIN_URL") + "/sign-up/" + "was opened!");
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
						driver.findElement(ui("SignUP.FirstName.Input"))));
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
		webElementOnPage.wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(ui("SignUP.SearchApp.Input"))));
		boolean tempElement =
				webElementOnPage.isElementOnPage("SignUP.SearchApp.Input");
		return tempElement;
	}

	/**
	 * Method clicks button Continue
	 * @return
	 */
	public boolean clickContinueButton() {
		boolean tempElement =
				webElementOnPage.clickButton("SignUP.Continue.Button");
		return tempElement;

	}

	/**
	 * Method clicks button "No Thanks"
	 * @return
	 */
	public boolean clickNoThanksButton() {
		boolean tempElement =
				webElementOnPage.clickButton("SignUP.NoThanks.Button");
		return tempElement;
	}

	public boolean isUserIconOnPage() {
		boolean tempElement =
				webElementOnPage.isElementOnPage("SignUP.UserIcon.Img");
		return tempElement;
	}
}
