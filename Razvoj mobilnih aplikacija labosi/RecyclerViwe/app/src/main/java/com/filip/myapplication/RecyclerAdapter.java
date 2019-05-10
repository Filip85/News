package com.filip.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ImageViewHolder> {

    private int[] images;

    public RecyclerAdapter(int[] images){
        this.images = images;

    }
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        ImageViewHolder imageViewHolder = new ImageViewHolder(view);

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        int pos = images[position];
        holder.image.setImageResource(pos);
        holder.bio.setText("Boooooooooook" + pos);

    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView bio;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.imageViewID);
            bio = itemView.findViewById(R.id.informationID);
        }
    }
}
