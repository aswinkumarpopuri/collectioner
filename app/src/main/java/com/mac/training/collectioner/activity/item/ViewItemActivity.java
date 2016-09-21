package com.mac.training.collectioner.activity.item;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.mac.training.collectioner.R;
import com.mac.training.collectioner.activity.collection.EditCollectionActivity;
import com.mac.training.collectioner.adapter.CollectionAdapter;
import com.mac.training.collectioner.adapter.ItemAdapter;
import com.mac.training.collectioner.adapter.helper.SimpleItemTouchHelperCallback;
import com.mac.training.collectioner.model.Collection;
import com.mac.training.collectioner.model.Item;

import java.util.ArrayList;
import java.util.List;

public class ViewItemActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ItemAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ItemTouchHelper mItemTouchHelper;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);

        Collection collection = getIntent().getParcelableExtra(ViewItemActivity.class.getName());

        mRecyclerView = (RecyclerView) findViewById(R.id.rvItem);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        Query getAllItemsByCollection = mDatabase
                .child("collections")
                .child(collection.getColId())
                .limitToFirst(100);

        mAdapter = new ItemAdapter(Item.class, R.layout.item_view,
                ItemAdapter.ViewHolder.class, getAllItemsByCollection, this);
        mRecyclerView.setAdapter(mAdapter);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);

    }

    // Add collection
    public void addItem(View view) {
        startActivity(new Intent(this, AddItemActivity.class));
    }

}
