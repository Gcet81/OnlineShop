package com.example.onlineshop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShoeAdapter extends RecyclerView.Adapter<ShoeAdapter.ShoeViewHolder> {

    private List<Shoe> shoes;

    public ShoeAdapter(List<Shoe> shoes) {
        this.shoes = shoes;
    }

    @NonNull
    @Override
    public ShoeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shoe_item, parent, false);
        return new ShoeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoeViewHolder holder, int position) {
        final Shoe shoe = shoes.get(position);
        holder.shoeName.setText(shoe.getName());
        holder.shoeImage.setImageResource(shoe.getImageResourceId());
        holder.shoePrice.setText("Price: " + shoe.getPrice() + " OMR");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(holder.itemView.getContext(),
                android.R.layout.simple_spinner_dropdown_item, shoe.getSizes());
        holder.shoeSize.setAdapter(adapter);

        holder.addToBasketButton.setOnClickListener(v -> {
            String selectedSize = (String) holder.shoeSize.getSelectedItem();
            BasketManager.getInstance().addItemToBasket(new Shoe(shoe.getName(), shoe.getImageResourceId(), new String[]{selectedSize}, shoe.getPrice()), 1);
            Toast.makeText(holder.itemView.getContext(), "Added " + shoe.getName() + " to basket!", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return shoes.size();
    }

    // Method to update the data in the adapter
    public void updateData(List<Shoe> newShoes) {
        this.shoes = newShoes;
        notifyDataSetChanged(); // Notify the adapter that the data has changed
    }

    static class ShoeViewHolder extends RecyclerView.ViewHolder {
        TextView shoeName;
        TextView shoePrice; // TextView for showing the price
        ImageView shoeImage;
        Spinner shoeSize;
        Button addToBasketButton;

        public ShoeViewHolder(@NonNull View itemView) {
            super(itemView);
            shoeName = itemView.findViewById(R.id.shoeName);
            shoePrice = itemView.findViewById(R.id.shoePrice);
            shoeImage = itemView.findViewById(R.id.shoeImage);
            shoeSize = itemView.findViewById(R.id.shoeSize);
            addToBasketButton = itemView.findViewById(R.id.addToBasketButton);
        }
    }
}
