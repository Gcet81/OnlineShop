package com.example.onlineshop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.BasketViewHolder> {

    private List<BasketItem> basketItems;

    public BasketAdapter(List<BasketItem> basketItems) {
        this.basketItems = basketItems;
    }

    @NonNull
    @Override
    public BasketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.basket_item, parent, false);
        return new BasketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BasketViewHolder holder, int position) {
        BasketItem item = basketItems.get(position);
        holder.shoeName.setText(item.getShoe().getName());
        holder.shoeSize.setText("Size: " + item.getShoe().getSizes()[0]);
        holder.shoePrice.setText("Price: " + item.getShoe().getPrice() + " OMR");
        holder.quantity.setText("Quantity: " + item.getQuantity());

        // Calculate the total for this item
        double totalForItem = item.getShoe().getPrice() * item.getQuantity();
        holder.totalAmountTextView.setText(String.format("Total for Item: %.2f OMR", totalForItem));

        holder.shoeImage.setImageResource(item.getShoe().getImageResourceId());
    }

    @Override
    public int getItemCount() {
        return basketItems.size();
    }

    static class BasketViewHolder extends RecyclerView.ViewHolder {
        TextView shoeName;
        TextView shoeSize;
        TextView shoePrice;
        TextView quantity;
        ImageView shoeImage;
        TextView totalAmountTextView;  // TextView to display the total amount for the item

        public BasketViewHolder(@NonNull View itemView) {
            super(itemView);
            shoeName = itemView.findViewById(R.id.shoeName);
            shoeSize = itemView.findViewById(R.id.shoeSize);
            shoePrice = itemView.findViewById(R.id.shoePrice);
            quantity = itemView.findViewById(R.id.quantity);
            shoeImage = itemView.findViewById(R.id.shoeImage);
            totalAmountTextView = itemView.findViewById(R.id.totalAmountTextView);  // Make sure this ID exists in your layout
        }
    }
}
