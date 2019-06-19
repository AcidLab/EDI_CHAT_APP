package co.acidlabs.proassur.networking;

public interface OTServiceListener {
    default void onSessionIdReceived(String sessionId) {};
    default void onSessionIdError() {};

    default void onTokenReceived(String token) {};
}
