package com.example.khairymobile;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewAdapter.ViewHolder> {

    private static final String TAG = "recyclerViewAdapter";

    private ArrayList<String> mImages = new ArrayList<>();
    private ArrayList<String> mtitle = new ArrayList<>();
    private ArrayList<String> mdescription = new ArrayList<>();
    private ArrayList<String> mdate = new ArrayList<>();
    private ArrayList<String> mcreator = new ArrayList<>();
    private Context mContext;
    private onItemListener monItemListener;

    public recyclerViewAdapter(Context mContext, onItemListener onItemListener,ArrayList<String> mcreator, ArrayList<String> mImages, ArrayList<String> mtitle, ArrayList<String> mdescription, ArrayList<String> mdate) {
        this.mImages = mImages;
        this.mtitle = mtitle;
        this.mdescription = mdescription;
        this.mdate = mdate;
        this.mContext = mContext;
        this.monItemListener = onItemListener;
        this.mcreator = mcreator;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_single_list,parent,false);
        ViewHolder holder = new ViewHolder(view , monItemListener);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Glide.with(mContext)
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


    public class ViewHolder extends RecyclerView.ViewHolder implements onItemListener, View.OnClickListener {

        public TextView title;
        public TextView description;
        public TextView date;
        public TextView creator;
        public TextView orders;
        public ImageView image;
        public CardView parentLayout;
        public onItemListener onItemListener;

        public ViewHolder(@NonNull View itemView,onItemListener onItemListener) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            date = itemView.findViewById(R.id.date);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            creator = itemView.findViewById(R.id.singleViewCreator);
            this.onItemListener = onItemListener;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onItemClick(int position) {
            onItemListener.onItemClick(getAdapterPosition());

        }

        @Override
        public void onClick(View v) {
            onItemListener.onItemClick(getAdapterPosition());
        }
    }
    public interface onItemListener{
        void onItemClick(int position);
    }
}
