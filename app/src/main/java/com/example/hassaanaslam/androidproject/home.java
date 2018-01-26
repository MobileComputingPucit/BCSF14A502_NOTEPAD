package com.example.hassaanaslam.androidproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Hassaan Aslam on 7/12/2017.
 */
public class home extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener{

    private SharedPreferences spStudent;
    private TextView textView4;
    private Button button4;
    private Button button5;

    private ListView lvStudents;

    private ArrayList<Notes> notes;
    private NoteListAdapter aaStudents;

    private SQLiteDatabase notedb;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        textView4=(TextView)findViewById(R.id.textView4);
        button4=(Button) findViewById(R.id.button4);
        button5=(Button) findViewById(R.id.button5);
        lvStudents = (ListView) findViewById(R.id.lvStudents);

        Intent g=getIntent();
        email=g.getExtras().getString("email");
        notes = new ArrayList<Notes>();

        aaStudents = new NoteListAdapter(this,R.layout.notes_list_item, notes);
        notedb = openOrCreateDatabase("notes_db", MODE_PRIVATE, null);

        notedb.execSQL("CREATE TABLE IF NOT EXISTS notes_db" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, note TEXT, email TEXT);");

        Cursor c = notedb.rawQuery("SELECT id, note, email from notes_db", null);
        c.moveToFirst();
        for (int i = 0; i < c.getCount(); i++) {
            Notes n=new Notes();
            n.noteDetails=c.getString(1);
            notes.add(n);
            c.moveToNext();
        }
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        lvStudents.setAdapter(aaStudents);
        lvStudents.setOnItemClickListener(this);

        spStudent = getSharedPreferences("use_sp", MODE_PRIVATE);
        textView4.setText(spStudent.getString("email", "No name defined"));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button4: //Login
                finish();
                break;
            case R.id.button5:
                Intent i = new Intent(this, addnote.class);
                String notetext = null;
                i.putExtra("Note",notetext);
                startActivityForResult(i,100);

                Toast.makeText(this, "Showing", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Notes n=new Notes();
        if (requestCode == 100 && resultCode == 200 && data != null) {
            String note=data.getStringExtra("Note");
            n.noteDetails=note;
            notedb.execSQL("INSERT INTO notes_db VALUES(NULL, '"+note+"', '"+email+"');");
            notes.add(n);
            aaStudents.notifyDataSetChanged();
            Toast.makeText(this, "Note Added", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this, "Empty Note not added!", Toast.LENGTH_SHORT).show();

    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Notes s = notes.get(i);

        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
    }
    public void remove(int position){
        SQLiteDatabase dbStudents;
        dbStudents = openOrCreateDatabase("notes_db",MODE_PRIVATE, null);
        dbStudents.execSQL("DELETE FROM notes_db WHERE id= "+position+"");
    }

}
