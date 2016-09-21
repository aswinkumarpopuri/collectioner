package com.mac.training.collectioner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mac.training.collectioner.activity.login.FirebaseCollectionsController;
import com.mac.training.collectioner.dummy.Dummy;
import com.mac.training.collectioner.model.Item;


public class JosTest extends AppCompatActivity {

    String TAG = "Test -- -- ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jos_test);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////COLLECTIONS////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////

    public void insertCollection(View view) {
        String user = Dummy.genUser();
        String collection = Dummy.genCollection();
        FirebaseCollectionsController.insertCollection(user, collection);

    }

    public void deleteCollection(View view) {
        String user = "Josimar";
        String collection = "-KS8eUq8WfAr_tnkEwkk";
        FirebaseCollectionsController.deleteUserCollection(user, collection);
    }


    public void queryCollections(View view) {
        String user = "Josimar";
        Log.d(TAG, user+"------------------");
        FirebaseCollectionsController.getUserCollections(user);
    }


    public void queryCollection(View view) {
        String user = "Josimar";
        String collection = "-KS8eV84MtddBn9YxSUZ";
         FirebaseCollectionsController.getUserCollection(user, collection);
    }


    public void updateCollection(View view) {
        String user = "Josimar";
        String collection = "-KS8eV84MtddBn9YxSUZ";

        FirebaseCollectionsController.updateCollection(user,"Juanito", collection);
    }


///////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////ITEMS/////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////

    public void insertItem(View view) {
        Item item = new Item("id" , "itemName", "urlImage", "item desc", 12.3, 324.4, "in your mom", 1.0, 1.1, "new");
        FirebaseCollectionsController.insertItem("-KS8eV84MtddBn9YxSUZ", item );

    }

    public void queryItemsCollection(View view) {
        String collection = "-KS8eV84MtddBn9YxSUZ";
        FirebaseCollectionsController.getItemsCollection(collection);
    }

    public void queryItemCollection(View view) {
        String collection = "-KS8eV84MtddBn9YxSUZ";
        String item = "-KS8rGrW8sfiJWnirodd";
        FirebaseCollectionsController.getItemCollection(collection, item);
    }

    public void updateItemCollection(View view) {
        String collection = "-KS8eV84MtddBn9YxSUZ";
        String item = "-KS8rGgetj2zsPSapvw0";
        Item i = new Item("id" , "itemName", "urlIuhiuhujiomage", "item desc", 12.3, 324.4, "in your mom", 1.0, 1.1, "new");
        FirebaseCollectionsController.updateItem(collection,item, i);
    }


    public void deleteItem(View view) {
        String collection = "-KS8eV84MtddBn9YxSUZ";
        String item = "-KS8rGrW8sfiJWnirodd";
        FirebaseCollectionsController.deleteItem(collection, item);
    }





}
