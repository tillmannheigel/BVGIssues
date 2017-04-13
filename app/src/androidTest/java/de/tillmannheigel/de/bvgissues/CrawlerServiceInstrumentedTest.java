package de.tillmannheigel.de.bvgissues;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ServiceTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeoutException;

import de.tillmannheigel.de.bvgissues.services.CrawlerService;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class CrawlerServiceInstrumentedTest {

    JSONArray jsonArray = null;

    @Rule
    public final ServiceTestRule mServiceRule = new ServiceTestRule();

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("de.tillmannheigel.de.bvgissues", appContext.getPackageName());
    }

    @Test
    public void testCrawlerServiceHustleing() throws TimeoutException{
        Intent crawlerServiceIntent = new Intent(InstrumentationRegistry.getTargetContext(), CrawlerService.class);

        IBinder binder = mServiceRule.bindService(crawlerServiceIntent);

        CrawlerService crawlerService = ((CrawlerService.LocalBinder) binder).getService();

        assertEquals(42, crawlerService.doTheHustle());
    }

    @Test
    public void testCrawlerServiceRequesting() throws Exception{
        System.out.println("testCrawlerServiceRequesting()");
        Intent crawlerServiceIntent = new Intent(InstrumentationRegistry.getTargetContext(), CrawlerService.class);

        IBinder binder = mServiceRule.bindService(crawlerServiceIntent);

        CrawlerService crawlerService = ((CrawlerService.LocalBinder) binder).getService();

        final Object syncObject = new Object();

        jsonArray = null;

        // dude, this test is expensive...
        crawlerService.doTheRequest(new JSONArrayRequestListener() {
            @Override
            public void onResponse(JSONArray response) {
                // do anything with response
                System.out.println(response.toString());
                synchronized (syncObject){
                    jsonArray = response;
                    syncObject.notify();
                }
            }
            @Override
            public void onError(ANError error) {
                // handle error
                System.out.println(error.toString());
                synchronized (syncObject){
                    syncObject.notify();
                }
            }
        });

         // Synchronization on local variable - ok for testing...but think twice doing this in the real world.
        synchronized (syncObject){
            syncObject.wait();
        }

        System.out.println(jsonArray.toString());
        assertNotNull(jsonArray);

    }
}
