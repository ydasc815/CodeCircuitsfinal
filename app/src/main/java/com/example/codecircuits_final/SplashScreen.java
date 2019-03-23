package com.example.codecircuits_final;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    ImageView splashImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); // will not produce java NullPointer Exception due to commenting of "FEATURE_NO_TITLE" line

        setContentView(R.layout.activity_splash_screen);


        splashImage = findViewById(R.id.splashImage);
        splashImage.animate().alpha(1).setDuration(3000);

        LogoLauncher logoLauncher = new LogoLauncher();
        logoLauncher.start();
    }
    private class LogoLauncher extends Thread{
        public void run(){
            try{
                sleep(4000);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            SplashScreen.this.finish();
        }
    }
}
