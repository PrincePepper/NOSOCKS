package ppts.website.nosocks;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import ppts.website.nosocks.R;


public class SplashActivity extends AppCompatActivity {

    public static boolean color;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        startSplashAmimation();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        color = false;
    }

    //запуск
    private void startSplashAmimation() {
        int SPLASH_DISPLAY = 1500;

        new Handler().postDelayed(() -> {

            Intent SplashIntent = new Intent(SplashActivity.this, MainActivity.class);
            SplashActivity.this.startActivity(SplashIntent);

            SplashActivity.this.finish();

        }, SPLASH_DISPLAY);


    }

}
