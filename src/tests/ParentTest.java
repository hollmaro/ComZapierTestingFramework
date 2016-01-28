package tests;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

@RunWith(Parameterized.class)
public class ParentTest {
  public RemoteWebDriver driver;
  WebDriver iE, fireFox, chrome;
  public Logger log = Logger.getLogger(getClass());

  @Rule 
  public TestName name = new TestName();

  @Parameterized.Parameters
  public static Collection data() {
   return Arrays.asList(new String[][] { 
    { "fireFoxString" }
   /* ,
    { "chromeString" }*/

    });
  }

  
  public ParentTest(String browser) throws MalformedURLException {
   if (browser.equals("fireFoxString")) {
    this.driver = new FirefoxDriver();
   } else {
    if (browser.equals("chromeString")) {
     this.driver = new ChromeDriver();
    } 
    
   }
   log.info(" ----- " + driver.getClass() + " -------" );
   
   

  }
 
 @Before
 public void setUp(){
//  driver = new FirefoxDriver();
//  driver = new ChromeDriver();
	 log.info("Test - " +  name.getMethodName() + " - started");
 }
 
 
 
 
 
}
