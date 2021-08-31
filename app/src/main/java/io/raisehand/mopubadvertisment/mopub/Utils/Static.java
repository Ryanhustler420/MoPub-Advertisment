package io.raisehand.mopubadvertisment.mopub.Utils;

public class Static {

    // Below are test IDs use only this when testing
    public final static String myRewardVideoID = "920b6145fb1546cf8b5cf2ac34638bb7";
    public final static String myMainActivityBannerID = "b195f8dd8ded45fe847ad89ed1d016da";
    public final static String myMainActivityInterstitialID = "24534e1901884e398f1253216226017e";

    // below is for facebook ads testing, HashID of the device can be found in the run log when
    // running an app with facebook SDK
    // look for something like I/AdInternalSettings: Test mode device hash: c9d1da04-c84c-4d36-a315-0aa7b6a8a7a8
    // public final static String MyEmulatorHashID = "c9d1da04-c84c-4d36-a315-0aa7b6a8a7a8";

    public final static boolean hasAd = true;

    public final static String keyForAdFreeStartDate = "AdFreeStart";
    public final static String keyForRewardVideoAdIntervalTime = "AdFreeEnd";
    public final static String keyForAdCounter = "TotalAdsCount";

    public final static double rewardedPeriod = 60.0 * 1000.0 * 4.0; // for Zero minutes (testing)
    // public final static double rewardedPeriod = 60.0*1000.0*60.0*24.0*30.0; // 30 days equivalent in milliseconds

    public final static String videoAdRewardedMessage = "Congratulations! \n Thank you for support, you can now enjoy Ad free App for 5 minutes";
    // public final static String videoAdRewardedMessage = "Congratulations! \n Thank you for support, you can now enjoy Ad free App for 1 month";

}
