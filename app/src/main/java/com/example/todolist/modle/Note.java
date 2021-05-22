package com.example.todolist.modle;


import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.ContactsContract;

import androidx.annotation.RequiresApi;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.lang.reflect.Array;
import java.util.Objects;

@SuppressLint("ParcelCreator")
@Entity(tableName = "note")
public class Note implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "text")
    public String text;

    @ColumnInfo(name = "timestamp")
    public long timestamp;

    @ColumnInfo(name = "done")
    public boolean done;
    public Note(){

    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object object) {
        Note note = (Note) object;
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
         return uid == note.uid &&
                timestamp == note.timestamp &&
                done == note.done &&
                Objects.equals(text, note.text);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        //return Objects.hash(uid, text, timestamp, done);
        int result = uid;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));
        result = 31 * result + (done ? 1 : 0);
        return result;
    }
    protected Note(Parcel source){
        uid = source.readInt();
        text = source.readString();
        timestamp = source.readLong();
        done = source.readByte() != 0;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(uid);
        dest.writeString(text);
        dest.writeLong(timestamp);
        dest.writeByte((byte) (done ? 1 : 0));
    }
    public static final Creator<Note> CREATOR = new Creator<Note>(){

        @Override
        public Note createFromParcel(Parcel source) {
            return new Note(source);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };
}
