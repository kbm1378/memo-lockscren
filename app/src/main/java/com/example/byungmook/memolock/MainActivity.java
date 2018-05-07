package com.example.byungmook.memolock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.byungmook.memolock.adapter.TodoListAdapter;
import com.example.byungmook.memolock.model.Todo;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    EditText todoEditText;
    ArrayList<String> todoList = new ArrayList<String>();
    private Realm mRealm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        todoEditText = (EditText) findViewById(R.id.todoEditText);
        Realm.init(this);

        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("todo.realm")
                .deleteRealmIfMigrationNeeded()
                .build();
        mRealm = Realm.getInstance(config);
        //Log.d("Realm", mRealm.getPath());
        RealmResults<Todo> todoResults = mRealm.where(Todo.class).findAllAsync();

        ListAdapter adapter = new TodoListAdapter(todoResults);
        ListView listView = (ListView) findViewById(R.id.todoListView);
        listView.setAdapter(adapter);
    }

    public void addTodoClick(View v) {
        String todoText = todoEditText.getText().toString();


        mRealm.beginTransaction();
        Todo mTodo = mRealm.createObject(Todo.class);
        mTodo.setTodo(todoText);
        mRealm.commitTransaction();

        Toast.makeText(MainActivity.this, todoText, Toast.LENGTH_SHORT).show();

        todoEditText.setText("");
    }
}
