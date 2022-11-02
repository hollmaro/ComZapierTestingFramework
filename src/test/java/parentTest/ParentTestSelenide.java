package parentTest;

import com.codeborne.selenide.Configuration;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import userLibs.ConfigData;
import userLibs.LoggerWrapper;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;


public class ParentTestSelenide {
    public static Logger LOG = LoggerWrapper.loggerForThisClass();
    private String userBrowserFromMvnCmd = "chrome";
    public MutableCapabilities capabilities;
    public boolean isSelenoid;
    public String browserFromConfig;
    public HashMap<String, Object> selenoidOptions = new HashMap<String, Object>() {
        {
            put("enableVNC", true);
            put("enableVideo", true);
        }
    };

    public ParentTestSelenide()  {
        try {
            browserFromConfig = ConfigData.getCfgValue("browser");
            userBrowserFromMvnCmd = System.getProperty("selenide.userBrowser");
            isSelenoid = Boolean.parseBoolean(ConfigData.getCfgValue("selenoid"));
            capabilities = new MutableCapabilities();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeClass
    public void beforeClass() {
        String browser = userBrowserFromMvnCmd == null
                ? browserFromConfig
                : userBrowserFromMvnCmd;
        if (isSelenoid) {
            //SELENOID (containers in docker) test execution
            Configuration.remote = "http://localhost:4444/wd/hub";
            Configuration.browser = browser;
                switch (browser) {
                    case "chrome":
                        //capabilities.setCapability("browserVersion", "106.0"); //provide specific browser version if it's need
                        break;
                    case "edge":
                        //specific logic
                        break;
                    case "firefox":
                        //capabilities.setCapability("browserVersion", "107.0"); //provide specific browser version if it's need
                        //specific logic
                        break;
                }
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            Configuration.browserCapabilities = capabilities;
        } else {
            //LOCAL test execution (browsers should be installed)
            Configuration.browser = browser;
        }
            Configuration.webdriverLogsEnabled = true;
            Configuration.pageLoadTimeout = 30*1000; //ms
        LOG.info("Test execution with BROWSER: " + Configuration.browser);
        LOG.info("Test execution with SOLENOID (in DOCKER): " + isSelenoid);
    }

    @BeforeMethod
    public void setUp(Method testInRun){
        LOG.info("Test - " +  testInRun.getName() + " - started");
    }





}
