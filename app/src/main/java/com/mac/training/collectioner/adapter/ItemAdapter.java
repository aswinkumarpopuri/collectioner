package com.mac.training.collectioner.adapter;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mac.training.collectioner.R;
import com.mac.training.collectioner.adapter.helper.ItemTouchHelperAdapter;
import com.mac.training.collectioner.adapter.helper.ItemTouchHelperViewHolder;
import com.mac.training.collectioner.model.Collection;
import com.mac.training.collectioner.model.Item;

import java.util.List;

/**
 * Created by User on 9/20/2016.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>
        implements ItemTouchHelperAdapter {

    private List<Item> itemList;
    private Activity hostActivity;

    public ItemAdapter(List<Item> itemList, Activity activity) {
        this.itemList = itemList;
        this.hostActivity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Item item = itemList.get(position);
        holder.rlItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(hostActivity, "show detail", Toast.LENGTH_SHORT).show();
                openItemDetail(item);
            }
        });
        holder.rlItemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(hostActivity, "edit Item", Toast.LENGTH_SHORT).show();
                editItem(position, item);
                return true;
            }
        });
    }

    private void editItem(int position, Item item) {
    }

    private void openItemDetail(Item item) {
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {

    }

    @Override
    public void onItemDismiss(int position) {

    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements
            ItemTouchHelperViewHolder {

        public RelativeLayout rlItemView;
        public ImageView ivItemView;
        public CardView cardViewItem;

        public ViewHolder(View itemView) {
            super(itemView);
            rlItemView = ((RelativeLayout) itemView.findViewById(R.id.rlItemView));
            ivItemView = ((ImageView) itemView.findViewById(R.id.ivItem));
            cardViewItem = ((CardView) itemView.findViewById(R.id.cv_item));
        }

        @Override
        public void onItemSelected() {

        }

        @Override
        public void onItemClear() {

        }
    }
}
