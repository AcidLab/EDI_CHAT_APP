package acidlabs.com.otinvited;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;

import acidlabs.com.otinvited.UI.OTActivity;

public class OTInvited extends Application implements OneSignal.NotificationOpenedHandler {
    @Override
    public void onCreate() {
        super.onCreate();

        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .setNotificationOpenedHandler(this)
                .init();


        Log.d("APP", "onCreate");
    }

    @Override
    public void notificationOpened(OSNotificationOpenResult result) {
        System.out.println("OTInvited.notificationOpened");
        System.out.println(result.toJSONObject());

        JSONObject data = result.notification.payload.additionalData;
        String sessionId;

        if (data != null) {
            sessionId = data.optString("sessionId", "");
            System.out.println("OTInvited.notificationOpened.sessionId: "+sessionId);
            if (sessionId != null){
                Intent intent = new Intent(this, OTActivity.class);
                intent.putExtra("sessionId", sessionId);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }


    }
}
