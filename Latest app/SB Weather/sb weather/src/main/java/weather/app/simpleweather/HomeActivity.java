package weather.app.simpleweather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import weather.app.simpleweather.Fragments.AboutFragment;
import weather.app.simpleweather.Fragments.HomeFragment;
import weather.app.simpleweather.Fragments.Room2Fragment;
import weather.app.simpleweather.Fragments.Room3Fragment;

public class HomeActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
   // private Button logout;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<ModelActivity> data;
    static View.OnClickListener myOnClickListener;
    DatabaseReference ref;
    AdapterActivity adaptercl;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        firebaseAuth = FirebaseAuth.getInstance();


        //Jeremy's Code

        recyclerView =(RecyclerView) findViewById(R.id.recycleview);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        ref = FirebaseDatabase.getInstance().getReference().child("GaganSensor");


        FirebaseRecyclerOptions<ModelActivity> options =
                new FirebaseRecyclerOptions.Builder<ModelActivity>()
                        .setQuery(ref.limitToLast(1),ModelActivity.class)
                        .build();



        adaptercl = new AdapterActivity(options,HomeActivity.this);
        recyclerView.setAdapter(adaptercl);
        adaptercl.startListening();




        BottomNavigationView bottomNavigationView1 = (BottomNavigationView) findViewById(R.id.bottom_navi);
        bottomNavigationView1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.action_home:
                        startActivity(new Intent(HomeActivity.this, HomeActivity.class));
                        break;

                    case R.id.action_Room2:
                        startActivity(new Intent(HomeActivity.this, Humid_Activity.class));
                        break;

                    case R.id.action_Room3:
                        startActivity(new Intent(HomeActivity.this, Pressure_Activity.class));
                        break;


                }
                return true;
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(new Intent(HomeActivity.this,HomeActivity.class));
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
       return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        Fragment selectedFragment = null;
        switch(item.getItemId()){

            case R.id.logoutMenu: {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                break;
            }


            case R.id.refreshMenu:{
                startActivity(new Intent(HomeActivity.this, HomeActivity.class));
                showsnackbar();
                break;
            }



            case R.id.settings: {

                startActivity(new Intent(HomeActivity.this, SettingsActivity.class));
                break;
            }


            case R.id.aboutUsMenu: {
                startActivity(new Intent(HomeActivity.this, AboutActivity.class));
                break;

            }

            case R.id.contactUsMenu: {

                startActivity(new Intent(HomeActivity.this, ContactUsActivity.class));
                break;
            }


        }

        return super.onOptionsItemSelected(item);
    }


    public void showsnackbar()
    {
        String refresh = getString(R.string.refresh);
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),refresh,Snackbar.LENGTH_SHORT);
        snackbar.show();
    }





}