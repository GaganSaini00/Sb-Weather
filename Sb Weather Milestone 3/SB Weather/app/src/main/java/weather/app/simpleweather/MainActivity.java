package weather.app.simpleweather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import weather.app.simpleweather.Fragments.BottomNavigation;


public class MainActivity extends AppCompatActivity {
    private EditText uPassword, uEmail;
    private Button btnsgn;
    private TextView userLogin;

    private FirebaseAuth firebaseAuth;
    //private ProgressBar progressBar;
    private ProgressDialog progressDialog;
    private TextView btnforgetPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI();

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        SharedPreferences sp =getApplicationContext().getSharedPreferences("mypref", Context.MODE_PRIVATE);
        String checkemail = sp.getString("email","");
        String checkpassword = sp.getString("password","");
        uEmail.setText(checkemail);
        uPassword.setText(checkpassword);


        btnsgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkEmptySpace()) {

                    checkInfo(uEmail.getText().toString(), uPassword.getText().toString());
                }
            }
        });

        //this takes them to register page
        userLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){

                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });


        btnforgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PasswordActivity.class));
            }
        });
    }


    private void checkInfo(String uEmail, String uPassword){
        String cred = getString(R.string.credentials);
        final String login = getString(R.string.login);
        final String failLogin = getString(R.string.faillogin);
        // String string = getString(R.string.credentials);

        progressDialog.setMessage("" + cred);
        progressDialog.show();

        

        firebaseAuth.signInWithEmailAndPassword(uEmail, uPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, login, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, BottomNavigation.class));
                }else{

                    Toast.makeText(MainActivity.this,failLogin, Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                    }
            }
        });
    }




    private void setupUI(){
        uPassword = (EditText) findViewById(R.id.Password);
        uEmail = (EditText) findViewById(R.id.Email);
        userLogin = (TextView) findViewById(R.id.nohaveAcc);
        btnsgn = (Button) findViewById(R.id.btnSignIn);
        btnforgetPassword = (TextView) findViewById(R.id.FrgtPassword);


    }


    private boolean checkEmptySpace(){

        boolean result = false;

        String details = getString(R.string.details);


        String checkpassword = uEmail.getText().toString();
        String checkemail = uPassword.getText().toString();

        if(checkemail.isEmpty() || checkpassword.isEmpty()) {

            Toast.makeText(this, details, Toast.LENGTH_SHORT).show();
        }
        else {
            result = true;
        }

        return result;
    }


    @Override
    public void onBackPressed() {

        String exit= getString(R.string.exit);
        String yes= getString(R.string.yes);
        String no=getString(R.string.no);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("" + exit)
                .setCancelable(false)
                .setPositiveButton(yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);

                    }
                })

                .setNegativeButton(no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();

                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }

}






    