package com.example.niv.e_kay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.niv.e_kay.LoginFragment.*;

public class MainActivity extends AppCompatActivity implements onSighUpListener, OnEditListener {

    private String username = "";

    private boolean isSmallScreen(){
        return findViewById(R.id.fragment_container) != null;
    }

    private boolean isLargeScreen(){
        return !isSmallScreen();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (isSmallScreen()) {
            if (savedInstanceState != null) {
                return;
            }
            LoginFragment f = new LoginFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, f).commit();
        }
    }

    @Override
    public void onEdit(String username, int age){
        this.username = username;
        if (isLargeScreen()){
            OrderFragment f = (OrderFragment) getSupportFragmentManager().findFragmentById(R.id.order_fragment);
            f.setUsername(username);
        }
    }

    @Override
    public void signUp(View v) {
        if (isSmallScreen()){
            OrderFragment f= new OrderFragment();
            Bundle args = new Bundle();
            args.putString(OrderFragment.USER_NAME,username);
            f.setArguments(args);
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, f).commit();
        }
    }
}
