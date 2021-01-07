package com.example.lenovolaptopsproj.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lenovolaptopsproj.Pages.LaptopPage;
import com.example.lenovolaptopsproj.R;
import com.example.lenovolaptopsproj.Models.ProductModel;

import java.util.ArrayList;

import static com.example.lenovolaptopsproj.Controllers.ProductController.allProducts;

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
        ProductModel target = arrayList.get(position);
        final String targetName = target.getLaptop_name();
        holder.name.setText(targetName);
        holder.image.setImageURI(Uri.parse(
                "android.resource://" + context.getPackageName() +
                        "/drawable/" + target.getLaptop_imageRef()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            for (int i = 0; i < allProducts.size(); i++) {
                if(allProducts.get(i).getLaptop_name().equalsIgnoreCase(targetName)){
                    Intent n_intent = new Intent(context, LaptopPage.class);
                    n_intent.putExtra("laptopID", i);
                    n_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(n_intent);
                    break;
                }
            }
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
