package com.example.usama.todolist.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usama.todolist.R;
import com.example.usama.todolist.activities.MainActivity;
import com.example.usama.todolist.adapters.ListAdapter;
import com.example.usama.todolist.db.SqlLiteDb;
import com.example.usama.todolist.model.TaskModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragment1 extends Fragment {

List<TaskModel> taskModelList;
    public TabFragment1() {
        // Required empty public constructor
    }
Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View getView = inflater.inflate(R.layout.fragment_tab_fragment1, container, false);
taskModelList = new ArrayList<>();
        context = container.getContext();
        SqlLiteDb db = new SqlLiteDb(context);

       taskModelList = db.getTasks();
        ListAdapter list = new ListAdapter(context,R.layout.item_task,taskModelList);
        final ListView taskItem = (ListView) getView.findViewById(R.id.fortaskItem);
        taskItem.setAdapter(list);
        taskItem.invalidateViews();

        taskItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                TextView t = (TextView) view.findViewById(R.id.status);

                String s = t.getText().toString();
Toast.makeText(getActivity(),s,Toast.LENGTH_SHORT).show();
            }
        });

        return getView;


    }

}
