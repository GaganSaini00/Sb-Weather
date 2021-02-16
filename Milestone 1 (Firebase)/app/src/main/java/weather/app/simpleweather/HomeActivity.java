package weather.app.simpleweather;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
   // private Button logout;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    public static FirebaseRecyclerOptions<Model> data;
    static View.OnClickListener myOnClickListener;
    DatabaseReference ref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);


        //Jeremy's Code

        recyclerView =(RecyclerView) findViewById(R.id.recycleview);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        ref = FirebaseDatabase.getInstance().getReference().child("Jeremy's Sensor");

        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                .setQuery(ref,Model.class)
                .build();
        adapter = new Adapter(options,HomeActivity.this);
        recyclerView.setAdapter(adapter);


        /*ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                fetchdatabase(dataSnapshot);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                fetchdatabase(dataSnapshot);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

        /*data.add(new Model
                ("Temperature","0"));
        data.add(new Model("Humidity","40%"));
        data.add(new Model("Air Pressure","100kpa"));
*/




    }
/*
    private void fetchdatabase(DataSnapshot dataSnapshot)
    {
        //data.clear();
        for(DataSnapshot ds : dataSnapshot.getChildren())
        {
            Model weavals = ds.getValue(Model.class);
            data.add(weavals);

        }

        Adapter winfo = new Adapter(data,HomeActivity.this);
        recyclerView.setAdapter(winfo);

    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){

            case R.id.logoutMenu: {

                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
                break;
            }

            case R.id.aboutUsMenu: {
                startActivity(new Intent(HomeActivity.this, AboutActivity.class));
                break;

            }

            case R.id.refreshMenu:{
                startActivity(new Intent(HomeActivity.this, HomeActivity.class));
                break;
            }

            case R.id.contactUsMenu: {

                startActivity(new Intent(HomeActivity.this, ContactUsActivity.class));
                break;
            }

            }

        return super.onOptionsItemSelected(item);
    }
}