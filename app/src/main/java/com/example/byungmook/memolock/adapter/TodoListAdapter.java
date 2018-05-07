package com.example.byungmook.memolock.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.byungmook.memolock.model.Todo;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;

public class TodoListAdapter extends RealmBaseAdapter<Todo>{
    private static class ViewHolder {
        TextView text;
    }

    public TodoListAdapter(OrderedRealmCollection<Todo> realmResults) {
        super(realmResults);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_list_item_1, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.text = convertView.findViewById(android.R.id.text1);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (adapterData != null) {
            Todo todo = adapterData.get(position);
            viewHolder.text.setText(todo.getTodo());
        }
        return convertView;
    }


}
