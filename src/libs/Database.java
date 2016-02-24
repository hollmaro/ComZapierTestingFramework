package libs;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static libs.ConfigData.getCfgValue;
//import static lib.TimeDate.getCurrentTimeDB;
//import static lib.TimeDate.getCurrentTimeUI;
//import static lib.TimeDate.printCalendarDB;
import org.apache.log4j.Logger;


/*
 *  Database class provides methods for working with database.
 */
public class Database {
   private Connection connection = null;
   private String url;
   static Logger log = Logger.getLogger(Database.class);
    /*
     *  Constructor opens connection to database using connection string from config.properties file.
     *  Note in config.properties, please, that username and password for access to the database should be named as
     *  relevant connection string including "_USER"  and "_PASSWORD"
     */
    public Database(String db, String driver) throws IOException, ClassNotFoundException, SQLException {
        url=getCfgValue(db);
        log.info("URL database: " + url);

        // Load driver for JDBC class
        Class.forName(getCfgValue(driver));
        log.info("SQL driver was determined from Config file!");
        
        // Create a connection to the database
        String user_name=getCfgValue((db + "_USER"));
        String user_pass=getCfgValue((db + "_PASSWORD"));
        log.info(" user - " + user_name + " pass " + user_pass);
        connection= DriverManager.getConnection(url,getCfgValue((db + "_USER")),getCfgValue((db + "_PASSWORD")));
        log.info("Connection " + connection + " established!");
    }


    /**
     * That method verifies if the row in the query exists in the database
     * @param query
     * @return
     * @throws SQLException
     */
    public boolean isRowPresent(String query) throws SQLException {
        // Create statement for connection, execute query and save outcome in ResultSet
        Statement stm=connection.createStatement();
        ResultSet rSet = stm.executeQuery(query);
        // Calculate number of rows
        int rowNumber=0;
        while (rSet.next()){
            rowNumber++;
        }
            stm.close();
        // Verify if the row exists in the table
        if (rowNumber==0){
            return false;
        } else{
            return true;
        }

    }

    /**
     * Method gets SQL [Select COLUMN_NAME from TABLE_NAME where ...] query as parameter and returns result as String
     * @param query
     * @return
     * @throws SQLException
     */
    public String selectValue(String query) throws SQLException {
        // Create statement for connection, execute query and save outcome in ResultSet
        Statement stm=connection.createStatement();
        ResultSet rSet = stm.executeQuery(query);
        ResultSetMetaData meta=rSet.getMetaData();
        // Retrieve value from ResultSet
        String value="";

        if (rSet.next()){
            if (rSet.getObject(1)!=null){
                value=rSet.getObject(1).toString();
                if(meta.getColumnType(1)==93){
                    value=value.substring(0,value.length()-2);
                }
            }
        }

        stm.close();
        value=value.trim();
        return value;
    }

    /**
     * That method gets SQL [Select COLUMN_NAME from TABLE_NAME where ...]
     * query as parameter and returns result set as List of Strings
     * @param query
     * @return
     * @throws SQLException
     */
    public List selectResultSet(String query) throws SQLException {
        // Create statement for connection, execute query and save outcome in ResultSet
        Statement stm=connection.createStatement();
        ResultSet rSet = stm.executeQuery(query);

        // Get ResultSet's meta data
        ResultSetMetaData meta=rSet.getMetaData();

        List<String> resultSet=new ArrayList<String>();

        while (rSet.next()){
            String value="";

            if (rSet.getObject(1)!=null){
                value=rSet.getObject(1).toString();

                if(meta.getColumnType(1)==93){
                    value=value.substring(0,value.length()-2);
                }
            }

            value=value.trim();
            resultSet.add(value);
        }

        // Close the statement
        stm.close();
        return resultSet;
    }

    /**
     * That method gets SQL [Select COLUMN_NAME_1,COLUMN_NAME_2 from TABLE_NAME where ...]
     * query as parameter and returns result set as List of Strings
     * @param query
     * @return
     * @throws SQLException
     */
    public List selectTable(String query) throws SQLException {
        // Create statement for connection, execute query and save outcome in ResultSet
        Statement stm=connection.createStatement();
        ResultSet rSet = stm.executeQuery(query);

        // Get ResultSet's meta data
        ResultSetMetaData meta=rSet.getMetaData();
        int columnNumber=meta.getColumnCount();

        List<ArrayList> resultTable=new ArrayList<ArrayList>();

        // Add column_name's values in the result table header
        ArrayList<String> columnNameSet=new ArrayList<String>();
        columnNameSet.add("");
        for(int i=0;i<columnNumber;i++){
            columnNameSet.add(meta.getColumnName(i+1));
        }
        resultTable.add(columnNameSet);

        // Add result rows in the result table
        int resultSize=0;

        while (rSet.next()){
            ArrayList<String> resultSet=new ArrayList<String>();
            resultSize++;
            resultSet.add(String.valueOf(resultSize));

            for (int k=1;k<(columnNumber+1);k++){
                String value="";

                if (rSet.getObject(k)!=null){
                    value=rSet.getObject(k).toString();

                    if(meta.getColumnType(k)==93){
                        value=value.substring(0,value.length()-2);
                    }
                }

                value=value.trim();
                resultSet.add(value);
            }

            resultTable.add(resultSet);
        }

        // Close the statement
        stm.close();
        return resultTable;
    }

    /**
     * That method gets SQL [Select count(*) from TABLE_NAME where ...]
     * query as parameter and returns number of rows as Integer
     * @param query
     * @return
     * @throws SQLException
     */
    public int getRowNumber(String query) throws SQLException {
        // Create statement for connection, execute query and save outcome in ResultSet
        Statement stm=connection.createStatement();
        ResultSet rSet = stm.executeQuery(query);
        ResultSetMetaData meta=rSet.getMetaData();
        // Retrieve value from ResultSet
        int rowCount=0;

        if (rSet.next()){
            if (rSet.getObject(1)!=null){
                rowCount=Integer.parseInt(rSet.getObject(1).toString());
            }
        }

        stm.close();
        return rowCount;
    }

    /**
     * That method gets SQL [select ..., RAND(185) as IDX from ... where ... ORDER BY IDX FETCH FIRST 1 ROWS ONLY]
     * query as parameter and returns random value from DB
     * @param query
     * @return
     * @throws SQLException
     */
    public String randDbValue(String query) throws SQLException {
        // Create statement for connection, execute query and save outcome in ResultSet
        Statement stm=connection.createStatement();
        ResultSet rSet = stm.executeQuery(query);
        ResultSetMetaData meta=rSet.getMetaData();
        // Retrieve value from ResultSet
        String value="";

        if (rSet.next()){
            if (rSet.getObject(1)!=null){
                value=rSet.getObject(1).toString();

                if(meta.getColumnType(1)==93){
                    value=value.substring(0,value.length()-2);
                }
            }
        }

        stm.close();
        value=value.trim();
        return value;
    }

    /**
     * Close connection to the database
     * @throws SQLException
     */
    public void quit() throws SQLException {
        connection.close();
    }

}
