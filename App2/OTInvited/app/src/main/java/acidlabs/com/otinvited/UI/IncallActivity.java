package acidlabs.com.otinvited.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import acidlabs.com.otinvited.R;

public class IncallActivity extends Activity {

    @Override
    public void onCreate (Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_incoming);

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(1000*60);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                    finishAffinity();
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);

                }
            }
        };
        timer.start();
    }

    public void respCall (View v) {

        Intent intent = new Intent(this, OTActivity.class);
        intent.putExtra("sessionId", "1_MX40NjI1NDQ2Mn5-MTU1MDE5Mjg2NDg2N35KT1hBa3JQMlAzTjJmR0pxRTVycFVkV2d-QX4");
        startActivity(intent);
        finishAffinity();

    }

    public void unrespCall (View v) {

        finishAffinity();
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);

    }

    public void onBackPressed() {

    }
}
