package com.mac.training.collectioner.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.ViewGroup;

import com.mac.training.collectioner.R;
import com.mac.training.collectioner.adapter.CollectionAdapter;
import com.mac.training.collectioner.adapter.helper.SimpleItemTouchHelperCallback;
import com.mac.training.collectioner.model.Collection;

import java.util.ArrayList;
import java.util.List;

public class ViewCollectionActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private CollectionAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_collection);

        mRecyclerView = (RecyclerView) findViewById(R.id.rvCollection);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // specify an adapter (see also next example)
        List<Collection> collectionList = new ArrayList<Collection>();
        collectionList.add(new Collection());
        collectionList.add(new Collection());
        mAdapter = new CollectionAdapter(collectionList, this);
        mRecyclerView.setAdapter(mAdapter);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);

    }

    // Add collection
    public void addCollection(View view) {
        startActivity(new Intent(this, AddCollectionActivity.class));
    }
}
