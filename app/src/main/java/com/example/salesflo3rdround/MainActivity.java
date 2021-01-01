package com.example.salesflo3rdround;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edt_name;
    Button btnSaveData;
    RadioButton rb_male, rb_female;
    Spinner spinner_skill;
    String MY_PREFS_NAME = "MyApp";
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_name = findViewById(R.id.edtNAme);
        btnSaveData = findViewById(R.id.btn_save);
        rb_male = findViewById(R.id.rb_mael);
        rb_female = findViewById(R.id.rb_female);
        spinner_skill = findViewById(R.id.spinner_skill);

        rb_male.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    gender = "male";
                }
            }
        });

        rb_female.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    gender = "female";
                }
            }
        });

        btnSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("name", edt_name.getText().toString().trim());
                editor.putString("gender", "gender");
                editor.putString("skill", spinner_skill.getSelectedItem().toString());
                editor.apply();
                Toast.makeText(MainActivity.this, "Data Saved", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), SecondScreen.class));
            }
        });
    }
}