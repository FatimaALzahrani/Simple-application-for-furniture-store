//package com.example.furniturestore;
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//import java.util.List;
//public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
//    Context context;
//    List<Product> MainImageUploadInfoList;
//    public RecyclerViewAdapter(Context context, List<Product> TempList) {
//        this.MainImageUploadInfoList = TempList;
//        this.context = context;
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
//        ViewHolder viewHolder = new ViewHolder(view);
//        return viewHolder;
//    }
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        Product product = MainImageUploadInfoList.get(position);
//        holder.Name.setText(product.getName_of_announcement());
//        holder.Price.setText(product.getPrice_of_announcement());
//    }
//    @Override
//    public int getItemCount() {
//        return MainImageUploadInfoList.size();
//    }
//    class ViewHolder extends RecyclerView.ViewHolder {
//        public TextView Name;
//        public ImageView Image;
//        public TextView Price;
//        public Button add;
//        public ViewHolder(View itemView) {
//            super(itemView);
//            Name = (TextView) itemView.findViewById(R.id.namep);
//            Price = (TextView) itemView.findViewById(R.id.price);
//            Image = (ImageView) itemView.findViewById(R.id.img);
//            add = (Button) itemView.findViewById(R.id.add);
//        }
//    }
//}