package com.nellpoi.task1_guide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        TextView textView_title = findViewById(R.id.title);
        TextView textView_content = findViewById(R.id.content);
        textView_title.setText(intent.getStringExtra("page_title"));
        textView_content.setText(intent.getStringExtra("page_content"));

    }
}