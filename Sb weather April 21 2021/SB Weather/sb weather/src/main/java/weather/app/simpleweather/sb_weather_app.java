package weather.app.simpleweather;
//Sunshine Boys
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class sb_weather_app extends Application {
//create channel 1



    public static final String Channel_1_ID = "save_set_channel_1";
    public static final String Channel_2_ID = "save_set_channel_2";

    @Override
    public void onCreate() {
        super.onCreate();
        settings_notifications();
    }
public void settings_notifications(){
    String set_not_message = getString(R.string.save_settings_con);
    String Alert = "Changed Settings";

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            NotificationChannel set_ch1 = new NotificationChannel(Channel_1_ID,Alert, NotificationManager.IMPORTANCE_HIGH);
            set_ch1.setDescription(set_not_message);

            NotificationChannel set_ch2 = new NotificationChannel(Channel_2_ID,Alert, NotificationManager.IMPORTANCE_HIGH);
            set_ch2.setDescription(set_not_message);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(set_ch1);
            manager.createNotificationChannel(set_ch2);
        }

}

}
