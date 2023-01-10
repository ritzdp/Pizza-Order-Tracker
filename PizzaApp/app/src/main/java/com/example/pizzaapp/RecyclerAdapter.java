package com.example.pizzaapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{
    private ArrayList<Topping> toppings;
    private int selectedPosition;
    private boolean disabled;

    private static final int NONE = -1;


    public RecyclerAdapter(ArrayList<Topping> toppings, boolean disabled) {
        this.toppings = toppings;
        this.disabled = disabled;
        selectedPosition = NONE;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView topping;
        private ImageView image;

        public MyViewHolder(final View view) {
            super(view);
            topping = view.findViewById(R.id.topping);
            image = view.findViewById(R.id.check);
        }

        private void bind(final Topping t, boolean disabled) {
            if ( ! ( disabled ) && selectedPosition != NONE
                    && selectedPosition == getAdapterPosition() ) {
                image.setVisibility(View.VISIBLE);
            } else {
                image.setVisibility(View.GONE);
            }

            topping.setText(t.toString());
            if ( ! ( disabled ) ) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if ( selectedPosition != getAdapterPosition() ) {
                            image.setVisibility(View.VISIBLE);
                            notifyItemChanged(selectedPosition);
                            selectedPosition = getAdapterPosition();
                        } else {
                            image.setVisibility(View.GONE);
                            selectedPosition = NONE;
                        }
                    }
                });
            }
        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.topping_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        holder.bind(toppings.get(position), disabled);
    }

    @Override
    public int getItemCount() {
        return toppings.size();
    }

    public Topping getSelected() {
        if ( selectedPosition != NONE ) {
            return toppings.get(selectedPosition);
        }
        return null;
    }
}
