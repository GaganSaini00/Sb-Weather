package weather.app.simpleweather.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

import weather.app.simpleweather.MainActivity;
import weather.app.simpleweather.R;

public class SettingsFragment extends Fragment {

    private static int DELAY = 4000;
    private FirebaseAuth firebaseAuth;
    private SwitchCompat darksw;
    SharedPreferences sp_nm;

    @Nullable
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        Fragment selectedFragment = null;
        switch(item.getItemId()){

            case R.id.logoutMenu: {

                getActivity().finish();
                startActivity(new Intent(getActivity(), MainActivity.class));
                break;
            }


            case R.id.refreshMenu:{
                selectedFragment = new HomeFragment();
                break;
            }


        }

        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        checkupdate(view);
        SeekBar seekBar = view.findViewById(R.id.seekbar);
        seekBar.setMax(255);

        Context current = getContext();
        boolean settingsCanWrite = Settings.System.canWrite(current);
        if (!settingsCanWrite) { // won't be able to change brightness if we can't write system settings
            requestPermissions(new String[]{Manifest.permission.WRITE_SETTINGS}, 101); // ...so ask for permission to do that
        }

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                android.provider.Settings.System.putInt(getActivity().getApplicationContext().getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, progress);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        askPermission(getActivity());


        darksw = view.findViewById(R.id.d_switch);
        sp_nm = getActivity().getSharedPreferences("prefnightmode",Context.MODE_PRIVATE);
        darksw.setChecked(sp_nm.getBoolean("nmode",true));



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



        return view;
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


                Toast.makeText(getActivity(), "Please allow the app permission", Toast.LENGTH_LONG).show();

            }




        }

    }



    public void checkupdate(View view){

        final Button chkbtn = view.findViewById(R.id.checkupdatebtn);
        final String ckup = getString(R.string.ckupd);

        chkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), ckup, Toast.LENGTH_SHORT).show();
                chkbtn.setTextColor(getResources().getColor(R.color.green));
                //Color(getResources().getColor(R.color.green));

            }
        });
    }



}



