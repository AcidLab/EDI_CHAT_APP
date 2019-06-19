package co.acidlabs.lanala.networking;




import java.util.ArrayList;

import co.acidlabs.lanala.models.Balance;
import co.acidlabs.lanala.models.Contract;
import co.acidlabs.lanala.models.QHistory;
import co.acidlabs.lanala.models.QImpayed;
import co.acidlabs.lanala.models.User;

public interface NetworkingAsyncResponse {

    default void onLoginSuccess(User user){}
    default void onLoginError(String message){}

    default void onContractListSuccess(ArrayList <Contract> contracts){}
    default void onContractListError(String message){}
    default void onTokenError(){}

    default void onBalanceSuccess(Balance balance){}
    default void onBalanceError(String message){}

    default void onHistorySuccess(ArrayList <QHistory> qImpayeds){}
    default void onHistoryError(String message){}

    default void onImpayedSuccess(ArrayList <QImpayed> qImpayeds){}
    default void onImpayedError(String message){}



}
