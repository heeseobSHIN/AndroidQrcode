package com.example.androidfinalprojectqrcode;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GetDbAdapter extends RecyclerView.Adapter<GetDbAdapter.ViewHolder> {
    private ArrayList<ButtonListitem> mFriendList;
    Context mContext;
    ArrayList<ButtonListitem> items = new ArrayList<>();

    public GetDbAdapter(Context mContext){
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_button, parent, false);
        return new ViewHolder(view);
//        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View itemView = inflater.inflate(R.layout.item_recycler_button, parent, false);
//        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(mFriendList.get(position));
        ButtonListitem item = items.get(position);
        //holder.setItem(item);
    }
//    public static void setButtonList(ArrayList<ButtonListitem> list){
//        this.mFriendList = list;
//        notifyDataSetChanged();
//    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(ButtonListitem item){
        items.add(item);
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        Button button_tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            button_tv = (Button) itemView.findViewById(R.id.getDbBtn);

            // 아이템 클릭 이벤트 처리.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Intent intent = new Intent(getInfotoDBActivity.this, showhistory.class);

                }
            });

        }

        void onBind(ButtonListitem item){
            button_tv.setText(item.date.toString());
        }
    }

}

