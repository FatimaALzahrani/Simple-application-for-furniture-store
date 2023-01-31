package com.example.furniturestore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.google.firebase.database.annotations.NotNull;

import java.util.List;

public class myadapter2 extends RecyclerView.Adapter<myadapter2.myviewholder>{
    List<Detail> products;
    TextView rateview;
    private ItemClickListener mItemListener;
    private OnItemClickListener listener;
    private Context context;
    public myadapter2( Context context,List<Detail> products, myadapter2.ItemClickListener itemClickListener) {
        this.products = products;
        this.context= context;
        this.mItemListener=itemClickListener;
    }

    @NonNull
    @NotNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(myviewholder holder, int position) {
        final Detail product = products.get(position);
        holder.Name.setText(product.getName_of_announcement());
        holder.Price.setText(product.getPrice_of_announcement());
//        holder.num.setText(product.getNumber());
        Glide.with(holder.Image.getContext()).load(product.getUrl_of_the_announcement_image()).into(holder.Image);
        holder.itemView.setOnClickListener(view -> {
            mItemListener.onItemClick(product);
        });
//        holder.delbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int result= DB.delete(product.getName_of_announcement());
////                setContentView(R.layout.activity_new_book);
//            }
//        });
//        holder.incr.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                int qnt=products.get(position).getQnt();
////                qnt++;
////                products.get(position).setQnt(qnt);
////                notifyDataSetChanged();
////                updateprice();
//            }
//        });
//
//        holder.decr.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                int qnt=products.get(position).getQnt();
////                qnt--;
////                products.get(position).setQnt(qnt);
////                notifyDataSetChanged();
////                updateprice();
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public interface ItemClickListener {
        void onItemClick(Detail d);
    }
    public interface OnItemClickListener {
        void onItemClicked(int position);
    }

    class myviewholder extends RecyclerView.ViewHolder{
        TextView Name,Price;
        ImageView incr,decr,delbtn,Image;

        public myviewholder(@NonNull @NotNull View itemView) {
            super(itemView);

            Name=(TextView) itemView.findViewById(R.id.name);
            Price=(TextView) itemView.findViewById(R.id.price);
            Image=(ImageView) itemView.findViewById(R.id.img);
            delbtn=itemView.findViewById(R.id.del);
            incr=itemView.findViewById(R.id.incbtn);
            decr=itemView.findViewById(R.id.decbtn);
            delbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClicked(getAbsoluteAdapterPosition());
                }
            });
            incr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClicked(getAbsoluteAdapterPosition());
                }
            });
            decr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClicked(getAbsoluteAdapterPosition());
                }
            });
        }
    }

//    public void updateprice(){
//        int sum=0,i;
//        for(i=0;i< products.size();i++)
//            sum=sum+(products.get(i).getPrice()*products.get(i).getQnt());
//
//        rateview.setText("Total Amount : INR "+sum);
//    }

}
