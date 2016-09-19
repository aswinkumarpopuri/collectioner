package com.mac.training.collectioner.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mac.training.collectioner.R;
import com.mac.training.collectioner.model.Collection;

import java.util.List;

/**
 * Created by User on 9/19/2016.
 */
public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.ViewHolder> {

    private List<Collection> collectionList;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;

        public ViewHolder(View v) {
            super(v);
            //mTextView = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CollectionAdapter(List<Collection> collectionList) {
        this.collectionList = collectionList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CollectionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.collection_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
        //...
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(CollectionAdapter.ViewHolder holder, int position) {
        Collection movie = collectionList.get(position);
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.mTextView.setText(mDataset[position]);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        // TODO: change for
        //return collectionList.size();
        return 1;
    }
}
