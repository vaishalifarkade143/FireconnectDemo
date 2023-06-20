package com.example.fireconnectdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class Register extends AppCompatActivity {

    EditText email;

    EditText password;
    Button register2;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        register2 = (Button) findViewById(R.id.register2);
        auth = FirebaseAuth.getInstance();

        register2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email1 = email.getText().toString();
                String password1 = password.getText().toString();

                //agar user ne koi field khali chod di to
                if( TextUtils.isEmpty(email1) || TextUtils.isEmpty(password1) )
                {
                    Toast.makeText(Register.this, "Enter  email and password", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    regis(email1,password1);
                }
            }
        });
    }

    //hume firebase me value store kARVANE Krvane k liye function/method create karna padega
    private void regis( String email1, String password1)
    {
        auth.createUserWithEmailAndPassword(email1,password1).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(Register.this, "Successfully registered", Toast.LENGTH_SHORT).show();
                    email.setText("");
                    password.setText("");

                }
                else {
                    Toast.makeText(Register.this, "Failed", Toast.LENGTH_SHORT).show();
                    Log.e("error","");
                }
            }
        });
    }
}