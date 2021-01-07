package com.example.lenovolaptopsproj.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lenovolaptopsproj.R;

import java.util.ArrayList;

public class SpecAdapter extends RecyclerView.Adapter<SpecAdapter.viewHolder> {
    Context context;
    ArrayList<ArrayList<String>> arrayList;

    public SpecAdapter(Context context, ArrayList<ArrayList<String>> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public SpecAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.laptop_specs_sample, viewGroup, false);
        return new viewHolder(view);
    }
    @Override
    public void onBindViewHolder(SpecAdapter.viewHolder holder, int position) {
        ArrayList<String> target = arrayList.get(position);
        holder.specType.setText(target.get(0));
        holder.specDetail.setText(target.get(1));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView specType;
        TextView specDetail;

        public viewHolder(View itemView) {
            super(itemView);
            specType = itemView.findViewById(R.id.spec_type);
            specDetail = itemView.findViewById(R.id.spec_detail);
        }
    }
}
