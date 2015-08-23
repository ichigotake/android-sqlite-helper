package net.ichigotake.sqlitehelper.sample;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;

import net.ichigotake.sqlitehelper.sample.db.Db;
import net.ichigotake.sqlitehelper.sample.db.TodoItemsTable;

import static android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE;
import static net.ichigotake.sqlitehelper.sample.db.TodoItemsTable.Field.DESCRIPTION;

public class AddNewItemsDialogFragment extends DialogFragment {

    public static final String TAG = AddNewItemsDialogFragment.class.getName();

    public static AddNewItemsDialogFragment newInstance() {
        return new AddNewItemsDialogFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View rootView = getActivity().getLayoutInflater().inflate(R.layout.fragment_add_new_item, null);
        final EditText newDescription = (EditText) rootView.findViewById(R.id.new_item_name);
        Dialog dialog = new AlertDialog.Builder(getActivity())
                .setTitle("New Item")
                .setView(rootView)
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onTitleChanged(newDescription.getText());
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.getWindow().setSoftInputMode(SOFT_INPUT_STATE_VISIBLE);
        return dialog;
    }

    private void onTitleChanged(CharSequence description) {
        SQLiteDatabase database = Db.getWritableDatabase(getActivity());
        TodoItemsTable table = new TodoItemsTable();
        ContentValues values = new ContentValues();
        values.put(DESCRIPTION.getFieldName(), description.toString());
        database.insert(table.getTableName(), null, values);
    }

}
