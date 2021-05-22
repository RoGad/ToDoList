package com.example.todolist.screens.main;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.App;
import com.example.todolist.R;
import com.example.todolist.modle.Note;
import com.example.todolist.screens.main.details.NoteDetailsActivity;

public class NoteViewHolder extends RecyclerView.ViewHolder{

    TextView noteText;
    CheckBox completed;
    View delete;

    Note note;
    boolean silentUpdate;

    public NoteViewHolder(@NonNull View itemView) {
        super(itemView);
        noteText = itemView.findViewById(R.id.note_text);
        completed = itemView.findViewById(R.id.completed);
        delete = itemView.findViewById(R.id.delete);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NoteDetailsActivity.start((Activity) itemView.getContext(), note);
            }
        });

       delete.setOnClickListener(new View.OnClickListener() {
           @Override public void onClick(View v) {
             App.getInstance().getNoteDao().delete(note);
          }
       });
        completed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!silentUpdate) {
                    note.done = isChecked;
                    App.getInstance().getNoteDao().update(note);

                }
                updateStrokeOut();
            }

        });

    }
    public void bind(Note note) {
        this.note = note;
        noteText.setText(note.text);
        updateStrokeOut();

        silentUpdate = true;
        completed.setChecked(note.done);
        silentUpdate = false;
    }
    private void updateStrokeOut() {
        if (note.done) {
            noteText.setPaintFlags(noteText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            noteText.setPaintFlags(noteText.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }

}
