package io.raisehand.mopubadvertisment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import io.raisehand.mopubadvertisment.mopub.Utils.Static;
import io.raisehand.mopubadvertisment.mopub.drives.MoPubInterstitialAds;
import io.raisehand.mopubadvertisment.mopub.drives.MoPubRewardVideoAds;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button showInterstitialButton = findViewById(R.id.interstitial);
        Button loadRewardVideoButton = findViewById(R.id.loadRewardVideo);

        MoPubInterstitialAds adsHandler = new MoPubInterstitialAds(this, Static.myMainActivityInterstitialID);
        MoPubRewardVideoAds AdsHandlerReward = new MoPubRewardVideoAds(this, Static.myRewardVideoID);

        showInterstitialButton.setOnClickListener(v -> adsHandler.showAfter(1000));
        loadRewardVideoButton.setOnClickListener(v -> AdsHandlerReward.showAfter(1000));
    }

}