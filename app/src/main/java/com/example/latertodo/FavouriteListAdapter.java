package com.example.latertodo;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class FavouriteListAdapter extends RecyclerView.Adapter<FavouriteListAdapter.ViewHolder>  {

    private List<FavouriteList> mFavouriteList;
    private RecyclerView recyclerView;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView number;
        ImageView enter;

        public ViewHolder (View view)
        {
            super(view);
            name = (TextView) view.findViewById(R.id.name_of_favourite);
            number = (TextView) view.findViewById(R.id.num_of_favourite);
        }

    }

    public  FavouriteListAdapter (List <FavouriteList> favouriteList){
        mFavouriteList = favouriteList;
    }

    @Override

    public FavouriteListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favourite_item, parent,false);
        FavouriteListAdapter.ViewHolder holder = new FavouriteListAdapter.ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(FavouriteListAdapter.ViewHolder holder, int position){

        FavouriteList favouriteList = mFavouriteList.get(position);
        holder.name.setText(favouriteList.getNameOfFavourite());
        holder.number.setText(favouriteList.getNumberOfFavourite());
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount(){
        return mFavouriteList.size();
    }


    public interface OnItemClickListener{
        void onItemClick(int position);
        void onItemLongClick(int position);
    }

    private FavouriteListAdapter.OnItemClickListener mItemClickListener;

    public void setItemClickListener(FavouriteListAdapter.OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }
}
