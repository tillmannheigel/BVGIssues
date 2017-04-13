package de.tillmannheigel.de.bvgissues;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ServiceTestRule;
import android.support.test.runner.AndroidJUnit4;

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
}
