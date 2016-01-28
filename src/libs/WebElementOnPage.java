package libs;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static libs.ConfigData.ui;


public class WebElementOnPage {

	WebDriver driver;
	Logger log;
	public WebDriverWait wait;
	Actions keyAction;
	
	
	/**
	 * CONSTRUCTOR FOR CLASS WebElementOnPAge
	 * @param extDriver
	 */
	public WebElementOnPage(WebDriver extDriver){
		this.driver = extDriver;
		log = Logger.getLogger(getClass());
		wait = new WebDriverWait(driver, 30);
		keyAction = new Actions(driver);

	}
	/**
	 * Method open browser and url
	 * @param url
	 */
	public void openBrowseAndURL(String url) {
		try {
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			driver.get(url);
			log.info("Browser and url "+ url + " was opened!");
		} catch (Exception e) {
			log.error(e);
			Assert.assertTrue("Browser wasn't opened", false);
			
		}
	}
	/**
	 * Method closes Browser
	 */
	public void closeBrowser() {
		try {
			driver.quit();
			log.info("Browser was closed!");
		} catch (Exception e) {
			log.error(e);
		}
	}
	/**
	 * Method typed text into input by keyInputLocator
	 * @param text
	 * @param keyInputLocator
	 * @return
	 */
	public boolean typeTextIntoInput(String text, String keyInputLocator){
		try {
			WebElement input = driver.findElement(ui(keyInputLocator));

			input.click();
			keyAction.sendKeys(text).build().perform();
			log.info("Text "+text+" was typed into input!");
			return true;
		} catch (Exception e) {
			log.error(e);
			return false;
		}
	}
	/**
	 * Method clicks link by keyLinkLocator
	 * @param keyLinkLocator
	 * @return
	 */
	public boolean clickLink(String keyLinkLocator) {
		try {
			WebElement link = driver.findElement(ui(keyLinkLocator));
			link.click();
			log.info("Lick "+link+" was clicked!");
			return true;
		} catch (Exception e) {
			log.error(e);
			return false;
		}
	}
	/**
	 * Metod clicks Button by keyButtonLocator
	 * @param keyButtonLocator
	 * @return
	 */
	public boolean clickButton(String keyButtonLocator) {
		try {
			WebElement button = driver.findElement(ui(keyButtonLocator));
			button.click();
			log.info("Button "+button+" was clicked");
			return true;
		} catch (Exception e) {
			log.error(e);
			return false;
		}
	}
	/**
	 * Method clicks on radio button by keyRadioButtonLocator
	 * @param keyRadioButtonLocator
	 * @return
	 */
	public boolean clickOnRadioButton(String keyRadioButtonLocator){
		try {
			WebElement radioButton = driver.findElement(ui(keyRadioButtonLocator));
			radioButton.click();
			log.info("Radiobutton was clicked!");
			return true;
		} catch (Exception e) {
			log.error(e);
			return false;
		}
	}
	/**
	 * Method select item from DD by text
	 * @param text
	 * @param keyMainDDLocator
	 * @return
	 */
	public boolean selectItemFromDDByText(String text, String keyMainDDLocator) {
		try {
			WebElement mainDD = driver.findElement(ui(keyMainDDLocator));
			Select itemFromDD = new Select(mainDD);
			itemFromDD.selectByVisibleText(text);
			log.info("Item "+text+" from drop down was selected!");
			return true;
		} catch (Exception e) {
			log.error(e);
			return false;
		}
	}
	/**
	 * Method check on uncheck checkbox
	 * @param checkBoxStateFromTC (sould be exactly "Check" or "Uncheck")
	 * @param keyCheckBoxLocator
	 * @return
	 */
	public boolean setActionInCheckBox(String checkBoxStateFromTC,
			String keyCheckBoxLocator) {
		try {
			WebElement checkBox = driver.findElement(ui(keyCheckBoxLocator));
			
			if (checkBox.isSelected()&&keyCheckBoxLocator.equals("Check"))
				log.info("Check box is already checked!");
			else if(checkBox.isSelected()&&keyCheckBoxLocator.equals("Uncheck")){
				checkBox.click();
				log.info("Check box is unchecked!");
			}
			else if(!checkBox.isSelected()&&keyCheckBoxLocator.equals("Check")){
				checkBox.click();
				log.info("Check box is checked");
			}
			else if(!checkBox.isSelected()&&keyCheckBoxLocator.equals("Uncheck")){
				log.info("Check box is already unchecked!");
			}
			return true;	
		} catch (Exception e) {
			log.error(e);
			return false;
		}
	}
	/**
	 * Method ckecks is element on page (by keyElementLocator)
	 * @param keyElementLocator
	 * @return
	 */
	public boolean isElementOnPage(String keyElementLocator){
		try {
			WebElement element = driver.findElement(ui(keyElementLocator));
		if (element.isDisplayed()&&element.isEnabled()){
			log.info("Element is on page!");
			return true;
		}
		else 
			log.error("Element is not Enable or not Displayed!");
			return false;
		} catch (Exception e) {
			log.error("Can't find element on page!");
			return false;
		}
	}
	public boolean isAnyElementOnPage(String elementLocator){
		try {
			WebElement tempElement;
			tempElement = driver.findElement(By.xpath(elementLocator));
			if (tempElement.isEnabled() && tempElement.isDisplayed()){
				log.info("Element is present on page");
				return true;
			}
			else{
				log.info("Element is not Enable or not Displayed");
				return false;
			}
		} catch (Exception e) {
			log.info("Element not found");
			return false;
		}
		
	}
	/**
	 * Method cheks is element has empty value by key from UIMapping.properties file
	 * @param keyElementLocator
	 * @return
	 */
	public boolean isElementHasEmptyValue(String keyElementLocator){
		try {
			WebElement tempElement = driver.findElement(ui(keyElementLocator));
			String valueConsist = tempElement.getAttribute("value");
			if(valueConsist.equals("")){
				log.info("Element is empty (cheked)!");
				return true;
			}
			else 
			log.info("Element is not empty!");
			return false;
					
		} catch (Exception e) {
			log.error(e);
			return false;
		}
	}
	/**
	 * Method press TAB key "numerosity" times
	 * @param numerosity
	 * @return
	 */
	public boolean pressTabKey(int numerosity) {
		try {
			for (int i = 0; i < numerosity; i++) {
				keyAction.sendKeys(Keys.TAB).build().perform();
			}
			log.info("Key TAB was pressed! ");
			return true;
		} catch (Exception e) {
			log.error(e);
			return false;
		}
	}
	/**
	 * Method press ENTER key "numerosity" times
	 * @param numerosity
	 * @return
	 */
	public boolean pressEnter(int numerosity){
		try {
			for(int i = 0; i < numerosity; i++){
			keyAction.sendKeys(Keys.ENTER).build().perform();
			}
			log.info("Key ENTER was pressed!");
			return true;
		} catch (Exception e) {
			log.error(e);
			return false;
		}
	}

}

