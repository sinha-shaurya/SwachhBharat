package com.example.swachhbharat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.swachhbharat.ui.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

public class DashboardActivity extends AppCompatActivity
{
    TextView welcome;
    Button signout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        //hide ActionSupportBar
        try{
            this.getSupportActionBar().hide();
        }catch(Exception e){/*do nothing*/}
        welcome=findViewById(R.id.welcome_user);
        signout=findViewById(R.id.sign_out);
        String username=getIntent().getStringExtra("username");
        welcome.setText("Welcome "+username);
        welcome.setVisibility(View.VISIBLE);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }
}
