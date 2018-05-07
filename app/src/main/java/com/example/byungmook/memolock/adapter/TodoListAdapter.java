package com.example.byungmook.memolock.adapter;


import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.byungmook.memolock.MainActivity;
import com.example.byungmook.memolock.R;
import com.example.byungmook.memolock.model.Todo;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmBaseAdapter;

public class TodoListAdapter extends RealmBaseAdapter<Todo>{
    private Realm mRealm;
    private static class ViewHolder {
        TextView text;
    }

    public TodoListAdapter(OrderedRealmCollection<Todo> realmResults, Realm mRealm) {
        super(realmResults);
        this.mRealm = mRealm;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        ViewHolder viewHolder;
        Button todoItemEditButton;
        Todo todo;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.todo_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.text = convertView.findViewById(R.id.todoItemTextView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (adapterData != null) {
            todo = adapterData.get(position);

            viewHolder.text.setText(todo.getTodo());
            todoItemEditButton = (Button) convertView.findViewById(R.id.todoItemEditButton);
            todoItemEditButton.setOnClickListener(new EditButtonOnClickListener(todo){
                @Override
                public void onClick(View v){
                    AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                    final EditText alertEditText = new EditText(parent.getContext());
                    alertEditText.setText(todo.getTodo());
                    builder.setTitle("할일 수정")
                            .setView(alertEditText)
                            .setPositiveButton("수정", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int i) {
                                    //finish();
                                    mRealm.beginTransaction();
                                    todo.setTodo(alertEditText.getText().toString());
                                    mRealm.commitTransaction();
                                    dialog.cancel();
                                }
                            })
                            .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int i) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            });
        }
        return convertView;
    }


}


class EditButtonOnClickListener implements View.OnClickListener
{

    Todo todo;
    public EditButtonOnClickListener(Todo todo) {
        this.todo = todo;
    }

    @Override
    public void onClick(View v)
    {
        //read your lovely variable
    }

};