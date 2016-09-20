package com.mac.training.collectioner.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mac.training.collectioner.R;
import com.mac.training.collectioner.activity.EditCollectionActivity;
import com.mac.training.collectioner.activity.ViewCollectionActivity;
import com.mac.training.collectioner.activity.ViewItemActivity;
import com.mac.training.collectioner.adapter.helper.ItemTouchHelperAdapter;
import com.mac.training.collectioner.adapter.helper.ItemTouchHelperViewHolder;
import com.mac.training.collectioner.model.Collection;

import java.util.List;

/**
 * Created by User on 9/19/2016.
 */
public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.ViewHolder>
        implements ItemTouchHelperAdapter {

    private List<Collection> collectionList;
    private Activity hostActivity;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder implements
            ItemTouchHelperViewHolder {
        // each data item is just a string in this case
        public RelativeLayout rlCollView;
        public ImageView ivCollection;
        public CardView cardViewCollection;

        public ViewHolder(View v) {
            super(v);
            rlCollView = ((RelativeLayout) v.findViewById(R.id.rlCollView));
            ivCollection = ((ImageView) v.findViewById(R.id.ivCollection));
            cardViewCollection = ((CardView) v.findViewById(R.id.card_view_collection));
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CollectionAdapter(List<Collection> collectionList, Activity activity) {
        this.collectionList = collectionList;
        this.hostActivity = activity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
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
    public void onBindViewHolder(final CollectionAdapter.ViewHolder holder,
                                 final int position) {
        final Collection collection = collectionList.get(position);
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.mTextView.setText(mDataset[position]);
        holder.rlCollView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(hostActivity, "open Items", Toast.LENGTH_SHORT).show();
                openItems(position, collection);
            }
        });
        holder.rlCollView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(hostActivity, "Edit", Toast.LENGTH_SHORT).show();
                editCollection(position, collection);
                return true;
            }


        });
    }

    private void editCollection(int position, Collection collection) {
        Intent intent = new Intent(hostActivity, EditCollectionActivity.class);
        intent.putExtra(EditCollectionActivity.class.getName(), collection);
        this.hostActivity.startActivity(intent);
    }

    private void removeCollection(Collection collection) {
        int position = collectionList.indexOf(collection);
        collectionList.remove(position);
        notifyItemRemoved(position);
    }

    private void openItems(int position, Collection collection) {
        Intent intent = new Intent(hostActivity, ViewItemActivity.class);
        intent.putExtra(ViewItemActivity.class.getName(), collection);
        this.hostActivity.startActivity(intent);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        // TODO: change for
        //return collectionList.size();
        return collectionList.size();
    }

    // Item Touch Helper
    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collection prev = collectionList.remove(fromPosition);
        collectionList.add(toPosition > fromPosition ? toPosition - 1 : toPosition, prev);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        collectionList.remove(position);
        notifyItemRemoved(position);
    }
}
