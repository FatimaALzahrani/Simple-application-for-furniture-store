package com.example.furniturestore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myadapter extends FirebaseRecyclerAdapter<Product,myadapter.myvewholder> {
    private ItemClickListener mItemListener;
    public myadapter(@NonNull FirebaseRecyclerOptions<Product> options,ItemClickListener itemClickListener){//, RecyclerViewInterface recyclerViewInterface) {
        super(options);
        this.mItemListener=itemClickListener;
//        this.recyclerViewInterface = recyclerViewInterface;
    }
//    private final RecyclerViewInterface recyclerViewInterface;
    @Override
    protected void onBindViewHolder(@NonNull myvewholder holder, int position, @NonNull Product model) {
        holder.name.setText(model.getName_of_announcement());
        holder.price.setText(model.getPrice_of_announcement());
        Glide.with(holder.img.getContext()).load(model.getUrl_of_the_announcement_image()).into(holder.img);
        holder.itemView.setOnClickListener(view -> {
            mItemListener.onItemClick(model);
        });
    }

    @NonNull
    @Override
    public myvewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
       return new myvewholder(view);
    }
    public interface ItemClickListener{
        void onItemClick(Product product);
    }

    class myvewholder extends RecyclerView.ViewHolder{
    ImageView img;
    TextView name,price;
    public myvewholder(View itemView){
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.namep);
        img = (ImageView) itemView.findViewById(R.id.img);
        price = (TextView) itemView.findViewById(R.id.price);
    }
}
}
