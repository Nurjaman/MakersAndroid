package com.example.nurjaman.makersandroid;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter adapter;
    DatabaseHandler db;
    ArrayList<String> toDoItem;
    List<String> daftarToDo;
    ListView toDoList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toDoList = (ListView)  findViewById(R.id.list_item);
        db = new DatabaseHandler(this);
        updateData();

//        toDoItem = new ArrayList<String>();
//       daftarToDo = db.getAllTodo();




//         toDoItem = new ArrayList<String>();
//        toDoItem.add("Bangun Pagi");
//        toDoItem.add("Mandi");
//        toDoItem.add("Berangkat");
//        toDoItem.add("Ngerjain tugas");
//        toDoItem.add("Contoh 1");
//        toDoItem.add("Contoh 2");
//        toDoItem.add("Contoh 3");
//        toDoItem.add("Contoh 4");

//
//        adapter = new ArrayAdapter(this, R.layout.item_list,R.id.task_title, daftarToDo);
//        toDoList.setAdapter(adapter);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText taskEditText = new EditText(MainActivity.this);
                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("add a new task")
                        .setView(taskEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which){
                                String task = String.valueOf(taskEditText.getText());
                               // Log.d("TAG", "Ditambahkan " + task);
                               // toDoItem.add(task);
                                db.addTodo(task);
                                updateData();
                                //Buat nambah data ListView
                                //adapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
            }
        });
    }

    public void updateData(){
        List <String> daftarToDo = db.getAllTodo();
        adapter = new ArrayAdapter(this, R.layout.item_list,R.id.task_title, daftarToDo);
       // ListView toDoList = null;
        toDoList.setAdapter(adapter);
    }

    public void deleteTask(View view){
        View parent = (View) view.getParent();
        TextView taskTextView = (TextView) parent.findViewById(R.id.task_title);
        String task = String.valueOf(taskTextView.getText());
        db.deleteTodo(task);
        updateData();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
