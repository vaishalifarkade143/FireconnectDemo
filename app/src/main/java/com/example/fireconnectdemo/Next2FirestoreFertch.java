package com.example.fireconnectdemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * to fetch the data from FireStore cloud
 */
public class Next2FirestoreFertch extends AppCompatActivity
{
    Button btn_show;
    ListView list;

    List<String> nameList = new ArrayList<>();//when we fetch the data then the data  Store in List
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next2_firestore_fertch);

        btn_show = (Button) findViewById(R.id.btn_show);
        list = (ListView) findViewById(R.id.list);

        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore.getInstance().collection("Vender").addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        nameList.clear();
                        for (DocumentSnapshot s : value)
                        {
                            nameList.add(s.getString("name")+ " : " +s.getString("pass"));

                        }
                        ArrayAdapter adapter = new ArrayAdapter<String>(Next2FirestoreFertch.this, android.R.layout.simple_selectable_list_item ,nameList);
                        adapter.notifyDataSetChanged();//in case data  me koi change hota hai to vo change kar diya jaye
                        list.setAdapter(adapter);// notifyDataSetChanged() jo bhi ha ListView pr set karvana chahte ho vo set karvadega
                    }
                });
            }
        });

    }
}