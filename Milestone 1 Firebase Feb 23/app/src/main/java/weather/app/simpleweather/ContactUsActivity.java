package weather.app.simpleweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ContactUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
    }

    public void Call(View v) {
        // Find the EditText by its unique ID
        TextView e = (TextView) findViewById(R.id.number);


        // Test to show that the click is acknowledged
        Toast.makeText(this, "clicked", Toast.LENGTH_LONG)
                .show();

        // Use format with "tel:" and phoneNumber created is
        // stored in u.
        Uri u = Uri.parse("tel:" + e.getText().toString());

        // Create the intent and set the data for the
        // intent as the phone number.
        Intent i = new Intent(Intent.ACTION_DIAL, u);


        try {
            // Launch the Phone app's dialer with a phone
            // number to dial a call.
            startActivity(i);
        } catch (SecurityException s) {
            // show() method display the toast with
            // exception message.
            Toast.makeText(this, s.getMessage(), Toast.LENGTH_LONG)
                    .show();
        }


    }


    public void Email(View v) {
        TextView e = (TextView) findViewById(R.id.EmailAddress);

        Toast.makeText(this, "clicked", Toast.LENGTH_LONG)
                .show();

        Uri u = Uri.parse(e.getText().toString());

        Intent emailIntent = new Intent(Intent.ACTION_SEND, u);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {String.valueOf(u)}); // recipients
        emailIntent.setType("message/rfc822");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ContactUsActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }


    }

    public void goToLink(View v){

        TextView e = (TextView) findViewById(R.id.Git);

        Toast.makeText(this, "clicked", Toast.LENGTH_LONG)
                .show();

        Uri u = Uri.parse(e.getText().toString());

        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, u);
        try {
            startActivity(Intent.createChooser(launchBrowser, "Visit URL..."));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ContactUsActivity.this, "There is no browser installed.", Toast.LENGTH_SHORT).show();
        }

    }


}

