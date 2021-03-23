package com.example.latertodo.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.net.ParseException;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.latertodo.MyItemClickListener;
import com.example.latertodo.R;
import com.example.latertodo.ToDoAdapter;
import com.example.latertodo.ToDoList;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TodoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TodoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private QMUITopBarLayout bar;
    private FloatingActionButton addButton;
    private Button model;
    private ImageButton set;

    private TextView yesterday_date;
    private TextView today_date;
    private TextView tomorrow_date;

    private RecyclerView recyclerView_1;
    private RecyclerView recyclerView_2;
    private RecyclerView recyclerView_3;

    private ToDoAdapter adapter_1;
    private ToDoAdapter adapter_2;
    private ToDoAdapter adapter_3;

    private LinearLayoutManager manager_1;
    private LinearLayoutManager manager_2;
    private LinearLayoutManager manager_3;

    private List<ToDoList> toDoLists_1 = new ArrayList<>();
    private List<ToDoList> toDoLists_2 = new ArrayList<>();
    private List<ToDoList> toDoLists_3 = new ArrayList<>();

    public TodoFragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TodoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TodoFragment newInstance(String param1, String param2) {
        TodoFragment fragment = new TodoFragment();
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
        View view = inflater.inflate(R.layout.fragment_todo, container, false);

        //初始化
        initToDo();

        bar = (QMUITopBarLayout) view.findViewById(R.id.topbar);
        addButton = (FloatingActionButton) view.findViewById(R.id.action_add);

        //三个recycleview 分为早中晚
        recyclerView_1 = (RecyclerView) view.findViewById(R.id.recycler_view_1);
        recyclerView_2 = (RecyclerView) view.findViewById(R.id.recycler_view_2);
        recyclerView_3 = (RecyclerView) view.findViewById(R.id.recycler_view_3);

        manager_1 = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        adapter_1 = new ToDoAdapter(toDoLists_1);
        recyclerView_1.setLayoutManager(manager_1);
        recyclerView_1.setAdapter(adapter_1);

        //滑动删除的方法实现
//        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter_1);
//        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
//        touchHelper.attachToRecyclerView(recyclerView_1);


        //点击view中item弹出修改编辑窗口
        recyclerView_1.addOnItemTouchListener(new MyItemClickListener(recyclerView_1){
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder) {
                Dialog bottomDialog = showBottomDialog(view);

                Button add_2 = bottomDialog.findViewById(R.id.add_detail);
                EditText name = bottomDialog.findViewById(R.id.name_todo);
                EditText time = bottomDialog.findViewById(R.id.time_todo);
                CheckBox am = bottomDialog.findViewById(R.id.radio1);
                CheckBox pm = bottomDialog.findViewById(R.id.radio2);
                CheckBox night = bottomDialog.findViewById(R.id.radio3);

                add_2.setText("Modify");

                TextView name_list = (TextView) viewHolder.itemView.findViewById(R.id.name_of_todo);
                TextView time_list = (TextView) viewHolder.itemView.findViewById(R.id.time_of_todo);

                String time_1 = time_list.getText().toString().substring(0, time_list.getText().toString().length()-3);

                name.setText(name_list.getText().toString());
                time.setText(time_1);
                am.setChecked(true);
                pm.setChecked(false);
                night.setChecked(false);
            }
        });

        manager_2 = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        adapter_2 = new ToDoAdapter(toDoLists_2);
        recyclerView_2.setLayoutManager(manager_2);
        recyclerView_2.setAdapter(adapter_2);
        recyclerView_2.addOnItemTouchListener(new MyItemClickListener(recyclerView_1){
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder) {
                Dialog bottomDialog = showBottomDialog(view);

                Button add_2 = bottomDialog.findViewById(R.id.add_detail);
                EditText name = bottomDialog.findViewById(R.id.name_todo);
                EditText time = bottomDialog.findViewById(R.id.time_todo);
                CheckBox am = bottomDialog.findViewById(R.id.radio1);
                CheckBox pm = bottomDialog.findViewById(R.id.radio2);
                CheckBox night = bottomDialog.findViewById(R.id.radio3);

                add_2.setText("Modify");

                TextView name_list = (TextView) viewHolder.itemView.findViewById(R.id.name_of_todo);
                TextView time_list = (TextView) viewHolder.itemView.findViewById(R.id.time_of_todo);

                String time_1 = time_list.getText().toString().substring(0, time_list.getText().toString().length()-3);

                name.setText(name_list.getText().toString());
                time.setText(time_1);
                am.setChecked(false);
                pm.setChecked(true);
                night.setChecked(false);
            }
        });

        manager_3 = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        adapter_3 = new ToDoAdapter(toDoLists_3);
        recyclerView_3.setLayoutManager(manager_3);
        recyclerView_3.setAdapter(adapter_3);
        recyclerView_3.addOnItemTouchListener(new MyItemClickListener(recyclerView_1){
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder) {
                Dialog bottomDialog = showBottomDialog(view);

                Button add_2 = bottomDialog.findViewById(R.id.add_detail);
                EditText name = bottomDialog.findViewById(R.id.name_todo);
                EditText time = bottomDialog.findViewById(R.id.time_todo);
                CheckBox am = bottomDialog.findViewById(R.id.radio1);
                CheckBox pm = bottomDialog.findViewById(R.id.radio2);
                CheckBox night = bottomDialog.findViewById(R.id.radio3);

                add_2.setText("Modify");

                TextView name_list = (TextView) viewHolder.itemView.findViewById(R.id.name_of_todo);
                TextView time_list = (TextView) viewHolder.itemView.findViewById(R.id.time_of_todo);

                String time_1 = time_list.getText().toString().substring(0, time_list.getText().toString().length()-3);

                name.setText(name_list.getText().toString());
                time.setText(time_1);
                am.setChecked(false);
                pm.setChecked(false);
                night.setChecked(true);
            }
        });

        yesterday_date = (TextView) view.findViewById(R.id.yes_date);
        today_date = (TextView) view.findViewById(R.id.today_date);
        tomorrow_date = (TextView) view.findViewById(R.id.tomo_date);

        yesterday_date.setText(getOurSelData(-1));
        today_date.setText(getOurSelData(0) + " " + getOurSelWeek(0));
        tomorrow_date.setText(getOurSelData(1));


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog bottomDialog = showBottomDialog(view);

                Button add_2 = bottomDialog.findViewById(R.id.add_detail);
                EditText name = bottomDialog.findViewById(R.id.name_todo);
                EditText time = bottomDialog.findViewById(R.id.time_todo);
                CheckBox am = bottomDialog.findViewById(R.id.radio1);
                CheckBox pm = bottomDialog.findViewById(R.id.radio2);
                CheckBox night = bottomDialog.findViewById(R.id.radio3);

                add_2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!name.getText().toString().equals("")&&!time.getText().toString().equals("")){
                            if(am.isChecked()){
                                //试验一下添加数据后的显示 后要改为数据库
                                SharedPreferences sharedPreferences= view.getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("name",name.getText().toString());
                                editor.putString("time",time.getText().toString());
                                editor.putInt("num", 1);
                                editor.commit();
                                bottomDialog.cancel();
                                onResume();
                            } else if(pm.isChecked()){

                                SharedPreferences sharedPreferences= view.getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("name",name.getText().toString());
                                editor.putString("time",time.getText().toString());
                                editor.putInt("num", 2);
                                editor.commit();
                                bottomDialog.cancel();
                                onResume();
                            } else if(night.isChecked()){

                                SharedPreferences sharedPreferences= view.getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("name",name.getText().toString());
                                editor.putString("time",time.getText().toString());
                                editor.putInt("num", 3);
                                editor.commit();
                                bottomDialog.cancel();
                                onResume();
                            } else {
                                bottomDialog.cancel();
                            }
                        }else{
                            bottomDialog.cancel();
                        }
                    }
                });
            }
        });
        return view;
    }

    //重新加载页面
    @Override
    public void onResume(){
        super.onResume();
        initView(getView());
    }

    private void initView(View view){
//        initList();

        recyclerView_1 = (RecyclerView) view.findViewById(R.id.recycler_view_1);
        recyclerView_2 = (RecyclerView) view.findViewById(R.id.recycler_view_2);
        recyclerView_3 = (RecyclerView) view.findViewById(R.id.recycler_view_3);

        manager_1 = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        adapter_1 = new ToDoAdapter(toDoLists_1);
        recyclerView_1.setLayoutManager(manager_1);
        recyclerView_1.setAdapter(adapter_1);

        manager_2 = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        adapter_2 = new ToDoAdapter(toDoLists_2);
        recyclerView_2.setLayoutManager(manager_2);
        recyclerView_2.setAdapter(adapter_2);

        manager_3 = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        adapter_3 = new ToDoAdapter(toDoLists_3);
        recyclerView_3.setLayoutManager(manager_3);
        recyclerView_3.setAdapter(adapter_3);
    }

    private void initToDo() {

        for (int i = 0; i < 2; i++) {
            String eventName = "第" + i + "件事";
            ToDoList list = new ToDoList(eventName, "15");
            toDoLists_1.add(list);
        }
        for (int i = 0; i < 2; i++) {
            String eventName = "第" + i + "件事";
            ToDoList list = new ToDoList(eventName, "15");
            toDoLists_2.add(list);
        }
        for (int i = 0; i < 2; i++) {
            String eventName = "第" + i + "件事";
            ToDoList list = new ToDoList(eventName, "15");
            toDoLists_3.add(list);
        }

    }

    //试验待办添加
    private void initList(){
        SharedPreferences sharedPreferences= this.getContext().getSharedPreferences("data", Context .MODE_PRIVATE);
        String name = sharedPreferences.getString("name","");
        String time  = sharedPreferences.getString("time","");
        int num = sharedPreferences.getInt("num",0);
        switch (num){
            case 1:
                ToDoList todo_1 = new ToDoList(name, time);
                toDoLists_1.add(todo_1);
                break;
            case 2:
                ToDoList todo_2 = new ToDoList(name, time);
                toDoLists_2.add(todo_2);
                break;
            case 3:
                ToDoList todo_3 = new ToDoList(name, time);
                toDoLists_3.add(todo_3);
                break;
            default:

        }
        sharedPreferences.edit().clear();
    }

    private static String getOurSelData(int sel){
        String str = "";
        //格式化日期格式
        SimpleDateFormat df = new SimpleDateFormat("M" + "月" + "d" + "日");
        Calendar calendar = Calendar.getInstance();
        calendar.roll(Calendar.DAY_OF_YEAR, sel);
        str = df.format(calendar.getTime());

        return str;
    }

    private static String getOurSelWeek(int sel){
        Date date = null;
        String[] weekDaysName = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
        SimpleDateFormat df = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.roll(Calendar.DAY_OF_YEAR, sel);
        String str = df.format(calendar.getTime());
        try{
            date = df.parse(str);
        } catch (ParseException | java.text.ParseException e){
            e.printStackTrace();
        }
        calendar.setTime(date);
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        return weekDaysName[intWeek];
    }

    private static Dialog showBottomDialog(View view){
        Dialog bottomDialog = new Dialog(view.getContext(), R.style.dialog_bottom_full);
        View contentView = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_bottom, null);
        bottomDialog.setContentView(contentView);
        bottomDialog.setCanceledOnTouchOutside(true);//点击窗体外部可以关闭弹窗
        bottomDialog.setCancelable(true);

        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);//设置为底部显示
        bottomDialog.getWindow().setWindowAnimations(R.style.share_animation);//设置动画

        bottomDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);//设置横向全屏
        bottomDialog.show();

        CheckBox am = bottomDialog.findViewById(R.id.radio1);
        CheckBox pm = bottomDialog.findViewById(R.id.radio2);
        CheckBox night = bottomDialog.findViewById(R.id.radio3);

        pm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    pm.setChecked(true);
                    am.setChecked(false);
                    night.setChecked(false);
                } else {
                    pm.setChecked(false);
                }
            }
        });

        am.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    am.setChecked(true);
                    pm.setChecked(false);
                    night.setChecked(false);
                } else {
                    am.setChecked(false);
                }
            }
        });

        night.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    night.setChecked(true);
                    pm.setChecked(false);
                    am.setChecked(false);
                } else {
                    night.setChecked(false);
                }
            }
        });

        return bottomDialog;
    }

}