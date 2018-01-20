package com.bitcodeing.framework.common;

import android.util.Log;
import com.bitcodeing.framework.BuildConfig;
import com.bitcodeing.framework.enums.LogType;
import com.google.firebase.crash.FirebaseCrash;

/**
 * Common Logger Utility Class
 *
 * @author Luis Hernandez
 * @version 1.0
 */
public final class CommonLogger {

    /**
     * Log messages utility
     *
     * @param tag  String Tag
     * @param msg  String message to log
     * @param type Log verbose type
     */
    public static void log(String tag, String msg, LogType type) {
       // if (BuildConfig.DEBUG) {
            switch (type) {
                case INFO:
                    Log.i(tag, msg);
                    break;
                case DEBUG:
                    Log.d(tag, msg);
                    break;
                case WARNING:
                    Log.w(tag, msg);
                    break;
                case ERROR:
                    Log.e(tag, msg);
                    break;
            }
        //}
    }

    /**
     * log any throwable exception
     *
     * @param tag       String Tag
     * @param throwable Throwable Exception
     * @param type      Log verbose type
     */
    public static void log(String tag, Throwable throwable, LogType type) {
        //FirebaseCrash.report(throwable);
        switch (type) {
            case INFO:
                Log.i(tag, throwable.getMessage(), throwable);
                break;
            case DEBUG:
                Log.d(tag, throwable.getMessage(), throwable);
                break;
            case WARNING:
                Log.w(tag, throwable.getMessage(), throwable);
                break;
            case ERROR:
                Log.e(tag, throwable.getMessage(), throwable);
                break;
        }
        throwable.printStackTrace();
    }

}
