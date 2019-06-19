package acidlabs.com.otinvited.Service;

public interface OTServiceListener {
    default void onSessionIdReceived(String sessionId) {};

    default void onTokenReceived(String token) {};
}
