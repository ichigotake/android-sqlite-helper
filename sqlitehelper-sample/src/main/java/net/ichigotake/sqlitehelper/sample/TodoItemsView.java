package net.ichigotake.sqlitehelper.sample;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import net.ichigotake.sqlitehelper.sample.db.TodoItem;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class TodoItemsView extends FrameLayout {

    @InjectView(R.id.list_view)
    ListView listView;

    public TodoItemsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_items, this, true);
        ButterKnife.inject(this);
        Adapter adapter = new Adapter(getContext());
        for (int i=0; i<100; i++) {
            adapter.add(new TodoItem("description " + i, false));
        }
        listView.setAdapter(adapter);
    }

    private static class Adapter extends ArrayAdapter<TodoItem> {

        private final LayoutInflater inflater;

        public Adapter(Context context) {
            super(context, 0);
            inflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            }
            TextView label = (TextView) convertView;
            label.setText(getItem(position).getDescription());
            return convertView;
        }
    }

}
