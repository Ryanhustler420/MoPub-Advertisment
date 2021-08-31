package io.raisehand.mopubadvertisment.mopub.parents;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.mopub.common.MoPub;
import com.mopub.common.MoPubReward;
import com.mopub.common.SdkConfiguration;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubRewardedVideoListener;
import com.mopub.mobileads.MoPubRewardedVideos;

import java.util.Set;

public abstract class MoPubRewardVideoAd implements MoPubRewardedVideoListener {

    protected Context context;
    protected String AD_ID;

    protected boolean rewardVideoLoaded = false;
    protected boolean adsInitializationCompleted = false;
    protected boolean loadingRewardVideoRequested = false;

    protected MoPubRewardVideoAd(Context mContext, String myRewardVideoID) {
        AD_ID = myRewardVideoID;
        context = mContext;
        loadMoPubSDK();
    }

    protected void loadMoPubSDK() {
        // configurations required to initialize
        /* Map<String, String> mediatedNetworkConfiguration1 = new HashMap<>();
        mediatedNetworkConfiguration1.put("<custom-adapter-class-data-key>", "<custom-adapter-class-data-value>");
        Map<String, String> mediatedNetworkConfiguration2 = new HashMap<>();
        mediatedNetworkConfiguration2.put("<custom-adapter-class-data-key>", "<custom-adapter-class-data-value>"); */
        SdkConfiguration sdkConfiguration = new SdkConfiguration.Builder(AD_ID)
                /*.withMediationSettings("MEDIATION_SETTINGS")
                .withAdditionalNetworks(CustomAdapterConfiguration.class.getName())
                .withMediatedNetworkConfiguration(CustomAdapterConfiguration1.class.getName(), mediatedNetworkConfiguration)
                .withMediatedNetworkConfiguration(CustomAdapterConfiguration2.class.getName(), mediatedNetworkConfiguration)
                .withMediatedNetworkConfiguration(CustomAdapterConfiguration1.class.getName(), mediatedNetworkConfiguration1)
                .withMediatedNetworkConfiguration(CustomAdapterConfiguration2.class.getName(), mediatedNetworkConfiguration2)
                .withLogLevel(LogLevel.Debug)*/
                .withLegitimateInterestAllowed(false).build();
        MoPub.initializeSdk(context, sdkConfiguration, () -> {
            Toast.makeText(context, "MoPub SDK Loaded", Toast.LENGTH_SHORT).show();
            adsInitializationCompleted = true;
        });
    }

    protected void playRewardVideoAd() {
        MoPub.onCreate((Activity) context);
        MoPubRewardedVideos.setRewardedVideoListener(this);
        if (adsInitializationCompleted) {
            MoPubRewardedVideos.loadRewardedVideo(AD_ID);
            loadingRewardVideoRequested = true;
        }
    }

    protected void destroyRequested() {
        MoPub.onDestroy((Activity) context);// for rewarded video ads only
    }

    protected void backPressed() {
        MoPub.onBackPressed((Activity) context); // for rewarded video ads only
    }

    @Override
    public void onRewardedVideoLoadSuccess(@NonNull String adUnitId) {
        // Called when the video for the given adUnitId has loaded. At this point you should be able to call MoPubRewardedVideos.showRewardedVideo(String) to show the video.
        if (adUnitId.equals(AD_ID)) {
            rewardVideoLoaded = true;
            loadingRewardVideoRequested = false;
            MoPubRewardedVideos.showRewardedVideo(AD_ID);
        }
    }

    @Override
    public void onRewardedVideoCompleted(@NonNull Set<String> adUnitIds, @NonNull MoPubReward reward) {
        // Called when a rewarded video is completed and the user should be rewarded.
        // You can query the reward object with boolean isSuccessful(), String getLabel(), and int getAmount().
        rewardVideoLoaded = false;
    }

    @Override
    public void onRewardedVideoLoadFailure(@NonNull String adUnitId, @NonNull MoPubErrorCode errorCode) {
        // Called when a video fails to load for the given adUnitId. The provided error code will provide more insight into the reason for the failure to load.
    }

    @Override
    public void onRewardedVideoStarted(@NonNull String adUnitId) {
        // Called when a rewarded video starts playing.
    }

    @Override
    public void onRewardedVideoPlaybackError(@NonNull String adUnitId, @NonNull MoPubErrorCode errorCode) {
        //  Called when there is an error during video playback.
    }

    @Override
    public void onRewardedVideoClicked(@NonNull String adUnitId) {
        //  Called when a rewarded video is clicked.
    }

    @Override
    public void onRewardedVideoClosed(@NonNull String adUnitId) {
        // Called when a rewarded video is closed. At this point your application should resume.
    }

}
