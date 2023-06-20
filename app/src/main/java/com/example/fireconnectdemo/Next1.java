package com.example.fireconnectdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * For Realtime DataBase Fetch
 */
public class Next1 extends AppCompatActivity
{

    Button btn_show;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next1);

        btn_show = (Button) findViewById(R.id.btn_show);
        list = (ListView) findViewById(R.id.list);

        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //to retrive the data from realtime database on listView  on click of button event
                ArrayList<String> a = new ArrayList<>();
                ArrayAdapter adapter = new ArrayAdapter<String>(Next1.this,R.layout.items,a);
                list.setAdapter(adapter);
                FirebaseDatabase.getInstance().getReference().child("Vender1").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot)
                    {
                        if(snapshot.exists())
                        {
                            a.clear();
                            for (DataSnapshot snapshot1:snapshot.getChildren())
                            {
                                VenderModel i = snapshot1.getValue(VenderModel.class);
                                String t =i.getName()+" : "+i.getAge();
                                a.add(t);

                            }
                            adapter.notifyDataSetChanged();//jaise hi humare pass data base me koi data change hota hai to vo jakar adapter me reflect ho jaye
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e("Error occur","Error"+error);
                    }
                });
            }
        });

    }
}