package com.example.rmaproject1.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rmaproject1.R;
import com.example.rmaproject1.activities.LoginActivity;
import com.example.rmaproject1.activities.MainActivity;

public class ProfileFragment extends Fragment {

    private TextView username;
    private TextView email;
    private Button logoutButton;

    public ProfileFragment() {
        super(R.layout.fragment_profile);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(this.getActivity().getPackageName(), Context.MODE_PRIVATE);

        username = view.findViewById(R.id.profileUsernameDisplay);
        email = view.findViewById(R.id.profileEmailDisplay);
        username.setText(sharedPreferences.getString(LoginActivity.USERNAME, ""));
        email.setText(sharedPreferences.getString(LoginActivity.EMAIL, ""));
        email.setPaintFlags(email.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        logoutButton = view.findViewById(R.id.buttonLogOut);
        logoutButton.setOnClickListener(v-> {
            System.out.println(sharedPreferences.getString("username", "nema, obrisano"));
            sharedPreferences.edit().clear().apply();
            System.out.println(sharedPreferences.getString("username", "nema, obrisano"));
            Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
            startActivity(loginIntent);
            getActivity().finish();
        });

    }
}
