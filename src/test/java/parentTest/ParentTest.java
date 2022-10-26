package parentTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.IInvokedMethod;
import org.testng.annotations.BeforeClass;
import userLibs.ConfigData;
import userLibs.LoggerWrapper;

import java.io.IOException;
import java.util.Set;


public class ParentTest {
    public static Logger LOG = LoggerWrapper.loggerForThisClass();
    //public RemoteWebDriver driver;
    public WebDriver driver;
    public DesiredCapabilities capability;

    @BeforeClass
    public void beforeClass() {
        try {
            switch (ConfigData.getCfgValue("browser")) {
                case "chrome":
                    System.setProperty("webdriver.chrome.driver", String.valueOf(WebDriverManager.chromedriver().getBrowserPath()));
//                    WebDriverManager.chromedriver().setup();
                    this.driver = WebDriverManager.chromedriver().create();
                    LOG.info("Chrome was started from ParentClass");
                      /*capability = DesiredCapabilities.chrome();
                      this.driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);*/
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    this.driver = new EdgeDriver();

                      /*capability = DesiredCapabilities.edge();
                      this.driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);*/
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    this.driver = new FirefoxDriver();
                      /*capability = DesiredCapabilities.firefox();
                      this.driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);*/
                    break;

            }
        } catch (IOException e) {
            e.printStackTrace();
            LOG.error("browser value is not set in config.properties file");
        }
        Set w = driver.getWindowHandles();
        w.remove(w.size() -1);
//      driver.manage().window().maximize();
//      driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        //log.info(" FROM Constructor ----- " + driver.getClass() + " -------" );
    }



    //@BeforeMethod
    public void setUp(IInvokedMethod iInvokedMethod){
        LOG.info("Test - " +  iInvokedMethod.getTestMethod() + " - started");
    }





}
