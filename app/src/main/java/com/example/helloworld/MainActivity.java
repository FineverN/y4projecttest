package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

//    public void onBtnClick (View view) {
//        TextView txtHello = findViewById(R.id.hahaText);
//        EditText edtTxtName = findViewById(R.id.inputText);
//        txtHello.setText("Hello Mr. " + edtTxtName.getText().toString());
//    }
    public void onBtnClick (View view) {
        Intent intent = new Intent(MainActivity.this,AnotherPlaybookDemo.class);
        startActivity(intent);
    }
}