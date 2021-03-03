package com.volchkov.notesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<String> mData1;
    private List<String> mData2;
    private List<String> mData3;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;


    // data is passed into the constructor
    Adapter(Context context, List<String> data1, List<String> data2, List<String> data3) {
        this.mInflater = LayoutInflater.from(context);
        this.mData1 = data1;
        this.mData2 = data2;
        this.mData3 = data3;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String title = mData1.get(position);
        String description = mData2.get(position);
        String date = mData3.get(position);
        holder.myTextView1.setText(title);
        holder.myTextView2.setText(description);
        holder.myTextView3.setText(date);


        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                mData1.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, mData1.size());

            }
        });


    }



        // total number of rows
    @Override
    public int getItemCount() {
        return mData1.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView1;
        TextView myTextView2;
        TextView myTextView3;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView1 = itemView.findViewById(R.id.title);
            myTextView2 = itemView.findViewById(R.id.description);
            myTextView3 = itemView.findViewById(R.id.time);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return mData1.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}