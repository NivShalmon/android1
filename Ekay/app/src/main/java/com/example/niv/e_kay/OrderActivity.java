package com.example.niv.e_kay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    public static final String USERNAME = "com.example.niv.e_kay.username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent intent = getIntent();
        String username = intent.getStringExtra(USERNAME);

        TextView textView = findViewById(R.id.textView5);
        textView.setText(getString(R.string.welcome_prefix) + username + getString(R.string.welcome_postfix));
    }
}
