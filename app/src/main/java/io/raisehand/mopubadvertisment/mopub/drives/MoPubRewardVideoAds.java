package io.raisehand.mopubadvertisment.mopub.drives;

import android.content.Context;
import android.os.Handler;

import androidx.annotation.NonNull;

import com.mopub.common.MoPubReward;

import java.util.Set;

import io.raisehand.mopubadvertisment.mopub.Utils.AdChecker;
import io.raisehand.mopubadvertisment.mopub.Utils.Static;
import io.raisehand.mopubadvertisment.mopub.parents.MoPubRewardVideoAd;

public class MoPubRewardVideoAds extends MoPubRewardVideoAd {

    Context context;
    AdChecker adChecker;

    public MoPubRewardVideoAds(Context mContext, String myRewardVideoID) {
        super(mContext, myRewardVideoID);
        adChecker = new AdChecker(mContext);
        context = mContext;
    }

    public void showBasedOnTime() {
        if (!rewardVideoLoaded && !loadingRewardVideoRequested && AdChecker.adEnabled)
            if (adChecker.shouldLoadRewardVideo()) playRewardVideoAd();

    }

    public void showAfter(int milliSeconds) {
        new Handler().postDelayed(this::playRewardVideoAd, milliSeconds);
    }

    public void show() {
        playRewardVideoAd();
    }

    // AdsHandlerReward.showInIntervalOf(1000 * 60 * 4); // 4 Minute
    // AdsHandlerReward.deleteOldAds();

    @Override
    public void onRewardedVideoCompleted(@NonNull Set<String> adUnitIds, @NonNull MoPubReward reward) {
        super.onRewardedVideoCompleted(adUnitIds, reward);

        AdChecker.SharedPref RSH = new AdChecker.SharedPref(context);
        AdChecker.adEnabled = false;

        double startPeriod = System.currentTimeMillis();
        double endPeriod = startPeriod + Static.rewardedPeriod;

        try {
            RSH.saveKeyValue(Static.keyForAdFreeStartDate, String.valueOf(startPeriod));
            RSH.saveKeyValue(Static.keyForRewardVideoAdIntervalTime, String.valueOf(endPeriod));
            // RSH.showToastMessage(Constants.videoAdRewardedMessage);
        } catch (Exception e) {
            // something went wrong while saving the last ad seen timestamp
        }

    }
}
