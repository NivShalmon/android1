package com.example.niv.e_kay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private class ButtonWatcher implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //
        }

        @Override
        public void afterTextChanged(Editable s) {
            Button button = findViewById(R.id.button);
            String name = ((EditText)findViewById(R.id.editText)).getText().toString();
            String age = ((EditText)findViewById(R.id.editText2)).getText().toString();

            if (age.isEmpty() || name.isEmpty()) {
                button.setEnabled(false);
                return;
            }

            int actualAge = Integer.parseInt(age);
            if (actualAge < 16 || actualAge > 100){
                button.setEnabled(false);
                return;
            }

            if (!name.matches("[a-zA-Z ]+")){
                button.setEnabled(false);
                return;
            }

            button.setEnabled(true);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,//
                R.array.countries, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button button = findViewById(R.id.button);
        button.setEnabled(false);
        EditText name = findViewById(R.id.editText);
        EditText age = findViewById(R.id.editText2);
        name.addTextChangedListener(new ButtonWatcher());
        age.addTextChangedListener(new ButtonWatcher());

        Toolbar tb = findViewById(R.id.my_toolbar);
        tb.setTitle(R.string.app_name);
    }

    public void signUp(View view){
        Intent intent = new Intent(this, OrderActivity.class);
        EditText name = findViewById(R.id.editText);
        String username = name.getText().toString();
        intent.putExtra(OrderActivity.USERNAME,username);
        startActivity(intent);
    }
}
