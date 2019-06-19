package acidlabs.com.otinvited;

import android.app.NotificationManager;
import android.content.Intent;

import com.onesignal.OSNotificationPayload;
import com.onesignal.NotificationExtenderService;
import com.onesignal.OSNotificationReceivedResult;

import org.json.JSONObject;

import acidlabs.com.otinvited.UI.IncallActivity;
import acidlabs.com.otinvited.UI.MainActivity;
import acidlabs.com.otinvited.UI.OTActivity;

public class NotificationExtenderBareBonesExample extends NotificationExtenderService {

    @Override
    protected boolean onNotificationProcessing(OSNotificationReceivedResult receivedResult) {

        Intent intent = new Intent(getApplicationContext(),   IncallActivity.class);
        //intent.putExtra("sessionId", "1_MX40NjI1NDQ2Mn5-MTU1MDE5Mjg2NDg2N35KT1hBa3JQMlAzTjJmR0pxRTVycFVkV2d-QX4");
        startActivity(intent);
        return true;
    }
}
