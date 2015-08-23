package net.ichigotake.sqlitehelper.sample.db;

import android.os.Parcel;
import android.os.Parcelable;

public class TodoItem implements Parcelable {

    private final String description;
    private final boolean complete;

    public TodoItem(String description, boolean complete) {
        this.description = description;
        this.complete = complete;
    }

    protected TodoItem(Parcel in) {
        description = in.readString();
        complete = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(description);
        dest.writeByte((byte) (complete ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TodoItem> CREATOR = new Creator<TodoItem>() {
        @Override
        public TodoItem createFromParcel(Parcel in) {
            return new TodoItem(in);
        }

        @Override
        public TodoItem[] newArray(int size) {
            return new TodoItem[size];
        }
    };

    public String getDescription() {
        return description;
    }

    public boolean isComplete() {
        return complete;
    }
}
