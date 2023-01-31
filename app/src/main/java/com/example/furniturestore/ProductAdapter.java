package com.example.furniturestore;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {
    private Context context;
    private List<Product> producttList;
    private ItemClickListener mItemListener;
    private OnItemClickListener listener;
    private OnItemClickListener2 listener2;
    private OnItemClickListener3 listener3;

    public ProductAdapter(Context context, List<Product> studentList, ProductAdapter.ItemClickListener itemClickListener) {
        this.context = context;
        this.producttList = studentList;
        this.mItemListener=itemClickListener;
    }

    @Override
    public ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ProductHolder(row,listener,listener2);
    }

    @Override
    public void onBindViewHolder(ProductHolder holder, int position) {
        final Product product = producttList.get(position);
        holder.Name.setText(product.getName_of_announcement());
        holder.Price.setText(product.getPrice_of_announcement());
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
        void onItemClick(Product d);
    }
    public interface OnItemClickListener {
        void onItemClicked(int position);
    }
    public void setOnItemClicked(OnItemClickListener clickListener){
        listener=clickListener;
    }
    public interface OnItemClickListener2 {
        void onItemClicked2(int position);
    }
    public void setOnItemClicked2(OnItemClickListener2 clickListener){
        listener2=clickListener;
    }
    public interface OnItemClickListener3 {
        void onItemClicked3(int position);
    }
    public void setOnItemClicked3(OnItemClickListener3 clickListener){
        listener3=clickListener;
    }

    class ProductHolder extends RecyclerView.ViewHolder {
        TextView Name, Price;
        ImageView Image,Fav,Call;
        TextView Add;
        public ProductHolder(View itemView, OnItemClickListener listener, OnItemClickListener2 listener2) {
            super(itemView);
            Name = (TextView) itemView.findViewById(R.id.namep);
            Price = (TextView) itemView.findViewById(R.id.price);
            Image = (ImageView) itemView.findViewById(R.id.img);
            Fav = (ImageView) itemView.findViewById(R.id.fav);
            Call = (ImageView) itemView.findViewById(R.id.call);
            Add = (TextView) itemView.findViewById(R.id.add);
            Add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ProductAdapter.this.listener.onItemClicked(getAbsoluteAdapterPosition());
                }
            });
            Fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ProductAdapter.this.listener2.onItemClicked2(getAbsoluteAdapterPosition());
                }
            });
            Call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ProductAdapter.this.listener3.onItemClicked3(getAbsoluteAdapterPosition());
                }
            });
        }
    }
}