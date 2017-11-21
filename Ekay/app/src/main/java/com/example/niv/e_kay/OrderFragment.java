package com.example.niv.e_kay;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class OrderFragment extends Fragment {

    private TextView welcomeMessage;
    private String initialUserName;
    public static final String USER_NAME = "com.example.niv.e_kay.OrderFragment.username";

    public void setUsername(String username) {
        if (username != null)
            welcomeMessage.setText(getString(R.string.welcome_prefix) + " " + username + getString(R.string.welcome_postfix));
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_order, container, false);

        welcomeMessage = v.findViewById(R.id.textView5);
        setUsername(initialUserName);

        ListView listView = v.findViewById(R.id.listView);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(v.getContext(),//
                R.array.devices, android.R.layout.simple_list_item_single_choice);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        Button b = v.findViewById(R.id.button2);
        b.setOnClickListener(new OrderListener(v));

        Toolbar tb = v.findViewById(R.id.my_toolbar);
        tb.setTitle(R.string.order_action_bar_title);

        return v;
    }

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
        if (args != null)
            initialUserName = args.getString(USER_NAME);
    }

    private class OrderListener implements View.OnClickListener{
        private final View v;

        public OrderListener(View v){
            this.v = v;
        }

        @Override
        public void onClick(@SuppressWarnings("Unused") View _) {
            ListView lv = v.findViewById(R.id.listView);
            if (lv == null)
                throw new RuntimeException(v.toString());
            if (lv.getCheckedItemPosition() == ListView.INVALID_POSITION)
                return;
            Toast.makeText(v.getContext(), R.string.order_sent, Toast.LENGTH_SHORT).show();
        }
    }
}
