package weather.app.simpleweather.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import weather.app.simpleweather.LoginActivity;
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
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            }


            case R.id.refreshMenu:{
                selectedFragment = new HomeFragment();
                showsnackbar();
                break;
            }


        }

        return super.onOptionsItemSelected(item);
    }

    public void showsnackbar()
    {
        String refresh = getString(R.string.refresh);
        Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),refresh,Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_aboutus, container, false);
        final String send_mail = getString(R.string.send_mail);
        final TextView e = (TextView) view.findViewById(R.id.EmailAddress);
        final ImageView imageView = (ImageView) view.findViewById(R.id.EmailImage);
        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri u = Uri.parse(e.getText().toString());
                Intent emailIntent = new Intent(Intent.ACTION_SEND, u);
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {String.valueOf(u)}); // recipients
                emailIntent.setType("message/rfc822");

                try {
                    startActivity(Intent.createChooser(emailIntent, send_mail));
                    getActivity().finish();
                } catch (android.content.ActivityNotFoundException ex) { }


            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri u = Uri.parse(e.getText().toString());
                Intent emailIntent = new Intent(Intent.ACTION_SEND, u);
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {String.valueOf(u)}); // recipients
                emailIntent.setType("message/rfc822");

                try {
                    startActivity(Intent.createChooser(emailIntent, send_mail));
                    getActivity().finish();
                } catch (android.content.ActivityNotFoundException ex) { }


            }
        });



        return view;
    }
}
