package libs;


import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Properties;

/*
 *  That class provides static methods for getting values from Config and UI mapping files
 */
public class ConfigData {
    public static String cfgFile="src/config.properties";
    public static String uiMappingFile="src/UIMapping.properties";
    static Logger log = Logger.getLogger("ConfigData");

    /*
     *  Return value from .properties file
     */
    public static String getValueFromFile(String key, String fileName) throws IOException {
        Properties p = new Properties();
        // Create stream for reading from file
        FileInputStream cfg = new FileInputStream(fileName);
        // Load Properties from input stream
        p.load(cfg);
        cfg.close();

        // Return value for the property
        return(p.getProperty(key));
    }


    /*
     *  Return value from UI mapping file.
     *  Note, please, that returned value is String.
     *  We should take care of value's type by himself when will use config data value in the test.
     */
    public static String getUiMappingValue(String key) throws IOException {
    	
        return(getValueFromFile(key, uiMappingFile));
    }


    /*
    *  Return value from config file.
    *  Note, please, that returned value is String.
    *  We should take care of value's type by himself when will use config data value in the test.
    */
    public static String getCfgValue(String key) throws IOException {

        return(getValueFromFile(key, cfgFile).trim());
    }


    /*
     *  Return By class with finding method and target for WebElement from UI mapping file
     */
    public static By ui(String key)  {
        try{
    	// Get WebElement's locator from UI mapping file and divide it to finding method and target
        String[] partsOfLocator=getValueFromFile(key, uiMappingFile).split("\"");
        String findMethod=partsOfLocator[0].substring(0,partsOfLocator[0].length()-1);
        String target=partsOfLocator[1];

        // Return By class with appropriate method and target
        if (findMethod.equals("id")){
            return By.id(target);
        } else {
            if (findMethod.equals("xpath")){
                return By.xpath(target);
            } else {
                if (findMethod.equals("name")){
                    return By.name(target);
                } else {
                    if (findMethod.equals("linkText")){
                        return By.linkText(target);
                    } else {
                        if (findMethod.equals("tagName")){
                            return By.tagName(target);
                        } else {
                            if (findMethod.equals("className")){
                                return By.className(target);
                            } else {
                                if (findMethod.equals("cssSelector")){
                                    return By.cssSelector(target);
                                } else {
                                    return By.partialLinkText(target);
                                }
                            }
                        }
                    }
                }
            }
        }
        }catch (Exception e){
        	log.error("Can't get locator!");
        	return null;
        }
    }
    


}
