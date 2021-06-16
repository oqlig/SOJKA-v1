package org.pytorch.demo.objectdetection;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

    private final Context context;
    private final ArrayList title;
    private final ArrayList time;
    private final ArrayList instructions;
    private final ArrayList imageUrl;

    CustomAdapter(Context context, ArrayList title, ArrayList time, ArrayList instructions, ArrayList imageUrl){
        this.context = context;
        this.title = title;
        this.time = time;
        this.instructions = instructions;
        this.imageUrl = imageUrl;
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.in_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        holder.title_txt.setText(String.valueOf(title.get(position)));
        holder.time_txt.setText(String.valueOf(time.get(position)));
        holder.instructions_txt.setText(String.valueOf(instructions.get(position)));
        Glide.with(holder.itemView).load(String.valueOf(imageUrl.get(position))).into(holder.image_url);
    }

    @Override
    public int getItemCount() {
        return title.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title_txt, time_txt, instructions_txt;
        ImageView image_url;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image_url = itemView.findViewById(R.id.imageUrl);
            title_txt = itemView.findViewById(R.id.title_txt);
            time_txt = itemView.findViewById(R.id.time_txt);
            instructions_txt = itemView.findViewById(R.id.instructions_txt);
        }
    }
}
