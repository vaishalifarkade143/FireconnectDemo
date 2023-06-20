package com.example.fireconnectdemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class Upload_image_In_Storage extends AppCompatActivity {

    Button upload;
    Button save;
    ImageView image;
    private final int IMG_REQUEST_ID = 1;
    private Uri u;
    FirebaseStorage storage;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image_in_storage);

        upload = (Button) findViewById(R.id.upload);
        save = (Button) findViewById(R.id.save);
        image = (ImageView) findViewById(R.id.image);
        save.setEnabled(false);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();//to open gallary or other
                intent.setType("image/*");//to choose any type of image we use *
                intent.setAction(Intent.ACTION_GET_CONTENT);//jo bhi image hum select karenge use uthna hai image view me
                startActivityForResult(Intent.createChooser(intent,"select image"),IMG_REQUEST_ID);

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog  progressDialog = new ProgressDialog(Upload_image_In_Storage.this);
                progressDialog.setTitle("Uploading image......");
                progressDialog.show();
                if(u != null)
                {
                    StorageReference reference = storageReference.child("picture/"+ UUID.randomUUID().toString());
                    reference.putFile(u).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(Upload_image_In_Storage.this, "image saved", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Upload_image_In_Storage.this, "ERROR"+e, Toast.LENGTH_SHORT).show();
                                }
                            });
                }
                upload.setEnabled(true);
                save.setEnabled(false);
            }
        });

    }

    //to show image in image view

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMG_REQUEST_ID && resultCode == RESULT_OK && data !=null && data.getData()!=null)
        {
            u=data.getData();
            try {
                Bitmap bit = MediaStore.Images.Media.getBitmap(getContentResolver(),u);
                image.setImageBitmap(bit);
                upload.setEnabled(false);
                save.setEnabled(true);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}