//package com.example.furniturestore;
//
//import android.app.Activity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import java.util.List;
//
//public class ProductList extends ArrayAdapter<Product> {
//    private Activity context;
//    List<Product> products;
//    public TextView Name;
//    public ImageView Image;
//    public TextView Price;
//    public Button add;
//    public ProductList(Activity context, List<Product> products) {
//        super(context, R.layout.item, products);
//        this.context = context;
//        this.products = products;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        LayoutInflater inflater = context.getLayoutInflater();
//        View itemView = inflater.inflate(R.layout.item, null, true);
//        Name = (TextView) itemView.findViewById(R.id.namep);
//        Price = (TextView) itemView.findViewById(R.id.price);
//        Image = (ImageView) itemView.findViewById(R.id.img);
//        add = (Button) itemView.findViewById(R.id.add);
//        Product product = products.get(position);
//        Name.setText(product.getName_of_announcement());
//        Price.setText(product.getPrice_of_announcement());
//        return itemView;
//
//    }
//}