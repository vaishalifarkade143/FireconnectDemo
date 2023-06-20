package com.example.fireconnectdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText email;

    EditText password;
    Button login2;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login2 = (Button) findViewById(R.id.login2);
        auth = FirebaseAuth.getInstance();

        login2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1 = email.getText().toString();
                String password1 = password.getText().toString();

                //agar user ne koi field khali chod di to
                if( TextUtils.isEmpty(email1) || TextUtils.isEmpty(password1) )
                {
                    Toast.makeText(Login.this, "Enter  email and password", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    login3(email1,password1);

                }

            }

        });
    }

    private void login3(String email1, String password1)
    {
        auth.signInWithEmailAndPassword(email1,password1).addOnSuccessListener(Login.this, new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(Login.this, "Login Successfully", Toast.LENGTH_SHORT).show();

                //to move on add name n age page
              //  startActivity(new Intent(Login.this, Next.class));

                //to show detail in list view
               //startActivity(new Intent(Login.this, Next1.class));

            }

        });
    }
}