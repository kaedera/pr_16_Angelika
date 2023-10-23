package com.example.pr_16_angelika;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etText;
    Button btnSave, btnLoad;
    SharedPreferences sPref;

    final String SAVED_TEXT = "saved_text";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etText = (EditText)findViewById(R.id.etText);
        btnSave = (Button)findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        btnLoad = (Button)findViewById(R.id.btnLoad);
        btnLoad.setOnClickListener(this);

        loadText();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnSave){
            saveText();
        }
        else if(v.getId() == R.id.btnLoad){
            loadText();
        }

    }



    private void saveText() {
        sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(SAVED_TEXT, etText.getText().toString());
        ed.commit();
        Toast.makeText(MainActivity.this, "Text saved", Toast.LENGTH_SHORT).show();


    }


    private void loadText() {
        sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        String savedText = sPref.getString(SAVED_TEXT, "");
        etText.setText(savedText);
        Toast.makeText(MainActivity.this, "Text loaded", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveText();
    }
}