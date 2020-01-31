
package com.example.khairymobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
//import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class eventsAdapter extends RecyclerView.Adapter<eventsAdapter.eventsViewHolder> {
    private Context context;
    private OnItemClickListener onItemClickListener;
    private ArrayList<String> mImages = new ArrayList<>();
    private ArrayList<String> mtitle = new ArrayList<>();
    private ArrayList<String> mdescription = new ArrayList<>();
    private ArrayList<String> mdate = new ArrayList<>();

    public eventsAdapter(Context context, ArrayList<String> mImages, ArrayList<String> mtitle, ArrayList<String> mdescription, ArrayList<String> mdate) {
        this.context = context;
        this.mImages = mImages;
        this.mtitle = mtitle;
        this.mdescription = mdescription;
        this.mdate = mdate;
    }

    @NonNull
    @Override
    public eventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_single_list,parent,false);
        return new eventsViewHolder(view,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull eventsViewHolder holders, int position) {
        final eventsViewHolder holder = holders;
        Glide.with(context)
                .asBitmap()
                .load(mImages.get(position))
                .into(holder.image);
        holder.title.setText(mtitle.get(position));
        holder.description.setText(mdescription.get(position));
        holder.date.setText(mdate.get(position));



    }

    @Override
    public int getItemCount() {
        return mtitle.size();
    }



public void setOnItemClickListener(OnItemClickListener onItemClickListener){
    this.onItemClickListener = onItemClickListener;
}
public interface OnItemClickListener{
    void onItemClick(View view, int position);
}

public class eventsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{



    public TextView title,description,date,creator,orders;
    public ImageView image;
    public CardView parentLayout;

    OnItemClickListener onItemClickListener;
    public eventsViewHolder(View itemView, OnItemClickListener onItemClickListener ) {
        super(itemView);
        itemView.setOnClickListener(this);
        image = itemView.findViewById(R.id.image);
        title = itemView.findViewById(R.id.title);
        description = itemView.findViewById(R.id.description);
        date = itemView.findViewById(R.id.date);
        parentLayout = itemView.findViewById(R.id.parent_layout);
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onClick(View v) {
        onItemClickListener.onItemClick(v,getAdapterPosition());
    }
}
}

