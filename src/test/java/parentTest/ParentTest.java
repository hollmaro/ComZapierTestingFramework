package parentTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import userLibs.ConfigData;
import userLibs.LoggerWrapper;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;


public class ParentTest {
    public static Logger LOG = LoggerWrapper.loggerForThisClass();
    public RemoteWebDriver driver;
    public DesiredCapabilities capabilities;
    public boolean isSelenoid;
    public String browser;
    public HashMap<String, Object> selenoidOptions = new HashMap<String, Object>() {
        {
            put("enableVNC", true);
            put("enableVideo", true);
        }
    };

    public ParentTest(){
        try {
            browser = ConfigData.getCfgValue("browser");
            isSelenoid = Boolean.parseBoolean(ConfigData.getCfgValue("selenoid"));
            capabilities = new DesiredCapabilities();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeClass
    public void beforeClass() throws MalformedURLException {
        if (!isSelenoid) {
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    this.driver = new ChromeDriver();
                    LOG.info("Chrome was started as local from ParentClass");
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    this.driver = new EdgeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    this.driver = new FirefoxDriver();
                    break;
            }
        } else {
            switch (browser) {
                case "chrome":
                    capabilities.setCapability("browserName", "chrome");
                    capabilities.setCapability("browserVersion", "107.0");
                    capabilities.setCapability("selenoid:options", selenoidOptions);
                    driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
                    break;
                case "firefox":
                    capabilities.setCapability("browserName", "firefox");
                    capabilities.setCapability("browserVersion", "105.0");
                    capabilities.setCapability("selenoid:options", selenoidOptions);
                    driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
                    break;
                case "edge":
                    //another implementation
                    break;
            }
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }


    @BeforeMethod
    public void setUp(Method testInRun){
        LOG.info("Test - " +  testInRun.getName() + " - started");
    }





}
