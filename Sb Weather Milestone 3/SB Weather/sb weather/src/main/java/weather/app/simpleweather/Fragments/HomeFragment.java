package weather.app.simpleweather.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import weather.app.simpleweather.Temperature_Adapter;
import weather.app.simpleweather.Temperature_Model;
import weather.app.simpleweather.R;

public class HomeFragment extends Fragment {

    private FirebaseAuth firebaseAuth;
    // private Button logout;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<Temperature_Model> data;
    static View.OnClickListener myOnClickListener;
    DatabaseReference ref;
    Temperature_Adapter temperature_adapter;

    public String URL = "http://api.openweathermap.org/data/2.5/weather?q=Toronto&appid=23f04464b7119837cf1dc4fa8b39caa3&units=metric";


    @Nullable
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    /*public void showsnackbar()
    {
        String refresh = getString(R.string.refresh);
        Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),refresh,Snackbar.LENGTH_SHORT);
        snackbar.show();
    }*/



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView =(RecyclerView) view.findViewById(R.id.recycleview);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());





        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottom_navi_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.action_home:
                        /*Intent intent = new Intent(new Intent(getActivity(),HomeActivity.class));
                        startActivity(intent);*/

                        HomeFragment homeFragment = new HomeFragment();
                        FragmentTransaction transaction_home = getFragmentManager().beginTransaction();
                        transaction_home.replace(R.id.container_room3,homeFragment);
                        transaction_home.commit();

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





}
