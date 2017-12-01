package com.example.usama.todolist.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.usama.todolist.R;
import com.example.usama.todolist.adapters.ListAdapter;
import com.example.usama.todolist.db.SqlLiteDb;
import com.example.usama.todolist.model.TaskModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragment3 extends Fragment {
List<TaskModel> taskModelList;

    public TabFragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View getView = inflater.inflate(R.layout.fragment_tab_fragment3, container, false);
        taskModelList = new ArrayList<>();

        SqlLiteDb db = new SqlLiteDb(getActivity());
        taskModelList = db.getTasksonStatus("Completed");
        ListAdapter list = new ListAdapter(getActivity(),R.layout.item_task,taskModelList);
        ListView taskItem = (ListView) getView.findViewById(R.id.fortaskItem3);
        taskItem.setAdapter(list);
        taskItem.invalidateViews();

        taskItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        return getView;
    }
}


