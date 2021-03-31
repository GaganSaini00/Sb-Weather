package weather.app.simpleweather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ContactUsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        TextView githublk = (TextView) findViewById(R.id.Git);
        githublk.setMovementMethod(LinkMovementMethod.getInstance());

        BottomNavigationView bottomNavigationView1 = (BottomNavigationView) findViewById(R.id.bottom_navi);
        bottomNavigationView1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        //Toast.makeText(ContactUsActivity.this, "@string/homePage", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ContactUsActivity.this, HomeActivity.class));
                        break;
                    case R.id.action_contact:
                        //Toast.makeText(ContactUsActivity.this, "@string/contactUs", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ContactUsActivity.this, ContactUsActivity.class));
                        break;
                    case R.id.action_about:
                        //Toast.makeText(ContactUsActivity.this, "@string/abouUs", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ContactUsActivity.this, AboutActivity.class));
                        break;
                }
                return true;
            }
        });


    }


}