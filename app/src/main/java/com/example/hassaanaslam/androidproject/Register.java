package com.example.hassaanaslam.androidproject;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Hassaan Aslam on 7/12/2017.
 */
public class Register extends AppCompatActivity implements View.OnClickListener ,AdapterView.OnItemSelectedListener{

    private EditText editText3;
    private EditText editText4;
    private EditText editText5;
    private EditText editText6;
    private DatePicker datePicker;
    private Spinner spinner2;
    private ImageView imageView;
    private Button button3;
    private SharedPreferences spStudent;
    private SQLiteDatabase dbStudents;
    private ArrayList<String> gen;
    private ArrayAdapter<String> gendname;
    String entered_dob ;
    String selection;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        editText3=(EditText) findViewById(R.id.editText3);
        editText4=(EditText) findViewById(R.id.editText4);
        editText5=(EditText) findViewById(R.id.editText5);
        editText6=(EditText) findViewById(R.id.editText6);
        button3=(Button) findViewById(R.id.button3);
        spinner2=(Spinner)findViewById(R.id.spinner2);
        imageView=(ImageView)findViewById(R.id.imageView);
        imageView.setImageResource(R.mipmap.ic_launcher);

        gen= new ArrayList<String>();
        gen.add("Male");
        gen.add("Female");

        gendname= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gen);
        gendname.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinner2.setAdapter(gendname);

        dbStudents = openOrCreateDatabase("user_db", MODE_PRIVATE, null);
        dbStudents.execSQL("CREATE TABLE IF NOT EXISTS user" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, firstname TEXT,lastname TEXT, email TEXT,password TEXT, dob TEXT,isMale TEXT);");

//        button3=(Button) findViewById(R.id.button3);
        button3.setOnClickListener(this);

//        spStudent = getSharedPreferences("users_sp", MODE_PRIVATE);
//
//
//        editText5.setText(spStudent.getString("name3","def email"));
//        SharedPreferences.Editor editor = spStudent.edit();
//        editor.putString("name", editText5.getText().toString().trim());
//        editor.commit();
    }


    @Override
    public void onClick(View view) {

        user s = new user();

        s.firstname=editText3.getText().toString();
        s.lastname=editText4.getText().toString();
        s.email=editText5.getText().toString();
        s.password=editText6.getText().toString();
        getDate();
        s.dob = entered_dob;
//        s.gender=spinner2.getSelectedItem().toString();
        s.gender=selection;
        dbStudents.execSQL("INSERT INTO user VALUES(NULL, '"+s.firstname+"', '"+s.lastname+"', '"+s.email+"','"+s.password+"','"+s.dob+"','"+s.gender+"');");

        Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
        finish();

    }
    public void getDate(){
        Date d;
        SimpleDateFormat dateFormatter ;
        datePicker = (DatePicker) findViewById(R.id.datePicker);


        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth() + 1;
        int year = datePicker.getYear();
        dateFormatter = new SimpleDateFormat("MM-dd-yyyy");
        d = new Date(year, month, day);

        entered_dob = dateFormatter.format(d);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
       selection= gen.get(i);
        imageView=new ImageView(this);
        if (selection=="female")
        {
            imageView.setImageResource(R.drawable.femal);
        }else
            imageView.setImageResource(R.mipmap.ic_launcher);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selection=gen.get(i);
        imageView=new ImageView(this);
        if (selection=="male")
        {
            imageView.setImageResource(R.mipmap.ic_launcher);
        }else
            imageView.setImageResource(R.drawable.femal);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
