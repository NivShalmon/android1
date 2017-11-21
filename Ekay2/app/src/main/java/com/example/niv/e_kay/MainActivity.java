    package com.example.niv.e_kay;

    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;

    public class MainActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            if (findViewById(R.id.fragment_container) != null) {
                if (savedInstanceState != null) {
                    return;
                }
                LoginFragment f = new LoginFragment();

                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, f).commit();
            }
        }
    }
