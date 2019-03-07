package com.vogella.android.projet.java.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.vogella.android.projet.R;

import org.w3c.dom.Text;

public class InfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        TextView txt_id = (TextView) findViewById(R.id.TestID);
        Integer info = getIntent().getIntExtra("key",0);
        txt_id.setText(info.toString());
    }
    public void showInfo() {
        TextView txt_id = findViewById(R.id.TestID);
        Integer info = getIntent().getIntExtra("key", 0);
        txt_id.setText(info);
    }
}
