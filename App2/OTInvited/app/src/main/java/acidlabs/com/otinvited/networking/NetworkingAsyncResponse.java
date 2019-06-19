package acidlabs.com.otinvited.networking;




import java.util.ArrayList;

import acidlabs.com.otinvited.models.User;


public interface NetworkingAsyncResponse {

    default void onLoginSuccess(User user){}
    default void onLoginError(String message){}

    default void onTokenError(){}




}
