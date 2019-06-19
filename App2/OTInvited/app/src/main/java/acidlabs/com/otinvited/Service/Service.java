package acidlabs.com.otinvited.Service;

import android.content.Context;

public class Service {
    protected Context context;
    protected Object receiver;

    public Service(Context ctx){
        context = ctx;
        receiver = ctx;
    }
    public Service(Context ctx, Object rcv){
        context = ctx;
        receiver = rcv;
    }
}
