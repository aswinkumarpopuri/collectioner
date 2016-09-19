package com.mac.training.collectioner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.mac.training.collectioner.dummy.Dummy;
import com.mac.training.collectioner.model.Collection;

import java.util.HashMap;
import java.util.Map;

public class JosTest extends AppCompatActivity {

    private static final String TAG = "CollectionsView";

    private DatabaseReference mDatabase;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jos_test);

        mDatabase= FirebaseDatabase.getInstance().getReference();


        mDatabase = FirebaseDatabase.getInstance().getReference();

        getQuery(mDatabase, "Jonathan");

    }


    public void insertCategories(View view) {

        String user = Dummy.genUser();
        String collection = Dummy.genCollection();

        String key = mDatabase.child(user).push().getKey();

        //Item i = new Item("name", "description", 12.0, 15.0, "location", "used", "img-url");
        Collection c = new Collection(collection);

        Map<String, Object> cValues = c.toMap();
        Map<String, Object> childUpdates = new HashMap<>();

        childUpdates.put(user+"/"+key , cValues);

        mDatabase.updateChildren(childUpdates);
    }








    public void getQuery(DatabaseReference databaseReference, String user) {
        // [START recent_posts_query]
        // Last 100 posts, these are automatically the 100 most recent
        // due to sorting by push() keys
        Query recentPostsQuery = databaseReference.child(user)
                .limitToFirst(100);

        recentPostsQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Collection c = dataSnapshot.getValue(Collection.class);
                Log.d(TAG, c.collection);
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
