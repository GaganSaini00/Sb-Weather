package weather.app.simpleweather.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import weather.app.simpleweather.Humidity_Adapter;
import weather.app.simpleweather.Humidity_Model;
import weather.app.simpleweather.Temperature_Activity;
import weather.app.simpleweather.Temperature_Model;
import weather.app.simpleweather.R;


public class Room2Fragment extends Fragment {

    /*private FirebaseAuth firebaseAuth;
    // private Button logout;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<Temperature_Model> data;
    static View.OnClickListener myOnClickListener;
    DatabaseReference ref;
    Humidity_Adapter humidity_adapter;

    public String URL = "http://api.openweathermap.org/data/2.5/weather?q=Miami&appid=23f04464b7119837cf1dc4fa8b39caa3&units=metric";



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ref = FirebaseDatabase.getInstance().getReference().child("JeremySensor");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_room2, container, false);

        recyclerView =(RecyclerView) view.findViewById(R.id.recycleview);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        FirebaseRecyclerOptions<Humidity_Model> options =
                new FirebaseRecyclerOptions.Builder<Humidity_Model>()
                        .setQuery(ref.limitToLast(1), Humidity_Model.class)
                        .build();

        humidity_adapter = new Humidity_Adapter(options,getActivity());
        recyclerView.setAdapter(humidity_adapter);
        humidity_adapter.startListening();




        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottom_navi_room2);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.action_home:
                        Intent intent = new Intent(new Intent(getActivity(), Temperature_Activity.class));
                        startActivity(intent);
                        break;

                    case R.id.action_Room2:
                        Room2Fragment room2Fragment = new Room2Fragment();
                        FragmentTransaction transaction_room2 = getFragmentManager().beginTransaction();
                        transaction_room2.replace(R.id.container_room3,room2Fragment);
                        transaction_room2.commit();
                        break;


                    case R.id.action_Room3:
                        Room3Fragment room3Fragment = new Room3Fragment();
                        FragmentTransaction transaction_room3 = getFragmentManager().beginTransaction();
                        transaction_room3.replace(R.id.container_room3,room3Fragment);
                        transaction_room3.commit();
                        break;
                }
                return true;
            }
        });


        return view;
    }


*/





}