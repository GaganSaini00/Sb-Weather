package weather.app.simpleweather;
//Sunshine Boys
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.Manifest;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Toast;
import com.google.android.material.snackbar.Snackbar;
import static weather.app.simpleweather.sb_weather_app.Channel_1_ID;
import static weather.app.simpleweather.sb_weather_app.Channel_2_ID;

public class SettingsActivity extends AppCompatActivity {

    private static int DELAY = 4000;
    private SwitchCompat darksw;
    SharedPreferences sp_nm;
    private Button save_set;
    private NotificationManagerCompat notificationManagerCompat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        checkupdate();


        SeekBar seekBar = findViewById(R.id.seekbar);


        seekBar.setMax(255);

        Context current = getApplicationContext();
        boolean settingsCanWrite = Settings.System.canWrite(current);
        if (!settingsCanWrite) { // won't be able to change brightness if we can't write system settings
            requestPermissions(new String[]{Manifest.permission.WRITE_SETTINGS}, 101); // ...so ask for permission to do that
        }

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {




                android.provider.Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, progress);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        askPermission(this);


        darksw = findViewById(R.id.d_switch);
        sp_nm = getSharedPreferences("prefnightmode",Context.MODE_PRIVATE);
        darksw.setChecked(sp_nm.getBoolean("nmode",false));



        darksw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    darksw.setChecked(true);
                    SharedPreferences.Editor edit = sp_nm.edit();
                    edit.putBoolean("nmode",b);
                    edit.apply();



                }else{

                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    darksw.setChecked(false);
                    SharedPreferences.Editor edit = sp_nm.edit();
                    edit.putBoolean("nmode",b);
                    edit.apply();


                }


            }
        });


        //Save Settings Button

        save_set = findViewById(R.id.save_settings);

        save_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            showsnackbar();
            create_notification();

            }
        });

        //Notification
        notificationManagerCompat = NotificationManagerCompat.from(this);

    }


    public void showsnackbar(){

        String save_set_string = getString(R.string.save_settings_con);
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),save_set_string,Snackbar.LENGTH_SHORT);
        snackbar.show();

    }
//Notification method

public void create_notification()
{
    String save_set_string = getString(R.string.save_settings_con);
    Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
   Notification notification = new NotificationCompat.Builder(this,Channel_1_ID)

           .setSmallIcon(R.drawable.ic_app_icon)
           .setContentTitle(save_set_string)
           .setPriority(NotificationCompat.PRIORITY_HIGH)
           .setSound(sound)
           .build();

    notificationManagerCompat.notify(1,notification);

    Notification notification2 = new NotificationCompat.Builder(this,Channel_2_ID)

            .setSmallIcon(R.drawable.ic_app_icon)
            .setContentTitle(save_set_string)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSound(sound)
            .build();

    notificationManagerCompat.notify(2,notification2);

}

    public void askPermission(final Context c){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            if(Settings.System.canWrite(c)){

                //you have permission

            }
            else {

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Intent i = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                        c.startActivity(i);
                    }
                }, DELAY);


                Toast.makeText(SettingsActivity.this, "Please allow the app permission", Toast.LENGTH_LONG).show();

            }




        }

    }

    public void checkupdate(){

        final Button chkbtn = findViewById(R.id.checkupdatebtn);
        final String ckup = getString(R.string.ckupd);
        chkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(SettingsActivity.this, ckup, Toast.LENGTH_SHORT).show();
                chkbtn.setTextColor(getResources().getColor(R.color.green));
            //Color(getResources().getColor(R.color.green));

            }
        });
        


    }




}