package com.example.hassaanaslam.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Hassaan Aslam on 7/13/2017.
 */
public class addnote  extends AppCompatActivity implements View.OnClickListener {
    private Button savebutton;
    private EditText notetext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnote);
        savebutton=(Button) findViewById(R.id.savebutton);
        notetext=(EditText) findViewById(R.id.notetext);
        savebutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String text=notetext.getText().toString();
        Intent intent =new Intent();
        intent.putExtra("Note",text);
        setResult(200,intent);
        finish();
    }
}
