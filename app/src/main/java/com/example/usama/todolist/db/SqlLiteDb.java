package com.example.usama.todolist.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.usama.todolist.model.TaskModel;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.cursorVisible;
import static android.R.attr.version;

/**
 * Created by Usama on 11/9/2017.
 */

public class SqlLiteDb extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1 ;
    private static final String TABLENAME_ACTIVE = "Active";
    private static final String Db_Name = "TodoList";
    private static final String Col_Name1 = "Tasks";
    private static final String Col_Name2 = "Status";
    private static final String Active_Table = "Create Table Active ( "+Col_Name1 + " Text, "+Col_Name2 + " Text )";

    public SqlLiteDb(Context context) {
        super(context, Db_Name,null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Active_Table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLENAME_ACTIVE);
        onCreate(sqLiteDatabase);
    }



    public void addTask(TaskModel task)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Col_Name1,task.getTask());
        values.put(Col_Name2,task.getStatus());
db.insert(TABLENAME_ACTIVE,null,values);
        db.close();
    }


    public List<TaskModel> getTasks()
    {
        List<TaskModel> taskModels = new ArrayList<>();
        String query = "SELECT * FROM Active";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst())
        {
            do {
                TaskModel taskModel = new TaskModel();
                taskModel.setTask(cursor.getString(0));
                taskModel.setStatus(cursor.getString(1));
                taskModels.add(taskModel);
            }
            while(cursor.moveToNext());


        }

return taskModels;
    }


    public List<TaskModel> getTasksonStatus(String status)
    {
        List<TaskModel> taskModels = new ArrayList<>();
        String query = "SELECT * FROM "+TABLENAME_ACTIVE +" Where Status = '" +status +"'" ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst())
        {
            do {
                TaskModel taskModel = new TaskModel();
                taskModel.setTask(cursor.getString(0));
                taskModels.add(taskModel);
            }
            while(cursor.moveToNext());


        }

        return taskModels;
    }

    public int updateStatus(String task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Status", "Completed");


        // updating row
        return db.update(TABLENAME_ACTIVE, values, Col_Name1+ " = ?",
                new String[] { task });
    }




}
