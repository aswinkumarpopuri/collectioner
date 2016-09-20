package com.mac.training.collectioner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.mac.training.collectioner.dummy.Dummy;
import com.mac.training.collectioner.model.Item;


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
        String user = "Josimar";
        Log.d(TAG, user+"------------------");
         FirebaseCollectionsController.getUserCollections(user);
    }



///////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////ITEMS/////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////

    public void insertItem(View view) {
        Item item = new Item("itemName", "item desc", 12.3, 324.4, "in your mom", "new", "urlImage");
        FirebaseCollectionsController.insertItem("Josimar","-KS3gncNobhYAVfXl83Y", item );

    }


    public void deleteItem(View view) {
        String user = "Josimar";
        String collection = "-KS3gncNobhYAVfXl83Y";
        String item = "-KS4F4Fuq6TcjMP55p2h";
        FirebaseCollectionsController.deleteItem(user, collection, item);
    }


    public void queryItemsCollection(View view) {
        String user = "Josimar";
        String collection = "-KS3gncNobhYAVfXl83Y";
        Log.d(TAG, user+"------------------");
        FirebaseCollectionsController.getItemsCollection(user, collection);
    }
}
