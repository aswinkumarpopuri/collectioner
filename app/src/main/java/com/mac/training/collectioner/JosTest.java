package com.mac.training.collectioner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.mac.training.collectioner.dummy.Dummy;
import com.mac.training.collectioner.model.Collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JosTest extends AppCompatActivity {

    String TAG = "Test -- -- ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jos_test);


    }


    public void insertCategories(View view) {

        ArrayList<String> asd = FirebaseCollectionsController.getUsers();

        for(String s : asd)
            Log.d(TAG, s);


       /* String user = Dummy.genUser();
        String collection = Dummy.genCollection();

        String key = mDatabase.child(user).push().getKey();

        //Item i = new Item("name", "description", 12.0, 15.0, "location", "used", "img-url");
        Collection c = new Collection(collection);

        Map<String, Object> cValues = c.toMap();
        Map<String, Object> childUpdates = new HashMap<>();

        childUpdates.put(user+"/"+key , cValues);

        mDatabase.updateChildren(childUpdates);*/
    }














}
