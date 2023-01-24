//package com.example.furniturestore;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//
//public class MyAddapter extends RecyclerView.Adapter<MyAddapter.MyViewHolder> {
//    Context context;
//    ArrayList<Product> list;
//
//    public MyAddapter(Context context, ArrayList<Product> list) {
//        this.context = context;
//        this.list = list;
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
//        return new MyViewHolder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        Product product = list.get(position);
//        holder.name.setText(product.getName_of_announcement());
////        int imgid=context.getResources().getIdentifier(book.Image,"drawable",context.getPackageName());
////        holder.image.setImageResource(product.getUrl_of_the_announcement_image());
//        holder.price.setText(product.getPrice_of_announcement());
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    public static class MyViewHolder extends RecyclerView.ViewHolder {
//        TextView name, price;
//        ImageView image;
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            name = (TextView) itemView.findViewById(R.id.namep);
//            image = (ImageView) itemView.findViewById(R.id.img);
//            price = (TextView) itemView.findViewById(R.id.price);
//        }
//    }
//}