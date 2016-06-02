package co.com.seguratec.theintell;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.astuetz.PagerSlidingTabStrip;
import com.onesignal.OneSignal;

import org.json.JSONObject;

import layout.alertas;
import layout.favoritos;
import layout.noticias;

public class MainActivity extends AppCompatActivity {

    private int alert_num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Implementación OneSignal para notificaciones con GCM
        OneSignal.startInit(this)
                .setNotificationOpenedHandler(new ExampleNotificationOpenedHandler())
                .setAutoPromptLocation(true)
                .init();

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager()));

        // Give the PagerSlidingTabStrip the ViewPager
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(viewPager);

    }

    //Se implementará para las alertas
    private class ExampleNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {
        /**
         *
         * @param message        The message string the user seen/should see in the Android status bar.
         * @param additionalData The additionalData key value pair section you entered in on onesignal.com.
         * @param isActive       Was the app in the foreground when the notification was received.
         */
        @Override
        public void notificationOpened(String message, JSONObject additionalData, boolean isActive) {
            String additionalMessage = "";

            try {
                if (additionalData != null) {
                    if (additionalData.has("actionSelected"))
                        additionalMessage += "Pressed ButtonID: " + additionalData.getString("actionSelected");

                    additionalMessage = message + "\nFull additionalData:\n" + additionalData.getInt("alert");
                    alert_num = (int) additionalData.getInt("alert");
                }

                Log.d("OneSignalExample", "message:\n" + message + "\nadditionalMessage:\n" + additionalMessage);
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    public class SampleFragmentPagerAdapter extends FragmentPagerAdapter implements PagerSlidingTabStrip.IconTabProvider {
        final int PAGE_COUNT = 3;
        //private String tabTitles[] = new String[] { "Tab1", "Tab2", "Tab3" };
        private int tabIcons[] = {R.mipmap.noticias, R.mipmap.favoritos, R.mipmap.alarm};

        public SampleFragmentPagerAdapter(FragmentManager fm) {
            super(fm);

            //Definir color de la alerta
            switch (alert_num) {
                case 1: // Color verde
                    tabIcons[2] = R.mipmap.alarm_green;
                case 2: // Color Naranja
                    tabIcons[2] =  R.mipmap.alarm_orange;
                case 3: // Color Rojo
                    tabIcons[2] = R.mipmap.alarm_red;
                default: // Color Gris default
                    tabIcons[2] = R.mipmap.alarm;
            }
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return noticias.newInstance(0, "Page # 1");
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return favoritos.newInstance(1, "Page # 2");
                case 2: // Fragment # 1 - This will show SecondFragment
                    return alertas.newInstance(2, "Page # 3");
                default:
                    return null;
            }
        }

        @Override
        public int getPageIconResId(int position) {
            return tabIcons[position];
        }
    }
}
