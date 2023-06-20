package com.example.fireconnectdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

/**
 * to insert data to RealTime Database of firestore
 */
public class Next extends AppCompatActivity {
    Button btn_add;
    EditText age;
    EditText name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        name = (EditText) findViewById(R.id.name);
        age = (EditText) findViewById(R.id.age);
        btn_add = (Button)findViewById(R.id.btn_add);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //to add manualy
                //FirebaseDatabase.getInstance().getReference().child("Vender1").setValue("shree");

                //add data using form
                HashMap<String,Object>  hm = new HashMap<>();
                hm.put("name",name.getText().toString());
                hm.put("age",age.getText().toString());
                FirebaseDatabase.getInstance().getReference().child("Vender1").push().setValue(hm);

                Toast.makeText(Next.this, "successfully added", Toast.LENGTH_SHORT).show();

                //to clear field ("name" is field id here)
                name.setText("");
                age.setText("");
            }
        });

    }
}