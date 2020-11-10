package com.example.recyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    private TextView view;
    public RecyclerViewHolder(@NonNull View itemView){
        super(itemView);
        view = itemView.findViewById(R.id.word);
    }

    public TextView getView(){
        return view;
    }
}
