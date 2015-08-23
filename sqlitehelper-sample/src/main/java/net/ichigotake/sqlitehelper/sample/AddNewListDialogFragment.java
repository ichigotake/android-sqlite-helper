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
import android.view.WindowManager;
import android.widget.EditText;

import net.ichigotake.sqlitehelper.sample.db.Db;
import net.ichigotake.sqlitehelper.sample.db.TodoListsTable;

import static android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE;
import static net.ichigotake.sqlitehelper.sample.db.TodoListsTable.Field.TITLE;

public class AddNewListDialogFragment extends DialogFragment {

    public static final String TAG = AddNewListDialogFragment.class.getName();

    public static AddNewListDialogFragment newInstance() {
        return new AddNewListDialogFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View rootView = getActivity().getLayoutInflater().inflate(R.layout.fragment_add_new_list, null);
        final EditText newListTitle = (EditText) rootView.findViewById(R.id.new_list_title);
        Dialog dialog = new AlertDialog.Builder(getActivity())
                .setTitle("New List")
                .setView(rootView)
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onTitleChanged(newListTitle.getText());
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.getWindow().setSoftInputMode(SOFT_INPUT_STATE_VISIBLE);
        return dialog;
    }

    private void onTitleChanged(CharSequence title) {
        SQLiteDatabase database = Db.getWritableDatabase(getActivity());
        TodoListsTable table = new TodoListsTable();
        ContentValues values = new ContentValues();
        values.put(TITLE.getFieldName(), title.toString());
        database.insert(table.getTableName(), null, values);
    }

}
