package com.example.lenovolaptopsproj;

import android.content.Context;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.viewHolder> {

    Context context;
    ArrayList<itemModel> arrayList;

    public ServicesAdapter(Context context, ArrayList<itemModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public  ServicesAdapter.viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_sample, viewGroup, false);
        return new viewHolder(view);
    }
    @Override
    public  void onBindViewHolder(ServicesAdapter.viewHolder holder, int position) {
        holder.service.setText(arrayList.get(position).getService());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView name, service, price, rating;

        public viewHolder(View itemView) {
            super(itemView);
            service = (TextView) itemView.findViewById(R.id.service);

        }
    }
}
