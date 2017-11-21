package com.example.niv.e_kay;


import android.content.Context;
import android.os.Bundle;
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


public class LoginFragment extends Fragment {

    private onSighUpListener signUpCallBack;
    private OnEditListener editCallback;

    public interface onSighUpListener {
        public void signUp(View v);
    }

    public interface OnEditListener {
        public void onEdit(String username, int age);
    }

    private class ButtonWatcher implements TextWatcher {

        private final View v;

        public ButtonWatcher(View v) {
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
            String name = ((EditText) v.findViewById(R.id.editText)).getText().toString();
            String ageString = ((EditText) v.findViewById(R.id.editText2)).getText().toString();
            int age = ageString.isEmpty() ? 0 : Integer.parseInt(ageString);

            editCallback.onEdit(name,age);

            if (ageString.isEmpty() || name.isEmpty()) {
                button.setEnabled(false);
                return;
            }


            if (age < 16 || age > 100) {
                button.setEnabled(false);
                return;
            }

            if (!name.matches("[a-zA-Z ]+")) {
                button.setEnabled(false);
                return;
            }

            button.setEnabled(true);
        }
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

    public void signUp(View v) {
        signUpCallBack.signUp(v);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            signUpCallBack = (onSighUpListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement onSighUpListener");
        }
        try {
            editCallback = (OnEditListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnEditListener");
        }
    }
}
