package com.example.fireconnectdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;

import java.util.List;

/**
 * for delete from firestore
 */
public class Delete extends AppCompatActivity {

    Button delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //here we have to provide id  inside document to delete
//                FirebaseFirestore.getInstance().collection("Vender").document(
//                        "2qKaT6FzPWH3ERM1f2hS").delete().addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        Toast.makeText(Delete.this, "Successfully deleted", Toast.LENGTH_SHORT).show();
//                    }
//                });

                //delete using condition to delete specific data  ,,, here get() method is use to get the data
                FirebaseFirestore.getInstance().collection("Vender").whereEqualTo("pass","27").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        //getDocuments() se humne specific "27" pass vala batch() data nikala(total 27 pass wala data)  or vo data hum list me store karenge for loop me delete karenge
                        WriteBatch batch = FirebaseFirestore.getInstance().batch();
                        List<DocumentSnapshot> s = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot snapshot : s)
                        {
                            batch.delete(snapshot.getReference());

                        }
                        batch.commit().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(Delete.this, "Successfully deleted", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });
    }
}