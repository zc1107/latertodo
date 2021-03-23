package com.example.latertodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> implements ItemTouchHelperAdapter {
    private  List<ToDoList> mToDoList;
    private RecyclerView recyclerView;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView time;
        TextView name;
        Button start;

        public ViewHolder (View view)
        {
            super(view);
            time = (TextView) view.findViewById(R.id.time_of_todo);
            name = (TextView) view.findViewById(R.id.name_of_todo);
            start = (Button) view.findViewById(R.id.start);
        }

    }

    public  ToDoAdapter (List <ToDoList> todoList){
        mToDoList = todoList;
    }

    @Override

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){

        ToDoList todo = mToDoList.get(position);
        holder.time.setText(todo.getTimeOfToDo() + "min");
        holder.name.setText(todo.getNameOfToDo());

    }

    @Override
    public int getItemCount(){
        return mToDoList.size();
    }

    @Override
    public void onItemDismiss(int position){
        mToDoList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onItemMove(int from, int to){
        if (from < to) {
            for (int i = from; i < to; i++) {
                Collections.swap(mToDoList, i, i + 1);
            }
        } else {
            for (int i = from; i > to; i--) {
                Collections.swap(mToDoList, i, i - 1);
            }
        }
        notifyItemMoved(from, to);
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    private OnItemClickListener mItemClickListener;

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

}
