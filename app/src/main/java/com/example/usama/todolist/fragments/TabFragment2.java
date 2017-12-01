package com.example.usama.todolist.fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.usama.todolist.R;
import com.example.usama.todolist.adapters.ListAdapter;
import com.example.usama.todolist.db.SqlLiteDb;
import com.example.usama.todolist.model.TaskModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragment2 extends Fragment {
    List<TaskModel> taskModelList;
    ListView taskItem;
    TextView t;
    public TabFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View getView = inflater.inflate(R.layout.fragment_tab_fragment2, container, false);
        taskModelList = new ArrayList<>();

        final SqlLiteDb db = new SqlLiteDb(getActivity());
        taskModelList = db.getTasksonStatus("Active");
        ListAdapter list = new ListAdapter(getActivity(),R.layout.item_task,taskModelList);
        taskItem = (ListView) getView.findViewById(R.id.fortaskItem2);
        taskItem.setAdapter(list);
        taskItem.invalidateViews();



        taskItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int j, long l) {
                final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setTitle("Do you want to complete it");
                t = (TextView) view.findViewById(R.id.task);
                dialog.setPositiveButton("Complete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        db.updateStatus(t.getText().toString());
                        taskItem.invalidateViews();
                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alert = dialog.create();
                alert.show();
            }
        });

        return getView;
    }

}
