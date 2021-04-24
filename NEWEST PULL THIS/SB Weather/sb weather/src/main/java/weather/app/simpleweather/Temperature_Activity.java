package weather.app.simpleweather;
//Sunshine Boys
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import weather.app.simpleweather.Fragments.Room2Fragment;
import weather.app.simpleweather.Fragments.Room3Fragment;

public class Temperature_Activity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
   // private Button logout;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<Temperature_Model> data;
    static View.OnClickListener myOnClickListener;
    GoogleSignInClient mGoogleSignInClient;
    DatabaseReference ref;
    Temperature_Adapter temperature_adapter;
    Button temp_button;

    public String URL = "http://api.openweathermap.org/data/2.5/weather?q=Toronto&appid=23f04464b7119837cf1dc4fa8b39caa3&units=metric";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp_activity);
        firebaseAuth = FirebaseAuth.getInstance();
        temp_button = findViewById(R.id.Temperature_Graph_button);

        //Jeremy's Code

        recyclerView =(RecyclerView) findViewById(R.id.recycleview);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        ref = FirebaseDatabase.getInstance().getReference().child("GaganSensor");


        FirebaseRecyclerOptions<Temperature_Model> options =
                new FirebaseRecyclerOptions.Builder<Temperature_Model>()
                        .setQuery(ref.limitToLast(1), Temperature_Model.class)
                        .build();



        temperature_adapter = new Temperature_Adapter(options,Temperature_Activity.this);
        recyclerView.setAdapter(temperature_adapter);
        temperature_adapter.startListening();

        temp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Temperature_Activity.this,Temperature_Graph.class);
                startActivity(intent);
            }
        });




        BottomNavigationView bottomNavigationView1 = (BottomNavigationView) findViewById(R.id.bottom_navi);
        bottomNavigationView1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        startActivity(new Intent(Temperature_Activity.this, Temperature_Activity.class));
                        break;

                    case R.id.action_Room2:
                        startActivity(new Intent(Temperature_Activity.this, Humid_Activity.class));
                        break;

                    case R.id.action_Room3:
                        startActivity(new Intent(Temperature_Activity.this, Pressure_Activity.class));
                        break;


                }
                return true;
            }
        });



    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(new Intent(Temperature_Activity.this, Temperature_Activity.class));
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
       return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch(item.getItemId()){

            case R.id.logoutMenu: {
                firebaseAuth.signOut();
               /* mGoogleSignInClient.signOut();*/
                finish();
                startActivity(new Intent(Temperature_Activity.this, LoginActivity.class));
                break;
            }


            case R.id.refreshMenu:{
                startActivity(new Intent(Temperature_Activity.this, Temperature_Activity.class));
                showsnackbar();
                break;
            }



            case R.id.settings: {

                startActivity(new Intent(Temperature_Activity.this, SettingsActivity.class));
                break;
            }


            case R.id.aboutUsMenu: {
                startActivity(new Intent(Temperature_Activity.this, AboutActivity.class));
                break;

            }

            case R.id.contactUsMenu: {
                startActivity(new Intent(Temperature_Activity.this, ContactUsActivity.class));
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