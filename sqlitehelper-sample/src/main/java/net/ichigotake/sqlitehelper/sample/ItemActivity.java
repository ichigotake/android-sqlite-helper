package net.ichigotake.sqlitehelper.sample;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import static android.support.v4.view.MenuItemCompat.SHOW_AS_ACTION_IF_ROOM;
import static android.support.v4.view.MenuItemCompat.SHOW_AS_ACTION_WITH_TEXT;

public class ItemActivity extends AppCompatActivity {

    private static final String EXTRA_ITEM_ID = "item_id";

    public static Intent createIntent(Context context, long itemId) {
        Intent intent = new Intent(context, ItemActivity.class);
        intent.putExtra(EXTRA_ITEM_ID, itemId);
        return intent;
    }

    private long itemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        itemId = getIntent().getExtras().getLong(EXTRA_ITEM_ID);
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
        if (fragmentManager.findFragmentByTag(AddNewItemsDialogFragment.TAG) == null) {
            fragmentManager
                    .beginTransaction()
                    .add(AddNewItemsDialogFragment.newInstance(), AddNewItemsDialogFragment.TAG)
                    .commit();
        }
    }

}
