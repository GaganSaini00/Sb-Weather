package weather.app.simpleweather.fragment;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import weather.app.simpleweather.R;

public class BottomNavigation extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_navigation);


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navi);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.action_home); // change to whichever id should be default
        }
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.action_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.action_about:
                          selectedFragment = new AboutFragment();
                            break;
                        case R.id.action_contact:
                            selectedFragment = new ContactFragment();
                            break;
                        case R.id.action_settings:
                            selectedFragment = new SettingsFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.container,
                            selectedFragment).commit();
                    return true;
                }
            };
}
        /* bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        Toast.makeText(BottomNavigation.this, "@string/homePage", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(BottomNavigation.this, HomeActivity.class));
                        break;
                    case R.id.action_contact:
                        Toast.makeText(BottomNavigation.this, "@string/contactUs", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(BottomNavigation.this, ContactUsActivity.class));
                        break;
                    case R.id.action_about:
                        Toast.makeText(BottomNavigation.this, "@string/abouUs", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(BottomNavigation.this, AboutActivity.class));
                        break;
                }
                return true;
            }
        });

        */
