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

import net.ichigotake.sqlitehelper.sample.db.TodoList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;

public class TodoListView extends FrameLayout {

    public interface Listener {
        void onListClicked(long listId);
    }

    @InjectView(R.id.todo_list)
    ListView listView;

    private Listener listener;

    public TodoListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_todo_list, this, true);
        ButterKnife.inject(this);
        Adapter adapter = new Adapter(getContext());
        for (int i=0; i<100; i++) {
            adapter.add(new TodoList(i, "todo " + i, 0));
        }
        listView.setAdapter(adapter);
    }

    @OnItemClick(R.id.todo_list)
    public void onListItemClicked(long listId) {
        listener.onListClicked(listId);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    private static class Adapter extends ArrayAdapter<TodoList> {

        private final LayoutInflater inflater;

        public Adapter(Context context) {
            super(context, 0);
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public long getItemId(int position) {
            return getItem(position).getListId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            }
            TextView label = (TextView) convertView;
            label.setText(getItem(position).getTitle());
            return convertView;
        }
    }
}
