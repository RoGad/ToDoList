package com.example.todolist.modle;


import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@SuppressLint("ParcelCreator")

@Entity (tableName = "note")
public class Note implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "text")
    public String text;

    @ColumnInfo(name = "timestamp")
    public long timestamp;

    @ColumnInfo(name = "done")
    public boolean done;

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
        return Objects.hash(uid, text, timestamp, done);
    }

    @Override
    public int describeContents() {
            return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
