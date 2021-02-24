package weather.app.simpleweather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class RegisterActivity extends AppCompatActivity {

    private EditText userUName, userPassword, userEmail;
    private Button btnreg;
    private TextView userLogin;

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
                   String user_password = userPassword.getText().toString().trim();
                   String user_email = userEmail.getText().toString().trim();


                   firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if(task.isSuccessful()) {
                               Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, MainActivity.class));

                           }else{

                               Toast.makeText(RegisterActivity.this, "Registration Unsuccessful, Please Try Again", Toast.LENGTH_SHORT).show();
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

        if(checkname.isEmpty() || checkpassword.isEmpty() || checkemail.isEmpty()) {

            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }
        else {
            result = true;
        }

        return result;
    }



}