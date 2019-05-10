package com.filip.biography;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private int[] images;

    public RecyclerAdapter(int[] images){
        this.images = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.biography_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int pos = images[position];
        holder.imageView.setImageResource(pos);
        holder.bio.setText("Albert Einstein (14 March 1879 – 18 April 1955) was a German-born theoretical physicist " +
                "He is best known to the general public for his mass–energy equivalence formula E = mc2, " +
                "which has been dubbed the world's most famous equation");

    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView bio;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageID);
            bio = itemView.findViewById(R.id.informationID);
        }
    }
}
