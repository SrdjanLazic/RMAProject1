package com.example.rmaproject1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rmaproject1.R;
import com.example.rmaproject1.model.User;

public class LoginActivity extends AppCompatActivity {

    public static final String USER_PASSWORD = "user123";
    public static final String ADMIN_PASSWORD = "admin123";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String TYPE = "type";
    private Button loginButton;
    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init(){
        initView();
        initListeners();
    }

    private void initView(){
        loginButton = findViewById(R.id.loginButton);
        usernameEditText = findViewById(R.id.usernameLoginTextField);
        passwordEditText = findViewById(R.id.passwordLoginTextField);
        emailEditText = findViewById(R.id.emailLoginTextField);
    }

    private void initListeners(){
        loginButton.setOnClickListener(v -> {

            SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);

            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String email = emailEditText.getText().toString();

            String type;

            if(username.startsWith("admin")){
                type = "admin";
            } else if (username.startsWith("user")){
                type = "user";
            } else {
                type = "error";
            }

            if(checkCredentials(username, password, email, type)){

                sharedPreferences
                        .edit()
                        .putString(USERNAME, username)
                        .putString(PASSWORD, password)
                        .putString(EMAIL, email)
                        .putString(TYPE, type)
                        .apply();

                Intent intent = new Intent(this, AppActivity.class);
                intent.putExtra("user", new User(username, email, password, type));
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Error logging in.",
                        Toast.LENGTH_LONG).show();
            }

        });
    }

    private boolean checkCredentials(String username, String password, String email, String type){

        boolean flag = false;
        if(!username.equals("") && password.length() > 5 && !email.equals("") && !type.equals("errorPagerAdapter")){
            flag =  type.equals("user") && password.equals(USER_PASSWORD)
                    || type.equals("admin") && password.equals(ADMIN_PASSWORD);
        }
        return flag;
    }
}