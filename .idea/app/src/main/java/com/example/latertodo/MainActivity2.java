package com.example.latertodo;

import android.os.Bundle;

import com.example.latertodo.ui.CollectionFragment;
import com.example.latertodo.ui.ListenFragment;
import com.example.latertodo.ui.MeFragment;
import com.example.latertodo.ui.TodoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.Collection;

public class MainActivity2 extends AppCompatActivity {

    private Fragment[] mFragments = new Fragment[4];
    private BottomNavigationView mBottomNav;
    private int mPreFragmentFlag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initView();
        initFragment();
        selectFragment();
    }

    private void initView() {
        mBottomNav = findViewById(R.id.mBottomNav);

    }

    private void initFragment() {
        mFragments[0] = new TodoFragment();
        mFragments[1] = new CollectionFragment();
        mFragments[2] = new ListenFragment();
        mFragments[3] = new MeFragment();
        initLoadFragment(R.id.mContainerView, 0, mFragments);
    }

    // 参数一 是一个FrameLayout的ID，用来动态加载Fragment，
    private void initLoadFragment(int containerId, int showFragment, Fragment... fragments) {
        //获取到FragmentManager实例的同时去开启事物
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < fragments.length; i++) {
            //首先将Fragment添加到事务中
            transaction.add(containerId, fragments[i], fragments[i].getClass().getName());
            //默认展示 fragments[showFragment]
            //这里做首次Fragment的展示，如果不是指定的Fragment就先隐藏，需要的时候再显示出来
            if (i != showFragment)
                transaction.hide(fragments[i]);
        }
        //提交事物
        transaction.commitAllowingStateLoss();

    }

    private void selectFragment() {
        //注册监听事件
        mBottomNav.setItemIconTintList(null);
        mBottomNav.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.navigation_todo:
                    showAndHideFragment(mFragments[0], mFragments[mPreFragmentFlag]);
                    mPreFragmentFlag = 0;
                    break;
                case R.id.navigation_collection:
                    showAndHideFragment(mFragments[1], mFragments[mPreFragmentFlag]);
                    mPreFragmentFlag = 1;
                    break;
                case R.id.navigation_listen:
                    showAndHideFragment(mFragments[2], mFragments[mPreFragmentFlag]);
                    mPreFragmentFlag = 2;
                    break;
                case R.id.navigation_me:
                    showAndHideFragment(mFragments[3], mFragments[mPreFragmentFlag]);
                    mPreFragmentFlag = 3;
                    break;
            }
            return true;
        });
    }

    //加载不同的Fragment
    private void showAndHideFragment(Fragment show, Fragment hide) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (show != hide)
            transaction.show(show).hide(hide).commitAllowingStateLoss();

    }
}