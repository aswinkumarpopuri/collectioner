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
import com.mac.training.collectioner.R;
import com.mac.training.collectioner.activity.item.EditItemActivity;
import com.mac.training.collectioner.activity.item.ViewItemDetailActivity;
import com.mac.training.collectioner.FirebaseCollectionsController;
import com.mac.training.collectioner.adapter.helper.ItemTouchHelperAdapter;
import com.mac.training.collectioner.adapter.helper.ItemTouchHelperViewHolder;
import com.mac.training.collectioner.model.Item;

/**
 * Created by User on 9/20/2016.
 */
public class ItemAdapter extends FirebaseRecyclerAdapter<Item, ItemAdapter.ViewHolder>
        implements ItemTouchHelperAdapter {

    private Activity hostActivity;

    public ItemAdapter(Class<Item> modelClass, int modelLayout,
                       Class<ItemAdapter.ViewHolder> viewHolderClass, Query query,
                       Activity activity) {
        super(modelClass, modelLayout, viewHolderClass, query);
        this.hostActivity = activity;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Item item = getItem(position);
        holder.rlItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(hostActivity, hostActivity.getString(R.string.msg_open_item_detail, item.getName()), Toast.LENGTH_SHORT).show();
                openItemDetail(item);
            }
        });
        holder.rlItemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(hostActivity, hostActivity.getString(R.string.msg_edit_item, item.getName()), Toast.LENGTH_SHORT).show();
                editItem(position, item);
                return true;
            }
        });
        populateViewHolder(holder, item, position);
    }

    @Override
    protected void populateViewHolder(ViewHolder viewHolder, Item model, int position) {
        viewHolder.txtItem.setText(model.getName());
    }

    private void editItem(int position, Item item) {
        Intent intent = new Intent(hostActivity, EditItemActivity.class);
        intent.putExtra(EditItemActivity.class.getName(), item);
        this.hostActivity.startActivity(intent);
    }

    private void openItemDetail(Item item) {
        Intent intent = new Intent(hostActivity, ViewItemDetailActivity.class);
        intent.putExtra(ViewItemDetailActivity.class.getName(), item);
        this.hostActivity.startActivity(intent);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        final Item item = getItem(position);
        FirebaseCollectionsController.
                deleteItem(item.getItemId(), item.getItemId());
        notifyItemRemoved(position);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements
            ItemTouchHelperViewHolder {

        public RelativeLayout rlItemView;
        public ImageView ivItemView;
        public CardView cardViewItem;
        public TextView txtItem;

        public ViewHolder(View itemView) {
            super(itemView);
            rlItemView = ((RelativeLayout) itemView.findViewById(R.id.rlItemView));
            ivItemView = ((ImageView) itemView.findViewById(R.id.ivItem));
            cardViewItem = ((CardView) itemView.findViewById(R.id.cv_item));
            txtItem = ((TextView) itemView.findViewById(R.id.txtItem));
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
}
