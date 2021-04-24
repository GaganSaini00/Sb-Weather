package weather.app.simpleweather.fragment;

import android.content.Intent;
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
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import weather.app.simpleweather.Adapter;
import weather.app.simpleweather.HomeActivity;
import weather.app.simpleweather.MainActivity;
import weather.app.simpleweather.Model;
import weather.app.simpleweather.R;

public class HomeFragment extends Fragment {

    private FirebaseAuth firebaseAuth;
    // private Button logout;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<Model> data;
    static View.OnClickListener myOnClickListener;


    @Nullable
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        String temp = getString(R.string.temperature);
        String humid = getString(R.string.humidity);
        String apressure = getString(R.string.windspeed);
        String wspeed = getString(R.string.airpressure);

        super.onCreate(savedInstanceState);
        data = new ArrayList<Model>();
        data.add(new Model
                ("" + temp,"0"));
        data.add(new Model("" + humid,"40%"));
        data.add(new Model("" + apressure,"100kpa"));
        data.add(new Model("" + wspeed,"10 Kmh"));


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

        String temp = getString(R.string.temperature);
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
        recyclerView.setAdapter(winfo);

        return view;
    }




}
