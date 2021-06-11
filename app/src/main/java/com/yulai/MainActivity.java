package com.yulai;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.yulai.quickclicklib.annotation.SingleClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //测试快速点击

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @SingleClick
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "btn被点击了", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
           @SingleClick(clickInterval = 600)
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "bt2被点击了", Toast.LENGTH_SHORT).show();
            }
        });
    }
}