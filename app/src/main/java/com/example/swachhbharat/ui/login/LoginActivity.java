package com.example.swachhbharat.ui.login;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.swachhbharat.DashboardActivity;
import com.example.swachhbharat.R;
import com.example.swachhbharat.RegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity
{
    int cBackPressed=0;//count number of times user has pressed back button
    private LoginViewModel loginViewModel;
    private FirebaseAuth mAuth;
    EditText emailText,passwordText;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        try
        {
            this.getSupportActionBar().hide();//hide support action bar on startup
        }catch(Exception e){/*do nothing*/}
        mAuth= FirebaseAuth.getInstance();
        emailText=findViewById(R.id.username);
        passwordText=findViewById(R.id.password);
    }
    private void updateUI(FirebaseUser user)
    {
        //hideProgressBar();
        if(user!=null)
        {
            Toast.makeText(getApplicationContext(),"Welcome",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(getApplicationContext(), DashboardActivity.class);
            String username=user.getDisplayName();
            intent.putExtra("username",username);
            startActivity(intent);
        }
        else if(user==null)
        {
           //do nothing
        }

    }
    @Override
    public void onBackPressed()
    {
        cBackPressed+=1;//increment by 1
        if(cBackPressed==1)
            Toast.makeText(this,"Press Again to Exit",Toast.LENGTH_SHORT).show();
        if(cBackPressed==2)
        {
            finishAffinity();
            System.exit(0);
        }
    }
    @Override
    public void onStart()
    {
        super.onStart();
        //check if user is already logged in
        FirebaseUser currentUser=mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    public void launch_RegisterActivity(View view)
    {
        Intent intent=new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void login_listener(View view)
    {
        String email=emailText.getText().toString();
        String password=passwordText.getText().toString();
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    FirebaseUser currentUser=mAuth.getCurrentUser();
                    updateUI(currentUser);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Email or Password is wrong,try again",Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
            }
        });
    }
}
