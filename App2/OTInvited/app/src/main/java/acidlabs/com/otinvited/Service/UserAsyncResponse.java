package acidlabs.com.otinvited.Service;

import org.json.JSONObject;

import java.util.ArrayList;

public interface UserAsyncResponse {
    default void onAccessTokenReceived(String token) {}
    default void onAccessError(String error) {System.out.println("UserAsyncResponse.onAccessError.error = [" + error + "]"); }
    default void onRegisterTokenReceived(String token) {}
    default void onRegisterError(String error) {System.out.println("UserAsyncResponse.onRegisterError.error = [" + error + "]");}
    default void onDetailsReceived(JSONObject user) {}
    default void onUserFollowed(int id) {}

}
