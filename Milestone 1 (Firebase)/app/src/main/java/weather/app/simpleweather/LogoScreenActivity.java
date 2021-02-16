package weather.app.simpleweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class LogoScreenActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 2000;
    Animation topAnim, bttmAnim;
    ImageView image;
    TextView logo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_logo_screen);

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bttmAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        image = findViewById(R.id.LogoScreen);
        logo = findViewById(R.id.logoScreenName);


        image.setAnimation(topAnim);
        logo.setAnimation(bttmAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LogoScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);


    }
}