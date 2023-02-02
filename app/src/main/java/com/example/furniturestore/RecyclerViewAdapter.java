package com.example.furniturestore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    // creating a variable for our array list and context.
    private ArrayList<Detail> courseDataArrayList;
    private Context mcontext;
    private ItemClickListener mItemListener;
    private OnItemClickListener listener;

    // creating a constructor class.
    public RecyclerViewAdapter(ArrayList<Detail> recyclerDataArrayList, Context mcontext, ItemClickListener itemClickListener) {
        this.courseDataArrayList = recyclerDataArrayList;
        this.mcontext = mcontext;
        this.mItemListener = itemClickListener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav, parent, false);
        return new RecyclerViewHolder(row, listener);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        // Set the data to textview from our modal class.
        Detail product = courseDataArrayList.get(position);
        holder.Name.setText(product.getName_of_announcement());
        holder.Price.setText(product.getPrice_of_announcement());
//        holder.num.setText(product.getNumber());
        Glide.with(holder.Image.getContext()).load(product.getUrl_of_the_announcement_image()).into(holder.Image);
        holder.itemView.setOnClickListener(view -> {
            mItemListener.onItemClick(product);
        });
    }

    @Override
    public int getItemCount() {
        // this method returns
        // the size of recyclerview
        return courseDataArrayList.size();
    }

    public interface ItemClickListener {
        void onItemClick(Detail d);
    }

    public interface OnItemClickListener {
        void onItemClicked(int position);
    }

    public void setOnItemClicked(OnItemClickListener clickListener) {
        listener = clickListener;
    }


    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        // creating a variable for our text view.
        TextView Name, Price, num;
        ImageView Image, del;
        NumberPicker np;

        public RecyclerViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            // initializing our text views.
//            del = (ImageView) itemView.findViewById(R.id.del);
            Name = (TextView) itemView.findViewById(R.id.name);
            Price = (TextView) itemView.findViewById(R.id.price);
            Image = (ImageView) itemView.findViewById(R.id.img);
//            np = (NumberPicker) itemView.findViewById(R.id.num);
            num = (TextView) itemView.findViewById(R.id.recqnt);
//            del.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    listener.onItemClicked(getAbsoluteAdapterPosition());
//                }
//            });
        }


    }
}

