package com.example.usama.todolist.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.usama.todolist.R;
import com.example.usama.todolist.model.TaskModel;

import java.util.List;

/**
 * Created by Usama on 11/9/2017.
 */

public class ListAdapter extends ArrayAdapter {
List<TaskModel> taskModel;
    public ListAdapter(@NonNull Context context, @LayoutRes int resource, List<TaskModel> taskModel) {
        super(context, resource);
        this.taskModel = taskModel;
    }

    @Override
    public int getCount() {
        return taskModel.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;
                if(v==null)
                {
                    v= LayoutInflater.from(getContext()).inflate(R.layout.item_task,null);
                }
        TextView task = (TextView) v.findViewById(R.id.task);
        TextView status = (TextView) v.findViewById(R.id.status);

        task.setText(taskModel.get(position).getTask());
        status.setText(taskModel.get(position).getStatus());



        return v;
    }
}
