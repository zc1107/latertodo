package com.example.latertodo.viewpager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.latertodo.FavouriteList;
import com.example.latertodo.FavouriteListAdapter;
import com.example.latertodo.R;
import com.example.latertodo.ToDoAdapter;
import com.example.latertodo.ToDoList;

import java.util.ArrayList;
import java.util.List;

public class MyFragment1 extends Fragment {

    private List<FavouriteList> favouriteLists = new ArrayList<>();

    private RecyclerView recyclerView;
    private FavouriteListAdapter adapter;
    private LinearLayoutManager manager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.myfragmentlayout_1,container,false);

        initToDo();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_favourite);
        manager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        adapter = new FavouriteListAdapter(favouriteLists);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void initToDo() {
        for (int i = 0; i < 14; i++) {
            String Name = "山河令"+ i + "个老婆";
            FavouriteList list = new FavouriteList(Name, "12");
            favouriteLists.add(list);
        }
    }
}

