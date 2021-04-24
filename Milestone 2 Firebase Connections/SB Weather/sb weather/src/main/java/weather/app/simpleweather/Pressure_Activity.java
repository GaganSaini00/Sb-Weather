package weather.app.simpleweather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Pressure_Activity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<ModelActivity> data;
    static View.OnClickListener myOnClickListener;
    DatabaseReference ref;
    Pressure_Adapter adaptercl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pressure_);
        firebaseAuth = FirebaseAuth.getInstance();


        recyclerView =(RecyclerView) findViewById(R.id.recycleview);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        ref = FirebaseDatabase.getInstance().getReference().child("NathanSensor");


        FirebaseRecyclerOptions<Pressure_Model> options =
                new FirebaseRecyclerOptions.Builder<Pressure_Model>()
                        .setQuery(ref.limitToLast(1),Pressure_Model.class)
                        .build();



        adaptercl = new Pressure_Adapter(options,Pressure_Activity.this);
        recyclerView.setAdapter(adaptercl);
        adaptercl.startListening();



        BottomNavigationView bottomNavigationView1 = (BottomNavigationView) findViewById(R.id.bottom_navi);
        bottomNavigationView1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.action_home:
                        startActivity(new Intent(Pressure_Activity.this, HomeActivity.class));
                        break;

                    case R.id.action_Room2:
                        startActivity(new Intent(Pressure_Activity.this, Humid_Activity.class));
                        break;

                    case R.id.action_Room3:
                        startActivity(new Intent(Pressure_Activity.this, Pressure_Activity.class));
                        break;


                }
                return true;
            }
        });



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(new Intent(Pressure_Activity.this,HomeActivity.class));
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
                startActivity(new Intent(Pressure_Activity.this, LoginActivity.class));
                break;
            }


            case R.id.refreshMenu:{
                startActivity(new Intent(Pressure_Activity.this, HomeActivity.class));
                showsnackbar();
                break;
            }



            case R.id.settings: {

                startActivity(new Intent(Pressure_Activity.this, SettingsActivity.class));
                break;
            }


            case R.id.aboutUsMenu: {
                startActivity(new Intent(Pressure_Activity.this, AboutActivity.class));
                break;

            }

            case R.id.contactUsMenu: {

                startActivity(new Intent(Pressure_Activity.this, ContactUsActivity.class));
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
