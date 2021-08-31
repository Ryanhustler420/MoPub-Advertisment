package io.raisehand.mopubadvertisment.mopub;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import io.raisehand.mopubadvertisment.R;
import io.raisehand.mopubadvertisment.mopub.Utils.Static;
import io.raisehand.mopubadvertisment.mopub.drives.MoPubInterstitialAds;
import io.raisehand.mopubadvertisment.mopub.drives.MoPubRewardVideoAds;

public class Test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button showInterstitialButton = findViewById(R.id.interstitial);
        Button loadRewardVideoButton = findViewById(R.id.loadRewardVideo);

        MoPubRewardVideoAds rewardVideoAd = new MoPubRewardVideoAds(this, Static.myRewardVideoID);
        MoPubInterstitialAds interstitialAd = new MoPubInterstitialAds(this, Static.myMainActivityInterstitialID);

        showInterstitialButton.setOnClickListener(v -> interstitialAd.showAfter(1000));
        loadRewardVideoButton.setOnClickListener(v -> rewardVideoAd.showAfter(1000));

    }

}
