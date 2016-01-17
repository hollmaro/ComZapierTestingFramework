package tests;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ParentTest {
	WebDriver driver;
	Logger log;
	
	@Before
	public void setUp(){
		//driver = new FirefoxDriver();
		driver = new ChromeDriver();
		log = Logger.getLogger(getClass());
		
	}

}
