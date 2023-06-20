package com.example.fireconnectdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 * for insert data in FireStore cloud
 */
public class FireStoreActivity extends AppCompatActivity {
       EditText name;
       EditText pass;
       Button fire;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_store);
        name = (EditText) findViewById(R.id.name);
        pass = (EditText) findViewById(R.id.pass);
        fire = (Button) findViewById(R.id.fire);

        fire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String,String> v = new HashMap<>();
                v.put("name",name.getText().toString());
                v.put("pass",pass.getText().toString());

                //here we have  used so it replaces the values when we add new value with old values
//                FirebaseFirestore.getInstance().collection("Vender").document("Personal details").set(v).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        Toast.makeText(FireStoreActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
//                    }
//                });

                //we use add () instead of set() ,not to replace the value

                FirebaseFirestore.getInstance().collection("Vender").add(v).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(FireStoreActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
                        name.setText("");
                        pass.setText("");
                    }
                });

            }
        });
    }
}