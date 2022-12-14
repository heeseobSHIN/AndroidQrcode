package com.example.androidfinalprojectqrcode;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GetDbAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    ArrayList<String> list;

    GetDbAdapter(ArrayList<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.getdb_activity, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        holder.tv.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

 class Holder extends RecyclerView.ViewHolder {
    ConstraintLayout tv;

    public Holder(@NonNull View itemView) {
        super(itemView);
        tv = itemView.findViewById(R.id.buttonLayout);
    }
}