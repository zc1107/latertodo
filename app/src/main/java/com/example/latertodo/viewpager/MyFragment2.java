package com.example.latertodo.viewpager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.latertodo.FavouriteList;
import com.example.latertodo.FavouriteListAdapter;
import com.example.latertodo.MyVoiceList;
import com.example.latertodo.MyVoiceListAdapter;
import com.example.latertodo.R;

import java.util.ArrayList;
import java.util.List;

public class MyFragment2 extends Fragment {

    private List<MyVoiceList> myVoiceLists = new ArrayList<>();

    private RecyclerView recyclerView;
    private MyVoiceListAdapter adapter;
    private LinearLayoutManager manager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.myfragmentlayout_2,container,false);

        initToDo();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_my_voice);
        manager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        adapter = new MyVoiceListAdapter(myVoiceLists);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void initToDo() {
        for (int i = 0; i < 14; i++) {
            String Name = "山河令"+ i + "个老龚";
            MyVoiceList list = new MyVoiceList(Name, "520");
            myVoiceLists.add(list);
        }
    }
}
