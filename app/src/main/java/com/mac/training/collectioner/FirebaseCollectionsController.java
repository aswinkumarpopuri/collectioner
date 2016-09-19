package com.mac.training.collectioner;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;


public class FirebaseCollectionsController {

    static private DatabaseReference mDatabase;
    static ArrayList<String> users;



    static {
        mDatabase= FirebaseDatabase.getInstance().getReference();
    }



    public static ArrayList<String> getUsers(){


        Query recentPostsQuery = mDatabase
                .limitToFirst(100);

         users = new ArrayList<>();

        recentPostsQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String c = dataSnapshot.getValue(String.class);
                users.add(c);
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

        return users;
    }



}
