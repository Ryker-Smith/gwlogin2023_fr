package net.fachtnaroe.gwlogin2023_fr;

import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.TinyDB;

import static net.fachtnaroe.gwlogin2023_fr.PrivateApplicationSettings.CLOUD_VERIFICATION_URL;
import static net.fachtnaroe.gwlogin2023_fr.PrivateApplicationSettings.GAME_URL;
import static net.fachtnaroe.gwlogin2023_fr.PrivateApplicationSettings.PRIVATE_TESTING_PASS;
import static net.fachtnaroe.gwlogin2023_fr.PrivateApplicationSettings.PRIVATE_TESTING_USER;

public class ApplicationSettings {

    public static final String URL_LOGIN = "https://grassworld.fachtnaroe.net/auth/";
    public static final String URL_MAIN=GAME_URL;
    public static final Integer SplashTimeOut=250;
    public static String myUserIs="";
    public static Integer retransmitTicker_interval=100;
    // the buildNumber can be generated automatically. Look in build.gradle to see how
    public static final String buildNumber=Integer.toString(BuildConfig.VERSION_CODE);
    //    public final String versionName=BuildConfig.VERSION_NAME;
    public static boolean TESTING=false;
    TinyDB localDB;

    public ApplicationSettings(ComponentContainer screenName){
        localDB= new TinyDB(screenName);
    }

    public boolean get () {
        try {
            myUserIs=(String) localDB.GetValue("useremailname","");
//            configurationStatus=(Integer) localDB.GetValue(str_configurationStatus, configurationStatus);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean set () {
        try {
            localDB.StoreValue("useremailname", myUserIs);
              //localDB.StoreValue(str_configurationStatus, configurationStatus);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

}

