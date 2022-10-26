package userLibs;

import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;

public class DataBaseData {
	static Logger LOG = LoggerWrapper.loggerForThisClass();
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
		LOG.info("someData = " + someData);
		dB = new Database("PADB_DB","Oracle");
		String someValue1 = dB.selectValue("select HASH,CREATE_DATE from cs_check_portal where " + someData + "");
		return someValue1;
	}

}

