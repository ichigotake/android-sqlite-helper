package net.ichigotake.sqlitehelper.dml;

import android.os.Parcel;
import android.os.Parcelable;

import net.ichigotake.sqlitehelper.ddl.TableField;

public class Order implements Parcelable {

    public static enum Sequence {
        ASC,
        DESC,
        ;
    }

    private final String fieldName;
    private final Sequence sequence;

    public Order(TableField field) {
        this(field, Sequence.ASC);
    }

    public Order(TableField field, Sequence sequence) {
        this.fieldName = field.getFieldName();
        this.sequence = sequence;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Sequence getSequence() {
        return sequence;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.fieldName);
        dest.writeInt(this.sequence == null ? -1 : this.sequence.ordinal());
    }

    private Order(Parcel in) {
        this.fieldName = in.readString();
        int tmpSequence = in.readInt();
        this.sequence = tmpSequence == -1 ? null : Sequence.values()[tmpSequence];
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        public Order createFromParcel(Parcel source) {
            return new Order(source);
        }

        public Order[] newArray(int size) {
            return new Order[size];
        }
    };
}
