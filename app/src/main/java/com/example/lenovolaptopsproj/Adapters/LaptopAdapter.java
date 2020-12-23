package com.example.lenovolaptopsproj.Adapters;

import android.content.Context;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lenovolaptopsproj.R;
import com.example.lenovolaptopsproj.Models.ProductModel;

import java.util.ArrayList;

public class LaptopAdapter extends RecyclerView.Adapter<LaptopAdapter.viewHolder> {

    Context context;
    ArrayList<ProductModel> arrayList;

    public LaptopAdapter(Context context, ArrayList<ProductModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public LaptopAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_sample, viewGroup, false);
        return new viewHolder(view);
    }
    @Override
    public void onBindViewHolder(LaptopAdapter.viewHolder holder, int position) {
        holder.name.setText(arrayList.get(position).getLaptop_name());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView name;

        public viewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
        }
    }
}
