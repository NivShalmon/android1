package com.example.niv.e_kay;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private class ButtonWatcher implements TextWatcher {

        private final View v;

        public ButtonWatcher(View v){
            this.v = v;
        }

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
            Button button = v.findViewById(R.id.button);
            String name = ((EditText)v.findViewById(R.id.editText)).getText().toString();
            String age = ((EditText)v.findViewById(R.id.editText2)).getText().toString();

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

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        Spinner spinner = v.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),//
                R.array.countries, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button button = v.findViewById(R.id.button);
        button.setEnabled(false);
        EditText name = v.findViewById(R.id.editText);
        EditText age = v.findViewById(R.id.editText2);
        name.addTextChangedListener(new ButtonWatcher(v));
        age.addTextChangedListener(new ButtonWatcher(v));

        Toolbar tb = v.findViewById(R.id.my_toolbar);
        tb.setTitle(R.string.app_name);

        return v;
    }

}
