package com.example.niv.e_kay;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;

import java.util.List;

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

        ListView listView = findViewById(R.id.listView);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,//
                R.array.devices, android.R.layout.simple_list_item_single_choice);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    public void order(View view) {
        ListView lv = findViewById(R.id.listView);
        int i = lv.getCheckedItemPosition();
        if (i == ListView.INVALID_POSITION)
            return;
        String choice = (String) lv.getItemAtPosition(i);
        new MaterialDialog.Builder(this)//
                .title(R.string.order_alert_title)//
                .content(getString(R.string.order_alert_content_prefix)//
                        + choice//
                        + getString(R.string.order_alert_content_suffix))//
                .positiveText(R.string.order_alert_positiveText)//
                .negativeText(R.string.order_alert_negativeText)//
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Toast.makeText(getApplicationContext(), R.string.order_sent, Toast.LENGTH_SHORT).show();
                    }
                })//
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })//
                .autoDismiss(false)//
                .theme(Theme.DARK)
                .show();

    }
}
