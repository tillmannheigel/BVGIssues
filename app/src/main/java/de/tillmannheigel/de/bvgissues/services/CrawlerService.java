package de.tillmannheigel.de.bvgissues.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.IntDef;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;

public class CrawlerService extends Service {

    // This is the object that receives interactions from clients.  See
    // RemoteService for a more complete example.
    private final IBinder mBinder = new LocalBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);

    }

    public int doTheHustle(){
        return 42;
    }

    public void doTheRequest(JSONArrayRequestListener listener){
        AndroidNetworking.initialize(getApplicationContext());

        AndroidNetworking.get("https://fierce-cove-29863.herokuapp.com/getAllUsers/{pageNumber}")
                .addPathParameter("pageNumber", "0")
                .addQueryParameter("limit", "3")
                .addHeaders("token", "1234")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONArray(listener);
    }

    public class LocalBinder extends Binder {
        public CrawlerService getService() {
            return CrawlerService.this;
        }
    }
}
