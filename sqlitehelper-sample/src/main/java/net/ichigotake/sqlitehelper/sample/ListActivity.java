package net.ichigotake.sqlitehelper.sample;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static android.support.v4.view.MenuItemCompat.SHOW_AS_ACTION_IF_ROOM;
import static android.support.v4.view.MenuItemCompat.SHOW_AS_ACTION_WITH_TEXT;

public class ListActivity extends AppCompatActivity {

    @InjectView(R.id.todo_list_view)
    TodoListView todoListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.inject(this);
        todoListView.setListener(new TodoListView.Listener() {
            @Override
            public void onListClicked(long listId) {
                startActivity(ItemActivity.createIntent(ListActivity.this, listId));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem item = menu.add(R.string.add)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        onNewListClicked();
                        return true;
                    }
                });
        MenuItemCompat.setShowAsAction(item, SHOW_AS_ACTION_IF_ROOM | SHOW_AS_ACTION_WITH_TEXT);
        return super.onCreateOptionsMenu(menu);
    }

    private void onNewListClicked() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentByTag(AddNewListDialogFragment.TAG) == null) {
            fragmentManager
                    .beginTransaction()
                    .add(AddNewListDialogFragment.newInstance(), AddNewListDialogFragment.TAG)
                    .commit();
        }
    }


}
