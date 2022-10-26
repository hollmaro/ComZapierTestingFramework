package userLibs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerWrapper {
    private static volatile LoggerWrapper instance = null;

    public static LoggerWrapper get() {
        if (instance == null) {
            synchronized (LoggerWrapper.class) {
                if (instance == null) {
                    instance = new LoggerWrapper();
                }
            }
        }
        return instance;
    }

    public static Logger loggerForThisClass() {
        // We use the third stack element; second is this method, first is .getStackTrace()
        StackTraceElement myCaller = Thread.currentThread().getStackTrace()[2];
        return LogManager.getLogger(myCaller.getClassName());
    }

}
