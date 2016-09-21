package com.mac.training.collectioner;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mac.training.collectioner.model.Collection;
import com.mac.training.collectioner.model.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FirebaseCollectionsController {

    static private DatabaseReference mDatabase;
    static ArrayList<Collection> collections = new ArrayList<>();
    static ArrayList<Item> items = new ArrayList<>();

    static {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }


    //COLLECTIONS////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////C


    public static void insertCollection(String user, String collectionName, String description) {
        String key = mDatabase.child(user).push().getKey();

        Collection c = new Collection();
        c.setColId(key);
        c.setCollectionName(collectionName);
        c.setDescription(description);

        Map<String, Object> cValues = c.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("users/" + user + "/" + key, cValues);
        mDatabase.updateChildren(childUpdates);
    }

    public static void deleteUserCollection(String user, String collectionName) {
        mDatabase
                .child("users")
                .child(user)
                .child(collectionName)
                .removeValue();
        mDatabase
                .child("collections")
                .child(collectionName)
                .removeValue();
    }

    public static void getUserCollections(String user) {

        collections.clear();

        Query recentPostsQuery = mDatabase
                .child("users")
                .child(user)
                .limitToFirst(100);

        recentPostsQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Collection c = dataSnapshot.getValue(Collection.class);
                if (c != null) {
                    collections.add(c);
                    Log.d("Collection: ", c.getCollectionName());
                    Log.d("Key: ", dataSnapshot.getKey());
                }
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

    public static void getUserCollection(String user, String collection) {

        Query recentPostsQuery = mDatabase
                .child("users")
                .child(user)
                .child(collection);


        recentPostsQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Collection c = dataSnapshot.getValue(Collection.class);
                if (c != null)
                    Log.d("hdwieudhaksj", c.getCollectionName());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public static void updateCollection(String userKey, String collectionId,
                                        String collectionName, String collectionDescription) {
        Collection c = new Collection();
        c.setColId(collectionId);
        c.setCollectionName(collectionName);
        c.setDescription(collectionDescription);

        Map<String, Object> cValues = c.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("users/" + userKey + "/" + collectionId, cValues);
        mDatabase.updateChildren(childUpdates);
    }


///////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////


    public static void insertItem(String collectionkey, Item item) {
        String key = mDatabase.child("collections")
                .child(collectionkey)
                .push().getKey();
        Map<String, Object> cValues = item.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("collections/" + collectionkey + "/" + key, cValues);
        mDatabase.updateChildren(childUpdates);
    }


    public static void getItemsCollection(String collection) {
        Query recentPostsQuery = mDatabase
                .child("collections")
                .child(collection)
                .limitToFirst(100);

        recentPostsQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Item i = dataSnapshot.getValue(Item.class);
                Log.d("wwedwhieud", i.getName());
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


    public static void getItemCollection(String collection, String item) {

        Query recentPostsQuery = mDatabase
                .child("collections")
                .child(collection)
                .child(item);


        recentPostsQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Item c = dataSnapshot.getValue(Item.class);
                if (c != null) {
                    Log.d("hdwieudhaksj", c.getName());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    static public void updateItem(String collectionKey, String itemKey, Item item) {
        Map<String, Object> cValues = item.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("collections/" + collectionKey + "/" + itemKey, cValues);
        mDatabase.updateChildren(childUpdates);
    }


    public static void deleteItem(String collectionKey, String itemKey) {
        mDatabase
                .child("collections")
                .child(collectionKey)
                .child(itemKey)
                .removeValue();
    }


}
