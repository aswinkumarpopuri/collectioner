package com.mac.training.collectioner;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.mac.training.collectioner.model.Collection;
import com.mac.training.collectioner.model.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class FirebaseCollectionsController {

    static private DatabaseReference mDatabase;
    //static ArrayList<Collection> users;

    static {
        mDatabase= FirebaseDatabase.getInstance().getReference();
    }

    static public void insertCollection(String user, String collectionName){
        String key = mDatabase.child(user).push().getKey();
        Collection c = new Collection(collectionName);
        Map<String, Object> cValues = c.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(user+"/"+key , cValues);
        mDatabase.updateChildren(childUpdates);
    }

    public static void deleteUserCollection(String user, String collectionName){
        mDatabase
                .child(user)
                .child(collectionName)
                .removeValue();
    }


    public static void getUserCollections(String user){


        Query recentPostsQuery = mDatabase
                .child(user)
                .limitToFirst(100);

        recentPostsQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Collection c = dataSnapshot.getValue(Collection.class);
                Log.d("wwedwhieud", c.collection);
                Log.d("wwedwhieud", dataSnapshot.getKey());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


///////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////



    static public void insertItem(String user, String collectionName, Item item){
        String key = mDatabase.child(user)
                .child(collectionName)
                .push().getKey();
        Map<String, Object> cValues = item.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(user+"/"+collectionName+"/items/"+key , cValues);
        mDatabase.updateChildren(childUpdates);
    }


    public static void deleteItem(String user, String collection, String item){
        mDatabase
                .child(user)
                .child(collection)
                .child("items")
                .child(item)
                .removeValue();
    }


    public static void getItemsCollection(String user, String collection) {

        Query recentPostsQuery = mDatabase
                .child(user)
                .child(collection)
                .child("items")
                .limitToFirst(100);

        recentPostsQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Item i = dataSnapshot.getValue(Item.class);
                Log.d("wwedwhieud", i.name);
                Log.d("wwedwhieud", dataSnapshot.getKey());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
