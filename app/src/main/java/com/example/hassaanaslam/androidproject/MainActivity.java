package com.example.hassaanaslam.androidproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textView; //welcome
    private EditText editText;//email
    private EditText editText2;//password
    private Button button;//login
    private Button button2;//register

    private ArrayList<user> user;
    private SharedPreferences spStudent;
    private SQLiteDatabase dbStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        button= (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        button2=(Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);


        dbStudents = openOrCreateDatabase("user_db", MODE_PRIVATE, null);
        dbStudents.execSQL("CREATE TABLE IF NOT EXISTS user" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, firstname TEXT,lastname TEXT, email TEXT,password TEXT, dob TEXT,isMale TEXT);");

        spStudent = getSharedPreferences("use_sp", MODE_PRIVATE);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button:           //Login
                // do your code
                Cursor d=null;
                int count;
                user s=new user();
                s.email=editText.getText().toString();
                s.password=editText2.getText().toString();
                Toast.makeText(this, "Running query", Toast.LENGTH_SHORT).show();

                d=dbStudents.rawQuery("Select * From user where email='"+s.email+"'And password='"+s.password+"'",null);

                count=d.getCount();
                if (count>0){

                    SharedPreferences.Editor editor = spStudent.edit();
                    editor.putString("email", s.email);
                    editor.commit();
                    Intent i = new Intent(this, home.class);
                    i.putExtra("email",s.email);
                    startActivity(i);
                    Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(this, "Not Valid! Try again", Toast.LENGTH_SHORT).show();
                }

                d.close();
                break;

            case R.id.button2:          //Register
                // do your code
                Intent i2 = new Intent(this, Register.class);
                startActivity(i2);
                break;

            default:
                break;
        }
    }
}
