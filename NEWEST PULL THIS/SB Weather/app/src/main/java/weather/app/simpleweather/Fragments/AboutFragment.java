package weather.app.simpleweather.Fragments;

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

import weather.app.simpleweather.MainActivity;
import weather.app.simpleweather.R;

public class AboutFragment extends Fragment {

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
        View view =  inflater.inflate(R.layout.fragment_aboutus, container, false);
        return view;
    }
}
