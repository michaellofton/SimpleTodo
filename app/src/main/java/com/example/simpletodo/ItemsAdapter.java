package com.example.simpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Responsible for displaying data from the model into a row in the recycler view
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder>{

    public interface OnClickListener {
        void onItemClicked(int position);
    }

    public interface OnLongClickListener {
        void onItemLongClicked(int position);
    }

    private List<String> items;
    private OnLongClickListener onLongClickListener;
    private OnClickListener clickListener;

    public ItemsAdapter(List<String> items, OnLongClickListener onLongClickListener, OnClickListener onClickListener) {
        this.items = items;
        this.onLongClickListener = onLongClickListener;
        this.clickListener = onClickListener;
    }

    //Creating each view
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Use layout inflater to inflate a view
        View todoView = LayoutInflater
                .from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);

        // wrap it inside a View Holder and return it
        return new ViewHolder(todoView);
    }

    // Takes data from a particular position and puts it in a view holder
    // responsible for binding data to a particular view holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Grab the item at the position
        String item = items.get(position);
        // Bind the item into the specified view holder
        holder.bind(item);
    }

    // Returns the number of items available in the data
    // Tells the recycler view how many items are in the list
    @Override
    public int getItemCount() {
        return items.size();
    }


    // Container to provide easy access to views that represent each row of the list
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvItem;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem =  itemView.findViewById(android.R.id.text1);
        }

        // Update the view inside of the view holder with this data
        void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClicked(getAdapterPosition());
                }
            });

            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    // Notify the listener which position was long pressed
                    onLongClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });
        }

    }



}
