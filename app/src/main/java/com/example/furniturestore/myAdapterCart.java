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
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

public class myAdapterCart extends RecyclerView.Adapter<myAdapterCart.CartHolder> {
private Context context;
private List<Detail> producttList;
private ItemClickListener mItemListener;
private OnItemClickListener listener;

public myAdapterCart(Context context, List<Detail> studentList, myAdapterCart.ItemClickListener itemClickListener) {
        this.context = context;
        this.producttList = studentList;
        this.mItemListener=itemClickListener;
        }

    @Override
    public CartHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart, parent, false);
        return new CartHolder(row,listener);
    }

    @Override
    public void onBindViewHolder(CartHolder holder, int position) {
        final Detail product = producttList.get(position);
        holder.Name.setText(product.getName_of_announcement());
        holder.Price.setText(product.getPrice_of_announcement());
//        holder.num.setText(product.getNumber());
        Glide.with(holder.Image.getContext()).load(product.getUrl_of_the_announcement_image()).into(holder.Image);
        holder.itemView.setOnClickListener(view -> {
            mItemListener.onItemClick(product);
        });
//        holder.np.setMaxValue(100);
//        holder.np.setMinValue(0);
    }

    @Override
    public int getItemCount() {
        return producttList.size();
    }

    public interface ItemClickListener {
        void onItemClick(Detail d);
    }
    public interface OnItemClickListener {
        void onItemClicked(int position);
    }
    public void setOnItemClicked(OnItemClickListener clickListener){
    listener=clickListener;
    }

    class CartHolder extends RecyclerView.ViewHolder {
        TextView Name, Price,num;
        ImageView Image,del;
        NumberPicker np;

        public CartHolder(View itemView,OnItemClickListener listener) {
            super(itemView);
            del = (ImageView) itemView.findViewById(R.id.del);
            Name = (TextView) itemView.findViewById(R.id.name);
            Price = (TextView) itemView.findViewById(R.id.price);
            Image = (ImageView) itemView.findViewById(R.id.img);
//            np = (NumberPicker) itemView.findViewById(R.id.num);
            num=(TextView) itemView.findViewById(R.id.recqnt);
            del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClicked(getAbsoluteAdapterPosition());
                }
            });
        }
    }
}
