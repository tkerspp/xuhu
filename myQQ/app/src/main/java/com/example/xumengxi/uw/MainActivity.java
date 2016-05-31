package com.example.xumengxi.uw;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText name;
    private EditText pwd;
    private Button button;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        name = (EditText) findViewById(R.id.name);
        pwd = (EditText) findViewById(R.id.password);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String un = name.getText().toString();

                String p = pwd.getText().toString();
                if (un.equals("xuhu")||un.equals("liaomengsi")||un.equals("xuming")) {
                    GetIn(un);
                } else {
                    Toast.makeText(getApplicationContext(), "密码错误或者用户不存在", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void GetIn(String str) {
        Intent intent = new Intent();
        intent.putExtra("extra", str);
        intent.setClass(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }

}
