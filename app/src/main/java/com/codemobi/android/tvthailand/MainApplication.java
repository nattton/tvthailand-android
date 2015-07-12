package com.codemobi.android.tvthailand;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;

import com.codemobi.android.tvthailand.utils.Contextor;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.SaveCallback;

public class MainApplication extends Application {
    // The following line should be changed to include the correct property id.
    private static final String PROPERTY_ID = "UA-22403997-3";

    public static int GENERAL_TRACKER = 0;
    private static String appVersion = "1.0";

    public enum TrackerName {
        APP_TRACKER, // Tracker used only in this app.
        OTV_TRACKER, // Tracker used by all ecommerce transactions from a company.
        GLOBAL_TRACKER, // Tracker used by all the apps from a company. eg: roll-up tracking.
    }

    public MainApplication() {
        super();
    }
    
    @Override
    public void onCreate() {
    	super.onCreate();
        Contextor.getInstance().init(getApplicationContext());

//        Parse.initialize(this, "EaoAvSNJcTy2YYs9rpl3qVnWtODDl2PNW4FArdLI", "CBdebyTPRHrxRqQPWNm5kPrhbZLuuKAFMCDE0Fbz");
//        ParseInstallation.getCurrentInstallation().saveInBackground();
//        ParsePush.subscribeInBackground("Notification", new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if (e != null) {
//                    Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
//                } else {
//                    Log.e("com.parse.push", "failed to subscribe for push", e);
//                }
//            }
//        });

        init();
    }
    
    private void init() {
    	MyVolley.init(this);
    	
		try {
			PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
			appVersion = pInfo.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
    	
    }
    
    public static String getAppVersion() {
    	return appVersion;
    }
}
