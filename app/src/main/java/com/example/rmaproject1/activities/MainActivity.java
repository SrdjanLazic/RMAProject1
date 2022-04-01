package com.example.rmaproject1.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.rmaproject1.R;
import com.example.rmaproject1.model.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        splashScreen.setKeepOnScreenCondition(() -> {

            SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);

            String username = sharedPreferences.getString(LoginActivity.USERNAME, "");

            Intent intent;
            if(username.equals("")){
                intent = new Intent(this, LoginActivity.class);
            } else {
                intent = new Intent(this, AppActivity.class);
                intent.putExtra("user", new User(LoginActivity.USERNAME, LoginActivity.EMAIL, LoginActivity.PASSWORD, LoginActivity.TYPE));
            }
            startActivity(intent);
            finish();
            return false;
        });

        setContentView(R.layout.activity_main);
    }
}