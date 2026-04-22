package com.example.restaurantrater;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {
    ArrayList<Restaurant> list;
    OnItemClickListener listener;

    public interface OnItemClickListener {
        void onDeleteClick(int id);
    }

    public RestaurantAdapter(ArrayList<Restaurant> list, OnItemClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Restaurant r = list.get(position);
        holder.tvName.setText(r.name);
        holder.tvCuisine.setText(r.cuisine);
        holder.tvRating.setText(String.valueOf(r.rating) + " / 5.0");
        
        holder.btnDelete.setOnClickListener(v -> listener.onDeleteClick(r.id));
    }

    @Override
    public int getItemCount() { return list.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvCuisine, tvRating;
        Button btnDelete;
        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvCuisine = itemView.findViewById(R.id.tvCuisine);
            tvRating = itemView.findViewById(R.id.tvRating);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
