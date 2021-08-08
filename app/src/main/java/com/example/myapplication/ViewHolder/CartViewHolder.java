package com.example.myapplication.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Interface.ItemClickListner;
import com.example.myapplication.R;

import org.jetbrains.annotations.NotNull;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtProductName, txtProductPrice;
    private ItemClickListner itemClickListner;

    public CartViewHolder(@NonNull @NotNull View itemView)
    {
        super(itemView);
        txtProductName = itemView.findViewById(R.id.cart_product_name);
        txtProductPrice = itemView.findViewById(R.id.cart_product_price);
    }

    @Override
    public void onClick(View v)
    {
        itemClickListner.onclick(v,getAdapterPosition(),false);
    }

    public void setItemClickListner(ItemClickListner itemClickListner) {
        this.itemClickListner = itemClickListner;
    }
}
