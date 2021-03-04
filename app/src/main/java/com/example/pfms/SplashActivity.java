package com.example.pfms;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pfms.database.DataBaseHelper;

/**
 * Initial activity that serves a temporary splash screen
 * @author zarif
 */
public class SplashActivity extends AppCompatActivity {

    DataBaseHelper db;
    final int SPLASH_DISPLAY_LENGTH = 1000;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_splash);

        db = new DataBaseHelper(this);

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                if (db.isAnyUserAvailable() && db.isLoggedIn()) {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    SplashActivity.this.startActivity(intent);
                }
                else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    SplashActivity.this.startActivity(intent);
                }
                SplashActivity.this.finish();

            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
