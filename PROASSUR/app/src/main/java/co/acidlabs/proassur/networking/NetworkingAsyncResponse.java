package co.acidlabs.proassur.networking;




import android.os.Build;

import java.util.ArrayList;

import co.acidlabs.proassur.models.Contract;
import co.acidlabs.proassur.models.User;


public interface NetworkingAsyncResponse {

    default void onLoginSuccess(User user){}
    default void onLoginError(String message){}

    default void onContractListSuccess(ArrayList<Contract> contracts){}
    default void onContractListError(String message){}
    default void onTokenError(){}




}
