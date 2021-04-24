package weather.app.simpleweather;
//Sunshine Boys
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import weather.app.simpleweather.Fragments.BottomNavigation;


public class LoginActivity extends AppCompatActivity {
    private EditText uPassword, uEmail;
    private Button btnsgn;
    private TextView userLogin;

    private FirebaseAuth firebaseAuth;
    //private ProgressBar progressBar;
    private ProgressDialog progressDialog;
    private TextView btnforgetPassword;


    //GoogleSignInClient mGoogleSignInClient;
    private static int RC_SIGN_IN = 100;

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

       String u_email = uEmail.getText().toString().trim();
       String u_password = uPassword.getText().toString().trim();

       SharedPreferences.Editor editor = sp.edit();
       editor.putString("email",u_email);
       editor.putString("password",u_password);
       editor.apply();




        btnsgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkEmptySpace()) {

                    checkInfo(uEmail.getText().toString(), uPassword.getText().toString());
                    String u_email = uEmail.getText().toString().trim();
                    String u_password = uPassword.getText().toString().trim();
                    SharedPreferences sp =getApplicationContext().getSharedPreferences("mypref", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("email",u_email);
                    editor.putString("password",u_password);
                    editor.apply();
                }
            }
        });

        //this takes them to register page
        userLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){

                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });


        btnforgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, PasswordActivity.class));
            }
        });



        /*// Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        // Check for existing Google Sign In account, if the user is already signed in
// the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);


        // Set the dimensions of the sign-in button.
        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);


        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //signIn();

            }
        });*/



    }

   /* private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }*/

   /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }*/

    /*private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            if (acct != null) {
                String personName = acct.getDisplayName();
                String personGivenName = acct.getGivenName();
                String personFamilyName = acct.getFamilyName();
                String personEmail = acct.getEmail();
                String personId = acct.getId();
                Uri personPhoto = acct.getPhotoUrl();

                Toast.makeText(this, getString(R.string.googlewelcomeMsg) +personName, Toast.LENGTH_SHORT).show();

            uEmail.setText(personEmail);

            }

            startActivity(new Intent(LoginActivity.this, Temperature_Activity.class));


        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.


            Log.d("Failed Login", e.toString());
        }
    }
*/


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
                    Toast.makeText(LoginActivity.this, login, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, BottomNavigation.class));
                }else{

                    Toast.makeText(LoginActivity.this,failLogin, Toast.LENGTH_SHORT).show();
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






    