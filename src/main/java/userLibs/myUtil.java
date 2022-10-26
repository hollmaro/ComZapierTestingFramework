package userLibs;

import java.util.Date;

public class myUtil {
    /**
     * Method generate unique num
     * @return
     */
    public static Long getNumFromDate(){
        Date date = new Date();
        return date.getTime();

    }

}
