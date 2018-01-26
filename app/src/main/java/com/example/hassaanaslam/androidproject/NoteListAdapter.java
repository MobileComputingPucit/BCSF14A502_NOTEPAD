package com.example.hassaanaslam.androidproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Hassaan Aslam on 7/13/2017.
 */
public class NoteListAdapter extends ArrayAdapter {
    private ImageButton imageButton;

    private Context context;
    private ArrayList<Notes> notes;



    public NoteListAdapter(Context context, int resId, ArrayList<Notes> notes) {
        super(context,resId, notes);
        this.context = context;
        this.notes = notes;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View listItemView = null;


        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            listItemView = inflater.inflate(R.layout.notes_list_item, null);
            ImageButton imageButton= (ImageButton) listItemView.findViewById(R.id.imageButton);
            imageButton.setTag(position);
            imageButton.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    notes.remove(position);
                    notifyDataSetChanged();
                }
            });

        } else {
            listItemView = convertView;
        }

        Notes s = notes.get(position);

        ((TextView) listItemView.findViewById(R.id.textView5)).setText(s.noteDetails);

        return listItemView;
    }
//    public void remove(int position){
//
//        SQLiteDatabase dbStudents = null;
//        dbStudents = dbStudents.openOrCreateDatabase("notes_db",null);
//        dbStudents.execSQL("DELETE FROM notes_db WHERE id= "+position+"");
//
//    }
}

// notedb