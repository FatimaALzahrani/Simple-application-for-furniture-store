package com.example.furniturestore;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class myadapterC extends RecyclerView.Adapter<myadapterC.ProductHolder> {
    private Context context;
    private List<Detail> producttList;
    private myadapterC.ItemClickListener mItemListener;
    private myadapterC.OnItemClickListener listener;
    private myadapterC.OnItemClickListener2 listener2;
    private myadapterC.OnItemClickListener3 listener3;

    public myadapterC(Context context, List<Detail> prodList, myadapterC.ItemClickListener itemClickListener) {
        this.context = context;
        this.producttList = prodList;
        this.mItemListener=itemClickListener;
    }

    @Override
    public myadapterC.ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new myadapterC.ProductHolder(row,listener,listener2);
    }

    @Override
    public void onBindViewHolder(myadapterC.ProductHolder holder, int position) {
        final Detail product = producttList.get(position);
        holder.Name.setText(product.getName_of_announcement());
        holder.Price.setText(product.getPrice_of_announcement());
        holder.num.setText(product.getNumber());
        Glide.with(holder.Image.getContext()).load(product.getUrl_of_the_announcement_image()).into(holder.Image);
        holder.itemView.setOnClickListener(view -> {
            mItemListener.onItemClick(product);
        });
    }

    @Override
    public int getItemCount() {
        return producttList.size();
    }
    public interface ItemClickListener{
        void onItemClick(Detail d);
    }
    public interface OnItemClickListener {
        void onItemClicked(int position);
    }
    public void setOnItemClicked(myadapterC.OnItemClickListener clickListener){
        listener=clickListener;
    }
    public interface OnItemClickListener2 {
        void onItemClicked2(int position);
    }
    public void setOnItemClicked2(myadapterC.OnItemClickListener2 clickListener){
        listener2=clickListener;
    }
    public interface OnItemClickListener3 {
        void onItemClicked3(int position);
    }
    public void setOnItemClicked3(myadapterC.OnItemClickListener3 clickListener){
        listener3=clickListener;
    }

    class ProductHolder extends RecyclerView.ViewHolder {
        TextView Name, Price;
        ImageView Image,dec,inc,del;
        TextView num;
        public ProductHolder(View itemView, myadapterC.OnItemClickListener listener, myadapterC.OnItemClickListener2 listener2) {
            super(itemView);
            Name = (TextView) itemView.findViewById(R.id.name);
            Price = (TextView) itemView.findViewById(R.id.price);
            Image = (ImageView) itemView.findViewById(R.id.img);
            dec = (ImageView) itemView.findViewById(R.id.decbtn);
            inc = (ImageView) itemView.findViewById(R.id.incbtn);
            del = (ImageView) itemView.findViewById(R.id.del);
            num = (TextView) itemView.findViewById(R.id.num);
            del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myadapterC.this.listener.onItemClicked(getAbsoluteAdapterPosition());
                }
            });
            dec.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myadapterC.this.listener2.onItemClicked2(getAbsoluteAdapterPosition());
                }
            });
            inc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myadapterC.this.listener3.onItemClicked3(getAbsoluteAdapterPosition());
                }
            });
        }
    }
}