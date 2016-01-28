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
        log.info("Данные считаны url database: " + url);

        // Load driver for JDBC class
        Class.forName(getCfgValue(driver));
        log.info("Считали SQL драйвер ");
        
        // Create a connection to the database
        String user_name=getCfgValue((db + "_USER"));
        String user_pass=getCfgValue((db + "_PASSWORD"));
        log.info(" user - " + user_name + " pass " + user_pass);
        connection= DriverManager.getConnection(url,getCfgValue((db + "_USER")),getCfgValue((db + "_PASSWORD")));
        log.info("дальше опять" + connection);
    }


    /*
     *  That method verifies if the row in the query exists in the database
     */
    public boolean isRowPresent(String query) throws SQLException {
        //System.out.println(query);

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

    /*
     *  That method gets SQL [Select COLUMN_NAME from TABLE_NAME where ...] query as parameter and returns result as String
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
        //System.out.println(query);
        value=value.trim();
        return value;
    }


    /*
     *  That method gets SQL [Select COLUMN_NAME from TABLE_NAME where ...] query as parameter and returns result set as List of Strings
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
        //System.out.println(query);
        return resultSet;
    }


    /*
     *  That method gets SQL [Select COLUMN_NAME_1,COLUMN_NAME_2 from TABLE_NAME where ...] query as parameter and returns result set as List of Strings
     */
    public List selectTable(String query) throws SQLException {
        // Create statement for connection, execute query and save outcome in ResultSet
        Statement stm=connection.createStatement();
        //System.out.println(query);
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
        //System.out.println(query);
        return resultTable;
    }


    /*
     *  That method gets SQL [Select count(*) from TABLE_NAME where ...] query as parameter and returns number of rows as Integer
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
        //System.out.println(query);
        return rowCount;
    }


    /*
     *  That method gets SQL [select ..., RAND(185) as IDX from ... where ... ORDER BY IDX FETCH FIRST 1 ROWS ONLY] query as parameter
     *  and returns random value from DB
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


    /*
     *  Return random Material for placing on the queue
     */
   /* public String randMaterialForQueue(String queueName, String endTime) throws SQLException {
        String material="";

        String assetType=selectValue("select ASSET_TYPE from QM_Q_ASSET_TYPE where QUEUE_NAME='" + queueName + "'");
        String is_hd=selectValue("select IS_HD from QM_QUEUE where QUEUE_NAME='" + queueName + "'");
        String is_3d=selectValue("select IS_3D from QM_QUEUE where QUEUE_NAME='" + queueName + "'");
        String is_mp4=selectValue("select IS_MP4 from QM_QUEUE where QUEUE_NAME='" + queueName + "'");
        String blockType=selectValue("select CHAN_BLOCK_TYPE from QM_QUEUE where QUEUE_NAME='"+queueName+"'");
        String pushType=selectValue("select PUSH_TYPE from CM_BLOCK_TYPES where CHAN_BLOCK_TYPE='" + blockType + "'");

        int seed=getCurrentTimeUI().get(Calendar.MILLISECOND);

        if(pushType.equals("CAROUSEL")){
            material=randDbValue("select MATERIAL_ID, RAND("+seed+") as IDX from MM_MATERIAL where (IS_DELETED='N' and ASSET_TYPE='"+assetType+"' and HD='"+is_hd+"' and IS_3D='"+is_3d+"' and MP4='"+is_mp4+"' and RIGHTS_END>='"+endTime+"' and IS_CAROUSEL_OK='Y' and MATERIAL_ID like 'B%M3') ORDER BY IDX FETCH FIRST 1 ROWS ONLY");
        } else{
            if(pushType.equals("PUSH")){
                material=randDbValue("select MATERIAL_ID, RAND("+seed+") as IDX from MM_MATERIAL where (IS_DELETED='N' and ASSET_TYPE='"+assetType+"' and HD='"+is_hd+"' and IS_3D='"+is_3d+"' and MP4='"+is_mp4+"' and PUSH_GROUP_ID is not null and RIGHTS_END>='"+endTime+"' and MATERIAL_ID like 'B%M3') ORDER BY IDX FETCH FIRST 1 ROWS ONLY");
            }
        }

        return material;
    }
*/

    /*
     *  Return random Material for placing on channel block
     */
   /* public String randMaterialForChanBlock(String chanBlock, String endTime) throws SQLException {
        String material="";

        String is_hd=selectValue("select IS_HD from CM_CHAN_BLOCKS where CHAN_BLOCK_ID="+chanBlock);
        String is_3d=selectValue("select IS_3D from CM_CHAN_BLOCKS where CHAN_BLOCK_ID="+chanBlock);
        String is_mp4=selectValue("select IS_MP4 from CM_CHAN_BLOCKS where CHAN_BLOCK_ID="+chanBlock);
        String blockType=selectValue("select CHAN_BLOCK_TYPE from CM_CHAN_BLOCKS where CHAN_BLOCK_ID="+chanBlock);
        String pushType=selectValue("select PUSH_TYPE from CM_BLOCK_TYPES where CHAN_BLOCK_TYPE='" + blockType + "'");

        int seed=getCurrentTimeUI().get(Calendar.MILLISECOND);

        if(pushType.equals("CAROUSEL")){
            material=randDbValue("select MATERIAL_ID, RAND("+seed+") as IDX from MM_MATERIAL where (IS_DELETED='N' and HD='"+is_hd+"' and IS_3D='"+is_3d+"' and MP4='"+is_mp4+"' and RIGHTS_END>='"+endTime+"' and IS_CAROUSEL_OK='Y' and MATERIAL_ID like 'B%M3') ORDER BY IDX FETCH FIRST 1 ROWS ONLY");
        } else{
            if(pushType.equals("PUSH")){
                material=randDbValue("select MATERIAL_ID, RAND("+seed+") as IDX from MM_MATERIAL where (IS_DELETED='N' and HD='"+is_hd+"' and IS_3D='"+is_3d+"' and MP4='"+is_mp4+"' and PUSH_GROUP_ID is not null and RIGHTS_END>='"+endTime+"' and MATERIAL_ID like 'B%M3') ORDER BY IDX FETCH FIRST 1 ROWS ONLY");
            }
        }

        return material;
    }
*/

    /*
     *  Return random Material
     */
   /* public String randMaterial() throws SQLException {
        int seed=getCurrentTimeUI().get(Calendar.MILLISECOND);
        String material=randDbValue("select MATERIAL_ID, RAND("+seed+") as IDX from MM_MATERIAL where (IS_DELETED='N' and RIGHTS_END>='"+printCalendarDB(getCurrentTimeDB())+"' and MATERIAL_ID like 'B%M3') ORDER BY IDX FETCH FIRST 1 ROWS ONLY");

        return material;
    }
*/

    /*
     *  Close connection to the database
     */
    public void quit() throws SQLException {
        connection.close();
    }

}
