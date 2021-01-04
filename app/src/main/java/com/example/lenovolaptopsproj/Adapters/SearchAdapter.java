package com.example.lenovolaptopsproj.Adapters;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lenovolaptopsproj.R;
import com.example.lenovolaptopsproj.Models.ProductModel;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.viewHolder> {

    Context context;
    ArrayList<ProductModel> arrayList;

    public SearchAdapter(Context context, ArrayList<ProductModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public SearchAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_sample, viewGroup, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchAdapter.viewHolder holder, int position) {
        ProductModel t_search = arrayList.get(position);
        holder.name.setText(arrayList.get(position).getLaptop_name());
        holder.image.setImageURI(Uri.parse(
                "android.resource://" + context.getPackageName() +
                        "/drawable/" + t_search.getLaptop_imageRef()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView image;
        public viewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            image = itemView.findViewById(R.id.search_result_image);
        }
    }
}
