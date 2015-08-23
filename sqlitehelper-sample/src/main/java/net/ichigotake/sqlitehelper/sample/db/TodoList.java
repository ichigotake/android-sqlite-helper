package net.ichigotake.sqlitehelper.sample.db;

import android.os.Parcel;
import android.os.Parcelable;

public class TodoList implements Parcelable {

    private final long listId;
    private final String title;
    private final int archived;

    public TodoList(long listId, String title, int archived) {
        this.listId = listId;
        this.title = title;
        this.archived = archived;
    }

    protected TodoList(Parcel in) {
        listId = in.readLong();
        title = in.readString();
        archived = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(listId);
        dest.writeString(title);
        dest.writeInt(archived);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TodoList> CREATOR = new Creator<TodoList>() {
        @Override
        public TodoList createFromParcel(Parcel in) {
            return new TodoList(in);
        }

        @Override
        public TodoList[] newArray(int size) {
            return new TodoList[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public int getArchived() {
        return archived;
    }

    public long getListId() {
        return listId;
    }

    public static Creator<TodoList> getCREATOR() {
        return CREATOR;
    }

}
