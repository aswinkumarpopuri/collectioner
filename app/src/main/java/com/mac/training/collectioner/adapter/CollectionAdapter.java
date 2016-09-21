package com.mac.training.collectioner.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;
import com.mac.training.collectioner.FirebaseCollectionsController;
import com.mac.training.collectioner.activity.collection.ViewCollectionActivity;

import com.mac.training.collectioner.R;
import com.mac.training.collectioner.activity.collection.EditCollectionActivity;
import com.mac.training.collectioner.activity.item.ViewItemActivity;
import com.mac.training.collectioner.adapter.helper.ItemTouchHelperAdapter;
import com.mac.training.collectioner.adapter.helper.ItemTouchHelperViewHolder;
import com.mac.training.collectioner.model.Collection;

/**
 * Created by User on 9/19/2016.
 */
public class CollectionAdapter extends FirebaseRecyclerAdapter<Collection, CollectionAdapter.ViewHolder>
        implements ItemTouchHelperAdapter {

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
        public TextView collectionName;
        public TextView collectionDescription;

        public ViewHolder(View v) {
            super(v);
            rlCollView = ((RelativeLayout) v.findViewById(R.id.rlCollView));
            ivCollection = ((ImageView) v.findViewById(R.id.ivCollection));
            cardViewCollection = ((CardView) v.findViewById(R.id.card_view_collection));
            collectionName = ((TextView) v.findViewById(R.id.CollectionName));
            collectionDescription = ((TextView) v.findViewById(R.id.CollectionDesc));
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
    public CollectionAdapter(Class<Collection> modelClass, int modelLayout,
                             Class<ViewHolder> viewHolderClass, Query query,
                             Activity activity) {
        super(modelClass, modelLayout, viewHolderClass, query);
        this.hostActivity = activity;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final CollectionAdapter.ViewHolder holder,
                                 final int position) {
        final Collection collection = getItem(position);
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.mTextView.setText(mDataset[position]);
        holder.rlCollView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(hostActivity, hostActivity.getString(R.string.msg_open_items), Toast.LENGTH_SHORT).show();
                openItems(collection);
            }
        });
        holder.rlCollView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(hostActivity, hostActivity.getString(R.string.msg_edit_collection, collection.getCollectionName()), Toast.LENGTH_SHORT).show();
                editCollection(collection);
                return true;
            }


        });
        populateViewHolder(holder, collection, position);
    }

    private void editCollection(Collection collection) {
        Intent intent = new Intent(hostActivity, EditCollectionActivity.class);
        intent.putExtra(EditCollectionActivity.class.getName(), collection);
        this.hostActivity.startActivity(intent);
    }

    private void openItems(Collection collection) {
        Intent intent = new Intent(hostActivity, ViewItemActivity.class);
        intent.putExtra(ViewItemActivity.class.getName(), collection);
        this.hostActivity.startActivity(intent);
    }

    // Item Touch Helper
    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        final Collection collection = getItem(position);

        FirebaseCollectionsController.
                deleteUserCollection(
                        ((ViewCollectionActivity)hostActivity).getUser().getUid(),
                            collection.getColId());
        notifyItemRemoved(position);
    }


    @Override
    protected void populateViewHolder(ViewHolder viewHolder, Collection model, int position) {
        viewHolder.collectionName.setText(model.getCollectionName());
        viewHolder.collectionDescription.setText(model.getDescription());
    }
}
