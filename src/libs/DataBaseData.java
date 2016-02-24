package libs;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DataBaseData {
	static Logger log = Logger.getLogger(DataBaseData.class);
	static Database dB;


	/**
	 * Test method that gets data from cs_check_portal
	 * @param someData
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public String getSomeValue(String someData) throws SQLException, ClassNotFoundException, IOException{
		log.info("someData = " + someData);
		dB = new Database("PADB_DB","Oracle");
		String someValue1 = dB.selectValue("select HASH,CREATE_DATE from cs_check_portal where " + someData + "");
		return someValue1;
	}

}

