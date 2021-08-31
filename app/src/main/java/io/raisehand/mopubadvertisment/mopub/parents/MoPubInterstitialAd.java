package io.raisehand.mopubadvertisment.mopub.parents;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.mopub.common.MoPub;
import com.mopub.common.SdkConfiguration;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;

public abstract class MoPubInterstitialAd implements MoPubInterstitial.InterstitialAdListener {

    private Context mContext;

    protected String AD_ID;
    protected boolean interstitialLoaded = false;
    protected MoPubInterstitial mInterstitial;

    public MoPubInterstitialAd(Context context, String myMainActivityInterstitialID) {
        AD_ID = myMainActivityInterstitialID;
        mContext = context;
        loadMoPubSDK();
    }

    private void loadMoPubSDK() {
        SdkConfiguration sdkConfiguration = new SdkConfiguration.Builder(AD_ID).withLegitimateInterestAllowed(false).build();
        MoPub.initializeSdk(mContext, sdkConfiguration, () -> {
            mInterstitial = new MoPubInterstitial((Activity) mContext, AD_ID);
            mInterstitial.setInterstitialAdListener(this);
            mInterstitial.load();
        });
    }

    public void destroyRequested() {
        if (mInterstitial != null) mInterstitial.destroy();
    }

    public void resumeRequested() {
        MoPub.onResume((Activity) mContext);
    }

    public void pauseRequested() {
        MoPub.onPause((Activity) mContext);
    }

    public void stopRequested() {
        MoPub.onStop((Activity) mContext);
    }

    @Override
    public void onInterstitialLoaded(MoPubInterstitial interstitial) {
        interstitialLoaded = true;
    }

    @Override
    public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {

    }

    @Override
    public void onInterstitialShown(MoPubInterstitial interstitial) {

    }

    @Override
    public void onInterstitialClicked(MoPubInterstitial interstitial) {
        Toast.makeText(mContext, "You have clicked on the ad", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInterstitialDismissed(MoPubInterstitial interstitial) {
        Toast.makeText(mContext, "Ad has been closed", Toast.LENGTH_LONG).show();
        mInterstitial.load(); // load again so that it can be displayed in the next call
    }

}
