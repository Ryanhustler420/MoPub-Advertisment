package io.raisehand.mopubadvertisment.mopub.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class AdChecker {

    private Context mContext;

    public AdChecker(Context context) {
        mContext = context;
    }

    public static boolean adEnabled = true; // Assumes that by default ad is required

    final static String MySourceActivity = "MainActivity";
    final static String MyAnotherSourceActivity = "AnotherActivity";

    boolean bannerAdRequired(String activity) {
        if (MySourceActivity.equals(activity))
            return adEnabled = isIntervalPassed();
        if (MyAnotherSourceActivity.equals(activity))
            return adEnabled = isIntervalPassed();
        return false;
    }

    boolean interstitialAdRequired(String activity) {
        if (MySourceActivity.equals(activity))
            return adEnabled = isIntervalPassed();
        if (MyAnotherSourceActivity.equals(activity))
            return adEnabled = isIntervalPassed();
        return false;
    }

    public boolean shouldLoadRewardVideo() {
        return adEnabled = isIntervalPassed();
    }

    private boolean isIntervalPassed() {
        try {
            SharedPref sharedPref = new SharedPref(mContext);
            double interval = Double.parseDouble(sharedPref.getKeyValue(Static.keyForRewardVideoAdIntervalTime));
            double currentTime = System.currentTimeMillis();
            return !(interval - currentTime >= 0);
        } catch (Exception e) {
            return true;
        }
    }

    public static class SharedPref {
        Context context;
        String defaultValue = "";
        String myConfigFileName = "RaisehandAdsPool";

        public SharedPref(Context context) {
            this.context = context;
        }

        public void saveAdCount(String totalAdsCount) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(myConfigFileName, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(Static.keyForAdCounter, totalAdsCount);
            // editor.putString("Count", count);
            editor.apply();
        }

        public void saveKeyValue(String key, String value) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(myConfigFileName, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(key, value);
            // editor.putString("Count", count);
            editor.apply();
        }

        public String getKeyValue(String key) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(myConfigFileName, Context.MODE_PRIVATE);
            return sharedPreferences.getString(key, defaultValue);
        }

    }

}
