package com.example.latertodo;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.RecyclerView;

public abstract class MyItemClickListener extends RecyclerView.SimpleOnItemTouchListener {

    private GestureDetectorCompat mGestureDetectorCompat;
    private RecyclerView mRecyclerView;

    /**
     * 通过构造传入我们的RecyclerView,并初始化GestureDetectorCompat
     */
    public MyItemClickListener(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
        mGestureDetectorCompat = new GestureDetectorCompat(
                mRecyclerView.getContext(),new MyGestureListener());
    }

    /**
     * 将事件交给GestureDetectorCompat处理
     *   并将MotionEvent 传入GestureDetectorCompat使得可以获取触摸的坐标
     */
    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetectorCompat.onTouchEvent(e);
    }

    /**
     * 不拦截触摸事件,将事件交给GestureDetectorCompat处理
     */
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetectorCompat.onTouchEvent(e);
        return false;
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }

    /**
     * 定义一个抽象回调方法
     */
    public abstract void onItemClick(RecyclerView.ViewHolder vh);

    public abstract void onItemLongClick(RecyclerView.ViewHolder vh);

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener{

        /**
         * 这个方法在简单的点击屏幕时执行
         *   如果要处理长按事件可以复写onLongPress（）方法，方法内具体逻辑类似
         */
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            View childView = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
            if (childView != null) {
                RecyclerView.ViewHolder viewHolder =
                        mRecyclerView.getChildViewHolder(childView);
                onItemClick(viewHolder);//触发回调
            }
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e){
            View childView = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
            if (childView != null) {
                RecyclerView.ViewHolder viewHolder =
                        mRecyclerView.getChildViewHolder(childView);
                onItemLongClick(viewHolder);//触发回调
            }
        }

    }
}
