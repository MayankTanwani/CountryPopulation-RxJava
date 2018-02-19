package com.example.mayank.countrypopulation;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by mayank on 2/19/18.
 */

public class CountryRecyclerView extends RecyclerView.Adapter<CountryRecyclerView.ViewHolder> {

    ArrayList<Country> countries;

    public interface ListImageClick{
        public void onListImageClick(String imageRes);
    }
        ListImageClick listener;
    public CountryRecyclerView(ArrayList<Country> countries,ListImageClick listener) {
        this.countries = countries;
        this.listener = listener;

    }
    public void swapArray(ArrayList<Country> countries)
    {
        this.countries = countries;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Country c = countries.get(position);
        holder.tvPopulation.setText(c.getPopulation() + "");
        holder.tvName.setText(c.getCountry() + "");
        holder.tvRank.setText(c.getRank() + "");
        Glide.with(holder.ivFlag.getContext())
                .load(c.getFlag())
                .into(holder.ivFlag);
        holder.imageRes = c.getFlag();
    }

    @Override
    public int getItemCount() {
        if(countries == null )
            return 0;
        else
            return countries.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvRank;
        TextView tvName;
        TextView tvPopulation;
        ImageView ivFlag;
        String imageRes = null;
        public ViewHolder(View itemView) {
            super(itemView);
            tvRank = itemView.findViewById(R.id.tvRank);
            tvName = itemView.findViewById(R.id.tvName);
            tvPopulation = itemView.findViewById(R.id.tvPopulation);
            ivFlag = itemView.findViewById(R.id.ivFlag);
            ivFlag.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onListImageClick(imageRes);
        }
    }
}
