package com.codemobi.android.tvthailand;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

import com.codemobi.android.tvthailand.utils.Contextor;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import java.util.HashMap;

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

    HashMap<TrackerName, Tracker> mTrackers = new HashMap<TrackerName, Tracker>();

    public MainApplication() {
        super();
    }
    
    @Override
    public void onCreate() {
    	super.onCreate();
        Contextor.getInstance().init(getApplicationContext());
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

    public synchronized Tracker getTracker(TrackerName trackerId) {
        if (!mTrackers.containsKey(trackerId)) {

            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            Tracker t = (trackerId == TrackerName.APP_TRACKER) ? analytics.newTracker(PROPERTY_ID)
                    : (trackerId == TrackerName.OTV_TRACKER) ? analytics.newTracker(R.xml.otv_tracker)
                    : analytics.newTracker(R.xml.global_tracker);
            mTrackers.put(trackerId, t);

        }
        return mTrackers.get(trackerId);
    }
    
    public static String getAppVersion() {
    	return appVersion;
    }
}
