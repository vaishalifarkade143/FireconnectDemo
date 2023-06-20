package com.example.fireconnectdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FireConnect extends AppCompatActivity {
    Button register1;
    Button login1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_connect);

        register1 = (Button) findViewById(R.id.register1);
        login1 = (Button) findViewById(R.id.login1);

        register1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //to move on register page
                startActivity(new Intent(FireConnect.this, Register.class));
            }
        });

        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //to move on Login page
                //startActivity(new Intent(FireConnect.this, Login.class));

                //without log in move on listview
                // startActivity(new Intent(FireConnect.this, Next1.class));

                //when click on login move to FireStore page
//                startActivity(new Intent(FireConnect.this,FireStoreActivity.class));

                //call Next2FireStore Activity on click of Login to save time
//                startActivity(new Intent(FireConnect.this,Next2FirestoreFertch.class));
//                Toast.makeText(FireConnect.this, "Fetching Data from fireStore ", Toast.LENGTH_SHORT).show();

                //to go on delete Activity
//                startActivity(new Intent(FireConnect.this, Delete.class));

                //upload image
                startActivity(new Intent(FireConnect.this, Upload_image_In_Storage.class));
            }
        });
    }
}