package com.example.latertodo.ui;

import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.latertodo.R;
import com.example.latertodo.viewpager.MyFragment1;
import com.example.latertodo.viewpager.MyFragment2;
import com.example.latertodo.viewpager.MyFragment3;
import com.example.latertodo.viewpager.MyFragmentAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class MeFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MeFragment newInstance(String param1, String param2) {
        MeFragment fragment = new MeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout_me);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager_me);

        viewPager.setOffscreenPageLimit(2);
        initViewPager();

        return view;
    }

    private void initViewPager(){
        List<String> titleList = new ArrayList<>();
        titleList.add("收藏的声音");
        titleList.add("我的声音");
        titleList.add("加入的自习室");

        for(int i = 0;i < titleList.size();i++){
            tabLayout.addTab(tabLayout.newTab().setText(titleList.get(i)));
        }

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new MyFragment1());
        fragmentList.add(new MyFragment2());
        fragmentList.add(new MyFragment3());


        MyFragmentAdapter myFragmentAdapter = new MyFragmentAdapter(getChildFragmentManager(), fragmentList, titleList);
        viewPager.setAdapter(myFragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }


}