package io.raisehand.mopubadvertisment.mopub.drives;

import android.content.Context;
import android.os.Handler;

import io.raisehand.mopubadvertisment.mopub.Utils.AdChecker;
import io.raisehand.mopubadvertisment.mopub.parents.MoPubInterstitialAd;

public class MoPubInterstitialAds extends MoPubInterstitialAd {

    public MoPubInterstitialAds(Context context, String myMainActivityInterstitialID) {
        super(context, myMainActivityInterstitialID);
    }

    public void showBasedOnTime() {
        if (AdChecker.adEnabled) show();
    }

    public void show() {
        if (interstitialLoaded && mInterstitial != null && mInterstitial.isReady())
            mInterstitial.show();
    }

    public void showAfter(int milliSeconds) {
        new Handler().postDelayed(this::show, milliSeconds);
    }

}
