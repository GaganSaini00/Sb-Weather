package weather.app.simpleweather.Fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
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
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

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

import weather.app.simpleweather.AdapterActivity;
import weather.app.simpleweather.LoginActivity;
import weather.app.simpleweather.ModelActivity;
import weather.app.simpleweather.R;

public class HomeFragment extends Fragment {

    private FirebaseAuth firebaseAuth;
    // private Button logout;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<ModelActivity> data;
    static View.OnClickListener myOnClickListener;

    public String URL = "http://api.openweathermap.org/data/2.5/weather?q=Toronto&appid=23f04464b7119837cf1dc4fa8b39caa3&units=metric";


    @Nullable
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {


        setHasOptionsMenu(true);


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

        /*String temp = getString(R.string.temperature);
        String humid = getString(R.string.humidity);
        String apressure = getString(R.string.windspeed);
        String wspeed = getString(R.string.airpressure);

        adapter = new Adapter(data);
        recyclerView.setAdapter(adapter);
        data = new ArrayList<Model>();
        data.add(new Model
                ("" + temp,"0"));
        data.add(new Model("" + humid,"40%"));
        data.add(new Model("" + apressure,"100kpa"));
        data.add(new Model("" + wspeed,"10 Kmh"));

        Adapter winfo = new Adapter(data, getActivity());
        recyclerView.setAdapter(winfo);*/


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
