package weather.app.simpleweather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN=
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

    private EditText userUName, userPassword, userEmail;
    private Button btnreg;
    private TextView userLogin;
    private SharedPreferences sp;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupUI();

        firebaseAuth = FirebaseAuth.getInstance();

        userLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));

                }
            });

        btnreg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


               if(checkInfo()){

                   //trim, removes spaces
                   String user_name = userUName.getText().toString().trim();
                   final String user_password = userPassword.getText().toString().trim();
                   final String user_email = userEmail.getText().toString().trim();
                   final String registersuccess = getString(R.string.registersuccess);
                   final String registerunsuccess = getString(R.string.registerunsuccess);


                   firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if(task.isSuccessful()) {

                               sp = getSharedPreferences("mypref", Context.MODE_PRIVATE);
                               SharedPreferences.Editor edit = sp.edit();
                               edit.putString("email",user_email);
                               edit.putString("password",user_password);
                               edit.apply();

                               Toast.makeText(RegisterActivity.this, registersuccess, Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, MainActivity.class));

                           }else{

                               Toast.makeText(RegisterActivity.this, registerunsuccess, Toast.LENGTH_SHORT).show();
                           }
                       }
                   });
               }


            }


        });



    }

    private void setupUI(){
        userUName = (EditText) findViewById(R.id.UserName);
        userPassword = (EditText) findViewById(R.id.Password);
        userEmail = (EditText) findViewById(R.id.Email);
        btnreg = (Button) findViewById(R.id.btnRegister);
        userLogin = (TextView) findViewById(R.id.haveAcc);
    }

    private boolean checkInfo(){

        boolean result = false;

        String checkname = userUName.getText().toString();
        String checkpassword = userPassword.getText().toString();
        String checkemail = userEmail.getText().toString();
        String details = getString(R.string.details);
        String name = getString(R.string.name);
        String eMail = getString(R.string.vemail);
        String password = getString(R.string.password);

        if(checkname.isEmpty() || checkpassword.isEmpty() || checkemail.isEmpty()) {
            Toast.makeText(this, details, Toast.LENGTH_SHORT).show();

        }

        else if(!PASSWORD_PATTERN.matcher(checkpassword).matches()){
            Toast.makeText(this,password, Toast.LENGTH_SHORT).show();

        }


       else if(!Patterns.EMAIL_ADDRESS.matcher(checkemail).matches()) {
            Toast.makeText(this, eMail, Toast.LENGTH_SHORT).show();

        }


       else if(!userUName.getText().toString().matches("[a-z,A-Z]*"))
       {
            Toast.makeText(this, name, Toast.LENGTH_SHORT).show();

        }


        else  {
            result = true;
        }

        return result;
    }



}