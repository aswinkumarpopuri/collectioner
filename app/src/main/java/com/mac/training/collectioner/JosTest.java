package com.mac.training.collectioner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.mac.training.collectioner.dummy.Dummy;


public class JosTest extends AppCompatActivity {

    String TAG = "Test -- -- ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jos_test);


    }


    public void insertCollection(View view) {
        String user = Dummy.genUser();
        String collection = Dummy.genCollection();

        FirebaseCollectionsController.insertCollection(user, collection);

    }
    public void deleteCollection(View view) {

        String user = ((EditText)findViewById(R.id.user)).getText().toString();
        String collection = ((EditText)findViewById(R.id.colKey)).getText().toString();

        FirebaseCollectionsController.deleteUserCollection(user, collection);

    }


    public void queryCollections(View view) {

        String user = ((EditText)findViewById(R.id.user)).getText().toString();
        Log.d(TAG, user+"------------------");
         FirebaseCollectionsController.getUserCollections(user);

    }
}
