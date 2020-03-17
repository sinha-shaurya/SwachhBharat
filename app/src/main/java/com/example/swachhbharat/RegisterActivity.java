package com.example.swachhbharat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.swachhbharat.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity
{
    EditText emailText,passwordText;
    Button registerButton;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        try
        {
            this.getSupportActionBar().hide();
        }catch(Exception e){/* do nothing */}//hide ActionBar
        emailText=findViewById(R.id.register_email);
        passwordText=findViewById(R.id.register_password);
        registerButton=findViewById(R.id.register);
        firebaseAuth=FirebaseAuth.getInstance();
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String email=emailText.getText().toString();
                String password=passwordText.getText().toString();
                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(getApplicationContext(),"Please enter email",Toast.LENGTH_LONG).show();
                }
                if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(getApplicationContext(),"Please enter password",Toast.LENGTH_LONG).show();
                }
                if(password.length()<5)
                {
                    Toast.makeText(getApplicationContext(),"Password should be of atleast 5 characters",Toast.LENGTH_LONG).show();

                }
               firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task)
                   {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(),"Registration Successful",Toast.LENGTH_LONG);
                            Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Email or Password is wrong",Toast.LENGTH_SHORT);
                        }
                   }
               });
            }
        });
    }

}
