package weather.app.simpleweather;
//Sunshine Boys
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;


public class ContactUsActivity extends AppCompatActivity {
    private final static int Request_code_permissions = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);


        if (ContextCompat.checkSelfPermission(ContactUsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            permissions_dialog();


        }


    }


    public void permissions_dialog() {
        String permissions = getString(R.string.permission_needed);
        String permissions_message = getString(R.string.permission_message);
        String OK = getString(R.string.ok);
        String Cancel = getString(R.string.cancel);



        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
            new AlertDialog.Builder(this)
                    .setTitle(permissions)
                    .setMessage(permissions_message)
                    .setPositiveButton(OK, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            ActivityCompat.requestPermissions(ContactUsActivity.this, new String[]{Manifest.permission.CALL_PHONE}, Request_code_permissions);

                        }
                    })
                    .setNegativeButton(Cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .create().show();

        } else {

            ActivityCompat.requestPermissions(ContactUsActivity.this, new String[]{Manifest.permission.CALL_PHONE}, Request_code_permissions);

        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        switch (requestCode) {
            case Request_code_permissions:
                for (int index = permissions.length - 1; index >= 0; --index) {
                    if (grantResults[index] != PackageManager.PERMISSION_GRANTED) {
                        // exit the app if one permission is not granted
                        finish();
                        return;
                    }
                }
        }
    }
}


