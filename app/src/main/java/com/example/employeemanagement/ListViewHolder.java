package com.example.employeemanagement;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListViewHolder extends RecyclerView.ViewHolder {

    public TextView name, number;
    public ListViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.cardName);
        number = itemView.findViewById(R.id.cardNumber);
    }
}
