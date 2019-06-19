package co.acidlabs.lanala;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import co.acidlabs.lanala.models.User;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                if (User.getCurrentUser(getApplicationContext()) != null) {

                    Intent mainIntent = new Intent(SplashActivity.this,ContractActivity.class);
                    SplashActivity.this.startActivity(mainIntent);
                    SplashActivity.this.finish();

                }

                else {

                    Intent mainIntent = new Intent(SplashActivity.this,LoginActivity.class);
                    SplashActivity.this.startActivity(mainIntent);
                    SplashActivity.this.finish();

                }

            }
        }, SPLASH_DISPLAY_LENGTH);



    }
}
