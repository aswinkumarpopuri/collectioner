package com.mac.training.collectioner.storage;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mac.training.collectioner.R;
import com.squareup.picasso.Picasso;

public class Storage extends AppCompatActivity {

    Button bSelect;
    ImageView imgV;


    Button bUpload;
    StorageReference mStorage;
    private static final int GALLERY_REQUEST = 2;
    private static final int CAMERA_REQUEST = 2;
    private ProgressDialog  progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        mStorage = FirebaseStorage.getInstance().getReference();


        bSelect = (Button) findViewById(R.id.selectImage);
        bUpload = (Button) findViewById(R.id.uploadImage);
        imgV = (ImageView) findViewById(R.id.imageStore);

        bSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,CAMERA_REQUEST);
            }
        });

        bUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,GALLERY_REQUEST);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            progressDialog = new ProgressDialog(this);
            if((requestCode == GALLERY_REQUEST)||(requestCode == CAMERA_REQUEST)){
                progressDialog.setMessage("Loading..");
                progressDialog.show();
                Uri uri = data.getData();
                StorageReference filepath = mStorage.child("Images").child("categoryId").child("imageId");
                filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        progressDialog.dismiss();

                        Uri downloadUri = taskSnapshot.getDownloadUrl();

                        Picasso.with(Storage.this).load(downloadUri).fit().centerCrop().into(imgV);

                        Toast.makeText(Storage.this, "Liiiistoo..! (8)", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
    }
}
