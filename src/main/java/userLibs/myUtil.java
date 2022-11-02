package userLibs;

import java.awt.*;
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

    public static String getScreenResolution(){
        Dimension size
                = Toolkit.getDefaultToolkit().getScreenSize();

        // width will store the width of the screen
        int width = (int)size.getWidth();

        // height will store the height of the screen
        int height = (int)size.getHeight();
        return width + "x" + height;
    }

}
