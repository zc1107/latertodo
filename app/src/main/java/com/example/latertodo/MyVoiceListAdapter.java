package com.example.latertodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyVoiceListAdapter extends RecyclerView.Adapter<MyVoiceListAdapter.ViewHolder>{

    private List<MyVoiceList> mMyVoiceList;
    private RecyclerView recyclerView;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView number;
        TextView position;

        public ViewHolder (View view)
        {
            super(view);
            name = (TextView) view.findViewById(R.id.name_of_myvoice);
            number = (TextView) view.findViewById(R.id.num_of_myvoice);
            position = (TextView) view.findViewById(R.id.position_my_voice);
        }

    }

    public  MyVoiceListAdapter (List <MyVoiceList> myVoiceLists){
        mMyVoiceList = myVoiceLists;
    }

    @Override

    public MyVoiceListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myvoice_item, parent,false);
        MyVoiceListAdapter.ViewHolder holder = new MyVoiceListAdapter.ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(MyVoiceListAdapter.ViewHolder holder, int position){

        MyVoiceList myVoiceList = mMyVoiceList.get(position);
        holder.name.setText(myVoiceList.getNameOfFavourite());
        holder.number.setText(myVoiceList.getNumberOfFavourite());
        holder.position.setText(String.valueOf(position + 1));
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount(){
        return mMyVoiceList.size();
    }


    public interface OnItemClickListener{
        void onItemClick(int position);
        void onItemLongClick(int position);
    }

    private MyVoiceListAdapter.OnItemClickListener mItemClickListener;

    public void setItemClickListener(MyVoiceListAdapter.OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

}
