package com.example.usama.todolist.activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.preference.DialogPreference;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.usama.todolist.R;
import com.example.usama.todolist.adapters.PageAdapter;
import com.example.usama.todolist.db.SqlLiteDb;
import com.example.usama.todolist.fragments.TabFragment1;
import com.example.usama.todolist.fragments.TabFragment2;
import com.example.usama.todolist.fragments.TabFragment3;
import com.example.usama.todolist.model.TaskModel;

public class MainActivity extends AppCompatActivity {

    SqlLiteDb db;
    int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
db = new SqlLiteDb(this);
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("All"));
        tabLayout.addTab(tabLayout.newTab().setText("Active"));
        tabLayout.addTab(tabLayout.newTab().setText("Completed"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PageAdapter adapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(final TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                counter = tab.getPosition();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        FloatingActionButton Addtask = (FloatingActionButton) findViewById(R.id.addTasks);
        Addtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder dialog = new AlertDialog.Builder(new ContextThemeWrapper(MainActivity.this, R.style.myDialog));
                LayoutInflater inflater = getLayoutInflater();
                final View view11 = inflater.inflate(R.layout.dialog_addtask,null);
                dialog.setView(view11)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                EditText task = (EditText) view11.findViewById(R.id.taskdetail);
                                TaskModel taskModel = new TaskModel();
                                taskModel.setTask(task.getText().toString());
                                taskModel.setStatus("Active");
                                db.addTask(taskModel);
                                viewPager.setCurrentItem(counter);


                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                AlertDialog alert = dialog.create();
                alert.show();

            }
        });


    }
}
